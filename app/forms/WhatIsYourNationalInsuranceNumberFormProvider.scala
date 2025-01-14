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

import javax.inject.Inject
import forms.mappings.Mappings
import play.api.data.Form
import uk.gov.hmrc.domain.Nino

import scala.util.Try



class WhatIsYourNationalInsuranceNumberFormProvider @Inject() extends Mappings {

  def apply(): Form[Nino] =
    Form(
      "value" -> text("whatIsYourNationalInsuranceNumber.error.required")
        .verifying("whatIsYourNationalInsuranceNumber.error.invalid",
          nino=> Try(Nino(nino.replaceAll("\\s", "").toUpperCase)).isSuccess)
        .transform[Nino](nino => Nino(nino.replaceAll("\\s", "").toUpperCase), nino => nino.toString)
    )
}
