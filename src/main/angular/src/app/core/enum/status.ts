/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {constantes} from "../constantes";

export enum Status {
  ATIVO = "ATIVO",
  INATIVO = "INATIVO"
}

export namespace Status{
  export function getStatus():Status[]{
    return [
      Status.ATIVO,
      Status.INATIVO
    ];
  }

  export function getValue(status:Status){
    let value;

    if(status && <string>status != 'undefined'){
      value = `${constantes.classes.status}.${status}`;
    }

    return value;
  }

  export function getLabel(status:Status){
    let label;

    switch (status){
      case Status.ATIVO:
        label = 'Ativo';
        break;
      default:
        label = 'Inativo';
        break;
    }

    return label;
  }

  export function isAtivo(status:Status){
    return Status[status] === Status.ATIVO;
  }
}
