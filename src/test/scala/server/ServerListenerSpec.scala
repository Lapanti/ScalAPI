package server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{StatusCodes, HttpRequest}
import akka.http.scaladsl.server.Directive0
import akka.http.scaladsl.server.Directives.get
import akka.stream.ActorMaterializer
import org.specs2.mutable.Specification
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scalapi.server.ServerListener
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lapanti on 14.3.2016.
  */
class ServerListenerSpec extends Specification {
  val testRoute = "testuser/testresponse"

  /*"Endpointlist" should {
    ServerListener.endpoints.contains{ case (name: String, route: Directive0, resp) =>
      name.contains(testRoute) && route == get
    }
  }*/

  "Response" should {
    val serverThread = new Thread(new Runnable {
      def run() {
        ServerListener.main(Array())
      }
    })
    serverThread.start()
    Thread.sleep(10000)
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    val responseFuture = Http().singleRequest(HttpRequest(uri = s"http://localhost/$testRoute"))
    responseFuture.onComplete(_ => serverThread.interrupt())
    Await.result(responseFuture.map(_.status mustEqual StatusCodes.OK), 200.seconds)
  }
}
