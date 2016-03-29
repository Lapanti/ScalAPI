package scalapi.server

/**
  * Created by Lapanti on 15.3.2016.
  */
trait Endpoint {

  def objectPath = {
    getClass.getSimpleName.toLowerCase
  }

}
