
package com.koushik.dating.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koushik.dating.entity.UserAccount;
import com.koushik.dating.entity.UserLike;
import com.koushik.dating.repository.UserAccountRepository;
import com.koushik.dating.repository.UserLikeRepository;

@Service
public class LikeService {

    private final UserLikeRepository userLikeRepository;
    private final UserAccountRepository userAccountRepository;
    private final MatchService matchService;

    public LikeService(
            UserLikeRepository userLikeRepository,
            UserAccountRepository userAccountRepository,
            MatchService matchService) {
        this.userLikeRepository = userLikeRepository;
        this.userAccountRepository = userAccountRepository;
        this.matchService = matchService;
    }

    public void likeUser(Integer likerId, Integer likedUserId) {

        if (likerId.equals(likedUserId)) {
            throw new IllegalArgumentException("You cannot like yourself.");
        }

        if (userLikeRepository.existsByLiker_IdAndLikedUser_Id(likerId, likedUserId)) {
            return;
        }

        UserAccount liker = userAccountRepository.findById(likerId)
                .orElseThrow(() -> new IllegalArgumentException("Liker not found."));

        UserAccount likedUser = userAccountRepository.findById(likedUserId)
                .orElseThrow(() -> new IllegalArgumentException("User to like not found."));

        UserLike userLike = new UserLike();
        userLike.setLiker(liker);
        userLike.setLikedUser(likedUser);

        userLikeRepository.save(userLike);
        matchService.checkAndCreateMatch(likerId, likedUserId);
    }

    @Transactional
    public void unlikeUser(Integer likerId, Integer likedUserId) {
        userLikeRepository.deleteByLiker_IdAndLikedUser_Id(likerId, likedUserId);
    }
    
    public List<Integer> getLikedUserIds(Integer likerId) {
        return userLikeRepository.findByLiker_Id(likerId)
                .stream()
                .map(userLike -> userLike.getLikedUser().getId())
                .collect(Collectors.toList());
    }
}
