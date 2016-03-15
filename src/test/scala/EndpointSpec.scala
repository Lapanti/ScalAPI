import org.specs2.mutable.Specification

import scalapi.server.Endpoint

/**
  * Created by Lauri on 15.3.2016.
  */
class EndpointSpec extends Specification {

  class TestUser extends Endpoint

  class TestUser2 extends Endpoint {
    override val objectPath = "notuser"
  }

  "A \"TestUser\" class's objectPath extending Endpoint" should {
    (new TestUser).objectPath mustEqual "testuser"
  }

  "A \"TestUser2\" class's overridden objectPath extending Endpoint" should {
    (new TestUser2).objectPath mustEqual "notuser"
  }

}
