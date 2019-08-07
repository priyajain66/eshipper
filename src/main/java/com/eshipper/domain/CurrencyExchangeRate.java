package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CurrencyExchangeRate.
 */
@Entity
@Table(name = "currency_exchange_rate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CurrencyExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @ManyToOne
    @JsonIgnoreProperties("currencyExchangeRates")
    private Currency currency;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public CurrencyExchangeRate exchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public CurrencyExchangeRate currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyExchangeRate)) {
            return false;
        }
        return id != null && id.equals(((CurrencyExchangeRate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRate{" +
            "id=" + getId() +
            ", exchangeRate=" + getExchangeRate() +
            "}";
    }
}
