import scala.concurrent.Future

import org.scalatest.{FreeSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures

class MapToTest extends FreeSpec with Matchers with ScalaFutures {
  "mapTo incompatible type should fail *inside* future" in {
    val f = Future.successful(10).mapTo[String]
    an[RuntimeException] should be thrownBy f.futureValue
  }
}
