/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Route} from "@angular/router";
import {constantes} from "../../core/constantes";
import {InicioComponent} from "./inicio.component";

export const InicioRoute: Route[] = [
  {
    path: constantes.paths.academico.inicio.basePath,
    component: InicioComponent
  }
];
