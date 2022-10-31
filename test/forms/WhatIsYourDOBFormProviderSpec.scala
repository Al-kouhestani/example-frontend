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

import forms.behaviours.DateBehaviours
import org.scalatestplus.mockito.MockitoSugar
import play.api.data.FormError
import java.time.LocalDate

class WhatIsYourDOBFormProviderSpec extends DateBehaviours with MockitoSugar{

  val form = new WhatIsYourDOBFormProvider()()

  ".value" - {

    val maximum = LocalDate.now().minusYears(16)
    val minimum = LocalDate.now().minusYears(120)

    val validData = datesBetween(
      min = minimum,
      max = maximum
    )

    behave like dateFieldWithMax(form, "value", maximum, FormError("value", "whatIsYourDOB.error.max"))
    behave like dateFieldWithMin(form, "value", minimum, FormError("value", "whatIsYourDOB.error.min"))

    behave like dateField(form, "value", validData)

    behave like mandatoryDateField(form, "value", "whatIsYourDOB.error.required.all")
  }

  "the person should not be younger than 16 years old " in {
    //val ineligibleDate = LocalDate.now().minusYears(16)
    //val ineligibleUser = UserAnswers("").set(WhatIsYourDOBFormProvider)
    //val dobPage = mock [WhatIsYourDOBPage]

  }

  "the date should be in the right format DD MM YYYY" in {

  }

  "any dates outside the value range should not be accepted" in {

  }
}
