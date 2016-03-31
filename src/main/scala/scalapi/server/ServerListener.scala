package scalapi.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.reflections.Reflections
import scala.collection.JavaConverters._
import scala.io.StdIn

/**
 * Created by Lapanti on 07.03.16.
 */
object ServerListener {
  implicit val system = ActorSystem("scalapi-actors")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val route =
    path("hello") {
      get {
        complete {
          "Hello"
        }
      }
    }

  def endpoints: List[Class[_ <: Endpoint]] = {
    val refl = new Reflections()
    refl.getSubTypesOf(classOf[Endpoint]).asScala.toList
  }

  def main(args: Array[String]){

    val port = args match {
      case Array(x, _*) => x.toInt
      case _ => 8080
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", port)

    println(s"Server online at port $port, press RETURN to stop")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
