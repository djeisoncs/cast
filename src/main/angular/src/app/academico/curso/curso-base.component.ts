/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {BaseCrudComponent} from "../../core/base/crud-compenent/base-crud.component";
import {Curso} from "./curso";
import {BaseCrudComponentProviderService} from "../../core/base/crud-compenent/base-crud-component-provider.service";
import {CursoService} from "./curso.service";
import {BaseCrudService} from "../../core/base-crud.service";

export class CursoBaseComponent extends BaseCrudComponent<Curso> {

  entidade: Curso;

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CursoService
  ){
    super(crudProvider);
  }

  getCRUDService(): BaseCrudService<Curso> {
    return this.service;
  }

  getEntidade(): Curso {
    return this.entidade;
  }

  setEntidade(entidade: Curso): void {
    this.entidade = entidade;
  }

}
