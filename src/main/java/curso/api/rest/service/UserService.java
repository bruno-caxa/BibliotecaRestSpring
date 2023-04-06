package curso.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.api.rest.model.User;
import curso.api.rest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findByToken(String token) {
		return userRepository.findByToken(token);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
} 
