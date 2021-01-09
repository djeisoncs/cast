/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {Injectable} from "@angular/core";
import {ConfirmationService} from "primeng/api";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class BaseComponentProviderService {

  constructor(
    private confirmationService:ConfirmationService,
    private router:Router
  ){}

  public getConfirmationService():ConfirmationService{
    return this.confirmationService;
  }

  public getRouter():Router{
    return this.router;
  }
}
