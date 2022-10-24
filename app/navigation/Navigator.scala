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

import javax.inject.{Inject, Singleton}

import play.api.mvc.Call
import controllers.routes
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case WhatIsYourNamePage => _ => routes.WhatIsYourNationalInsuranceNumberController.onPageLoad(NormalMode)
    case WhatIsYourNationalInsuranceNumberPage => _ => routes.WhatIsYourDOBController.onPageLoad(NormalMode)
    case WhatIsYourDOBPage => _ => routes.DYKYClockOrPayrollNumberController.onPageLoad(NormalMode)
    case DYKYClockOrPayrollNumberPage => payrollLogic
    case EnterSicknessDetailsPage => _ => routes.WhenDidYourSicknessBeginController.onPageLoad(NormalMode)
    case WIYClockOrPayrollNumberPage => _ => routes.EnterSicknessDetailsController.onPageLoad(NormalMode)
    case WhenDidYourSicknessBeginPage => _ => routes.HasYourSicknessEndedController.onPageLoad(NormalMode)
    case HasYourSicknessEndedPage => sickdateLogic
    case WhenDidYourSicknessEndPage => _ => routes.WhenDidYouLastWorkController.onPageLoad(NormalMode)
    case WhenDidYouLastWorkPage => _ => routes.CauseOfSicknessController.onPageLoad(NormalMode)
    case CauseOfSicknessPage => _ => routes.WhatIsYourPhoneNumberController.onPageLoad(NormalMode)
    case WhatIsYourPhoneNumberPage => _ => routes.CheckYourAnswersController.onPageLoad
    case _ => _ => routes.IndexController.onPageLoad
  }

  private val checkRouteMap: Page => UserAnswers => Call = {
    case _ => _ => routes.CheckYourAnswersController.onPageLoad
  }

  private def payrollLogic(userAnswers: UserAnswers): Call={
        userAnswers.get(DYKYClockOrPayrollNumberPage).map{
          case true => routes.WIYClockOrPayrollNumberController.onPageLoad(NormalMode)
          case false => routes.EnterSicknessDetailsController.onPageLoad(NormalMode)
        }.getOrElse(routes.JourneyRecoveryController.onPageLoad())
  }

  private def sickdateLogic(userAnswers: UserAnswers): Call = {
    userAnswers.get(HasYourSicknessEndedPage).map {
      case true => routes.WhenDidYourSicknessEndController.onPageLoad(NormalMode)
      case false => routes.WhenDidYouLastWorkController.onPageLoad(NormalMode)
    }.getOrElse(routes.JourneyRecoveryController.onPageLoad())
  }
  def nextPage(page: Page, mode: Mode, userAnswers: UserAnswers): Call = mode match {
    case NormalMode =>
      normalRoutes(page)(userAnswers)
    case CheckMode =>
      checkRouteMap(page)(userAnswers)
  }
}
