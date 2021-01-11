/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Entidade} from "../../core/model/Entidade";
import {Curso} from "../curso/curso";

export class Categoria extends Entidade{

  codigo: number;

  nome: string;

  cursos: Curso[] = [];
}
