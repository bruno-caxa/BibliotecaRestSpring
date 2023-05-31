package curso.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.User;
import curso.api.rest.model.dto.AuthRequest;
import curso.api.rest.service.JwtService;
import curso.api.rest.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
		if (authentication.isAuthenticated()) {
			User user = userService.findByUsername(authRequest.getUsername());
			String token = jwtService.generateToken(authRequest.getUsername());
			user.setToken(token);
			return ResponseEntity.ok().body(userService.save(user));
		}
		throw new UsernameNotFoundException("Invalid user request!");
	}
	
	@GetMapping("/token/{token}")
	public ResponseEntity<User> findByToken(@PathVariable String token) {
		return ResponseEntity.ok().body(userService.findByToken(token));
	}
	
	@PostMapping()
	public ResponseEntity<User> save(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return ResponseEntity.ok().body(userService.save(user));
	}
	
}
