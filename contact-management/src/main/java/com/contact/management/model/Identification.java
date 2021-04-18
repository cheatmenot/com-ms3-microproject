package com.contact.management.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.contact.management.sdk.model.SpringBaseModel;
import com.contact.management.sdk.constants.FormatConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Identification
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
 
@JsonInclude(Include.NON_NULL)
@JsonRootName("identification")
@XmlRootElement(name = "identification")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIdentityInfo(
    scope = Identification.class,
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Identification extends SpringBaseModel implements Serializable {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1L;

  // ----- Fields -----
  @XmlElement(name = "id")
  @JsonProperty("id")
  private BigInteger id;
  
  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "firstName")
  @JsonProperty("firstName")
  @Size(max = 64)
  private String firstName;
  
  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "lastName")
  @JsonProperty("lastName")
  @Size(max = 64)
  private String lastName;
  
  @NotNull
  @XmlElement(name = "dob")
  @JsonProperty("dob")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatConstants.DATE_DATE)
  private Date dob;
  
  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "gender")
  @JsonProperty("gender")
  @Size(max = 1)
  private String gender;
  
  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "title")
  @JsonProperty("title")
  @Size(max = 64)
  private String title;
  
  // ----- Links -----
  @Valid
  //@JsonManagedReference
  @XmlElement(name = "address")
  @JsonProperty("address")
  private List<Address> listOfAddress;

  @Valid
  //@JsonManagedReference
  @XmlElement(name = "communication")
  @JsonProperty("communication")
  private List<Communication> listOfCommunication;

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
  public List<Address> getListOfAddress() {
    return listOfAddress;
  }
  
  public void setAddress(List<Address> listOfAddress) {
    this.listOfAddress = listOfAddress;
  }
  
  public List<Communication> getListOfCommunication() {
    return listOfCommunication;
  }
  
  public void setCommunication(List<Communication> listOfCommunication) {
    this.listOfCommunication = listOfCommunication;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
