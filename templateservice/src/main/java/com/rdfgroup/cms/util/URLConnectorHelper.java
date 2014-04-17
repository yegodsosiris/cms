package com.rdfgroup.cms.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class URLConnectorHelper {
	static final String key = "zy1xw2vu3ts4-r5qp6o.7nm8lk9ji0hgfe@dcba"; // The key for 'encrypting' and 'decrypting'.
	static final String key2 = "ab-cdefghijklm@nopq0r9s8t7u6.5v4w3x2y1z"; // The key for 'encrypting' and 'decrypting'.

	   public static String encryptString(String str)
	   {
		   return encryptString(str, false);
	   }
	   public static String decryptString(String str)
	   {
		   return encryptString(str, true);
	   }

	   public static String encryptString(String str, boolean reverse)
	   {
		   String src = reverse ? key : key2;
		   String dest = reverse ? key2 : key;
	      StringBuffer sb = new StringBuffer ();

	      int lenStr = str.length();
		   
	      //
	      // For each character in our string, encrypt it...
	      for ( int i = 0; i < lenStr; i++ ) 
	      {
	    	  char x;
	    	  int indexOf = StringUtils.indexOf(src, str.charAt(i));
	    	  if (indexOf==-1) {
	    		  x=str.charAt(i);
	    	  }
	    	  else {
	    		  x = dest.charAt(indexOf);
	    	  }
	    	  sb.append(x);
	      }

	      return sb.toString();
	   }
	
	public static String encode(String s) throws UnsupportedEncodingException {
		return s==null? "" : URLEncoder.encode(s, "UTF-8");
	}
	
	public static boolean isHost(HttpServletRequest request, String host) {
		String requestIdentity = request.getRequestURL().toString();
		return requestIdentity.contains(host);
	}

	public static Object decode(String s) throws UnsupportedEncodingException {
		return s==null? "" : URLDecoder.decode(s, "UTF-8");
	}
	
	public static String executeJsonPost(String urlin, String json)  {
		StringBuilder sb = new StringBuilder();
		try {

			URL url = new URL(urlin);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			

			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return sb.toString();
	}
	
	public static String excutePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = null;
			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				is = connection.getErrorStream();
			}
			else {
				is = connection.getInputStream();
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String executeGet(String desiredUrl) throws Exception {
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			// create the HttpURLConnection
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// just want to do an HTTP GET here
			connection.setRequestMethod("GET");

			// uncomment this if you want to write output to this url
			// connection.setDoOutput(true);

			// give it 15 seconds to respond
			connection.setReadTimeout(15 * 1000);
			connection.connect();

			// read the output from the server
			
			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				throw new IOException("Error "+responseCode+" communication error with server");
			}
			else {
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
			}
			stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// close the reader; this can throw an exception too, so
			// wrap it in another try/catch block.
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
