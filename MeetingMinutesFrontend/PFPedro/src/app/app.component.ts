import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'PFPedro';

  constructor(public api: ApiService,
              private router: Router) { }


  logout() {
    this.api.logOut();
    this.router.navigate(['login']);
  }

}
