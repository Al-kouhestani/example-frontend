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
        navigator.nextPage(UnknownPage, CheckMode, UserAnswers("")) mustBe routes.CheckYourAnswersController.onPageLoad
      }
      "must go from what is your name page to what is your national insurance page" in {
        navigator.nextPage(WhatIsYourNamePage, CheckMode, UserAnswers("a")) mustBe routes.WhatIsYourNationalInsuranceNumberController.onPageLoad(CheckMode)
      }

      "must go from what is your nino page to what is your DOB page" in {
        navigator.nextPage(WhatIsYourNationalInsuranceNumberPage, CheckMode, UserAnswers("b")) mustBe routes.WhatIsYourDOBController.onPageLoad(CheckMode)
      }

      "must go from what is your DOB page to DYKYourClockOrPayroll page" in {
        navigator.nextPage(WhatIsYourDOBPage, CheckMode, UserAnswers("b")) mustBe routes.DYKYClockOrPayrollNumberController.onPageLoad(CheckMode)
      }
      "must go from what is DYKYourClockOrPayroll page to What is your payroll number page if the user choose yes" in {
        val someUA = UserAnswers("").set(DYKYClockOrPayrollNumberPage, true).success.value
        navigator.nextPage(DYKYClockOrPayrollNumberPage, CheckMode, someUA) mustBe routes.WIYClockOrPayrollNumberController.onPageLoad(CheckMode)
      }
      "must go from what is DYKYourClockOrPayroll page to enter your sickness details page if the user choose no" in {
        val someUA = UserAnswers("").set(DYKYClockOrPayrollNumberPage, false).success.value
        navigator.nextPage(DYKYClockOrPayrollNumberPage, CheckMode, someUA) mustBe routes.EnterSicknessDetailsController.onPageLoad(CheckMode)
      }
      "must go from what is what is your payroll number page to enter your sickness details" in {
        navigator.nextPage(WIYClockOrPayrollNumberPage, CheckMode, UserAnswers("c")) mustBe routes.EnterSicknessDetailsController.onPageLoad(CheckMode)
      }
      "must go from what is DYKYourClockOrPayroll page to the journey recovery if there are no user answers captured" in {
        val someUA = UserAnswers("")
        navigator.nextPage(DYKYClockOrPayrollNumberPage, CheckMode, someUA) mustBe routes.JourneyRecoveryController.onPageLoad()
      }
      "must go from enter details of your sickness page to when did your sickness begin" in {
        val someUA = UserAnswers("")
        navigator.nextPage(EnterSicknessDetailsPage, CheckMode, someUA) mustBe routes.WhenDidYourSicknessBeginController.onPageLoad(CheckMode)
      }
      "must go from when did your sickness begin page to has your sickness ended" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhenDidYourSicknessBeginPage, CheckMode, someUA) mustBe routes.HasYourSicknessEndedController.onPageLoad(CheckMode)
      }

      "must go from has your sickness ended page to when did you last work if no" in {
        val someUA = UserAnswers("").set(HasYourSicknessEndedPage, false).success.value
        navigator.nextPage(HasYourSicknessEndedPage, CheckMode, someUA) mustBe routes.WhenDidYouLastWorkController.onPageLoad(CheckMode)
      }

      "must go from has your sickness ended page to when did your sickness end if yes" in {
        val someUA = UserAnswers("").set(HasYourSicknessEndedPage, true).success.value
        navigator.nextPage(HasYourSicknessEndedPage, CheckMode, someUA) mustBe routes.WhenDidYourSicknessEndController.onPageLoad(CheckMode)
      }

      "must go from what is has your sickness ended page to the journey recovery if there are no user answers captured" in {
        val someUA = UserAnswers("")
        navigator.nextPage(HasYourSicknessEndedPage, CheckMode, someUA) mustBe routes.JourneyRecoveryController.onPageLoad()
      }

      "must go from when did you last work page to cause of sickness page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhenDidYouLastWorkPage, CheckMode, someUA) mustBe routes.CauseOfSicknessController.onPageLoad(CheckMode)
      }


      "must go from cause of sickness page to what is your phone number page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(CauseOfSicknessPage, CheckMode, someUA) mustBe routes.WhatIsYourPhoneNumberController.onPageLoad(CheckMode)
      }

      "must go from what's your phone number to check your answers page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhatIsYourPhoneNumberPage, CheckMode, someUA) mustBe routes.CheckYourAnswersController.onPageLoad
      }
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
      "must go from enter details of your sickness page to when did your sickness begin" in {
        val someUA = UserAnswers("")
        navigator.nextPage(EnterSicknessDetailsPage, NormalMode, someUA) mustBe routes.WhenDidYourSicknessBeginController.onPageLoad(NormalMode)
      }
      "must go from when did your sickness begin page to has your sickness ended" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhenDidYourSicknessBeginPage, NormalMode, someUA) mustBe routes.HasYourSicknessEndedController.onPageLoad(NormalMode)
      }

      "must go from has your sickness ended page to when did you last work if no" in {
        val someUA = UserAnswers("").set(HasYourSicknessEndedPage, false).success.value
        navigator.nextPage(HasYourSicknessEndedPage, NormalMode, someUA) mustBe routes.WhenDidYouLastWorkController.onPageLoad(NormalMode)
      }

      "must go from has your sickness ended page to when did your sickness end if yes" in {
        val someUA = UserAnswers("").set(HasYourSicknessEndedPage, true).success.value
        navigator.nextPage(HasYourSicknessEndedPage, NormalMode, someUA) mustBe routes.WhenDidYourSicknessEndController.onPageLoad(NormalMode)
      }

      "must go from what is has your sickness ended page to the journey recovery if there are no user answers captured" in {
        val someUA = UserAnswers("")
        navigator.nextPage(HasYourSicknessEndedPage, NormalMode, someUA) mustBe routes.JourneyRecoveryController.onPageLoad()
      }

      "must go from when did you last work page to cause of sickness page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhenDidYouLastWorkPage, NormalMode, someUA) mustBe routes.CauseOfSicknessController.onPageLoad(NormalMode)
      }


      "must go from cause of sickness page to what is your phone number page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(CauseOfSicknessPage, NormalMode, someUA) mustBe routes.WhatIsYourPhoneNumberController.onPageLoad(NormalMode)
      }

      "must go from what's your phone number to check your answers page" in {
        val someUA = UserAnswers("")
        navigator.nextPage(WhatIsYourPhoneNumberPage, NormalMode, someUA) mustBe routes.CheckYourAnswersController.onPageLoad
      }
    }
}
