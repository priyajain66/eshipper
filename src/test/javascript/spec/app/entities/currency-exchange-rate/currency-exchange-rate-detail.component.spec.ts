/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CurrencyExchangeRateDetailComponent } from 'app/entities/currency-exchange-rate/currency-exchange-rate-detail.component';
import { CurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';

describe('Component Tests', () => {
  describe('CurrencyExchangeRate Management Detail Component', () => {
    let comp: CurrencyExchangeRateDetailComponent;
    let fixture: ComponentFixture<CurrencyExchangeRateDetailComponent>;
    const route = ({ data: of({ currencyExchangeRate: new CurrencyExchangeRate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CurrencyExchangeRateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CurrencyExchangeRateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CurrencyExchangeRateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.currencyExchangeRate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
