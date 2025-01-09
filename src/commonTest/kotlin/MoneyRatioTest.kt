/*
 * Copyright 2024-2025 Kazimierz Pogoda / Xemantic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
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

class MoneyRatioTest {

    @Test
    fun `Should create Money Ratio instance from String`() {
        assert(Money.Ratio("1.25").toString() == "1.25")
    }

    @Test
    fun `Should not create Money Ratio instance from invalid String`() {
        assertFailsWith<NumberFormatException> {
            Money.Ratio("foo")
        }
    }

    @Test
    fun `Should create Money Ratio instance with long fractional part`() {
        assert(Money.Ratio("0.0000001").toString() == "0.0000001")
    }

    @Test
    fun `Should compare Money Ratio instances`() {
        assert(Money.Ratio.ONE == Money.Ratio("1"))
        assert(Money.Ratio("0") == Money.Ratio("0.0"))
        assert(Money.Ratio("0.1") == Money.Ratio("0.100000"))
    }

    @Test
    fun `Should multiple Money Ratio and Money`() {
        assert(Money.Ratio("0.000001") * Money("3.0") == Money("0.000003"))
    }

    @Test
    fun `Should convert valid string to Money Ratio`() {
        assert("0.000001".moneyRatio == Money.Ratio("0.000001"))
    }

    @Test
    fun `Should not convert invalid string to Money Ratio`() {
        assertFailsWith<NumberFormatException> {
            "foo".moneyRatio
        }
    }

    @Test
    fun `Should convert Int to Money Ratio`() {
        assert(1.toMoneyRatio() == Money.Ratio("1"))
        assert(42.toMoneyRatio() == Money.Ratio("42"))
    }

}
