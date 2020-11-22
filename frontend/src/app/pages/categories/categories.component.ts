import { Component } from '@angular/core';
import {CategoriesService} from './categories.service';

@Component({
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {

  constructor(private categoriesService: CategoriesService) {
    this.categoriesService.getCategoryList().subscribe((result) => console.log(result));
  }

}
