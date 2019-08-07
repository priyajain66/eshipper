import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

type EntityResponseType = HttpResponse<ICurrencyExchangeRate>;
type EntityArrayResponseType = HttpResponse<ICurrencyExchangeRate[]>;

@Injectable({ providedIn: 'root' })
export class CurrencyExchangeRateService {
  public resourceUrl = SERVER_API_URL + 'api/currency-exchange-rates';

  constructor(protected http: HttpClient) {}

  create(currencyExchangeRate: ICurrencyExchangeRate): Observable<EntityResponseType> {
    return this.http.post<ICurrencyExchangeRate>(this.resourceUrl, currencyExchangeRate, { observe: 'response' });
  }

  update(currencyExchangeRate: ICurrencyExchangeRate): Observable<EntityResponseType> {
    return this.http.put<ICurrencyExchangeRate>(this.resourceUrl, currencyExchangeRate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICurrencyExchangeRate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICurrencyExchangeRate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
