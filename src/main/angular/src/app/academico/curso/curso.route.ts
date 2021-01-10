/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Route} from "@angular/router";
import {constantes} from "../../core/constantes";
import {CursoListarComponent} from "./listar/curso-listar.component";
import {CursoCadatroComponent} from "./cadatro/curso-cadatro.component";

export const CursoRoute: Route[] = [
  {
    path: constantes.paths.academico.curso.listar,
    component: CursoListarComponent
  },
  {
    path: constantes.paths.academico.curso.cadastrar,
    component: CursoCadatroComponent
  },
  {
    path: constantes.paths.academico.curso.editar,
    component: CursoCadatroComponent
  },
  {
    path: constantes.paths.academico.curso.consultar,
    component: CursoCadatroComponent
  },
];
