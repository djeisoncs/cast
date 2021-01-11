import { Injectable } from '@angular/core';
import {BaseCrudService} from "../../core/base-crud.service";
import {Categoria} from "./categoria";
import {HttpClient} from "@angular/common/http";
import {constantes} from "../../core/constantes";

@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends BaseCrudService<Categoria>{

  constructor(
    http: HttpClient
  ) {
    super(http);
  }

  protected getPathEndPoint(): string {
    return constantes.paths.academico.categoria.basePath;
  }
}
