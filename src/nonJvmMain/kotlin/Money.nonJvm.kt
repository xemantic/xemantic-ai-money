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

import com.ionspin.kotlin.bignum.decimal.BigDecimal

public class NonJvmMoney(private val value: BigDecimal) : Money {

    override fun plus(amount: Money): Money = NonJvmMoney(
        value + (amount as NonJvmMoney).value
    )

    override fun minus(amount: Money): Money = NonJvmMoney(
        value - (amount as NonJvmMoney).value
    )

    override fun times(amount: Money): Money = NonJvmMoney(
        value * (amount as NonJvmMoney).value
    )

    override fun times(ratio: Money.Ratio): Money = NonJvmMoney(
        value * (ratio as NonJvmRatio).value
    )

    override fun compareTo(
        other: Money
    ): Int = value.compareTo((other as NonJvmMoney).value)

    override fun toString(): String {
        return value.toPlainString()
    }

    override fun equals(
        other: Any?
    ): Boolean = (other != null)
            && (other is NonJvmMoney)
            && (value == other.value)

    override fun hashCode(): Int = value.hashCode()

    internal class NonJvmRatio(internal val value: BigDecimal) : Money.Ratio {

        override fun times(amount: Money): Money = NonJvmMoney(
            (value * (amount as NonJvmMoney).value)
        )

        override fun toString(): String {
            return value.toPlainString()
        }

        override fun equals(
            other: Any?
        ): Boolean = (other != null)
                && (other is NonJvmRatio)
                && (value == other.value)

        override fun hashCode(): Int = value.hashCode()

    }

}

public actual fun Money(amount: String): Money = NonJvmMoney(
    BigDecimal.parseString(amount)
)

@Suppress("ObjectPropertyName")
private val _ZERO = NonJvmMoney(BigDecimal.ZERO)
public actual val Money.Companion.ZERO: Money get() = _ZERO

@Suppress("ObjectPropertyName")
private val _ONE = NonJvmMoney(BigDecimal.ONE)
public actual val Money.Companion.ONE: Money get() = _ONE

public actual fun Money.Companion.Ratio(
    value: String
): Money.Ratio = NonJvmMoney.NonJvmRatio(
    BigDecimal.parseString(value)
)

@Suppress("ObjectPropertyName")
private val _RATIO_ONE = NonJvmMoney.NonJvmRatio(BigDecimal.ONE)
public actual val Money.Ratio.Companion.ONE: Money.Ratio get() = _RATIO_ONE

public actual fun Int.toMoneyRatio(): Money.Ratio =
    NonJvmMoney.NonJvmRatio(BigDecimal.fromInt(this))
