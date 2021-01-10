import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RouterModule} from "@angular/router";
import {ComponentesModule} from '../componentes/componentes.module';



@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    ComponentesModule
  ],

  declarations: [],

  exports: [
    HttpClientModule,
    ComponentesModule
  ]
})
export class BaseModule { }
