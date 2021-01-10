import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CursoListarComponent } from './listar/curso-listar.component';
import { CursoCadatroComponent } from './cadatro/curso-cadatro.component';



@NgModule({
  declarations: [CursoListarComponent, CursoCadatroComponent],
  imports: [
    CommonModule
  ]
})
export class CursoModule { }
