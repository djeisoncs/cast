import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AcademicoRoute} from "./academico/academico.route";


export const routes: Routes = [
  {
    path: '',
    children: [
      ...AcademicoRoute
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
