/*
 * Copyright 2023 HM Revenue & Customs
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

import java.time.{LocalDate, ZoneOffset}
import base.SpecBase
import forms.WhenDidYouLastWorkFormProvider
import models.{NormalMode, UserAnswers}
import navigation.{FakeNavigator, Navigator}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import pages.{WhenDidYouLastWorkPage, WhenDidYourSicknessBeginPage}
import play.api.inject.bind
import play.api.mvc.{AnyContentAsEmpty, AnyContentAsFormUrlEncoded, Call}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import repositories.SessionRepository
import views.html.WhenDidYouLastWorkView

import scala.concurrent.Future

class WhenDidYouLastWorkControllerSpec extends SpecBase with MockitoSugar {

  val formProvider = new WhenDidYouLastWorkFormProvider()
  private val sickDate: LocalDate = LocalDate.now().minusWeeks(29)

  private def form(sickDate: LocalDate) = formProvider(sickDate)

  def onwardRoute = Call("GET", "/foo")

  val validAnswer = LocalDate.now(ZoneOffset.UTC).minusWeeks(22)

  lazy val whenDidYouLastWorkRoute = routes.WhenDidYouLastWorkController.onPageLoad(NormalMode).url

  override val emptyUserAnswers = UserAnswers(userAnswersId)

  def getRequest(): FakeRequest[AnyContentAsEmpty.type] =
    FakeRequest(GET, whenDidYouLastWorkRoute)

  def postRequest(): FakeRequest[AnyContentAsFormUrlEncoded] =
    FakeRequest(POST, whenDidYouLastWorkRoute)
      .withFormUrlEncodedBody(
        "value.day"   -> validAnswer.getDayOfMonth.toString,
        "value.month" -> validAnswer.getMonthValue.toString,
        "value.year"  -> validAnswer.getYear.toString
      )

  "WhenDidYouLastWork Controller" - {

    "must return OK and the correct view for a GET" in {
      val someUA = UserAnswers("").set(WhenDidYourSicknessBeginPage, validAnswer ).success.value

      val application = applicationBuilder(userAnswers = Some(someUA)).build()

      running(application) {
        val result = route(application, getRequest).value

        val view = application.injector.instanceOf[WhenDidYouLastWorkView]

        status(result) mustEqual OK
        contentAsString(result) mustEqual view(form(sickDate), NormalMode)(getRequest, messages(application)).toString
      }
    }

    "must populate the view correctly on a GET when the question has previously been answered" in {
      val someUA = UserAnswers("").set(WhenDidYourSicknessBeginPage, validAnswer ).success.value

      val userAnswers = someUA
        .set(WhenDidYouLastWorkPage, validAnswer).success.value

      val application = applicationBuilder(userAnswers = Some(userAnswers)).build()

      running(application) {
        val view = application.injector.instanceOf[WhenDidYouLastWorkView]

        val result = route(application, getRequest).value

        status(result) mustEqual OK
        contentAsString(result) mustEqual view(form(sickDate).fill(validAnswer), NormalMode)(getRequest, messages(application)).toString
      }
    }

    "must redirect to the next page when valid data is submitted" in {
      val someUA = UserAnswers("").set(WhenDidYourSicknessBeginPage, validAnswer ).success.value

      val mockSessionRepository = mock[SessionRepository]

      when(mockSessionRepository.set(any())) thenReturn Future.successful(true)

      val application =
        applicationBuilder(userAnswers = Some(someUA))
          .overrides(
            bind[Navigator].toInstance(new FakeNavigator(onwardRoute)),
            bind[SessionRepository].toInstance(mockSessionRepository)
          )
          .build()

      running(application) {
        val result = route(application, postRequest).value

        status(result) mustEqual SEE_OTHER
        redirectLocation(result).value mustEqual onwardRoute.url
      }
    }

    "must return a Bad Request and errors when invalid data is submitted" in {

      val someUA = UserAnswers("").set(WhenDidYourSicknessBeginPage, validAnswer ).success.value

      val application = applicationBuilder(userAnswers = Some(someUA)).build()

      val request =
        FakeRequest(POST, whenDidYouLastWorkRoute)
          .withFormUrlEncodedBody(("value", "invalid value"))

      running(application) {
        val boundForm = form(sickDate).bind(Map("value" -> "invalid value"))

        val view = application.injector.instanceOf[WhenDidYouLastWorkView]

        val result = route(application, request).value

        status(result) mustEqual BAD_REQUEST
        contentAsString(result) mustEqual view(boundForm, NormalMode)(request, messages(application)).toString
      }
    }

    "must redirect to Journey Recovery for a GET if no existing data is found" in {

      val application = applicationBuilder(userAnswers = None).build()

      running(application) {
        val result = route(application, getRequest).value

        status(result) mustEqual SEE_OTHER
        redirectLocation(result).value mustEqual routes.JourneyRecoveryController.onPageLoad().url
      }
    }

    "must redirect to Journey Recovery for a POST if no existing data is found" in {

      val application = applicationBuilder(userAnswers = None).build()

      running(application) {
        val result = route(application, postRequest).value

        status(result) mustEqual SEE_OTHER
        redirectLocation(result).value mustEqual routes.JourneyRecoveryController.onPageLoad().url
      }
    }
  }
}
