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
import com.xemantic.ai.money.test.shouldBe
import com.xemantic.ai.money.test.testJson
import com.xemantic.ai.tool.schema.meta.Description
import com.xemantic.ai.tool.schema.meta.Pattern
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlin.test.Test

class MoneySerializationTest {

  @Test
  fun `Should serialize Money to JSON`() {
    val money = Money("42.50")
    testJson.encodeToString(money) shouldBe """"42.5""""
  }

  @Test
  fun `Should deserialize Money from JSON`() {
    val json = """"42.5""""
    val money = testJson.decodeFromString<Money>(json)
    money shouldBe Money("42.5")
  }

  @Test
  fun `Should preserve tool schema annotations of Money`() {
    @OptIn(ExperimentalSerializationApi::class)
    val meta = Money.serializer().descriptor.annotations
    (meta[0] as Description).value shouldBe "Represents a monetary amount with arbitrary precision and no currency information"
    (meta[1] as Pattern).regex shouldBe $$"""^-?\d*\.?\d+$"""
  }

}
