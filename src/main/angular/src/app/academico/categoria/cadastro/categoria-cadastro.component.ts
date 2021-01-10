import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-categoria-cadastro',
  templateUrl: './categoria-cadastro.component.html'
})
export class CategoriaCadastroComponent implements OnInit {

  currentAction: string;
  form: FormGroup;
  pageTitle: string;

  constructor() { }

  ngOnInit() {
  }

}
