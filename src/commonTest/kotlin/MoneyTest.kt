/*
 * Copyright 2024 Kazimierz Pogoda / Xemantic
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

package com.xemantic.ai.money

import com.xemantic.ai.money.test.shouldBe
import kotlin.test.Test

class MoneyTest {

  @Test
  fun `Should create Money instance from String`() {
    Money("42.50").toString() shouldBe "42.5"
  }

  @Test
  fun `Should create Money instance with long fractional part`() {
    Money("42.123456789").toString() shouldBe "42.123456789"
  }

  @Test
  fun `Should compare Money instances`() {
    Money.ZERO shouldBe Money("0")
    Money.ONE shouldBe Money("1")
    Money.ONE shouldBe Money("1.0")
    Money("0.1") shouldBe Money("0.100")
  }

  @Test
  fun `Should add Moneys`() {
    Money("10.25") + Money("5.75") shouldBe Money("16.00")
  }

  @Test
  fun `Should subtract Moneys`() {
    Money("20.00") - Money("7.50") shouldBe Money("12.50")
  }

  @Test
  fun `Should multiply Moneys`() {
    Money("5.01") * Money("3.00") shouldBe Money("15.03")
  }

  @Test
  fun `Should multiply Money by Int Ratio scalar`() {
    Money("5.01") * Money.Ratio("3") shouldBe Money("15.03")
  }

  @Test
  fun `Should multiply Money by proportion Ratio`() {
    Money("2.0") * Money.Ratio("1.25") shouldBe Money("2.5")
  }

  @Test
  fun `Should create Money instance for token price`() {
    Money("3.0") * Money.Ratio("0.000001") shouldBe Money("0.000003")
  }

  @Test
  fun `Should compare different Money amounts`() {
    // given
    val money1 = Money("10.00")
    val money2 = Money("10.00")
    val money3 = Money("20.00")

    // then
    (money1 == money2) shouldBe true
    (money1 < money3) shouldBe true
    (money3 > money1) shouldBe true
    (money1 == money3) shouldBe false
    (money3 < money1) shouldBe false
  }

}
