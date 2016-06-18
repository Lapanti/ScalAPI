package scalapi.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directive0
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.reflections.Reflections
import scala.collection.JavaConverters._
import scala.concurrent.Future
import scala.io.StdIn
import scalapi.server.response.HttpResponse

/**
 * Created by Lapanti on 07.03.16.
 */
object ServerListener {
  implicit val system = ActorSystem("scalapi-actors")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  def endpoints: List[(String, Directive0, ToResponseMarshallable)] = {
    val refl = new Reflections()
    refl.getSubTypesOf(classOf[Endpoint]).asScala.toList.flatMap{ aClass =>
      aClass.getDeclaredMethods.toList.filter(_.getReturnType == classOf[HttpResponse]).map{ method =>
        val returnType = method.getReturnType.asInstanceOf[HttpResponse]
        (s"${aClass.getName}/${method.getName}", returnType.directive, method.asInstanceOf[ToResponseMarshallable])
      }
    }
  }

  val route = endpoints.map{ case (route, httpMethod, function) =>
    path(route) {
      httpMethod {
        complete {
          function
        }
      }
    }
  }

  def main(args: Array[String]){

    val port = args match {
      case Array(x, _*) => x.toInt
      case _ => 8080
    }

    val bindingFuture = route.map{ singleRoute =>
      Http().bindAndHandle(singleRoute, "localhost", port)
    }

    println(s"Server online at port $port, press RETURN to stop")
    StdIn.readLine()
    Future.sequence(bindingFuture.map(_.flatMap(_.unbind()))).onComplete(_ => system.terminate())
  }
}
