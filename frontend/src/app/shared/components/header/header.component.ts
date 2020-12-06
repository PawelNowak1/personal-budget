import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuthService} from '../../services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  @Output()
  menuToggle = new EventEmitter<boolean>();

  @Input()
  menuToggleEnabled = false;
  constructor(private authService: AuthService) { }

  toggleMenu = () => {
    this.menuToggle.emit();
  }

  logOut = () => {
    this.authService.logOut();
  }
}
