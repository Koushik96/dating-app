package com.koushik.dating.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.dating.dto.MatchResponse;
import com.koushik.dating.service.MatchService;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = {"http://localhost:4200","http://10.166.253.251:4200"})
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MatchResponse>> getMatches(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(matchService.getMatches(userId));
    }
}