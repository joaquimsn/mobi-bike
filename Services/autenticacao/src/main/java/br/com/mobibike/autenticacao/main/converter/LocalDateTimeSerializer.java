package br.com.mobibike.autenticacao.main.converter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mobibike.autenticacao.main.Ambiente;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.isNull(value)) {
			gen.writeString("null");
		} else {
			gen.writeString(value.atZone(ZoneId.of(Ambiente.getTimeZone())).toOffsetDateTime()
					.format(DateTimeFormatter.ISO_DATE_TIME));
		}
	}

}
