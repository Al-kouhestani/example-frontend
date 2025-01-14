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

class WhatIsYourDOBFormProvider @Inject()() extends Mappings {


  def apply(): Form[LocalDate] = {
    val max = LocalDate.now().minusYears(16)
    val min = LocalDate.now().minusYears(120)
    val present = LocalDate.now()
    implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)



    Form(
      "value" -> localDate(
        invalidKey     = "whatIsYourDOB.error.invalid",
        allRequiredKey = "whatIsYourDOB.error.required.all",
        twoRequiredKey = "whatIsYourDOB.error.required.two",
        requiredKey    = "whatIsYourDOB.error.required"
      )

        .verifying(firstError( maxDate(present, "whatIsYourDOB.error.future", present.format(dateTimeFormat)),
          maxDate(max, "whatIsYourDOB.error.min",max.format(dateTimeFormat))
    ))
        .verifying(minDate(min,"whatIsYourDOB.error.max", min.format(dateTimeFormat)))



    )


  }
}
