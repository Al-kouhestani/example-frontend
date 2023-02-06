import generators.Generators
import models.UserAnswers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll
import uk.gov.voa.play.form

import java.time.LocalDate

class Did extends Generators {
  val min: LocalDate = LocalDate.now()
  val generator = datesBetween(min.minusYears(10), min.minusDays(1))
  forAll(generator -> "invalid dates") {
    date =>

      val data = Map(
        s"$key.day" -> date.getDayOfMonth.toString,
        s"$key.month" -> date.getMonthValue.toString,
        s"$key.year" -> date.getYear.toString
      )

      val result = form.bind(data)
      val ua = UserAnswers("").set()

}