
package com.koushik.dating.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "user_likes",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"liker_id", "liked_user_id"}
        )
    }
)
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "liker_id", nullable = false)
    private UserAccount liker;

    @ManyToOne
    @JoinColumn(name = "liked_user_id", nullable = false)
    private UserAccount likedUser;

    public UserLike() {
    }

    public Integer getId() {
        return id;
    }

    public UserAccount getLiker() {
        return liker;
    }

    public void setLiker(UserAccount liker) {
        this.liker = liker;
    }

    public UserAccount getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(UserAccount likedUser) {
        this.likedUser = likedUser;
    }
}
