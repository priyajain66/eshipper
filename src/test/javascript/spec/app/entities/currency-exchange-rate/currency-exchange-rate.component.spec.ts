/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { CurrencyExchangeRateComponent } from 'app/entities/currency-exchange-rate/currency-exchange-rate.component';
import { CurrencyExchangeRateService } from 'app/entities/currency-exchange-rate/currency-exchange-rate.service';
import { CurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

describe('Component Tests', () => {
  describe('CurrencyExchangeRate Management Component', () => {
    let comp: CurrencyExchangeRateComponent;
    let fixture: ComponentFixture<CurrencyExchangeRateComponent>;
    let service: CurrencyExchangeRateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CurrencyExchangeRateComponent],
        providers: []
      })
        .overrideTemplate(CurrencyExchangeRateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CurrencyExchangeRateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrencyExchangeRateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CurrencyExchangeRate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.currencyExchangeRates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
