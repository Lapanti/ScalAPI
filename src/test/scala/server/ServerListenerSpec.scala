package server

import org.specs2.mutable.Specification
import scalapi.server.ServerListener
import server.helpers.TestUser

/**
  * Created by Lapanti on 14.3.2016.
  */
class ServerListenerSpec extends Specification {
  "ServerListener" should {
    ServerListener.endpoints.contains(classOf[TestUser]) must beTrue
  }
}
