package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CurrencyExchangeRate> currencyExchangeRates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CurrencyExchangeRate> getCurrencyExchangeRates() {
        return currencyExchangeRates;
    }

    public Currency currencyExchangeRates(Set<CurrencyExchangeRate> currencyExchangeRates) {
        this.currencyExchangeRates = currencyExchangeRates;
        return this;
    }

    public Currency addCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
        this.currencyExchangeRates.add(currencyExchangeRate);
        currencyExchangeRate.setCurrency(this);
        return this;
    }

    public Currency removeCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
        this.currencyExchangeRates.remove(currencyExchangeRate);
        currencyExchangeRate.setCurrency(null);
        return this;
    }

    public void setCurrencyExchangeRates(Set<CurrencyExchangeRate> currencyExchangeRates) {
        this.currencyExchangeRates = currencyExchangeRates;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return id != null && id.equals(((Currency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            "}";
    }
}
