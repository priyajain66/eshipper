package com.eshipper.service.impl;

import com.eshipper.service.CurrencyExchangeRateService;
import com.eshipper.domain.CurrencyExchangeRate;
import com.eshipper.repository.CurrencyExchangeRateRepository;
import com.eshipper.service.dto.CurrencyExchangeRateDTO;
import com.eshipper.service.mapper.CurrencyExchangeRateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CurrencyExchangeRate}.
 */
@Service
@Transactional
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeRateServiceImpl.class);

    private final CurrencyExchangeRateRepository currencyExchangeRateRepository;

    private final CurrencyExchangeRateMapper currencyExchangeRateMapper;

    public CurrencyExchangeRateServiceImpl(CurrencyExchangeRateRepository currencyExchangeRateRepository, CurrencyExchangeRateMapper currencyExchangeRateMapper) {
        this.currencyExchangeRateRepository = currencyExchangeRateRepository;
        this.currencyExchangeRateMapper = currencyExchangeRateMapper;
    }

    /**
     * Save a currencyExchangeRate.
     *
     * @param currencyExchangeRateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CurrencyExchangeRateDTO save(CurrencyExchangeRateDTO currencyExchangeRateDTO) {
        log.debug("Request to save CurrencyExchangeRate : {}", currencyExchangeRateDTO);
        CurrencyExchangeRate currencyExchangeRate = currencyExchangeRateMapper.toEntity(currencyExchangeRateDTO);
        currencyExchangeRate = currencyExchangeRateRepository.save(currencyExchangeRate);
        return currencyExchangeRateMapper.toDto(currencyExchangeRate);
    }

    /**
     * Get all the currencyExchangeRates.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CurrencyExchangeRateDTO> findAll() {
        log.debug("Request to get all CurrencyExchangeRates");
        return currencyExchangeRateRepository.findAll().stream()
            .map(currencyExchangeRateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one currencyExchangeRate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyExchangeRateDTO> findOne(Long id) {
        log.debug("Request to get CurrencyExchangeRate : {}", id);
        return currencyExchangeRateRepository.findById(id)
            .map(currencyExchangeRateMapper::toDto);
    }

    /**
     * Delete the currencyExchangeRate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CurrencyExchangeRate : {}", id);
        currencyExchangeRateRepository.deleteById(id);
    }
}
