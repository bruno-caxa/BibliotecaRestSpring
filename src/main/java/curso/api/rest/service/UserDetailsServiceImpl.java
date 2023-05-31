package curso.api.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import curso.api.rest.model.User;
import curso.api.rest.model.UserDetailsImpl;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService; 	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = Optional.of(userService.findByUsername(username));
		return user.map(UserDetailsImpl::new)
				   .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

}
