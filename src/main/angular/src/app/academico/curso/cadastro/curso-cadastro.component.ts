import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-curso-cadastro',
  templateUrl: './curso-cadastro.component.html'
})
export class CursoCadastroComponent implements OnInit {

  currentAction: string;
  form: FormGroup;
  pageTitle: string;

  constructor() { }

  ngOnInit() {
  }

}
