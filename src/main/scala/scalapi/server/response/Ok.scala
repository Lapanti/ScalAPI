package scalapi.server.response

/**
 * Created by Lapanti on 01.04.2016.
 */
final class Ok extends ResponseStatus {

  override val value = 200
}

object Ok {
  def apply() = {
    new Ok()
  }
}
