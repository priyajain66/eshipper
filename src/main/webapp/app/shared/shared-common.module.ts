import { NgModule } from '@angular/core';

import { EshipperSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [EshipperSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [EshipperSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class EshipperSharedCommonModule {}
