/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {Entidade} from "./model/Entidade";
import {BaseHttpService} from "./base-http.service";
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {Observable} from "rxjs";
import {RespostaRequisicao} from "./model/RespostaRequisicao";

export abstract class BaseCrudService<E extends Entidade> extends BaseHttpService {

  protected constructor(
    protected http: HttpClient
  ) {
    super();
  }

  public salvar(entidade: E): Observable<RespostaRequisicao<E>> {
    return this.http.post(this.getUrlEndPoint(), entidade).pipe(tap(resposta => this.mapper(resposta)));
  }

  public editar(entidade: E): Observable<RespostaRequisicao<E>> {
    return this.http.put(this.getUrlEndPoint(), entidade).pipe(tap(resposta => this.mapper(resposta)));
  }

  public consultar(entidade: E): Observable<E> {
    return this.http.get<E>(`${this.getUrlEndPoint()}/${entidade.id}`).pipe(tap(resposta => this.mapper(resposta)));
  }

  public listar(): Observable<RespostaRequisicao<E>> {
    return this.http.get<RespostaRequisicao<E>>(`${this.getUrlEndPoint()}/listar`).pipe(tap(resposta => this.mapper(resposta)));
  }

  public excluir(entidade: E): Observable<RespostaRequisicao<E>> {
    return this.http.delete(`${this.getUrlEndPoint()}/${entidade.id}`).pipe(tap(resposta => this.mapper(resposta)));
  }
}
