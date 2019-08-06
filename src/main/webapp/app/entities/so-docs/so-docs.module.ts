import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared';
import {
  SoDocsComponent,
  SoDocsDetailComponent,
  SoDocsUpdateComponent,
  SoDocsDeletePopupComponent,
  SoDocsDeleteDialogComponent,
  soDocsRoute,
  soDocsPopupRoute
} from './';

const ENTITY_STATES = [...soDocsRoute, ...soDocsPopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [SoDocsComponent, SoDocsDetailComponent, SoDocsUpdateComponent, SoDocsDeleteDialogComponent, SoDocsDeletePopupComponent],
  entryComponents: [SoDocsComponent, SoDocsUpdateComponent, SoDocsDeleteDialogComponent, SoDocsDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshipperSoDocsModule {}
