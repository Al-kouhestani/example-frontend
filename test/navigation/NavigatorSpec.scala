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

package navigation

import base.SpecBase
import controllers.routes
import models._
import pages._

class NavigatorSpec extends SpecBase {

  val navigator = new Navigator

  "Navigator" - {


    "in Check mode" - {

      "must go from a page that doesn't exist in the edit route map to CheckYourAnswers" in {

        case object UnknownPage extends Page
        navigator.nextPage(UnknownPage, CheckMode, UserAnswers("id")) mustBe routes.CheckYourAnswersController.onPageLoad
      }
    }

    "in Normal mode" - {

      "must go from a page that doesn't exist in the route map to Index" in {

        case object UnknownPage extends Page
        navigator.nextPage(UnknownPage, NormalMode, UserAnswers("id")) mustBe routes.IndexController.onPageLoad
      }


      "must go from what is your name page to what is your national insurance page" in {
        navigator.nextPage(WhatIsYourNamePage, NormalMode, UserAnswers("a")) mustBe routes.WhatIsYourNationalInsuranceNumberController.onPageLoad(NormalMode)
      }

      "must go from what is your nino page to what is your DOB page" in {
        navigator.nextPage(WhatIsYourNationalInsuranceNumberPage, NormalMode, UserAnswers("b")) mustBe routes.WhatIsYourDOBController.onPageLoad(NormalMode)
      }

      "must go from what is your DOB page to DYKYourClockOrPayroll page" in {
        navigator.nextPage(WhatIsYourDOBPage, NormalMode, UserAnswers("b")) mustBe routes.DYKYClockOrPayrollNumberController.onPageLoad(NormalMode)
      }
      "must go from what is DYKYourClockOrPayroll page to What is your payroll number page if the user choose yes" in {
        val someUA = UserAnswers("").set(DYKYClockOrPayrollNumberPage, true).success.value
        navigator.nextPage(DYKYClockOrPayrollNumberPage, NormalMode, someUA) mustBe routes.WIYClockOrPayrollNumberController.onPageLoad(NormalMode)
      }
      "must go from what is DYKYourClockOrPayroll page to enter your sickness details page if the user choose no" in {
        val someUA = UserAnswers("").set(DYKYClockOrPayrollNumberPage, false).success.value
        navigator.nextPage(DYKYClockOrPayrollNumberPage, NormalMode, someUA) mustBe routes.EnterSicknessDetailsController.onPageLoad(NormalMode)
      }
      "must go from what is what is your payroll number page to enter your sickness details" in {
        navigator.nextPage(WIYClockOrPayrollNumberPage, NormalMode, UserAnswers("c")) mustBe routes.EnterSicknessDetailsController.onPageLoad(NormalMode)
      }
      "must go from what is DYKYourClockOrPayroll page to the journey recovery if there are no user answers captured" in {
        val someUA = UserAnswers("")
        navigator.nextPage(DYKYClockOrPayrollNumberPage, NormalMode, someUA) mustBe routes.JourneyRecoveryController.onPageLoad()
      }
    }
  }
}
