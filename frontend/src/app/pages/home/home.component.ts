import { Component } from '@angular/core';
import {HomeService} from './home.service';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  {

  constructor(private homeService: HomeService) {
  }
}
