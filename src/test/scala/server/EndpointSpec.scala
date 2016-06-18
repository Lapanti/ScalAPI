package server

import org.specs2.mutable.Specification
import server.helpers.{TestUser2, TestUser}


/**
  * Created by Lapanti on 15.3.2016.
  */
class EndpointSpec extends Specification {

  "A \"TestUser\"" should {
    (new TestUser).objectPath mustEqual "testuser"
  }

  "A \"TestUser2\" class's overridden objectPath extending Endpoint" should {
    (new TestUser2).objectPath mustEqual "notuser"
  }

}
