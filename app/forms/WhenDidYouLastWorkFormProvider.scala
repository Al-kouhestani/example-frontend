/*
 * Copyright 2022 HM Revenue & Customs
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

class WhenDidYouLastWorkFormProvider @Inject() extends Mappings {

  def apply(): Form[LocalDate] = {

    val max = LocalDate.now().minusDays(1L)
    val min = LocalDate.now().minusWeeks(28L).minusDays(1)

    Form(
      "value" -> localDate(
        invalidKey = "whenDidYouLastWork.error.invalid",
        allRequiredKey = "whenDidYouLastWork.error.required.all",
        twoRequiredKey = "whenDidYouLastWork.error.required.two",
        requiredKey = "whenDidYouLastWork.error.required"
      )

        .verifying(minDate(min, "whenDidYouLastWork.error.required.min", min.format(dateTimeFormat)))
        .verifying(maxDate(max, "whenDidYouLastWork.error.required.max", max.format(dateTimeFormat)))
    )
  }
}