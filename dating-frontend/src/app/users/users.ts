
import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Like } from '../like';

@Component({
  selector: 'app-users',
  imports: [CommonModule],
  templateUrl: './users.html',
  styleUrl: './users.css'
})
export class Users implements OnInit {

  showDetails = false;

  //users: any[] = [];
  users = signal<any[]>([]);
  currentUserId: number | null = null;
  likedUserIds = signal<number[]>([]);
  constructor(private http: HttpClient,
    private likeService: Like
  ) { }

  ngOnInit() {
    const userData = localStorage.getItem('user');

    if (userData) {
      const currentUser = JSON.parse(userData);
      this.currentUserId = Number(currentUser.id);
    }
    if (this.currentUserId !== null) {
      this.likeService.getLikedUserIds(this.currentUserId)
        .subscribe({
          next: (likedIds) => {
            this.likedUserIds.set(likedIds);
            console.log('Saved Likes loaded:', likedIds);
          },
          error: (error) => {
            console.error('Failed to load saved Likes:', error);
          }
        });
    }
    this.http.get<any[]>(
      `http://${window.location.hostname}:8080/api/users`
    )
      .subscribe({
        next: (response) => {
          const otherUsers = response.filter(
            user => user.id !== this.currentUserId
          );

          this.users.set(otherUsers);
          console.log('Other Users loaded:', this.users);
          //console.log('Users count:', this.users.length);
          //console.log('Current user ID:', this.currentUserId);
          //this.cdr.detectChanges();
        },
        error: (error) => {
          console.error('Failed to load users:', error);
        }
      });
  }

  dislikeUser(userId: number) {
    this.users.update(users =>
      users.filter(user => user.id !== userId)
    );

    this.showDetails = false;
  }

  private startX = 0;
  private startY = 0;

  onSwipeStart(event: TouchEvent) {
    this.startX = event.changedTouches[0].screenX;
    this.startY = event.changedTouches[0].screenY;
  }

  onSwipeEnd(event: TouchEvent) {
    const endX = event.changedTouches[0].screenX;
    const endY = event.changedTouches[0].screenY;

    const differenceX = endX - this.startX;
    const differenceY = endY - this.startY;

    const currentUser = this.users()[0];

    if (!currentUser) return;

    // Swipe up: open About section
    if (differenceY < -80 && Math.abs(differenceY) > Math.abs(differenceX)) {
      this.showDetails = true;
      return;
    }

    // Swipe down: return to photo
    if (differenceY > 80 && Math.abs(differenceY) > Math.abs(differenceX)) {
      this.showDetails = false;
      return;
    }

    // Swipe right: Like and move to next profile
    if (differenceX > 80 && Math.abs(differenceX) > Math.abs(differenceY)) {
      this.likeAndNext(currentUser.id);
    }

    // Swipe left: Dislike and move to next profile
    if (differenceX < -80 && Math.abs(differenceX) > Math.abs(differenceY)) {
      this.dislikeUser(currentUser.id);
    }
  }

  likeAndNext(userId: number) {
    if (this.currentUserId === null) return;

    this.likeService.likeUser(this.currentUserId, userId)
      .subscribe({
        next: () => {
          this.likedUserIds.update(ids =>
            ids.includes(userId) ? ids : [...ids, userId]
          );

          this.dislikeUser(userId);
          this.showDetails = false;
        },
        error: (error) => {
          console.error('Like failed:', error);
        }
      });
  }

  toggleLike(userId: number) {
    if (this.currentUserId === null) return;

    const alreadyLiked = this.likedUserIds().includes(userId);

    if (alreadyLiked) {
      this.likeService.unlikeUser(this.currentUserId, userId)
        .subscribe({
          next: () => {
            this.likedUserIds.update(ids =>
              ids.filter(id => id !== userId)
            );
            console.log('Like removed:', userId);
          },
          error: error => console.error('Unlike failed:', error)
        });
    } else {
      this.likeService.likeUser(this.currentUserId, userId)
        .subscribe({
          next: () => {
            this.likedUserIds.update(ids =>
              ids.includes(userId) ? ids : [...ids, userId]
            );
            console.log('Like added:', userId);
          },
          error: error => console.error('Like failed:', error)
        });
    }
  }

  profilePhotos: string[] = [
    'images/profiles/profile1.jpg',
    'images/profiles/profile2.jpg',
    'images/profiles/profile3.jpg',
    'images/profiles/profile4.jpg',
    'images/profiles/profile5.jpg'
  ];
  getProfilePhoto(userId: number): string {
    const index = Math.abs(userId - 1) % this.profilePhotos.length;
    return this.profilePhotos[index];
  }
}
