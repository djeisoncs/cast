import { Component } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {BaseCrudComponentProviderService} from "../../../core/base/crud-compenent/base-crud-component-provider.service";
import {CursoService} from "../curso.service";
import {CursoBaseComponent} from "../curso-base.component";

@Component({
  selector: 'app-curso-cadastro',
  templateUrl: './curso-cadastro.component.html'
})
export class CursoCadastroComponent extends CursoBaseComponent {

  currentAction: string;
  form: FormGroup;
  pageTitle: string;

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CursoService
  ) {
    super(crudProvider, service);
  }

  ngOnInit() {
  }

}
