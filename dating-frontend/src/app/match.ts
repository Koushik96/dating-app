
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface Match {
  matchId: number;
  userId: number;
  name: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl = `http://${window.location.hostname}:8080/api/matches`;

  constructor(private http: HttpClient) {}

  getMatches(userId: number) {
    return this.http.get<Match[]>(`${this.apiUrl}/${userId}`);
  }
}
