import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout-operacao',
  templateUrl: './layout-operacao.component.html',
  styleUrls: ['./layout-operacao.component.scss']
})
export class LayoutOperacaoComponent implements OnInit {

  sidebarActive: boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
