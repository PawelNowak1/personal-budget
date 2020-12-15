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
    this.operationService.getOperations().subscribe((operation: any) => {
      operation.forEach((op) => {
        if (op.category.parent.type === 'expense') {
          op.amount *= -1;
        }
      });
      this.dataSource = {
        fields: [{
          caption: 'Kategoria główna',
          width: 120,
          dataField: 'category.parent.title',
          area: 'row',
          displayFolder: 'Kategorie'
        }, {
          caption: 'Podkategoria',
          width: 120,
          dataField: 'category.title',
          area: 'row',
          displayFolder: 'Kategorie'
        }, {
          caption: 'Nazwa',
          width: 120,
          dataField: 'account.name',
          area: 'filter',
          displayFolder: 'Konto'
        }, {
          caption: 'Data (rok)',
          dataField: 'operationDate',
          dataType: 'date',
          area: 'column',
          groupInterval: 'year'
        }, {
          caption: 'Data (kwartał)',
          dataField: 'operationDate',
          dataType: 'date',
          area: 'column',
          groupInterval: 'quarter'
        }, {
          caption: 'Data (miesiąc)',
          dataField: 'operationDate',
          dataType: 'date',
          area: 'column',
          groupInterval: 'month'
        }, {
          caption: 'Kwota operacji',
          dataField: 'amount',
          dataType: 'number',
          format: {type: 'currency', precision: 2},
          area: 'data',
          summaryType: 'sum'
        }],
        store: operation,
        retrieveFields: false
      };
    });
  }

  ngAfterViewInit() {
    this.pivotGrid.instance.bindChart(this.chart.instance, {
      dataFieldsDisplayMode: 'splitPanes',
      alternateDataFields: false,
      processCell: (cellData) => {
        cellData.chartDataItem.val = Math.abs(cellData.chartDataItem.val);
        return cellData; // This line is optional
      }
    });
  }

  customizeTooltip(args) {
    const valueText = args.originalValue.toFixed(2);
    return {
      html: args.seriesName + '<div class=\'currency\'>' + valueText + ' zł</div>'
    };
  }
}
