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

import com.xemantic.ai.money.serialization.MoneyRatioSerializer
import com.xemantic.ai.money.serialization.MoneySerializer
import com.xemantic.ai.tool.schema.meta.Description
import com.xemantic.ai.tool.schema.meta.Pattern
import kotlinx.serialization.Serializable

/**
 * Represents a monetary amount, including small fractional amounts,
 * so it can be used for expressing amounts like per token usage cost of LLM APIs,
 * and real-time summarization of these costs.
 *
 * Note: This interface is not intended as a full implementation of
 * arbitrary precision arithmetics related to monetary amounts.
 *
 * The [Money] is representing a multiplatform convenience wrapper around decimal
 * arithmetics of each platform, to avoid floating point arithmetics
 * in calculating total costs.
 *
 * The [Pattern] annotation, when serialized as a part of the JSON schema representing
 * LLM tool use (function calling) input, will give the LLM a hint how to encode
 * the financial value as a decimal number, without any string formatting.
 */
@Description("Represents a monetary amount with arbitrary precision and no currency information")
@Pattern($$"""^-?\d*\.?\d+$""")
@Serializable(MoneySerializer::class)
public interface Money {

    public operator fun plus(money: Money): Money

    public operator fun minus(money: Money): Money

    public operator fun times(money: Money): Money

    public operator fun times(ratio: Ratio): Money

    public operator fun times(
        scalar: Int
    ): Money = this * scalar.toMoneyRatio()

    public operator fun compareTo(money: Money): Int

    /**
     * A ratio to multiply Money. It can be used for representing
     * small ratios, like price per LLM token.
     */
    @Description("Represents a ratio used to multiply Money")
    @Pattern($$"""^-?\d*\.?\d+$""")
    @Serializable(MoneyRatioSerializer::class)
    public interface Ratio {

        public operator fun times(money: Money): Money

        public companion object

    }

}

public expect fun Money(amount: String): Money

public expect val Money.Companion.ZERO: Money

public expect val Money.Companion.ONE: Money

public expect fun Money.Companion.Ratio(value: String): Money.Ratio

public expect val Money.Ratio.Companion.ONE: Money.Ratio

public val String.moneyRatio get() = Money.Ratio(this)

public expect fun Int.toMoneyRatio(): Money.Ratio

public operator fun Int.times(money: Money): Money = toMoneyRatio().times(money)
