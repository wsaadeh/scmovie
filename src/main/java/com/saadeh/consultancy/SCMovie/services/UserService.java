package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.entities.RoleEntity;
import com.saadeh.consultancy.SCMovie.entities.UserEntity;
import com.saadeh.consultancy.SCMovie.projections.UserDetailsProjection;
import com.saadeh.consultancy.SCMovie.repositories.UserRepository;
import com.saadeh.consultancy.SCMovie.utils.CustomUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CustomUserUtil userUtil;

	public UserEntity authenticated() {
		try {
			String username = userUtil.getLoggedUsername();
			return repository.findByUsername(username).get();
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Invalid user");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = repository.searchUserAndRolesByUsername(username);
		if (result.size() == 0) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		UserEntity user = new UserEntity();
		user.setUsername(result.get(0).getUsername());
		user.setPassword(result.get(0).getPassword());
		for (UserDetailsProjection projection : result) {
			user.addRole(new RoleEntity(projection.getRoleId(), projection.getAuthority()));
		}
		
		return user;
	}
}
