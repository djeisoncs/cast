import { Component, OnInit } from '@angular/core';
import {CursoBaseComponent} from "../curso-base.component";
import {BaseCrudComponentProviderService} from "../../../core/base/crud-compenent/base-crud-component-provider.service";
import {CursoService} from "../curso.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-cadatro',
  templateUrl: './curso-cadatro.component.html'
})
export class CursoCadatroComponent extends CursoBaseComponent {

  formGroup: FormGroup;

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CursoService
  ) {
    super(crudProvider, service);
  }

  ngOnInit() {
  }

}
