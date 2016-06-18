package scalapi.server.response

import akka.http.scaladsl.server.Directives.get

/**
 * Created by Lapanti on 01.04.2016.
 */
class GET(status: ResponseStatus) extends HttpResponse {

  override protected val response = akka.http.scaladsl.model.HttpResponse(status.value)
  val directive = get
}

object GET {
  def apply(status: ResponseStatus) = {
    new GET(status)
  }
}
