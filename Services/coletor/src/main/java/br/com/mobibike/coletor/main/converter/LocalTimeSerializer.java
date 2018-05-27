package br.com.mobibike.coletor.main.converter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.isNull(value)) {
			gen.writeString("null");
		} else {
			gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_TIME));
		}
	}

}
