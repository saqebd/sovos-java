package com.sovos.coupa.servlet;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class HttpServletRequestPreferredAccept extends HttpServletRequestWrapper {

  private List<MediaType> headerMap;

  public HttpServletRequestPreferredAccept(HttpServletRequest request) {

    super(request);
    headerMap = new ArrayList<MediaType>();

  }

  public void addAcceptHeader(String value) {

    headerMap.add(MediaType.parseMediaType(value));

  }

  public Enumeration<String> getHeaders(String name) {

    HttpServletRequest request = (HttpServletRequest) getRequest();
    List<String> list = new ArrayList<String>();

    if (HttpHeaders.ACCEPT.equalsIgnoreCase(name)) {

      headerMap.stream()
          .forEach(mediaType -> list.add(mediaType.toString()));

    } else {

      Collections.list(request.getHeaders(name)).stream()
          .forEach(mediaTypeString -> list.add(mediaTypeString));

    }

    return Collections.enumeration(list);

  }

}
