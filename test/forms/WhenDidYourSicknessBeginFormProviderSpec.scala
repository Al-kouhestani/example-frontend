/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package forms

import config.Formats.dateTimeFormat

import java.time.{LocalDate, ZoneOffset}
import forms.behaviours.DateBehaviours
import play.api.data.FormError

class WhenDidYourSicknessBeginFormProviderSpec extends DateBehaviours {

  val form = new WhenDidYourSicknessBeginFormProvider()()

  val maxDate = LocalDate.now().minusDays(1L)
  val minDate = LocalDate.now().minusWeeks(28L)



  ".value" - {

    val validData = datesBetween(
      min = minDate,
      max = maxDate
    )

    behave like dateField(form, "value", validData)

    behave like mandatoryDateField(form, "value", "whenDidYourSicknessBegin.error.required.all")

    behave like dateFieldWithMax(form, "value", maxDate, FormError("value", "whenDidYourSicknessBegin.error.required.max", Seq(maxDate.format(dateTimeFormat))))
    behave like dateFieldWithMin(form, "value", minDate, FormError("value", "whenDidYourSicknessBegin.error.required.min", Seq(minDate.format(dateTimeFormat))))
  }
}
