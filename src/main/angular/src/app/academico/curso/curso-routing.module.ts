import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CursoListarComponent} from './listar/curso-listar.component';
import {CursoCadastroComponent} from './cadastro/curso-cadastro.component';


const routes: Routes = [
  {path: '', component: CursoListarComponent},
  {path: 'cadastrar', component: CursoCadastroComponent},
  {path: 'editar/:id', component: CursoCadastroComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CursoRoutingModule { }
