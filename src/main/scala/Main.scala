/**
 * Created by llav on 07/03/16.
 */
object Main {

  def main(args: Array[String]): Unit = {
    val port = args.headOption.getOrElse("80")
    println(s"Listening to $port")
  }
}
