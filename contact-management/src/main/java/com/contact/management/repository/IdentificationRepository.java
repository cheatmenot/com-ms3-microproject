package com.contact.management.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contact.management.entity.IdentificationEntity;

/**
 * IdentificationRepository
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */

@Repository
public interface IdentificationRepository extends JpaRepository<IdentificationEntity, BigInteger> {

  @Query("SELECT e FROM IdentificationEntity e WHERE e.id = :id AND e.status <> 'DEL'")
  Optional<IdentificationEntity> findByNotDeleted(@Param("id") BigInteger id);

}
