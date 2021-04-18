package com.contact.management.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.contact.management.sdk.entity.SpringBaseEntity;

/**
 * AddressEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Entity
@Table(name = "TBL_ADDRESS")
public class AddressEntity extends SpringBaseEntity implements Serializable {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1L;

  public AddressEntity() {
    super();
  }
  
  // ----- Fields -----
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeqGen")
  @SequenceGenerator(name = "addressSeqGen", sequenceName = "SEQ_ADDRESS_ID", 
      allocationSize = 1)
  @Column(name = "ID", nullable = false)
  private BigInteger id;
  
  @Column(name = "TYPE", nullable = false)
  private String type;
  
  @Column(name = "NUMBER", nullable = false)
  private String number;
  
  @Column(name = "STREET", nullable = false)
  private String street;
  
  @Column(name = "UNIT", nullable = false)
  private String unit;
  
  @Column(name = "CITY", nullable = false)
  private String city;
  
  @Column(name = "STATE", nullable = false)
  private String state;
  
  @Column(name = "ZIP_CODE", nullable = false)
  private String zipCode;
  
  // ----- Links -----
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = IdentificationEntity.class)
  @JoinColumn(name = "IDENTIFICATION_ID", referencedColumnName = "ID")
  private IdentificationEntity identification;
  
  // ----- Setters and Getters for Fields -----
  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
  
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }
  
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
  
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  
  // ----- Setters and Getters for Links -----
  public IdentificationEntity getIdentification() {
    return identification;
  }

  public void setIdentification(IdentificationEntity identification) {
    this.identification = identification;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
