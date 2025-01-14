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

package generators

import org.scalacheck.Arbitrary
import pages._

trait PageGenerators {

  implicit lazy val arbitraryWhatIsYourPhoneNumberPage: Arbitrary[WhatIsYourPhoneNumberPage.type] =
    Arbitrary(WhatIsYourPhoneNumberPage)

  implicit lazy val arbitraryCauseOfSicknessPage: Arbitrary[CauseOfSicknessPage.type] =
    Arbitrary(CauseOfSicknessPage)

  implicit lazy val arbitraryWhenDidYourSicknessEndPage: Arbitrary[WhenDidYourSicknessEndPage.type] =
    Arbitrary(WhenDidYourSicknessEndPage)

  implicit lazy val arbitraryWhenDidYouLastWorkPage: Arbitrary[WhenDidYouLastWorkPage.type] =
    Arbitrary(WhenDidYouLastWorkPage)

  implicit lazy val arbitraryHasYourSicknessEndedPage: Arbitrary[HasYourSicknessEndedPage.type] =
    Arbitrary(HasYourSicknessEndedPage)

  implicit lazy val arbitraryWhenDidYourSicknessBeginPage: Arbitrary[WhenDidYourSicknessBeginPage.type] =
    Arbitrary(WhenDidYourSicknessBeginPage)

  implicit lazy val arbitraryEnterSicknessDetailsPage: Arbitrary[EnterSicknessDetailsPage.type] =
    Arbitrary(EnterSicknessDetailsPage)

  implicit lazy val arbitraryWIYClockOrPayrollNumberPage: Arbitrary[WIYClockOrPayrollNumberPage.type] =
    Arbitrary(WIYClockOrPayrollNumberPage)

  implicit lazy val arbitraryDYKYClockOrPayrollNumberPage: Arbitrary[DYKYClockOrPayrollNumberPage.type] =
    Arbitrary(DYKYClockOrPayrollNumberPage)

  implicit lazy val arbitraryWhatIsYourDOBPage: Arbitrary[WhatIsYourDOBPage.type] =
    Arbitrary(WhatIsYourDOBPage)

  implicit lazy val arbitraryWhatIsYourNationalInsuranceNumberPage: Arbitrary[WhatIsYourNationalInsuranceNumberPage.type] =
    Arbitrary(WhatIsYourNationalInsuranceNumberPage)

  implicit lazy val arbitraryWhatIsYourNamePage: Arbitrary[WhatIsYourNamePage.type] =
    Arbitrary(WhatIsYourNamePage)
}
