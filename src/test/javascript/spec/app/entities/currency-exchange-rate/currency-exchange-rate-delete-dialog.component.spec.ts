/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { CurrencyExchangeRateDeleteDialogComponent } from 'app/entities/currency-exchange-rate/currency-exchange-rate-delete-dialog.component';
import { CurrencyExchangeRateService } from 'app/entities/currency-exchange-rate/currency-exchange-rate.service';

describe('Component Tests', () => {
  describe('CurrencyExchangeRate Management Delete Component', () => {
    let comp: CurrencyExchangeRateDeleteDialogComponent;
    let fixture: ComponentFixture<CurrencyExchangeRateDeleteDialogComponent>;
    let service: CurrencyExchangeRateService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CurrencyExchangeRateDeleteDialogComponent]
      })
        .overrideTemplate(CurrencyExchangeRateDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CurrencyExchangeRateDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrencyExchangeRateService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
