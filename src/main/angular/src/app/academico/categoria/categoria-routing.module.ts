import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoriaListarComponent} from './listar/categoria-listar.component';
import {CategoriaCadastroComponent} from './cadastro/categoria-cadastro.component';


const routes: Routes = [
  {path: '', component: CategoriaListarComponent},
  {path: 'cadastrar', component: CategoriaCadastroComponent},
  {path: 'editar/:id', component: CategoriaCadastroComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
