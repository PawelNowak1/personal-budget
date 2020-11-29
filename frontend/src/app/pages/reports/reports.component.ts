import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { formatMessage } from 'devextreme/localization';
import {OperationService} from '../../shared/services/operation.service';
import {DxChartComponent, DxPivotGridComponent} from 'devextreme-angular';
import { CurrencyPipe } from '@angular/common';

@Component({
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css'],
  providers: [CurrencyPipe]
})
export class ReportsComponent implements AfterViewInit {
  @ViewChild(DxPivotGridComponent, { static: false }) pivotGrid: DxPivotGridComponent;
  @ViewChild(DxChartComponent, { static: false }) chart: DxChartComponent;
  operations: any;
  dataSource: any;
  formatMessage = formatMessage;

  constructor(private operationService: OperationService,
              private currencyPipe: CurrencyPipe) {
    this.customizeTooltip = this.customizeTooltip.bind(this);
    this.operationService.getOperations().subscribe((operation) => {
      this.dataSource = {
        fields: [{
          caption: 'Category',
          width: 120,
          dataField: 'category.parent.title',
          area: 'row'
        }, {
          caption: 'SubCategory',
          width: 120,
          dataField: 'category.title',
          area: 'row'
        }, {
          dataField: 'operationDate',
          dataType: 'date',
          area: 'column'
        }, {
          caption: 'Sales',
          dataField: 'amount',
          dataType: 'number',
          summaryType: 'sum',
          format: {type: 'currency', precision: 2},
          area: 'data'
        }],
        store: operation
      };
    });
  }

  ngAfterViewInit() {
    this.pivotGrid.instance.bindChart(this.chart.instance, {
      dataFieldsDisplayMode: 'splitPanes',
      alternateDataFields: false
    });
  }

  customizeTooltip(args) {
    const valueText = args.originalValue.toFixed(2);

    return {
      html: args.seriesName + '<div class=\'currency\'>' + valueText + ' zł</div>'
    };
  }
}
