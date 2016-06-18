package scalapi.server.response

import akka.http.scaladsl.server.Directive0

/**
 * Created by Lapanti on 31.03.2016.
 */
abstract class HttpResponse {

  protected val response: akka.http.scaladsl.model.HttpResponse

  def getResponse = {
    response
  }
  def directive: Directive0
}
