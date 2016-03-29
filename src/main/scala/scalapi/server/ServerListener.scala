package scalapi.server

import java.util.ServiceLoader

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import scala.reflect.runtime.universe._

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

  def endpoints = {
    typeOf[Endpoint].members.toList.map(_.asClass)
  }

  def main(args: Array[String]){

    val ws = (ServiceLoader load classOf[Endpoint]).asScala
    for (w <- ws) {
      Console println s"Turn a ${w.getClass} by ${w.turn}"
    }

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
