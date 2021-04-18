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
 * Communication
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
 
@JsonInclude(Include.NON_NULL)
@JsonRootName("communication")
@XmlRootElement(name = "communication")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIdentityInfo(
    scope = Communication.class,
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Communication extends SpringBaseModel implements Serializable {

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
  @XmlElement(name = "type")
  @JsonProperty("type")
  @Size(max = 129)
  private String type;
  
  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
  @XmlElement(name = "value")
  @JsonProperty("value")
  @Size(max = 130)
  private String value;
  
  @XmlElement(name = "preferred")
  @JsonProperty("preferred")
  private Boolean preferred;
  
  // ----- Links -----
  //@JsonBackReference
  @JsonIdentityReference(alwaysAsId = true)
  @XmlElement(name = "identification")
  @JsonProperty("identification")
  private Identification identification;

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
  
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  public Boolean getPreferred() {
    return preferred;
  }

  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }
  
  // ----- Setters and Getters for Links -----
  public Identification getIdentification() {
    return identification;
  }
  
  public void setIdentification(Identification identification) {
    this.identification = identification;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
