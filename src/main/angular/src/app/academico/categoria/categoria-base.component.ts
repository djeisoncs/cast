/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Categoria} from "./categoria";
import {BaseCrudComponent} from "../../core/base/crud-compenent/base-crud.component";
import {BaseCrudComponentProviderService} from "../../core/base/crud-compenent/base-crud-component-provider.service";
import {CategoriaService} from "./categoria.service";
import {BaseCrudService} from "../../core/base-crud.service";

export abstract class CategoriaBaseComponent extends BaseCrudComponent<Categoria>{

  entidade: Categoria;

  protected constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CategoriaService
  ) {
    super(crudProvider);
  }

  getCRUDService(): BaseCrudService<Categoria> {
    return this.service;
  }

  getEntidade(): Categoria {
    return this.entidade;
  }

  setEntidade(entidade: Categoria): void {
    this.entidade = entidade;
  }
}
