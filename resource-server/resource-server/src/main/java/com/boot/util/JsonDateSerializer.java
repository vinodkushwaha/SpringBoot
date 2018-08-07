package com.boot.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>
{
	private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider sp)
	        throws IOException, JsonProcessingException
	{
		String formattedDate = format.format(date);
		gen.writeString(formattedDate);
	}

}
