import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout-content',
  templateUrl: './layout-content.component.html'
})
export class LayoutContentComponent implements OnInit {

  exibirBreadcumb: boolean = false;

  constructor() { }

  ngOnInit() {
  }

}
