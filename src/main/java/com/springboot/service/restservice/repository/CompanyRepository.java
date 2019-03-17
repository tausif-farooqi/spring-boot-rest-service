package com.springboot.service.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.service.restservice.resource.Company;

/**
 * Repository interface for the {@link Company} entity.
 * 
 * @author Tausif Farooqi
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

}
