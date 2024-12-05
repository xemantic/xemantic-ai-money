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

import java.math.BigDecimal
import kotlin.minus
import kotlin.plus
import kotlin.times

public class JvmMoney(private val value: BigDecimal) : Money {

  override fun plus(amount: Money): Money = JvmMoney(
    (value + (amount as JvmMoney).value).stripTrailingZeros()
  )

  override fun minus(amount: Money): Money = JvmMoney(
    (value - (amount as JvmMoney).value).stripTrailingZeros()
  )

  override fun times(amount: Money): Money = JvmMoney(
    (value * (amount as JvmMoney).value).stripTrailingZeros()
  )

  override fun times(ratio: Money.Ratio): Money = JvmMoney(
    (value * (ratio as JvmRatio).value).stripTrailingZeros()
  )

  override fun compareTo(
    other: Money
  ): Int = value.compareTo((other as JvmMoney).value)

  override fun toString(): String {
    return value.toPlainString()
  }

  override fun equals(
    other: Any?
  ): Boolean = (other != null)
      && (other is JvmMoney)
      && (value == other.value)

  override fun hashCode(): Int = value.hashCode()

  internal class JvmRatio(internal val value: BigDecimal) : Money.Ratio {

    override fun times(amount: Money): Money = JvmMoney(
      (value * (amount as JvmMoney).value).stripTrailingZeros()
    )

    override fun toString(): String {
      return value.toPlainString()
    }

    override fun equals(
      other: Any?
    ): Boolean = (other != null)
        && (other is JvmRatio)
        && (value == other.value)

    override fun hashCode(): Int = value.hashCode()

  }

}

public actual fun Money(amount: String): Money = JvmMoney(
  BigDecimal(amount).stripTrailingZeros()
)

@Suppress("ObjectPropertyName")
private val _ZERO = JvmMoney(BigDecimal.ZERO)
public actual val Money.Companion.ZERO: Money get() = _ZERO

@Suppress("ObjectPropertyName")
private val _ONE = JvmMoney(BigDecimal.ONE)
public actual val Money.Companion.ONE: Money get() = _ONE

public actual fun Money.Companion.Ratio(
  value: String
): Money.Ratio = JvmMoney.JvmRatio(
  BigDecimal(value).stripTrailingZeros()
)

@Suppress("ObjectPropertyName")
private val _RATIO_ONE = JvmMoney.JvmRatio(BigDecimal.ONE)
public actual val Money.Ratio.Companion.ONE: Money.Ratio get() = _RATIO_ONE
