import {Component, Input, OnInit} from '@angular/core';

interface Breadcrmb {
  text: string;
  link?: string;

}

@Component({
  selector: 'app-breadcrmb-menu',
  templateUrl: './breadcrmb-menu.component.html'
})
export class BreadcrmbMenuComponent implements OnInit {

  @Input() itens: Array<Breadcrmb> = [];

  constructor() { }

  ngOnInit() {
  }

  isTheLastItem(item: Breadcrmb): boolean {
    return this.itens.indexOf(item)+1 == this.itens.length;
  }

}
