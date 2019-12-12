/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.upskill.microcervices.currencyexchangeservice.dao.ExchangeValueDao;

/**
 * @author GangaRajuUdimudi
 *
 */
@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValueDao, Long> {
	
	ExchangeValueDao findByFromAndTo(String from, String to);
	
	Optional<ExchangeValueDao> findById(Long id);
}
