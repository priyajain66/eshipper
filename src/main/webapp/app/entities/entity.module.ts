import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'docs-type',
        loadChildren: () => import('./docs-type/docs-type.module').then(m => m.EshipperDocsTypeModule)
      },
      {
        path: 'so-docs',
        loadChildren: () => import('./so-docs/so-docs.module').then(m => m.EshipperSoDocsModule)
      },
      {
        path: 'shipping-order',
        loadChildren: () => import('./shipping-order/shipping-order.module').then(m => m.EshipperShippingOrderModule)
      },
      {
        path: 'currency-exchange-rate',
        loadChildren: () => import('./currency-exchange-rate/currency-exchange-rate.module').then(m => m.EshipperCurrencyExchangeRateModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EshipperCurrencyModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshipperEntityModule {}
