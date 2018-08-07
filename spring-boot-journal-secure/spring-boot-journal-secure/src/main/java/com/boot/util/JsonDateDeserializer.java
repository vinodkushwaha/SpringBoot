package com.boot.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date>
{
	private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext ctx)
	        throws IOException, JsonProcessingException
	{
		String date = parser.getText();
		try
		{
			return format.parse(date);
		}
		catch (ParseException e)
		{
			throw new RuntimeException();
		}

	}

}
