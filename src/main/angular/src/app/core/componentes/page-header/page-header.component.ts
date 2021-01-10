import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html'
})
export class PageHeaderComponent implements OnInit {

  @Input() pageTitle: string;
  @Input() buttonClass: string;
  @Input() buttonText: string;
  @Input() buttonLink: string;
  @Input() showButton: boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
