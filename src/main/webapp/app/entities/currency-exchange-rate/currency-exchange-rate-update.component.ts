import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICurrencyExchangeRate, CurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';
import { CurrencyExchangeRateService } from './currency-exchange-rate.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency';

@Component({
  selector: 'jhi-currency-exchange-rate-update',
  templateUrl: './currency-exchange-rate-update.component.html'
})
export class CurrencyExchangeRateUpdateComponent implements OnInit {
  isSaving: boolean;

  currencies: ICurrency[];

  editForm = this.fb.group({
    id: [],
    exchangeRate: [],
    currencyId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected currencyExchangeRateService: CurrencyExchangeRateService,
    protected currencyService: CurrencyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ currencyExchangeRate }) => {
      this.updateForm(currencyExchangeRate);
    });
    this.currencyService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICurrency[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICurrency[]>) => response.body)
      )
      .subscribe((res: ICurrency[]) => (this.currencies = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(currencyExchangeRate: ICurrencyExchangeRate) {
    this.editForm.patchValue({
      id: currencyExchangeRate.id,
      exchangeRate: currencyExchangeRate.exchangeRate,
      currencyId: currencyExchangeRate.currencyId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const currencyExchangeRate = this.createFromForm();
    if (currencyExchangeRate.id !== undefined) {
      this.subscribeToSaveResponse(this.currencyExchangeRateService.update(currencyExchangeRate));
    } else {
      this.subscribeToSaveResponse(this.currencyExchangeRateService.create(currencyExchangeRate));
    }
  }

  private createFromForm(): ICurrencyExchangeRate {
    return {
      ...new CurrencyExchangeRate(),
      id: this.editForm.get(['id']).value,
      exchangeRate: this.editForm.get(['exchangeRate']).value,
      currencyId: this.editForm.get(['currencyId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrencyExchangeRate>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCurrencyById(index: number, item: ICurrency) {
    return item.id;
  }
}
