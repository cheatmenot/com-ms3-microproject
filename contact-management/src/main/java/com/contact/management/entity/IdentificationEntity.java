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
 * IdentificationEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Entity
@Table(name = "TBL_IDENTIFICATION")
public class IdentificationEntity extends SpringBaseEntity implements Serializable {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1L;

  public IdentificationEntity() {
    super();
  }
  
  // ----- Fields -----
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identificationSeqGen")
  @SequenceGenerator(name = "identificationSeqGen", sequenceName = "SEQ_IDENTIFICATION_ID", 
      allocationSize = 1)
  @Column(name = "ID", nullable = false)
  private BigInteger id;
  
  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;
  
  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;
  
  @Column(name = "DOB", nullable = false)
  private Date dob;
  
  @Column(name = "GENDER", nullable = false)
  private String gender;
  
  @Column(name = "TITLE", nullable = false)
  private String title;
  
  // ----- Links -----
  @OneToMany(mappedBy = "identification", targetEntity = AddressEntity.class,
      cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<AddressEntity> listOfAddress;
  
  @OneToMany(mappedBy = "identification", targetEntity = CommunicationEntity.class,
      cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CommunicationEntity> listOfCommunication;
  
  // ----- Setters and Getters for Fields -----
  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }
  
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  // ----- Setters and Getters for Links -----
  public List<AddressEntity> getListOfAddress() {
    return this.listOfAddress;
  }

  public void setListOfAddress(List<AddressEntity> listOfAddress) {
    if (this.getListOfAddress() == null) {
      if (listOfAddress != null) {
        for (AddressEntity listItem : listOfAddress) {
          listItem.setIdentification(this);
        }
      }
      this.listOfAddress = listOfAddress;
    }
    else {
      if (listOfAddress != null) {
        for (AddressEntity listItem : listOfAddress) {
          listItem.setIdentification(this);
        }
        this.getListOfAddress().clear();
        this.getListOfAddress()
            .addAll(listOfAddress);
      }
    }
  }
  public List<CommunicationEntity> getListOfCommunication() {
    return this.listOfCommunication;
  }

  public void setListOfCommunication(List<CommunicationEntity> listOfCommunication) {
    if (this.getListOfCommunication() == null) {
      if (listOfCommunication != null) {
        for (CommunicationEntity listItem : listOfCommunication) {
          listItem.setIdentification(this);
        }
      }
      this.listOfCommunication = listOfCommunication;
    }
    else {
      if (listOfCommunication != null) {
        for (CommunicationEntity listItem : listOfCommunication) {
          listItem.setIdentification(this);
        }
        this.getListOfCommunication().clear();
        this.getListOfCommunication()
            .addAll(listOfCommunication);
      }
    }
  }
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
