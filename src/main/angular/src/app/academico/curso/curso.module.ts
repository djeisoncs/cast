import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursoRoutingModule } from './curso-routing.module';
import { CursoListarComponent } from './listar/curso-listar.component';
import { CursoCadastroComponent } from './cadastro/curso-cadastro.component';
import {BaseModule} from "../../core/base/base.module";
import {LayoutModule} from "../../compartilhado/layout/layout.module";


@NgModule({
  declarations: [CursoListarComponent, CursoCadastroComponent],
  imports: [
    CommonModule,
    CursoRoutingModule,
    BaseModule,
    LayoutModule
  ]
})
export class CursoModule { }
