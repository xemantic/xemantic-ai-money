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

package com.xemantic.ai.money.serialization

import com.xemantic.ai.money.Money
import com.xemantic.ai.money.Ratio
import com.xemantic.ai.money.test.shouldBe
import com.xemantic.ai.money.test.testJson
import com.xemantic.ai.tool.schema.meta.Description
import com.xemantic.ai.tool.schema.meta.Pattern
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlin.test.Test

class MoneyRatioSerializationTest {

  @Test
  fun `Should serialize Money Ratio to JSON`() {
    val ratio = Money.Ratio("0.000001")
    testJson.encodeToString(ratio) shouldBe """"0.000001""""
  }

  @Test
  fun `Should deserialize Money Ratio from JSON`() {
    val json = """"0.000001""""
    val ratio = testJson.decodeFromString<Money.Ratio>(json)
    ratio shouldBe Money.Ratio("0.000001")
  }

  @Test
  fun `Should preserve tool schema annotations of Money Ratio`() {
    @OptIn(ExperimentalSerializationApi::class)
    val meta = Money.Ratio.serializer().descriptor.annotations
    (meta[0] as Description).value shouldBe "Represents a ratio used to multiply Money"
    (meta[1] as Pattern).regex shouldBe $$"""^-?\d*\.?\d+$"""
  }

}
