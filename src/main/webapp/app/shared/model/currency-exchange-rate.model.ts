export interface ICurrencyExchangeRate {
  id?: number;
  exchangeRate?: number;
  currencyId?: number;
}

export class CurrencyExchangeRate implements ICurrencyExchangeRate {
  constructor(public id?: number, public exchangeRate?: number, public currencyId?: number) {}
}
