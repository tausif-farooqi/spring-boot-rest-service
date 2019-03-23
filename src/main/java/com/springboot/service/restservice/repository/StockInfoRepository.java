package com.springboot.service.restservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.service.restservice.resource.StockInfo;

/**
 * Repository interface for {@link StockInfo} entity.
 * 
 * @author Tausif Farooqi
 *
 */
@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {
	/**
	 * Finds stock information for a company given it's symbol.
	 * 
	 * @param symbol
	 * @param pageable
	 * @return
	 */
	Page<StockInfo> findBySymbol(String symbol, Pageable pageable);
}
