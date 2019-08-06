import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared';
import {
  DocsTypeComponent,
  DocsTypeDetailComponent,
  DocsTypeUpdateComponent,
  DocsTypeDeletePopupComponent,
  DocsTypeDeleteDialogComponent,
  docsTypeRoute,
  docsTypePopupRoute
} from './';

const ENTITY_STATES = [...docsTypeRoute, ...docsTypePopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DocsTypeComponent,
    DocsTypeDetailComponent,
    DocsTypeUpdateComponent,
    DocsTypeDeleteDialogComponent,
    DocsTypeDeletePopupComponent
  ],
  entryComponents: [DocsTypeComponent, DocsTypeUpdateComponent, DocsTypeDeleteDialogComponent, DocsTypeDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshipperDocsTypeModule {}
