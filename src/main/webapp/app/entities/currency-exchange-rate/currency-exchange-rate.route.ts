import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';
import { CurrencyExchangeRateService } from './currency-exchange-rate.service';
import { CurrencyExchangeRateComponent } from './currency-exchange-rate.component';
import { CurrencyExchangeRateDetailComponent } from './currency-exchange-rate-detail.component';
import { CurrencyExchangeRateUpdateComponent } from './currency-exchange-rate-update.component';
import { CurrencyExchangeRateDeletePopupComponent } from './currency-exchange-rate-delete-dialog.component';
import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

@Injectable({ providedIn: 'root' })
export class CurrencyExchangeRateResolve implements Resolve<ICurrencyExchangeRate> {
  constructor(private service: CurrencyExchangeRateService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICurrencyExchangeRate> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CurrencyExchangeRate>) => response.ok),
        map((currencyExchangeRate: HttpResponse<CurrencyExchangeRate>) => currencyExchangeRate.body)
      );
    }
    return of(new CurrencyExchangeRate());
  }
}

export const currencyExchangeRateRoute: Routes = [
  {
    path: '',
    component: CurrencyExchangeRateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CurrencyExchangeRates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CurrencyExchangeRateDetailComponent,
    resolve: {
      currencyExchangeRate: CurrencyExchangeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CurrencyExchangeRates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CurrencyExchangeRateUpdateComponent,
    resolve: {
      currencyExchangeRate: CurrencyExchangeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CurrencyExchangeRates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CurrencyExchangeRateUpdateComponent,
    resolve: {
      currencyExchangeRate: CurrencyExchangeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CurrencyExchangeRates'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const currencyExchangeRatePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CurrencyExchangeRateDeletePopupComponent,
    resolve: {
      currencyExchangeRate: CurrencyExchangeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CurrencyExchangeRates'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
