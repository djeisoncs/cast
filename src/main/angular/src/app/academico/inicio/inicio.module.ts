import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioComponent } from './inicio.component';
import {BaseModule} from "../../core/base/base.module";

@NgModule({
  imports: [
    CommonModule,
    BaseModule
  ],
  declarations: [InicioComponent]
})
export class InicioModule { }
