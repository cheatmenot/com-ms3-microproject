package com.contact.management.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.contact.management.sdk.constants.FormatConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import java.time.ZonedDateTime;

@Component
public class SpringBaseModel {

  @Schema(description = "AuditFields - status",
      example = "ACT", required = true)
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "status")
  @JsonProperty("status")
  @Size(max = 3)
  private String status;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "createdBy")
  @JsonProperty("createdBy")
  @Size(max = 32)
  private String createdBy;

  @XmlElement(name = "createdDatetime")
  @JsonProperty("createdDatetime")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatConstants.DATETIME_WTZ)
  private ZonedDateTime createdDatetime;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "modifiedBy")
  @JsonProperty("modifiedBy")
  @Size(max = 32)
  private String modifiedBy;

  @XmlElement(name = "modifiedDatetime")
  @JsonProperty("modifiedDatetime")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatConstants.DATETIME_WTZ)
  private ZonedDateTime modifiedDatetime;

  @XmlElement(name = "verNo")
  @JsonProperty("verNo")
  private Short verNo;

  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "source")
  @JsonProperty("source")
  @Size(max = 32)
  private String source;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public ZonedDateTime getCreatedDatetime() {
    return createdDatetime;
  }

  public void setCreatedDatetime(ZonedDateTime createdDatetime) {
    this.createdDatetime = createdDatetime;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public ZonedDateTime getModifiedDatetime() {
    return modifiedDatetime;
  }

  public void setModifiedDatetime(ZonedDateTime modifiedDatetime) {
    this.modifiedDatetime = modifiedDatetime;
  }

  public Short getVerNo() {
    return verNo;
  }

  public void setVerNo(Short verNo) {
    this.verNo = verNo;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
