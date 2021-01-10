/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {Entidade} from "../../model/Entidade";
import {BaseComponent} from "../component/base.component";
import {BaseCrudService} from "../../base-crud.service";
import {BaseCrudComponentProviderService} from "./base-crud-component-provider.service";
import {LazyLoadEvent} from "primeng/api";
import {RespostaRequisicao} from "../../model/RespostaRequisicao";
import {Observable} from "rxjs";
import {constantes} from "../../constantes";

export abstract class BaseCrudComponent<T extends Entidade> extends BaseComponent {

  protected constructor(
    private crudProvider: BaseCrudComponentProviderService
  ) {
    super(crudProvider.getBaseComponentProviderService());
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  public consultar(entidade: T, callback: (entidade: T) => void = () => {}) {
    if (entidade.id) {
      this.getCRUDService().consultar(entidade).subscribe((entidade: T) => {
        this.setEntidade(entidade)
        callback(entidade);
      });
    }
  }

  public listar(event: LazyLoadEvent, callBack: (resposta: RespostaRequisicao<T>) => void = () => {}): void {

    this.getCRUDService().listar().subscribe((resposta: RespostaRequisicao<T>) => {
      callBack(resposta);
    })
  }

  public salvar(entidade: T = this.getEntidade(), callBack: (resposta: RespostaRequisicao<T>) => void = () => {}): void {
    let requisicao: Observable<RespostaRequisicao<T>>;

    if (entidade.id) {
      requisicao = this.getCRUDService().editar(entidade);
    } else {
      requisicao = this.getCRUDService().salvar(entidade);
    }

    requisicao.subscribe((resposta: RespostaRequisicao<T>) => {
      this.setEntidade(resposta.entidade);
      callBack(resposta);
    })
  }

  public excluir(entidade: T = this.getEntidade(), callback: () => void): void {
    this.confirmar({
      message: `Você tem certeza que deseja excluir a entidade?`,
      accept: () => {
        this.getCRUDService().excluir(entidade).subscribe(() => {
          if (callback) {
            callback();
          } else {
            this.router.navigate([this.getPathListagem()]);
          }
        });
      }
    })
  }

  public voltar(): void {
    this.router.navigate([this.getPathListagem()])
  }

  public getPathListagem(paths: any = constantes.paths): string {
    /**
     * TODO implementar o retorno do path da pagina de listagem posteriormente
     */
    return
  }


  public abstract getCRUDService(): BaseCrudService<T>;

  public abstract getEntidade(): T;

  public abstract setEntidade(entidade: T): void;
}
