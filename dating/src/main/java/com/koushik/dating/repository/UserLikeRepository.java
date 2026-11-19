
package com.koushik.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.dating.entity.UserLike;

public interface UserLikeRepository extends JpaRepository<UserLike, Integer> {

    boolean existsByLiker_IdAndLikedUser_Id(Integer likerId, Integer likedUserId);

    void deleteByLiker_IdAndLikedUser_Id(Integer likerId, Integer likedUserId);
       List<UserLike> findByLiker_Id(Integer likerId);
}
