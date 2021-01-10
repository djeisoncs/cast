import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriaListarComponent } from './listar/categoria-listar.component';
import { CategoriaCadastroComponent } from './cadastro/categoria-cadastro.component';



@NgModule({
  declarations: [CategoriaListarComponent, CategoriaCadastroComponent],
  imports: [
    CommonModule
  ]
})
export class CategoriaModule { }
