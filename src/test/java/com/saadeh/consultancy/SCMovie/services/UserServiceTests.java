package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.entities.UserEntity;
import com.saadeh.consultancy.SCMovie.projections.UserDetailsProjection;
import com.saadeh.consultancy.SCMovie.repositories.UserRepository;
import com.saadeh.consultancy.SCMovie.tests.UserDetailsFactory;
import com.saadeh.consultancy.SCMovie.tests.UserFactory;
import com.saadeh.consultancy.SCMovie.utils.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTests {

    @InjectMocks
    private UserService service;

    @Mock
    private CustomUserUtil userUtil;

    @Mock
    private UserRepository repository;

    private String userName;
    private UserEntity user;
	private List<UserDetailsProjection> listUserDetails = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userName = "wilson.saadeh@gmail.com";
        user = UserFactory.createUserEntity();
		user.setUsername(userName);
		listUserDetails = UserDetailsFactory.createCustomAdminClientUser(userName);

		//authenticated
        Mockito.when(userUtil.getLoggedUsername()).thenReturn(userName);

		//loadUserByUserName
		Mockito.when(repository.searchUserAndRolesByUsername(any())).thenReturn(new ArrayList<>());
		Mockito.when(repository.searchUserAndRolesByUsername(userName)).thenReturn(listUserDetails);

    }

    @Test
    public void authenticatedShouldReturnUserEntityWhenUserExists() {

		Mockito.when(repository.findByUsername(userName)).thenReturn(Optional.of(user));
		UserEntity result = service.authenticated();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), user.getId());

    }

    @Test
    public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		Mockito.when(repository.findByUsername(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            UserEntity result = service.authenticated();
        });
    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
		UserDetails result = service.loadUserByUsername(userName);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(),user.getUsername());
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

		Assertions.assertThrows(UsernameNotFoundException.class,()->{
			UserDetails userDetails = service.loadUserByUsername(any());
		});
    }
}
