import { Component, ChangeDetectorRef} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../auth';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  name: string = '';
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private auth: Auth,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) { }

  register() {
    const userData = {
      name: this.name,
      email: this.email,
      password: this.password
    };


    this.auth.register(userData).subscribe({
      next: (response) => {
        this.successMessage = 'Registration successful! Redirecting to loginpage....';
        this.cdr.detectChanges();
        console.log(response);
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        if (error.status === 409) {
          this.errorMessage = 'Email already exists. Please use another email.';
        } else if (error.status === 400) {
          this.errorMessage = 'Please enter valid details.';
        } else {
          this.errorMessage = 'Registration failed. Please try again.';
        }
      }
    });
  }
}