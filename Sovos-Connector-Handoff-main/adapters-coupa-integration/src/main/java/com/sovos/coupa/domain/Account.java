/**
 * Copyright (c) 2021 by Sovos Compliance
 */
package com.sovos.coupa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Account {

  public static final String STATUS_INACTIVE = "INACTIVE";
  public static final String STATUS_ACTIVE = "ACTIVE";
  public static final String TYPE_ADMIN = "ADMIN";
  public static final String TYPE_API = "API";
  public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  @Setter
  @Getter
  private String id;
  @Getter
  @Setter
  private String merchantId;
  @Getter
  @Setter
  private String partnerId;
  @Getter
  @Setter
  private String email;
  @Getter
  @Setter
  @JsonIgnore
  private String secretKey;
  @Getter
  @Setter
  private String status = STATUS_ACTIVE;
  @Getter
  @Setter
  private String type;
  @Getter
  @Setter
  private String userId;
  @Setter
  private boolean isCassInputEnabled;
  @Setter
  private boolean isCassOutputEnabled;

  @Getter
  @Embedded
  private ApiInformation apiInformation;

  @Setter
  @Getter
  private List<AccountProperty> properties = new ArrayList<>();

  public boolean isCassInputEnabled() {

    return isCassInputEnabled;
  }

  public boolean isCassOutputEnabled() {

    return isCassOutputEnabled;
  }

  @Override
  public String toString() {

    return "Account{" + "id='" + id + "'}";
  }

}
