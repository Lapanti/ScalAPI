package scalapi.server.test

import scalapi.server.Endpoint
import scalapi.server.response.{Ok, GET}

/**
 * Created by Lapanti on 14.07.2016.
 */
class Hello extends Endpoint {
  def world = GET {
    Ok()
  }
}
