import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchService, Match } from '../match';

@Component({
  selector: 'app-matches',
  imports: [CommonModule],
  templateUrl: './matches.html',
  styleUrl: './matches.css'
})
export class Matches implements OnInit {

  matches = signal<Match[]>([]);

  constructor(private matchService: MatchService) { }

  ngOnInit() {
    const userData = localStorage.getItem('user');

    if (userData) {
      const currentUser = JSON.parse(userData);

      console.log('Logged-in user:', currentUser);
      console.log('Fetching matches for ID:', Number(currentUser.id));

      this.matchService.getMatches(Number(currentUser.id))
        .subscribe({
          next: (response) => {
            this.matches.set(response);
            console.log('Matches API response:', response);
             console.log('Response length:', response.length);
            console.log('Matches loaded:', response);
          },
          error: (error) => {
            console.error('Failed to load matches:', error);
          }
        });
    }
  }
}