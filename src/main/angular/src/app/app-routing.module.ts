import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: 'categoria', loadChildren: () => import('./academico/categoria/categoria.module').then(m => m.CategoriaModule)},
  {path: 'curso', loadChildren: () => import('./academico/curso/curso.module').then(m => m.CursoModule)},
  {path: 'inicio', loadChildren: () => import('./academico/inicio/inicio.module').then(m => m.InicioModule)},

  {path: '', redirectTo: '/inicio', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
