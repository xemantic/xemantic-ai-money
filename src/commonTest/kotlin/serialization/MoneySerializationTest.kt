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

package com.xemantic.ai.money.serialization

import com.xemantic.ai.money.Money
import com.xemantic.ai.money.test.testJson
import com.xemantic.ai.tool.schema.meta.Description
import com.xemantic.ai.tool.schema.meta.Pattern
import com.xemantic.kotlin.test.assert
import com.xemantic.kotlin.test.be
import com.xemantic.kotlin.test.have
import com.xemantic.kotlin.test.should
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test

class MoneySerializationTest {

    @Test
    fun `Should serialize Money to JSON`() {
        assert(
            testJson.encodeToString(
                Money("42.50")
            ) == """"42.5""""
        )
    }

    @Test
    fun `Should deserialize Money from JSON`() {
        assert(
            testJson.decodeFromString<Money>(
                """"42.5""""
            ) == Money("42.5")
        )
    }

    @Test
    fun `Should preserve tool schema annotations of Money`() {
        @OptIn(ExperimentalSerializationApi::class)
        val meta = Money.serializer().descriptor.annotations
        meta[0] should {
            be<Description>()
            have(value == "Represents a monetary amount with arbitrary precision and no currency information")
        }
        meta[1] should {
            be<Pattern>()
            have(regex == $$"""^-?\d*\.?\d+$""")
        }
    }

}
