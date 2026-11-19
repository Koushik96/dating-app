package com.koushik.dating.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koushik.dating.dto.MatchResponse;
import com.koushik.dating.entity.UserAccount;
import com.koushik.dating.entity.UserMatch;
import com.koushik.dating.repository.UserAccountRepository;
import com.koushik.dating.repository.UserLikeRepository;
import com.koushik.dating.repository.UserMatchRepository;

@Service
public class MatchService {

    private final UserAccountRepository userAccountRepository;
    private final UserLikeRepository userLikeRepository;
    private final UserMatchRepository userMatchRepository;

    public MatchService(
            UserAccountRepository userAccountRepository,
            UserLikeRepository userLikeRepository,
            UserMatchRepository userMatchRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userLikeRepository = userLikeRepository;
        this.userMatchRepository = userMatchRepository;
    }

    @Transactional
    public boolean checkAndCreateMatch(Integer userId, Integer likedUserId) {

        // Check whether the other user has also liked this user.
        boolean mutualLike = userLikeRepository
                .existsByLiker_IdAndLikedUser_Id(likedUserId, userId);

        if (!mutualLike) {
            return false;
        }

        // Store IDs in ascending order.
        Integer userOneId = Math.min(userId, likedUserId);
        Integer userTwoId = Math.max(userId, likedUserId);

        // Avoid creating duplicate matches.
        if (userMatchRepository.existsByUserOne_IdAndUserTwo_Id(userOneId, userTwoId)) {
            return true;
        }

        UserAccount userOne = userAccountRepository.findById(userOneId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        UserAccount userTwo = userAccountRepository.findById(userTwoId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        UserMatch match = new UserMatch();
        match.setUserOne(userOne);
        match.setUserTwo(userTwo);

        userMatchRepository.save(match);

        return true;
    }
    public List<MatchResponse> getMatches(Integer userId) {

        List<UserMatch> matches = userMatchRepository
                .findByUserOne_IdOrUserTwo_Id(userId, userId);
        System.out.println("Fetching matches for user ID: " + userId);

        return matches.stream()
                .map(match -> {

                    UserAccount otherUser;

                    if (match.getUserOne().getId().equals(userId)) {
                        otherUser = match.getUserTwo();
                    } else {
                        otherUser = match.getUserOne();
                    }
                    System.out.println("Total matches found: " + matches.size());

                    return new MatchResponse(
                            match.getId(),
                            otherUser.getId(),
                            otherUser.getName(),
                            otherUser.getEmail()
                    );
                })
                .collect(Collectors.toList());
    }
}