package com.eshipper.web.rest;

import com.eshipper.service.CurrencyExchangeRateService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CurrencyExchangeRateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.CurrencyExchangeRate}.
 */
@RestController
@RequestMapping("/api")
public class CurrencyExchangeRateResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeRateResource.class);

    private static final String ENTITY_NAME = "currencyExchangeRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CurrencyExchangeRateService currencyExchangeRateService;

    public CurrencyExchangeRateResource(CurrencyExchangeRateService currencyExchangeRateService) {
        this.currencyExchangeRateService = currencyExchangeRateService;
    }

    /**
     * {@code POST  /currency-exchange-rates} : Create a new currencyExchangeRate.
     *
     * @param currencyExchangeRateDTO the currencyExchangeRateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new currencyExchangeRateDTO, or with status {@code 400 (Bad Request)} if the currencyExchangeRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/currency-exchange-rates")
    public ResponseEntity<CurrencyExchangeRateDTO> createCurrencyExchangeRate(@RequestBody CurrencyExchangeRateDTO currencyExchangeRateDTO) throws URISyntaxException {
        log.debug("REST request to save CurrencyExchangeRate : {}", currencyExchangeRateDTO);
        if (currencyExchangeRateDTO.getId() != null) {
            throw new BadRequestAlertException("A new currencyExchangeRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CurrencyExchangeRateDTO result = currencyExchangeRateService.save(currencyExchangeRateDTO);
        return ResponseEntity.created(new URI("/api/currency-exchange-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /currency-exchange-rates} : Updates an existing currencyExchangeRate.
     *
     * @param currencyExchangeRateDTO the currencyExchangeRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currencyExchangeRateDTO,
     * or with status {@code 400 (Bad Request)} if the currencyExchangeRateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the currencyExchangeRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/currency-exchange-rates")
    public ResponseEntity<CurrencyExchangeRateDTO> updateCurrencyExchangeRate(@RequestBody CurrencyExchangeRateDTO currencyExchangeRateDTO) throws URISyntaxException {
        log.debug("REST request to update CurrencyExchangeRate : {}", currencyExchangeRateDTO);
        if (currencyExchangeRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CurrencyExchangeRateDTO result = currencyExchangeRateService.save(currencyExchangeRateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, currencyExchangeRateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /currency-exchange-rates} : get all the currencyExchangeRates.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of currencyExchangeRates in body.
     */
    @GetMapping("/currency-exchange-rates")
    public List<CurrencyExchangeRateDTO> getAllCurrencyExchangeRates() {
        log.debug("REST request to get all CurrencyExchangeRates");
        return currencyExchangeRateService.findAll();
    }

    /**
     * {@code GET  /currency-exchange-rates/:id} : get the "id" currencyExchangeRate.
     *
     * @param id the id of the currencyExchangeRateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the currencyExchangeRateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/currency-exchange-rates/{id}")
    public ResponseEntity<CurrencyExchangeRateDTO> getCurrencyExchangeRate(@PathVariable Long id) {
        log.debug("REST request to get CurrencyExchangeRate : {}", id);
        Optional<CurrencyExchangeRateDTO> currencyExchangeRateDTO = currencyExchangeRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(currencyExchangeRateDTO);
    }

    /**
     * {@code DELETE  /currency-exchange-rates/:id} : delete the "id" currencyExchangeRate.
     *
     * @param id the id of the currencyExchangeRateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/currency-exchange-rates/{id}")
    public ResponseEntity<Void> deleteCurrencyExchangeRate(@PathVariable Long id) {
        log.debug("REST request to delete CurrencyExchangeRate : {}", id);
        currencyExchangeRateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
