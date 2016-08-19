/**
  * Created by antonelpazargic on 19/08/16.
  */
object ScalaSchool extends App {

  val opt: Option[Int] = None
  val opt2: Option[Int] = Some(100)

  println(opt getOrElse -1)
  println(opt2 getOrElse -1)
  println(opt2 get)
  println(opt2 orElse None)
}
