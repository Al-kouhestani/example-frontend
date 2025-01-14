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

import models._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import pages._
import play.api.libs.json.{JsValue, Json}

trait UserAnswersEntryGenerators extends PageGenerators with ModelGenerators {

  implicit lazy val arbitraryWhatIsYourPhoneNumberUserAnswersEntry: Arbitrary[(WhatIsYourPhoneNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsYourPhoneNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryCauseOfSicknessUserAnswersEntry: Arbitrary[(CauseOfSicknessPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[CauseOfSicknessPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhenDidYourSicknessEndUserAnswersEntry: Arbitrary[(WhenDidYourSicknessEndPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhenDidYourSicknessEndPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhenDidYouLastWorkUserAnswersEntry: Arbitrary[(WhenDidYouLastWorkPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhenDidYouLastWorkPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryHasYourSicknessEndedUserAnswersEntry: Arbitrary[(HasYourSicknessEndedPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[HasYourSicknessEndedPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhenDidYourSicknessBeginUserAnswersEntry: Arbitrary[(WhenDidYourSicknessBeginPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhenDidYourSicknessBeginPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryEnterSicknessDetailsUserAnswersEntry: Arbitrary[(EnterSicknessDetailsPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[EnterSicknessDetailsPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWIYClockOrPayrollNumberUserAnswersEntry: Arbitrary[(WIYClockOrPayrollNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WIYClockOrPayrollNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDYKYClockOrPayrollNumberUserAnswersEntry: Arbitrary[(DYKYClockOrPayrollNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DYKYClockOrPayrollNumberPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsYourDOBUserAnswersEntry: Arbitrary[(WhatIsYourDOBPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsYourDOBPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsYourNationalInsuranceNumberUserAnswersEntry: Arbitrary[(WhatIsYourNationalInsuranceNumberPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsYourNationalInsuranceNumberPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsYourNameUserAnswersEntry: Arbitrary[(WhatIsYourNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsYourNamePage.type]
        value <- arbitrary[WhatIsYourName].map(Json.toJson(_))
      } yield (page, value)
    }
}
