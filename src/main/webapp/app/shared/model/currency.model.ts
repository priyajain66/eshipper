import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

export interface ICurrency {
  id?: number;
  currencyExchangeRates?: ICurrencyExchangeRate[];
}

export class Currency implements ICurrency {
  constructor(public id?: number, public currencyExchangeRates?: ICurrencyExchangeRate[]) {}
}
