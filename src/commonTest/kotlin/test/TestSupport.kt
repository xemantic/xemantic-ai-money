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

package com.xemantic.ai.money.test

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.test.asserter

/**
 * A pretty printing [Json] for tests.
 */
val testJson = Json {
  prettyPrint = true
  @OptIn(ExperimentalSerializationApi::class)
  prettyPrintIndent = "  "
}

/**
 * Provides an infix function for equality assertions in tests.
 *
 * This function is inspired by the Kotest library's assertions but is implemented
 * to support power-assert conventions. It allows for a more readable and expressive
 * way of writing assertions in Kotlin tests.
 *
 * Note: Together with the `shouldBe` variant receiving a message this function fulfills
 * the `power-assert` convention.
 *
 * @param expected The expected value to compare against.
 * @throws AssertionError if the actual value is not equal to the expected value.
 */
infix fun <V> V.shouldBe(
  expected: V
) {
  shouldBe(
    expected = expected,
    message = null
  )
}

/**
 * Asserts that the actual value is equal to the expected value.
 *
 * This function provides a more flexible way to perform equality assertions
 * by allowing an optional custom error message.
 *
 * @param expected The expected value to compare against.
 * @param message An optional custom message to be used in case of assertion failure.
 * @throws AssertionError if the actual value is not equal to the expected value.
 */
fun <V> V.shouldBe(
  expected: V,
  message: String? = null
) {
  asserter.assertEquals(
    message = message,
    actual = this,
    expected = expected
  )
}
