package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.CurrencyExchangeRateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CurrencyExchangeRate} and its DTO {@link CurrencyExchangeRateDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public interface CurrencyExchangeRateMapper extends EntityMapper<CurrencyExchangeRateDTO, CurrencyExchangeRate> {

    @Mapping(source = "currency.id", target = "currencyId")
    CurrencyExchangeRateDTO toDto(CurrencyExchangeRate currencyExchangeRate);

    @Mapping(source = "currencyId", target = "currency")
    CurrencyExchangeRate toEntity(CurrencyExchangeRateDTO currencyExchangeRateDTO);

    default CurrencyExchangeRate fromId(Long id) {
        if (id == null) {
            return null;
        }
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
        currencyExchangeRate.setId(id);
        return currencyExchangeRate;
    }
}
