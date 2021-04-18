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
 * CommunicationEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Entity
@Table(name = "TBL_COMMUNICATION")
public class CommunicationEntity extends SpringBaseEntity implements Serializable {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1L;

  public CommunicationEntity() {
    super();
  }
  
  // ----- Fields -----
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communicationSeqGen")
  @SequenceGenerator(name = "communicationSeqGen", sequenceName = "SEQ_COMMUNICATION_ID", 
      allocationSize = 1)
  @Column(name = "ID", nullable = false)
  private BigInteger id;
  
  @Column(name = "TYPE", nullable = false)
  private String type;
  
  @Column(name = "VALUE", nullable = false)
  private String value;
  
  @Column(name = "PREFERRED")
  private Boolean preferred;
  
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
