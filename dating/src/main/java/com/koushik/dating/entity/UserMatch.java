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
    name = "user_matches",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_one_id", "user_two_id"})
    }
)
public class UserMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_one_id", nullable = false)
    private UserAccount userOne;

    @ManyToOne
    @JoinColumn(name = "user_two_id", nullable = false)
    private UserAccount userTwo;

    public UserMatch() {
    }

    public Integer getId() {
        return id;
    }

    public UserAccount getUserOne() {
        return userOne;
    }

    public void setUserOne(UserAccount userOne) {
        this.userOne = userOne;
    }

    public UserAccount getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserAccount userTwo) {
        this.userTwo = userTwo;
    }
}