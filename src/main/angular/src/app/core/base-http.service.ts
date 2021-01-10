/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {environment} from "../../environments/environment";
import {Util} from "./util/util";

export abstract class BaseHttpService {

  protected mapper(resp:any):any{
    try {
      //noinspection TypeScriptValidateJSTypes
      return (resp.json() || resp.text());

    }catch (error){

      return resp;
    }
  }

  public getUrlEndPoint():string{
    let pathEndpoint = this.getPathEndPoint();

    let url:string = pathEndpoint && pathEndpoint.length > 0 ?`${this.getUrlContext()}/${pathEndpoint}` : `${this.getUrlContext()}`;

    return Util.removerBarrasDuplicadasDaUrl(url);
  }

  protected getUrlContext():string{
    return environment.contexto.api;
  }

  protected abstract getPathEndPoint():string;
}
