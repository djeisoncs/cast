/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {OnInit} from "@angular/core";
import {Confirmation, ConfirmationService} from "primeng/api";
import {Router} from "@angular/router";
import {constantes} from "../../constantes";
import {BaseComponentProviderService} from "./base-component-provider.service";

export abstract class BaseComponent implements OnInit {

  private confirmationService: ConfirmationService;
  protected router: Router;

  protected constructor(private baseProvider: BaseComponentProviderService){
    this.confirmationService = baseProvider.getConfirmationService();
    this.router = baseProvider.getRouter();
  }

  ngOnInit(): void {
  }

  protected confirmar(confirmation: Confirmation):void{
    if(!confirmation.icon){
      /**
       * TODO implementar depois
       * é necessário configurar o fontawesome
       */
      confirmation.icon = constantes.icones.confirmacao;
    }

    this.confirmationService.confirm(confirmation);
  }

}
