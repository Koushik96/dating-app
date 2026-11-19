package com.koushik.dating.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.dating.dto.LikeRequest;
import com.koushik.dating.service.LikeService;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = {"http://localhost:4200","http://10.166.253.251:4200"})
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<String> likeUser(@RequestBody LikeRequest request) {
        likeService.likeUser(request.getLikerId(), request.getLikedUserId());
        return ResponseEntity.ok("User liked successfully.");
    }

    @DeleteMapping("/{likerId}/{likedUserId}")
    public ResponseEntity<String> unlikeUser(
            @PathVariable Integer likerId,
            @PathVariable Integer likedUserId) {

        likeService.unlikeUser(likerId, likedUserId);
        return ResponseEntity.ok("Like removed successfully.");
    }
    @GetMapping("/{likerId}")
    public ResponseEntity<List<Integer>> getLikedUserIds(
            @PathVariable Integer likerId) {

        List<Integer> likedUserIds = likeService.getLikedUserIds(likerId);

        return ResponseEntity.ok(likedUserIds);
    }
}