/**
 * Created by djeison.cassimiro on 10/01/2021
 */
import {Entidade} from "../../core/model/Entidade";
import {Categoria} from "../categoria/categoria";

export class Curso extends Entidade {

  nome: string;

  dataInicio: Date;

  dataTermino: Date;

  qtdAlunosTurma: number;

  categoria: Categoria;
}
