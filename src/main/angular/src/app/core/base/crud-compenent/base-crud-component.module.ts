import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {BaseCrudComponentProviderService} from "./base-crud-component-provider.service";

/**
 * Created by djeison.cassimiro on 09/01/2021
 */
@NgModule({
  imports: [
    CommonModule
  ],

  declarations: [],

  providers: [
    BaseCrudComponentProviderService
  ],

  exports: []
})
export class BaseCrudComponentModule { }
