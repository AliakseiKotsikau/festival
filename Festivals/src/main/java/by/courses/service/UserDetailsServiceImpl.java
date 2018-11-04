package by.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.courses.model.Login;
import by.courses.repositories.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private LoginRepository loginRepository;

	@Autowired
	public UserDetailsServiceImpl(LoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = loginRepository.findByUsername(username);

		return null;
	}

}
