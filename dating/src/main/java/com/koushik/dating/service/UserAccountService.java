package com.koushik.dating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koushik.dating.dto.LoginRequest;
import com.koushik.dating.dto.LoginResponse;
import com.koushik.dating.dto.UserAccountRequest;
import com.koushik.dating.entity.UserAccount;
import com.koushik.dating.entity.UserAccountResponse;
import com.koushik.dating.exception.UserNotFoundException;
import com.koushik.dating.repository.UserAccountRepository;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountResponse createUser(UserAccountRequest request) {
    	
        if (userAccountRepository.existsByEmail(request.getEmail())) {
            return null;
        }

        UserAccount user = new UserAccount();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserAccount savedUser = userAccountRepository.save(user);

        return new UserAccountResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
    
	
    public List<UserAccountResponse> getAllUsers() {

        List<UserAccount> users = userAccountRepository.findAll();

        return users.stream()
                .map(user -> new UserAccountResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
	 
    
	/*
	 * public List<UserAccountResponse> getAllUsers() {
	 * 
	 * List<UserAccount> users = userAccountRepository.findAll();
	 * List<UserAccountResponse> result = new ArrayList<>();
	 * 
	 * for (UserAccount user : users) {
	 * 
	 * UserAccountResponse response = new UserAccountResponse( user.getId(),
	 * user.getName(), user.getEmail() );
	 * 
	 * result.add(response); } }
	 */
	 
    
    public UserAccountResponse getUserById(Integer id) {

        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() ->
                    new UserNotFoundException("User not found with id: " + id));

        return new UserAccountResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

	public boolean deleteUser(Integer id) {
		if (!userAccountRepository.existsById(id)) {
	        return false;
	    }

	    userAccountRepository.deleteById(id);
	    return true;
	}

	public UserAccountResponse updateUser(Integer id, UserAccountRequest request) {

	    UserAccount user = userAccountRepository.findById(id).orElse(null);

	    if (user == null) {
	        return null;
	    }

	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword());

	    UserAccount updatedUser = userAccountRepository.save(user);

	    return new UserAccountResponse(
	            updatedUser.getId(),
	            updatedUser.getName(),
	            updatedUser.getEmail()
	    );
	}
	
	public LoginResponse login(LoginRequest request) {

	    UserAccount user = userAccountRepository
	            .findByEmail(request.getEmail())
	            .orElse(null);

	    if (user == null) {
	        return null;
	    }

	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        return null;
	    }

	    return new LoginResponse(
	            user.getId(),
	            user.getName(),
	            user.getEmail()
	    );
	}

}