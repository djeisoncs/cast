import { Component, OnInit } from '@angular/core';
import {constantes} from "../../../../core/constantes";

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit {

  routeCategoria: string = constantes.paths.academico.categoria.listar;
  routeCurso: string = constantes.paths.academico.curso.listar;

  constructor() { }

  ngOnInit() {
  }

}
