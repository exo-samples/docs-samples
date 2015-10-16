/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.rest.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com Sep
 * 29, 2015
 */
public class HttpUtils {

  // GET
  public static String get(String url) throws Exception {
    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
    connection.setRequestMethod("GET");
    connection.connect();

    int code = connection.getResponseCode();
    if (code > 300) {
      connection.disconnect();
      return null;
    }
    InputStream in = connection.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line = null;
    StringBuilder builder = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }
    in.close();
    reader.close();
    connection.disconnect();
    return builder.toString();
  }

  // POST
  public static String post(String json, String url) throws Exception {
    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setUseCaches(false);
    connection.setRequestProperty("Content-Type", "application/JSON");

    // Write to the connection output stream.
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.writeBytes(json);

    int code = connection.getResponseCode();
    if (code > 300) {
      out.flush();
      out.close();
      connection.disconnect();
      return null;
    }
    InputStream in = connection.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line = null;
    StringBuilder builder = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }
    in.close();
    reader.close();
    connection.disconnect();
    return builder.toString();
  }

  // POST without body
  public static String post(String url) throws Exception {
    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setUseCaches(false);
    connection.setRequestProperty("Content-Type", "application/JSON");

    int code = connection.getResponseCode();
    if (code > 300) {
      connection.disconnect();
      return null;
    }
    InputStream in = connection.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line = null;
    StringBuilder builder = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }
    in.close();
    reader.close();
    connection.disconnect();
    return builder.toString();
  }

  // PUT
  public static int put(String json, String url) throws Exception {
    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
    connection.setRequestMethod("PUT");
    connection.setDoOutput(true);
    connection.setUseCaches(false);
    connection.setRequestProperty("Content-Type", "application/JSON");

    // Write to the connection output stream.
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.writeBytes(json);

    int code = connection.getResponseCode();
    out.flush();
    out.close();
    connection.disconnect();
    return code;
  }

  // DELETE
  public static int delete(String url) throws Exception {
    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
    connection.setRequestMethod("DELETE");
    connection.connect();

    int code = connection.getResponseCode();
    connection.disconnect();
    return code;
  }

  // UPLOAD
  public static String upload(List<Path> paths, String url) throws Exception {
    // form-data stuffs
    String crlf = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    String attachmentName;
    String attachmentFileName;
    byte[] data;

    // set up the connection
    HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setUseCaches(false);
    connection.setRequestProperty("Cache-Control", "nocache");
    connection.setRequestProperty("Connection", "Keep-Alive");
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

    // write to connection output stream
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    int len = paths.size();
    for (int i = 0; i < len; i++) {
      attachmentFileName = paths.get(i).getFileName().toString();
      attachmentName = attachmentFileName;
      data = Files.readAllBytes(paths.get(i));
      out.writeBytes(twoHyphens + boundary + crlf);
      out.writeBytes("Content-Disposition: form-data;" + "name=\"" + attachmentName + "\";"
          + "filename=\"" + attachmentFileName + "\"" + crlf);
      out.writeBytes(crlf);
      out.write(data);
      out.writeBytes(crlf);
    }
    out.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

    int code = connection.getResponseCode();
    if (code > 300) {
      out.flush();
      out.close();
      connection.disconnect();
      return null;
    }
    String href = connection.getHeaderField("Location");
    out.flush();
    out.close();
    connection.disconnect();
    return href;
  }
}
