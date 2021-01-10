import { NgModule } from '@angular/core';
import { LayoutOperacaoComponent } from './operacao/layout-operacao.component';
import { TopBarComponent } from './operacao/top-bar/top-bar.component';
import {BaseModule} from "../../core/base/base.module";
import {LayoutContentComponent} from "./componentes/layout-content/layout-content.component";
import { BreadcrmbMenuComponent } from './componentes/breadcrmb-menu/breadcrmb-menu.component';
import {RouterModule} from "@angular/router";
import {InicioModule} from "../../academico/inicio/inicio.module";
import {CommonModule} from '@angular/common';

/**
 * Created by djeison.cassimiro on 09/01/2021
 */
@NgModule({
  declarations: [
    LayoutOperacaoComponent,
    TopBarComponent,
    LayoutContentComponent,
    BreadcrmbMenuComponent,

  ],
  imports: [
    BaseModule,
    RouterModule,
    InicioModule,
    CommonModule
  ],
  exports: [
    LayoutOperacaoComponent,
    BreadcrmbMenuComponent,
  ]
})
export class LayoutModule { }
