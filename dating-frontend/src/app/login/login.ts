import { Component, ChangeDetectorRef  } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../auth';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';



@Component({
  imports: [FormsModule, RouterLink, CommonModule],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',

})
export class Login {
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  isSubmitting: boolean = false;

  constructor(private auth: Auth,
     private router: Router,
     private cdr: ChangeDetectorRef
  ) {
  }

  login() {
      this.errorMessage = '';
       this.isSubmitting = true;


    this.auth.login(this.email, this.password)
      .subscribe({next:response => {

        console.log('Login successful');
        console.log(response);
        localStorage.setItem('user', JSON.stringify(response));
        this.router.navigate(['/home']);
        
      },
     error: (error) => {
        if (error.status === 401) {
          this.errorMessage = 'Invalid email or password.';
        } else {
          this.errorMessage = 'Login failed. Please try again.';
        }
         this.isSubmitting = false;
         this.cdr.detectChanges();
      }
  });
  }
}
