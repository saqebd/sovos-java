package com.sovos.coupa.controller.dto;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class StringAdapter extends XmlAdapter<String, String> {

  @Override
  public String unmarshal(String v) throws Exception {
    return v;
  }

  @Override
  public String marshal(String v) throws Exception {
    return v;
  }
}

