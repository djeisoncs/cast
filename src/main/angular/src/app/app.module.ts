import { BrowserModule } from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BaseModule} from "./core/base/base.module";
import {LayoutModule} from "./compartilhado/layout/layout.module";

@NgModule({
  declarations: [
    AppComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BaseModule,

    LayoutModule
  ],

  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR'
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
