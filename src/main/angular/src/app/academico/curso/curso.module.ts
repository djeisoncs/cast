import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursoRoutingModule } from './curso-routing.module';
import { CursoListarComponent } from './listar/curso-listar.component';
import { CursoCadastroComponent } from './cadastro/curso-cadastro.component';


@NgModule({
  declarations: [CursoListarComponent, CursoCadastroComponent],
  imports: [
    CommonModule,
    CursoRoutingModule
  ]
})
export class CursoModule { }
