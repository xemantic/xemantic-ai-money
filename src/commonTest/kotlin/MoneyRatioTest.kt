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

class MoneyRatioTest {

  @Test
  fun `Should create Money Ratio instance from String`() {
    Money.Ratio("1.25").toString() shouldBe "1.25"
  }

  @Test
  fun `Should create Money Ratio instance with long fractional part`() {
    Money.Ratio("0.0000001").toString() shouldBe "0.0000001"
  }

  @Test
  fun `Should compare Money Ratio instances`() {
    Money.Ratio.ONE shouldBe Money.Ratio("1")
    Money.Ratio("0") shouldBe Money.Ratio("0.0")
    Money.Ratio("0.1") shouldBe Money.Ratio("0.100000")
  }

  @Test
  fun `Should multiple Money Ratio and Money`() {
    Money.Ratio("0.000001") * Money("3.0") shouldBe Money("0.000003")
  }

}
