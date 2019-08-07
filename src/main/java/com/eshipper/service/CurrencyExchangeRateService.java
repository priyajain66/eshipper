package com.eshipper.service;

import com.eshipper.service.dto.CurrencyExchangeRateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CurrencyExchangeRate}.
 */
public interface CurrencyExchangeRateService {

    /**
     * Save a currencyExchangeRate.
     *
     * @param currencyExchangeRateDTO the entity to save.
     * @return the persisted entity.
     */
    CurrencyExchangeRateDTO save(CurrencyExchangeRateDTO currencyExchangeRateDTO);

    /**
     * Get all the currencyExchangeRates.
     *
     * @return the list of entities.
     */
    List<CurrencyExchangeRateDTO> findAll();


    /**
     * Get the "id" currencyExchangeRate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CurrencyExchangeRateDTO> findOne(Long id);

    /**
     * Delete the "id" currencyExchangeRate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
