package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CurrencyExchangeRate;
import com.eshipper.repository.CurrencyExchangeRateRepository;
import com.eshipper.service.CurrencyExchangeRateService;
import com.eshipper.service.dto.CurrencyExchangeRateDTO;
import com.eshipper.service.mapper.CurrencyExchangeRateMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CurrencyExchangeRateResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class CurrencyExchangeRateResourceIT {

    private static final Double DEFAULT_EXCHANGE_RATE = 1D;
    private static final Double UPDATED_EXCHANGE_RATE = 2D;
    private static final Double SMALLER_EXCHANGE_RATE = 1D - 1D;

    @Autowired
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @Autowired
    private CurrencyExchangeRateMapper currencyExchangeRateMapper;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCurrencyExchangeRateMockMvc;

    private CurrencyExchangeRate currencyExchangeRate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CurrencyExchangeRateResource currencyExchangeRateResource = new CurrencyExchangeRateResource(currencyExchangeRateService);
        this.restCurrencyExchangeRateMockMvc = MockMvcBuilders.standaloneSetup(currencyExchangeRateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchangeRate createEntity(EntityManager em) {
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate()
            .exchangeRate(DEFAULT_EXCHANGE_RATE);
        return currencyExchangeRate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchangeRate createUpdatedEntity(EntityManager em) {
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate()
            .exchangeRate(UPDATED_EXCHANGE_RATE);
        return currencyExchangeRate;
    }

    @BeforeEach
    public void initTest() {
        currencyExchangeRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurrencyExchangeRate() throws Exception {
        int databaseSizeBeforeCreate = currencyExchangeRateRepository.findAll().size();

        // Create the CurrencyExchangeRate
        CurrencyExchangeRateDTO currencyExchangeRateDTO = currencyExchangeRateMapper.toDto(currencyExchangeRate);
        restCurrencyExchangeRateMockMvc.perform(post("/api/currency-exchange-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchangeRateDTO)))
            .andExpect(status().isCreated());

        // Validate the CurrencyExchangeRate in the database
        List<CurrencyExchangeRate> currencyExchangeRateList = currencyExchangeRateRepository.findAll();
        assertThat(currencyExchangeRateList).hasSize(databaseSizeBeforeCreate + 1);
        CurrencyExchangeRate testCurrencyExchangeRate = currencyExchangeRateList.get(currencyExchangeRateList.size() - 1);
        assertThat(testCurrencyExchangeRate.getExchangeRate()).isEqualTo(DEFAULT_EXCHANGE_RATE);
    }

    @Test
    @Transactional
    public void createCurrencyExchangeRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = currencyExchangeRateRepository.findAll().size();

        // Create the CurrencyExchangeRate with an existing ID
        currencyExchangeRate.setId(1L);
        CurrencyExchangeRateDTO currencyExchangeRateDTO = currencyExchangeRateMapper.toDto(currencyExchangeRate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrencyExchangeRateMockMvc.perform(post("/api/currency-exchange-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchangeRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeRate in the database
        List<CurrencyExchangeRate> currencyExchangeRateList = currencyExchangeRateRepository.findAll();
        assertThat(currencyExchangeRateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCurrencyExchangeRates() throws Exception {
        // Initialize the database
        currencyExchangeRateRepository.saveAndFlush(currencyExchangeRate);

        // Get all the currencyExchangeRateList
        restCurrencyExchangeRateMockMvc.perform(get("/api/currency-exchange-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(currencyExchangeRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].exchangeRate").value(hasItem(DEFAULT_EXCHANGE_RATE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getCurrencyExchangeRate() throws Exception {
        // Initialize the database
        currencyExchangeRateRepository.saveAndFlush(currencyExchangeRate);

        // Get the currencyExchangeRate
        restCurrencyExchangeRateMockMvc.perform(get("/api/currency-exchange-rates/{id}", currencyExchangeRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(currencyExchangeRate.getId().intValue()))
            .andExpect(jsonPath("$.exchangeRate").value(DEFAULT_EXCHANGE_RATE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCurrencyExchangeRate() throws Exception {
        // Get the currencyExchangeRate
        restCurrencyExchangeRateMockMvc.perform(get("/api/currency-exchange-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrencyExchangeRate() throws Exception {
        // Initialize the database
        currencyExchangeRateRepository.saveAndFlush(currencyExchangeRate);

        int databaseSizeBeforeUpdate = currencyExchangeRateRepository.findAll().size();

        // Update the currencyExchangeRate
        CurrencyExchangeRate updatedCurrencyExchangeRate = currencyExchangeRateRepository.findById(currencyExchangeRate.getId()).get();
        // Disconnect from session so that the updates on updatedCurrencyExchangeRate are not directly saved in db
        em.detach(updatedCurrencyExchangeRate);
        updatedCurrencyExchangeRate
            .exchangeRate(UPDATED_EXCHANGE_RATE);
        CurrencyExchangeRateDTO currencyExchangeRateDTO = currencyExchangeRateMapper.toDto(updatedCurrencyExchangeRate);

        restCurrencyExchangeRateMockMvc.perform(put("/api/currency-exchange-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchangeRateDTO)))
            .andExpect(status().isOk());

        // Validate the CurrencyExchangeRate in the database
        List<CurrencyExchangeRate> currencyExchangeRateList = currencyExchangeRateRepository.findAll();
        assertThat(currencyExchangeRateList).hasSize(databaseSizeBeforeUpdate);
        CurrencyExchangeRate testCurrencyExchangeRate = currencyExchangeRateList.get(currencyExchangeRateList.size() - 1);
        assertThat(testCurrencyExchangeRate.getExchangeRate()).isEqualTo(UPDATED_EXCHANGE_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCurrencyExchangeRate() throws Exception {
        int databaseSizeBeforeUpdate = currencyExchangeRateRepository.findAll().size();

        // Create the CurrencyExchangeRate
        CurrencyExchangeRateDTO currencyExchangeRateDTO = currencyExchangeRateMapper.toDto(currencyExchangeRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyExchangeRateMockMvc.perform(put("/api/currency-exchange-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchangeRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeRate in the database
        List<CurrencyExchangeRate> currencyExchangeRateList = currencyExchangeRateRepository.findAll();
        assertThat(currencyExchangeRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurrencyExchangeRate() throws Exception {
        // Initialize the database
        currencyExchangeRateRepository.saveAndFlush(currencyExchangeRate);

        int databaseSizeBeforeDelete = currencyExchangeRateRepository.findAll().size();

        // Delete the currencyExchangeRate
        restCurrencyExchangeRateMockMvc.perform(delete("/api/currency-exchange-rates/{id}", currencyExchangeRate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CurrencyExchangeRate> currencyExchangeRateList = currencyExchangeRateRepository.findAll();
        assertThat(currencyExchangeRateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyExchangeRate.class);
        CurrencyExchangeRate currencyExchangeRate1 = new CurrencyExchangeRate();
        currencyExchangeRate1.setId(1L);
        CurrencyExchangeRate currencyExchangeRate2 = new CurrencyExchangeRate();
        currencyExchangeRate2.setId(currencyExchangeRate1.getId());
        assertThat(currencyExchangeRate1).isEqualTo(currencyExchangeRate2);
        currencyExchangeRate2.setId(2L);
        assertThat(currencyExchangeRate1).isNotEqualTo(currencyExchangeRate2);
        currencyExchangeRate1.setId(null);
        assertThat(currencyExchangeRate1).isNotEqualTo(currencyExchangeRate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyExchangeRateDTO.class);
        CurrencyExchangeRateDTO currencyExchangeRateDTO1 = new CurrencyExchangeRateDTO();
        currencyExchangeRateDTO1.setId(1L);
        CurrencyExchangeRateDTO currencyExchangeRateDTO2 = new CurrencyExchangeRateDTO();
        assertThat(currencyExchangeRateDTO1).isNotEqualTo(currencyExchangeRateDTO2);
        currencyExchangeRateDTO2.setId(currencyExchangeRateDTO1.getId());
        assertThat(currencyExchangeRateDTO1).isEqualTo(currencyExchangeRateDTO2);
        currencyExchangeRateDTO2.setId(2L);
        assertThat(currencyExchangeRateDTO1).isNotEqualTo(currencyExchangeRateDTO2);
        currencyExchangeRateDTO1.setId(null);
        assertThat(currencyExchangeRateDTO1).isNotEqualTo(currencyExchangeRateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(currencyExchangeRateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(currencyExchangeRateMapper.fromId(null)).isNull();
    }
}
