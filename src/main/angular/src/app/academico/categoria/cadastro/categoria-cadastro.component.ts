import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {CategoriaBaseComponent} from "../categoria-base.component";
import {BaseCrudComponentProviderService} from "../../../core/base/crud-compenent/base-crud-component-provider.service";
import {CategoriaService} from "../categoria.service";

@Component({
  selector: 'app-categoria-cadastro',
  templateUrl: './categoria-cadastro.component.html'
})
export class CategoriaCadastroComponent extends CategoriaBaseComponent {

  currentAction: string;
  form: FormGroup;
  pageTitle: string;

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CategoriaService
  ) {
    super(crudProvider, service);
  }

  ngOnInit() {
  }

}
