package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.CurrencyExchangeRate} entity.
 */
public class CurrencyExchangeRateDTO implements Serializable {

    private Long id;

    private Double exchangeRate;


    private Long currencyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyExchangeRateDTO currencyExchangeRateDTO = (CurrencyExchangeRateDTO) o;
        if (currencyExchangeRateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyExchangeRateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRateDTO{" +
            "id=" + getId() +
            ", exchangeRate=" + getExchangeRate() +
            ", currency=" + getCurrencyId() +
            "}";
    }
}
