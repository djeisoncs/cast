import { Component } from '@angular/core';
import {CategoriaBaseComponent} from "../categoria-base.component";
import {Categoria} from "../categoria";
import {BaseCrudComponentProviderService} from "../../../core/base/crud-compenent/base-crud-component-provider.service";
import {CategoriaService} from "../categoria.service";

@Component({
  selector: 'app-listar',
  templateUrl: './categoria-listar.component.html'
})
export class CategoriaListarComponent extends CategoriaBaseComponent {

  entidades: Categoria[] = [];

  constructor(
    crudProvider: BaseCrudComponentProviderService,
    protected service: CategoriaService
  ) {
    super(crudProvider, service);
  }

  ngOnInit() {
  }

}
