/**
  * Created by antonelpazargic on 19/08/16.
  */
import slick.driver.MySQLDriver.api._
import slick.jdbc.meta.MTable
import slick.lifted.ProvenShape
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

class Customer(tag: Tag) extends Table[(Int, String)](tag, "customer") {

  def id: Rep[Int] = column[Int] ("id", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("name")

  def * : ProvenShape[(Int, String)] = (id, name)
}

object SlickTest extends App {

  val db = Database.forURL("jdbc:mysql://localhost:3306/scala?serverTimezone=UTC&useSSL=false", "root", "antonel1")

  println(db)

  val customers = TableQuery[Customer]

  val tables = Await.result(db.run(MTable.getTables("*")), 1 seconds).toList
  tables.foreach {
    println(_)
  }

  val name: String = s"Antonel Pazargic ${System.currentTimeMillis()}"

  val cust1 = (1, name)
  println(cust1)

  val result: Future[String] = db.run(customers += cust1)
    .map(res => "Success")
    .recover{
      case ex: Exception => s"Exception: ${ex.getCause.getMessage}"
    }

  result onSuccess {
    case msg => println(s"All is ok. $msg")
  }

  result onFailure {
    case msg => println(s"Exception!!!! The message: $msg")
  }

  val results = db.run(customers.result).value
  assert(results.isEmpty)

}

