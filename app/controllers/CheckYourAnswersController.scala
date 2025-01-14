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

package controllers

import com.google.inject.Inject
import controllers.actions.{DataRequiredAction, DataRetrievalAction, IdentifierAction}
import pages.DYKYClockOrPayrollNumberPage
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendBaseController
import viewmodels.checkAnswers.{CauseOfSicknessSummary, DYKYClockOrPayrollNumberSummary, EnterSicknessDetailsSummary, HasYourSicknessEndedSummary, WIYClockOrPayrollNumberSummary, WhatIsYourDOBSummary, WhatIsYourNameSummary, WhatIsYourNationalInsuranceNumberSummary, WhatIsYourPhoneNumberSummary, WhenDidYouLastWorkSummary, WhenDidYourSicknessBeginSummary, WhenDidYourSicknessEndSummary}
import viewmodels.govuk.summarylist._
import views.html.CheckYourAnswersView

class CheckYourAnswersController @Inject()(
                                            override val messagesApi: MessagesApi,
                                            identify: IdentifierAction,
                                            getData: DataRetrievalAction,
                                            requireData: DataRequiredAction,
                                            val controllerComponents: MessagesControllerComponents,
                                            view: CheckYourAnswersView
                                          ) extends FrontendBaseController with I18nSupport {

  def onPageLoad(): Action[AnyContent] = (identify andThen getData andThen requireData) {
    implicit request =>

      val list = SummaryListViewModel(
        rows = Seq(WhatIsYourNameSummary.row(request.userAnswers),
          WhatIsYourNationalInsuranceNumberSummary.row(request.userAnswers),
          WhatIsYourDOBSummary.row(request.userAnswers),
          DYKYClockOrPayrollNumberSummary.row(request.userAnswers),
          WIYClockOrPayrollNumberSummary.row(request.userAnswers),
          EnterSicknessDetailsSummary.row(request.userAnswers),
          WhenDidYourSicknessBeginSummary.row(request.userAnswers),
          HasYourSicknessEndedSummary.row(request.userAnswers),
          WhenDidYourSicknessEndSummary.row(request.userAnswers),
          WhenDidYouLastWorkSummary.row(request.userAnswers),
          CauseOfSicknessSummary.row(request.userAnswers),
          WhatIsYourPhoneNumberSummary.row(request.userAnswers)
        ).flatten
      )

      Ok(view(list))
  }
}
