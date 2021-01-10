/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Route} from "@angular/router";
import {constantes} from "../../core/constantes";
import {CategoriaListarComponent} from "./listar/categoria-listar.component";
import {CategoriaCadastroComponent} from "./cadastro/categoria-cadastro.component";

export const CategoriaRoute: Route[] = [
  {
    path: constantes.paths.academico.categoria.listar,
    component: CategoriaListarComponent
  },
  {
    path: constantes.paths.academico.categoria.cadastrar,
    component: CategoriaCadastroComponent
  },
  {
    path: constantes.paths.academico.categoria.editar,
    component: CategoriaCadastroComponent
  },
  {
    path: constantes.paths.academico.categoria.consultar,
    component: CategoriaCadastroComponent
  },
];
