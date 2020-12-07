import { Component } from '@angular/core';
import {CategoriesService} from './categories.service';
import {formatMessage} from 'devextreme/localization';
import notify from 'devextreme/ui/notify';

@Component({
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  formatMessage = formatMessage;
  categories: any;
  categoryTypes = [
    {
      id: 'income',
      typeName: 'Przychód'
    },
    {
      id: 'expense',
      typeName: 'Wydatek'
    }
  ];

  constructor(private categoriesService: CategoriesService) {
    this.categoriesService.getCategoryList().subscribe((result) => this.categories = result);
  }

  onReorder(e) {
  }

  refreshGrid() {
    this.categoriesService.getCategoryList().subscribe((result) => this.categories = result);
  }

  removeCategory(event) {
    let result = this.categoriesService.deleteCategory(event.data.categoryId).toPromise();
    event.cancel = new Promise((resolve, reject) => {
      result.then(() => {
        notify({
          message: 'Operacja usunięta!',
          type: 'info',
          displayTime: 4000,
          position: 'top'
        });
        resolve();
      })
        .catch(() => {
          reject('Nie można usunąć kategorii, która ma istniejące operacje!');
        });
    });
  }
}
