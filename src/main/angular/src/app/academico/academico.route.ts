/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Route} from "@angular/router";
import {CategoriaRoute} from "./categoria/categoria.route";
import {CursoRoute} from "./curso/curso.route";

export const AcademicoRoute: Route[] = [
  ...CategoriaRoute,
  ...CursoRoute
];
