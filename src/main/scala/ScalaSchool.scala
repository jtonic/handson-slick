/**
  * Created by antonelpazargic on 19/08/16.
  */
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ScalaSchool {
  def main(args: Array[String]) = {
    futureExample()

    System.exit(0)
    optionExample
  }

  def futureExample() = {

    val op1 = 1
    val op2 = 10

    val computeTheSum: Future[Int] = Future {
      throw new RuntimeException("Throwing an exception")
//      op1 + op2
    }

    computeTheSum onSuccess {
      case msg => println(msg)
    }

    computeTheSum onFailure {
      case exc: Exception => println(exc.getMessage)
    }

    Await.ready(computeTheSum, 1 seconds)

  }

  def optionExample = {
    val opt: Option[Int] = None
    val opt2: Option[Int] = Some(100)

    println(opt getOrElse -1)
    println(opt2 getOrElse -1)
    println(opt2 get)
    println(opt2 orElse None)
  }
}

