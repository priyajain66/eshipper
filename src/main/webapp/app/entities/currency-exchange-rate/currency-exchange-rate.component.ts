import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';
import { AccountService } from 'app/core';
import { CurrencyExchangeRateService } from './currency-exchange-rate.service';

@Component({
  selector: 'jhi-currency-exchange-rate',
  templateUrl: './currency-exchange-rate.component.html'
})
export class CurrencyExchangeRateComponent implements OnInit, OnDestroy {
  currencyExchangeRates: ICurrencyExchangeRate[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected currencyExchangeRateService: CurrencyExchangeRateService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.currencyExchangeRateService
      .query()
      .pipe(
        filter((res: HttpResponse<ICurrencyExchangeRate[]>) => res.ok),
        map((res: HttpResponse<ICurrencyExchangeRate[]>) => res.body)
      )
      .subscribe(
        (res: ICurrencyExchangeRate[]) => {
          this.currencyExchangeRates = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCurrencyExchangeRates();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICurrencyExchangeRate) {
    return item.id;
  }

  registerChangeInCurrencyExchangeRates() {
    this.eventSubscriber = this.eventManager.subscribe('currencyExchangeRateListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
