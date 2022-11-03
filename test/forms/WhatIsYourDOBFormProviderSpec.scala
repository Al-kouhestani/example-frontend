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

import forms.behaviours.DateBehaviours
import org.scalatestplus.mockito.MockitoSugar
import play.api.data.FormError
import java.time.LocalDate

class WhatIsYourDOBFormProviderSpec extends DateBehaviours with MockitoSugar{

  val form = new WhatIsYourDOBFormProvider()()

  ".value" - {

    val maximum = LocalDate.now().minusYears(16)
    val minimum = LocalDate.now().minusYears(120)
    val present = LocalDate.now()

    val validData = datesBetween(
      min = minimum,
      max = maximum
    )

    behave like dateFieldWithMax(form, "value", present, FormError("value", "whatIsYourDOB.error.future", Seq(present.format(dateTimeFormat))))
    behave like dateFieldWithMin(form, "value", minimum, FormError("value", "whatIsYourDOB.error.max", Seq(minimum.format(dateTimeFormat))))

    behave like dateField(form, "value", validData)

    behave like mandatoryDateField(form, "value", "whatIsYourDOB.error.required.all")
  }

}
