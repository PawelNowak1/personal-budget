import { Component } from '@angular/core';
import {CategoriesService} from './categories.service';
import {formatMessage} from 'devextreme/localization';

@Component({
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  formatMessage = formatMessage;
  categories: any;

  constructor(private categoriesService: CategoriesService) {
    this.categoriesService.getCategoryList().subscribe((result) => this.categories = result);
  }

  onReorder(e) {
  }

  refreshGrid() {
    this.categoriesService.getCategoryList().subscribe((result) => this.categories = result);
  }
}
