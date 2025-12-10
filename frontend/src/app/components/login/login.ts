import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router) { }

  login() {
    this.errorMessage = '';

    if (this.username === 'Carolina' && this.password === '1234') {
      this.router.navigate(['/main']);
    } else {
      this.errorMessage = 'Usuario o contraseña incorrectos';
    }
  }
}
