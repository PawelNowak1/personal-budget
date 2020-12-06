import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {formatMessage} from 'devextreme/localization';
import {CategoriesService} from '../../../pages/categories/categories.service';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-add-category-button',
  templateUrl: './add-category-button.component.html',
  styleUrls: ['./add-category-button.component.css']
})
export class AddCategoryButtonComponent implements OnInit {
  @Output() refreshGrid = new EventEmitter();

  formatMessage = formatMessage;
  parentCategories: any;
  visible = false;
  categoryData = {};
  types = [{
    key: 'income',
    display: 'Przychód'
  }, {
    key: 'expense',
    display: 'Wydatek'
  }];
  title = 'Add main category';
  categoryMode = 0; // 0 - kategoria, 1 - podkategoria

  constructor(private categoriesService: CategoriesService) { }

  ngOnInit(): void {
    this.categoriesService.getOnlyParentCategories().subscribe((result) => this.parentCategories = result);
  }

  submit = () => {
    this.categoriesService.createCategory(this.categoryData).subscribe(() => {
      notify({
        message: 'Kategoria dodana!',
        type: 'success',
        displayTime: 4000,
        position: 'top'
      });
      this.visible = false;
      this.refreshGrid.emit();
    });
  }


  showCreateCatPopup() {
    this.title = 'Add main category';
    this.categoryMode = 0;
    this.visible = true;
  }

  showCreateSubcatPopup() {
    this.title = 'Add subcategory';
    this.categoryMode = 1;
    this.visible = true;
  }
}
