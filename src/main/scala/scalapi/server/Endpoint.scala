package scalapi.server

/**
  * Created by Lauri on 15.3.2016.
  */
trait Endpoint {

  def objectPath = {
    getClass.getSimpleName.toLowerCase
  }

}
