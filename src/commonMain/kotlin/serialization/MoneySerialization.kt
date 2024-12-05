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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object MoneySerializer : KSerializer<Money> {

  // we can autogenerate a serializer which will retain annotations of serialized class
  @OptIn(ExperimentalSerializationApi::class)
  @Serializer(forClass = Money::class)
  private object AutoSerializer

  @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
  public override val descriptor: SerialDescriptor = buildSerialDescriptor(
    serialName = AutoSerializer.descriptor.serialName,
    kind = PrimitiveKind.STRING
  ) {
    annotations = AutoSerializer.descriptor.annotations
  }

  override fun serialize(encoder: Encoder, value: Money) {
    encoder.encodeString(value.toString())
  }

  override fun deserialize(
    decoder: Decoder
  ): Money = Money(decoder.decodeString())

}

public object MoneyRatioSerializer : KSerializer<Money.Ratio> {

  // we can autogenerate a serializer which will retain annotations of serialized class
  @OptIn(ExperimentalSerializationApi::class)
  @Serializer(forClass = Money.Ratio::class)
  private object AutoSerializer

  @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
  public override val descriptor: SerialDescriptor = buildSerialDescriptor(
    serialName = AutoSerializer.descriptor.serialName,
    kind = PrimitiveKind.STRING
  ) {
    annotations = AutoSerializer.descriptor.annotations
  }

  override fun serialize(encoder: Encoder, value: Money.Ratio) {
    encoder.encodeString(value.toString())
  }

  override fun deserialize(
    decoder: Decoder
  ): Money.Ratio = Money.Ratio(decoder.decodeString())

}
