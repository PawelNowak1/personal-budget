import { Injectable } from '@angular/core';

@Injectable()
export class AppInfoService {
  constructor() {}

  public get title(): string {
    return 'Budżet domowy';
  }
}
