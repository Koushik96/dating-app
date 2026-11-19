package com.koushik.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.dating.entity.UserMatch;

public interface UserMatchRepository extends JpaRepository<UserMatch, Integer> {

    boolean existsByUserOne_IdAndUserTwo_Id(Integer userOneId, Integer userTwoId);
    List<UserMatch> findByUserOne_IdOrUserTwo_Id(
            Integer userOneId, Integer userTwoId);

}