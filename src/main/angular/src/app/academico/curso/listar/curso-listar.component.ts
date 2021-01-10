import { Component, OnInit } from '@angular/core';
import {CursoBaseComponent} from "../curso-base.component";
import {BaseCrudComponentProviderService} from "../../../core/base/crud-compenent/base-crud-component-provider.service";
import {CursoService} from "../curso.service";
import {Curso} from "../curso";

@Component({
  selector: 'app-listar',
  templateUrl: './curso-listar.component.html'
})
export class CursoListarComponent extends CursoBaseComponent {

  entidades: Curso[] = [];

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CursoService
  ) {
    super(crudProvider, service);
  }

  ngOnInit() {
  }

}
