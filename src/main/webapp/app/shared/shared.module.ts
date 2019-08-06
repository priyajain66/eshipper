import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { EshipperSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [EshipperSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [EshipperSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshipperSharedModule {
  static forRoot() {
    return {
      ngModule: EshipperSharedModule
    };
  }
}
