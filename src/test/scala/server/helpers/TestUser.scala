package server.helpers

import scalapi.server.Endpoint
import scalapi.server.response._

/**
 * Created by Lapanti on 21.03.16.
 */

class TestUser extends Endpoint {

  def testResponse = GET {
    Ok()
  }
}
