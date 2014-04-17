package com.rdfgroup.cms.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JacksonHelper
{
	public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";

	protected static final Log logger = LogFactory.getLog(JacksonHelper.class);

	/**
	 * Configured to not close streams.
	 */
	public static final ObjectMapper MAPPER; // Threadsafe since Jackson docs say: can reuse, share globally

	/**
	 * Configured to not close streams.
	 */
	public static final JsonFactory JSON_FACTORY;

	static
	{
		MAPPER = new ObjectMapper();

		// We don't want to close the streams passed in to this class.
		// This means that any streams created by this class must be closed explicitly within finally blocks
		MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

		JSON_FACTORY = new JsonFactory();

		JSON_FACTORY.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	}

	private JacksonHelper()
	{
		// static methods on this class mean that it should not be instantiated
	}

	/**
	 * Returns a String containing the JSON representation of the Object passed.
	 *
	 * @param value
	 * @return
	 */
	public static String convertToJSON(Object value)
	{
		StringWriter stringWriter = new StringWriter();
		try
		{
			MAPPER.writeValue(stringWriter, value);
			stringWriter.flush();
			return stringWriter.toString();
		}
		catch (Exception e)
		{
			logger.error("Error serialising Object to JSON using value: " + value, e);
			throw new IllegalStateException(e.getMessage());
		}
		finally
		{
			try
			{
				stringWriter.close();
			}
			catch (IOException e)
			{
				logger.error("Error during finally: " + value, e);
			}
		}
	}

	/**
	 * Converts an object into JSON and writes that directly to the supplied Writer. Mainly intended for use by AJAX calls.
	 * <p>
	 * For example:
	 *
	 * <pre>
	 * convertToJSON(someDto, response.getWriter());
	 * </pre>
	 *
	 * Objects can be lists, which produce JSON arrays. For example a list of DTOs with three values, id, version and auctionIndex would
	 * produce something like:
	 *
	 * <pre>
	 * [{"id":38,"version":2,"auctionIndex":0},{"id":37,"version":2,"auctionIndex":1}]
	 * </pre>
	 *
	 * @param value
	 * @param writer
	 */
	public static void convertToJSON(Object value, PrintWriter writer)
	{
		try
		{
			MAPPER.writeValue(writer, value);
		}
		catch (Exception e)
		{
			logger.error("Error serialising Object to JSON using value: " + value, e);
			throw new IllegalStateException(e.getMessage());
		}
	}





	/**
	 * Expects a String such as "{"first":"1", "second":1}" and a Class of the type of Object encoded in the String.
	 *
	 * @param objectAsJson
	 * @param clazz
	 * @return
	 */
	public static <T> T getObjectFromJson(String objectAsJson, Class<T> clazz)
	{
		try
		{
			return MAPPER.readValue(objectAsJson, clazz);
		}
		catch (Exception e)
		{
			throw new IllegalStateException(e.getMessage());
		}
	}





	/**
	 * Expects a String such as "{"first":"1", "second":1}" and a Class of the type of Object encoded in the String.
	 *
	 * @param objectAsJson
	 * @param clazz
	 * @return
	 */
	public static <T> T getObjectFromJsonArray(final TypeReference<T> type,
		      final String jsonPacket) {
		   T data = null;

		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
			   throw new IllegalStateException(e.getMessage());
		   }
		   return data;
	}


}