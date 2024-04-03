package com.challenges.challengeTwo.Security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenges.challengeTwo.Entity.ERole;
import com.challenges.challengeTwo.Entity.Role;
import com.challenges.challengeTwo.Entity.User;
import com.challenges.challengeTwo.Exception.BaseResponse;
import com.challenges.challengeTwo.PayLoad.Request.LoginRequest;
import com.challenges.challengeTwo.PayLoad.Request.SignupRequest;
import com.challenges.challengeTwo.PayLoad.Responce.JwtResponse;
import com.challenges.challengeTwo.Repository.Interfaces.RoleRepository;
import com.challenges.challengeTwo.Repository.Interfaces.UserRepository;
import com.challenges.challengeTwo.Security.jwt.JwtUtils;


//@Configuration
@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	public BaseResponse<JwtResponse> authenticateUser(LoginRequest loginRequest) throws Exception {

		log.info("hello now well be found user");
		String username = loginRequest.getUsername();
		log.info("hello found user1 :" + username);
		String password = loginRequest.getPassword();
		log.info("hello found user" + password);
		UsernamePasswordAuthenticationToken itemToken = new UsernamePasswordAuthenticationToken(username, password);
		log.info("hello found user" + itemToken);
		try {
			Authentication authentication = authenticationManager.authenticate(itemToken);	

			log.info("hello found user");

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles);
			BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
			baseResponse.setData(jwtResponse);
			return baseResponse;
		} catch (Exception e) {
			throw new Exception("Username or Password is faild");
		}
		
	}

//	@Bean
	public BaseResponse<Void> registerUser(SignupRequest signUpRequest) throws Exception {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new Exception("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new Exception("Error: Email is already in use!");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new Exception("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(
							() -> new RuntimeException(new Exception("Error: Role is not found.")));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
							() -> new RuntimeException(new Exception("Error: Role is not found.")));
					roles.add(userRole);
				}
			});
		}

		try {
			user.setRoles(roles);
			userRepository.save(user);
		} catch (Exception e) {
			new Exception(e.getMessage());
		}

		return new BaseResponse<>();
	}
}
