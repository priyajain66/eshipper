import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICurrencyExchangeRate } from 'app/shared/model/currency-exchange-rate.model';
import { CurrencyExchangeRateService } from './currency-exchange-rate.service';

@Component({
  selector: 'jhi-currency-exchange-rate-delete-dialog',
  templateUrl: './currency-exchange-rate-delete-dialog.component.html'
})
export class CurrencyExchangeRateDeleteDialogComponent {
  currencyExchangeRate: ICurrencyExchangeRate;

  constructor(
    protected currencyExchangeRateService: CurrencyExchangeRateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.currencyExchangeRateService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'currencyExchangeRateListModification',
        content: 'Deleted an currencyExchangeRate'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-currency-exchange-rate-delete-popup',
  template: ''
})
export class CurrencyExchangeRateDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ currencyExchangeRate }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CurrencyExchangeRateDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.currencyExchangeRate = currencyExchangeRate;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/currency-exchange-rate', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/currency-exchange-rate', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
