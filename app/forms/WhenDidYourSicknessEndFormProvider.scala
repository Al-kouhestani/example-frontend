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

import java.time.LocalDate
import forms.mappings.Mappings

import javax.inject.Inject
import play.api.data.Form

class WhenDidYourSicknessEndFormProvider @Inject() extends Mappings {

  def apply(startDate: LocalDate): Form[LocalDate] = {

    val max = LocalDate.now().minusDays(1L)
    val min = LocalDate.now().minusWeeks(28L)

    Form(
      "value" -> localDate(
        invalidKey = "whenDidYourSicknessEnd.error.invalid",
        allRequiredKey = "whenDidYourSicknessEnd.error.required.all",
        twoRequiredKey = "whenDidYourSicknessEnd.error.required.two",
        requiredKey = "whenDidYourSicknessEnd.error.required"
      )

        .verifying(firstError(minDate(min, "hasYourSicknessEnded.error.required.min", min.format(dateTimeFormat)),
          minDate(startDate , "hasYourSicknessEnded.error.required.start", startDate.format(dateTimeFormat))))
        .verifying(maxDate(max, "hasYourSicknessEnded.error.required.max", max.format(dateTimeFormat)))
    )

  }

}
