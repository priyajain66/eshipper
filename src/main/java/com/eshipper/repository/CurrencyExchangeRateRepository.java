package com.eshipper.repository;

import com.eshipper.domain.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CurrencyExchangeRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, Long> {

}
