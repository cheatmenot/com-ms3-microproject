package com.contact.management.sdk.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
public class SpringBaseEntity {

  @Column(name = "STATUS")
  private String status;

  @Column(name = "CREATED_BY")
  private String createdBy;

  @Column(name = "CREATED_DATETIME")
  private ZonedDateTime createdDatetime;

  @Column(name = "MODIFIED_BY")
  private String modifiedBy;

  @Column(name = "MODIFIED_DATETIME")
  private ZonedDateTime modifiedDatetime;

  @Column(name = "VERSION_NO")
  private Short verNo;

  @Column(name = "SOURCE")
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
