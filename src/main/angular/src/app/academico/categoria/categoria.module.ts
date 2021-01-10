import { NgModule } from '@angular/core';

import { CategoriaRoutingModule } from './categoria-routing.module';
import { CategoriaListarComponent } from './listar/categoria-listar.component';
import { CategoriaCadastroComponent } from './cadastro/categoria-cadastro.component';
import {BaseModule} from '../../core/base/base.module';
import {CommonModule} from '@angular/common';
import {LayoutModule} from '../../compartilhado/layout/layout.module';


@NgModule({
  declarations: [CategoriaListarComponent, CategoriaCadastroComponent],
  imports: [
    CommonModule,
    BaseModule,
    CategoriaRoutingModule,
    LayoutModule
  ]
})
export class CategoriaModule { }
