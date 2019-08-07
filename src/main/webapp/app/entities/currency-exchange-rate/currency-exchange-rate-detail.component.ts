import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

@Component({
  selector: 'jhi-currency-exchange-rate-detail',
  templateUrl: './currency-exchange-rate-detail.component.html'
})
export class CurrencyExchangeRateDetailComponent implements OnInit {
  currencyExchangeRate: ICurrencyExchangeRate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ currencyExchangeRate }) => {
      this.currencyExchangeRate = currencyExchangeRate;
    });
  }

  previousState() {
    window.history.back();
  }
}
