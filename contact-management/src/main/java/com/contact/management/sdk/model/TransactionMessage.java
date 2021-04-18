package com.contact.management.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionMessage implements Serializable {

  public TransactionMessage(String message) {
    this.transactionMessage = message;
  }

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1L;

  // ----- Fields -----
  @XmlElement(name = "transactionMessage")
  @JsonProperty("transactionMessage")
  private String transactionMessage;

  // ----- Setters and Getters for Fields -----
  public String getTransactionMessage() {
    return transactionMessage;
  }

  public void setTransactionMessage(String transactionMessage) {
    this.transactionMessage = transactionMessage;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
