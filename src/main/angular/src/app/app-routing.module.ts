import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AcademicoRoute} from "./academico/academico.route";
import {InicioRoute} from "./academico/inicio/inicio.routes";
import {InicioComponent} from "./academico/inicio/inicio.component";


export const routes: Routes = [
  ...InicioRoute,
  {
    path: '',
    children: [
      {path: '', component: InicioComponent},
      ...AcademicoRoute
    ]
  }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes, {useHash: true})]
})
export class AppRoutingModule { }
