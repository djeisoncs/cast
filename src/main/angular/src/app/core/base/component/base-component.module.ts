/**
 * Created by djeison.cassimiro on 09/01/2021
 */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {BaseComponentProviderService} from "./base-component-provider.service";

@NgModule({
  imports: [
    CommonModule
  ],

  providers: [
    BaseComponentProviderService
  ]
})
export class BaseComponentModule { }
