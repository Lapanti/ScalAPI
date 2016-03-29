package server

import org.specs2.mutable.Specification
import server.helpers.TestUser
import scalapi.server.ServerListener

/**
  * Created by Lapanti on 14.3.2016.
  */
class ServerListenerSpec extends Specification {
  "ServerListener" should {
    ServerListener.endpoints must contain(classOf[TestUser])
  }
}
