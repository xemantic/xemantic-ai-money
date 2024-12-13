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

import com.xemantic.kotlin.test.assert
import kotlin.test.Test
import kotlin.test.assertFailsWith

class MoneyTest {

  @Test
  fun `Should create Money instance from String`() {
    assert(Money("42.50").toString() == "42.5")
  }

  @Test
  fun `Should not create Money instance from invalid String`() {
    assertFailsWith<NumberFormatException> {
      Money.Ratio("foo")
    }
  }

  @Test
  fun `Should create Money instance with long fractional part`() {
    assert(Money("42.123456789").toString() == "42.123456789")
  }

  @Test
  fun `Should compare Money instances`() {
    assert(Money.ZERO == Money("0"))
    assert(Money.ONE == Money("1"))
    assert(Money.ONE == Money("1.0"))
    assert(Money("0.1") == Money("0.100"))
  }

  @Test
  fun `Should add Moneys`() {
    assert(Money("10.25") + Money("5.75") == Money("16.00"))
  }

  @Test
  fun `Should subtract Moneys`() {
    assert(Money("20.00") - Money("7.50") == Money("12.50"))
  }

  @Test
  fun `Should multiply Moneys`() {
    assert(Money("5.01") * Money("3.00") == Money("15.03"))
  }

  @Test
  fun `Should multiply Money by Int Ratio scalar`() {
    assert(Money("5.01") * Money.Ratio("3") == Money("15.03"))
  }

  @Test
  fun `Should multiply Money by proportion Ratio`() {
    assert(Money("2.0") * Money.Ratio("1.25") == Money("2.5"))
  }

  @Test
  fun `Should create Money instance for token price`() {
    assert(Money("3.0") * Money.Ratio("0.000001") == Money("0.000003"))
  }

  @Test
  fun `Should compare different Money amounts`() {
    // given
    val money1 = Money("10.00")
    val money2 = Money("10.00")
    val money3 = Money("20.00")

    // then
    assert(money1 == money2)
    assert(money1 < money3)
    assert(money3 > money1)
    assert(money1 < money3)
    assert(money3 > money2)
  }

  @Test
  fun `Should multiply Int and Money`() {
    assert(2 * Money("2.25") == Money("4.5"))
  }

  @Test
  fun `Should multiply Money and Int`() {
    assert(Money("2.25") * 2 == Money("4.5"))
  }

}
