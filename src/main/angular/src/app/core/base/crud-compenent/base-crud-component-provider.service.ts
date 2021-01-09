/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {BaseComponentProviderService} from "../component/base-component-provider.service";

export class BaseCrudComponentProviderService {

  constructor(
    private baseComponentProviderService:BaseComponentProviderService
  ){}

  public getBaseComponentProviderService():BaseComponentProviderService{
    return this.baseComponentProviderService;
  }
}
