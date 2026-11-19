
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  private apiUrl = `http://${window.location.hostname}:8080/api/auth`;

  constructor(private http: HttpClient) {}

  login(email: string, password: string) {

    const loginData = {
      email: email,
      password: password
    };

    return this.http.post(`${this.apiUrl}/login`, loginData);
  }

  register(userData: {
    name: string;
    email: string;
    password: string;
  }) {
    return this.http.post(
      this.apiUrl.replace('/auth', '/users'),
      userData
    );
  }
}
