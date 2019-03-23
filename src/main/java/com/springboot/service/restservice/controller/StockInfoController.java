package com.springboot.service.restservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.service.restservice.repository.StockInfoRepository;
import com.springboot.service.restservice.resource.StockInfo;

/**
 * Controller class that allows operations on the StockInfo resource. We can get/add/update/delete 
 * stock information here.
 * 
 * @author Tausif Farooqi
 *
 */
@RestController
public class StockInfoController {
	
	@Autowired
	private StockInfoRepository stockInfoRepository;
	
	/**
	 * @return
	 */
	@GetMapping("/companies/{companySymbol}/stockInfos")
	public List<StockInfo> getAllStockInfosForComany(@PathVariable String symbol, Pageable pageable) {
		Page<StockInfo> stockInfos = stockInfoRepository.findBySymbol(symbol, pageable);
		return stockInfos.getContent();
	}
	
	/**
	 * @return
	 */
	@GetMapping("/companies/{companySymbol}/stockInfos/{id}")
	public StockInfo getStockInfo(@PathVariable String symbol, @PathVariable Integer id) {
		Optional<StockInfo> stockInfo = stockInfoRepository.findById(id);
		return stockInfo.get();
	}	
	
	/**
	 * @param stockInfo
	 */
	@PostMapping("/companies/{companySymbol}/stockInfos")
	public ResponseEntity<StockInfo> addStockInfo(@RequestBody StockInfo stockInfo) {
		StockInfo createdStockInfo = stockInfoRepository.save(stockInfo);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdStockInfo.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * @param company
	 */
	@PutMapping("/companies/{symbol}/stockInfos/{id}")
	public void updateCompany(@RequestBody StockInfo stockInfo, @PathVariable String symbol, @PathVariable Integer id) {
		
	}
	
	/**
	 * @param symbol
	 * @param id
	 */
	@DeleteMapping("/companies/{symbol}/stockInfos/{id}")
	public void deleteCompany(@PathVariable String symbol, @PathVariable Integer id) {
		stockInfoRepository.deleteById(id);
	}
}
