import { Injectable } from '@angular/core';
import {BaseCrudService} from "../../core/base-crud.service";
import {Curso} from "./curso";
import {HttpClient} from "@angular/common/http";
import {constantes} from "../../core/constantes";

@Injectable({
  providedIn: 'root'
})
export class CursoService extends BaseCrudService<Curso> {

  constructor(
    http: HttpClient
  ) {
    super(http);
  }

  protected getPathEndPoint(): string {
    return constantes.paths.academico.curso.basePath;
  }
}
