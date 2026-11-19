package com.koushik.dating.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.dating.dto.ErrorResponse;
import com.koushik.dating.dto.UserAccountRequest;
import com.koushik.dating.entity.UserAccount;
import com.koushik.dating.entity.UserAccountResponse;
import com.koushik.dating.service.UserAccountService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:4200","http://10.166.253.251:4200"})
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
    		@Valid @RequestBody UserAccountRequest request) {

        UserAccountResponse response = userAccountService.createUser(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Email already exists"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<UserAccountResponse> getAllUsers() {
        return userAccountService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponse> getUserById(@PathVariable Integer id) {
    	UserAccountResponse user = userAccountService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        boolean deleted = userAccountService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserAccountRequest requrest) {

    	UserAccountResponse responce = userAccountService.updateUser(id, requrest);

        if (responce == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responce);
    }
}