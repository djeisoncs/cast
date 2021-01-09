import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutOperacaoComponent } from './operacao/layout-operacao.component';
import { TopBarComponent } from './operacao/top-bar/top-bar.component';
import {BaseModule} from "../../core/base/base.module";
import {LayoutContentComponent} from "./componentes/layout-content/layout-content.component";
import { BreadcrmbMenuComponent } from './componentes/breadcrmb-menu/breadcrmb-menu.component';

/**
 * Created by djeison.cassimiro on 09/01/2021
 */
@NgModule({
  declarations: [
    LayoutOperacaoComponent,
    TopBarComponent,
    LayoutContentComponent,
    BreadcrmbMenuComponent
  ],
  imports: [
    CommonModule,
    BaseModule
  ],
  exports: [
    LayoutOperacaoComponent
  ]
})
export class LayoutModule { }
