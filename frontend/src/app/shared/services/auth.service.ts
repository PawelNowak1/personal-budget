import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from './http-helper';
import {map} from 'rxjs/operators';

const defaultPath = '/home';

@Injectable()
export class AuthService {
  private _loggedIn = false;
  get loggedIn(): boolean {
    return this._loggedIn;
  }

  private _lastAuthenticatedPath: string = defaultPath;
  set lastAuthenticatedPath(value: string) {
    this._lastAuthenticatedPath = value;
  }

  constructor(private router: Router, private http: HttpClient) {
    if (localStorage.getItem('id_token')) {
      this._loggedIn = true;
    }
  }

  logIn(login: string, password: string) {
    return this.http.post(`${HttpHelper.baseURL}/login`, {username: login, password})
      .pipe(map((result: any) => {
        localStorage.setItem('id_token', result.token);
        this._loggedIn = true;
        return result;
      }));
  }

  createAccount(username: string, email: string, password: string) {
    return this.http.post(`${HttpHelper.baseURL}/registration`, {username, password, email})
      .pipe(map((result: any) => {
        localStorage.setItem('id_token', result.token);
        this._loggedIn = true;
        return result;
      }));
  }

  logOut() {
    this._loggedIn = false;
    this.router.navigate(['/login']);
  }
}

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private router: Router, private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const isLoggedIn = this.authService.loggedIn;
    const isLoginForm = route.routeConfig.path === 'login';
    const isCreateAccForm = route.routeConfig.path === 'createAccount';

    if (isLoggedIn && (isLoginForm || isCreateAccForm)) {
      this.authService.lastAuthenticatedPath = defaultPath;
      this.router.navigate([defaultPath]);
      return false;
    }

    if (!isLoggedIn && isCreateAccForm) {
      return true;
    }

    if (!isLoggedIn && !isLoginForm) {
      this.router.navigate(['/login']);
    }

    if (isLoggedIn) {
      this.authService.lastAuthenticatedPath = route.routeConfig.path;
    }

    return isLoggedIn || isLoginForm;
  }
}
