package com.rcg.commercialData.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberWithCommaSerializer extends JsonSerializer<Long> {

    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(numberFormat.format(value)); // 숫자를 문자열로 쉼표 포함해 직렬화
    }
}

