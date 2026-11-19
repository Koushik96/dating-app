import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Like {

  private apiUrl = `http://${window.location.hostname}:8080/api/likes`;

  constructor(private http: HttpClient) { }

  likeUser(likerId: number, likedUserId: number) {
    const likeData = {
      likerId: likerId,
      likedUserId: likedUserId
    };

    return this.http.post(this.apiUrl, likeData, {
      responseType: 'text'
    });
  }

  unlikeUser(likerId: number, likedUserId: number) {
    return this.http.delete(
      `${this.apiUrl}/${likerId}/${likedUserId}`,
      { responseType: 'text' }
    );
  }

  getLikedUserIds(likerId: number) {
    return this.http.get<number[]>(`${this.apiUrl}/${likerId}`);
  }
}