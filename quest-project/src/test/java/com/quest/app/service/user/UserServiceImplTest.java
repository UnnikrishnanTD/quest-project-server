package com.quest.app.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.classfile.ClassFile.Option;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.quest.app.entity.User;
import com.quest.app.exception.BussinessException;
import com.quest.app.repo.user.UserRepo;
import com.quest.app.vo.UserVO;

public class UserServiceImplTest {
	
	@Mock
	private UserRepo userRepo;
	
	private UserService userService;
	
	AutoCloseable autoCloseable;
	
	List<User> users;
	
	@BeforeEach
	void setup() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepo);
		users = Arrays.asList(new User(1L, "Sample", "12345", new Date(), null));
	}
	
	@Test
	void testGetAllUsers_success() {
		mock(UserRepo.class);
		when(userRepo.findAll()).thenReturn(users);
		
		List<UserVO> users = userService.getAllUsers();
		assertThat(users.isEmpty()).isFalse();
	}
	
	@Test
	void testGetAllUsers_nodata() {
		mock(UserRepo.class);
		when(userRepo.findAll()).thenReturn(null);
		
		List<UserVO> users = userService.getAllUsers();
		assertThat(users).isEqualTo(null);
	}
	
	@Test()
	void testSaveUser_validation1() {
		mock(UserRepo.class);
		mock(User.class);
		UserVO clientInfo = new UserVO(null, null, null, null, null);
//		User user = new User(1L, "Sample", "12345", new Date(), null);
//		when(userRepo.save(user)).thenReturn(user);
		
//		UserVO savedUser = userService.saveUser(clientInfo);
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "Mandatory fields are missing..");
	}
	
	@Test()
	void testSaveUser_validation2() {
		UserVO clientInfo = new UserVO(null, "Maximumlenthtestingsamplename", "12345", new Date(), null);
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "Maximum length exceeded for Name");
	}
	@Test()
	void testSaveUser_validation3() {
		UserVO clientInfo = new UserVO(null, "Sample", "12345", new Date(), null);
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "User must be at least 16 years old.");
	}
	@Test()
	void testSaveUser_validation4() {
		Date dob = Date.from(LocalDateTime.now().minusYears(20).atZone(ZoneId.systemDefault()).toInstant());
		UserVO clientInfo = new UserVO(null, "Sample", "12345", dob, "984711111");
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "Mobile number must begin with 08.");
	}
	@Test()
	void testSaveUser_validation5() {
		mock(UserRepo.class);
		mock(User.class);
		UserVO clientInfo = new UserVO(null, "Sample", "12345", Date.from(LocalDateTime.now().minusYears(20).atZone(ZoneId.systemDefault()).toInstant()), null);
		when(userRepo.existsByppsNumer(clientInfo.getPpsNumber())).thenReturn(true);
		
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "PPSN already exists.");
	}
	@Test()
	void testSaveUser_validation6() {
		Date dob = Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant());
		UserVO clientInfo = new UserVO(null, "Sample", "12345", dob, null);
		assertThrows(BussinessException.class,() -> userService.saveUser(clientInfo), "Date of birth cannot be in the future.");
	}
	@Test()
	void testSaveUser_success() {
		mock(UserRepo.class);
		mock(User.class);
		Date dob = Date.from(LocalDateTime.now().minusYears(20).atZone(ZoneId.systemDefault()).toInstant());
		UserVO clientInfo = new UserVO(1L, "Sample", "12345",dob , null);
		
		User user = new User(1L, "Sample", "12345", dob, null);
		when(userRepo.existsByppsNumer(clientInfo.getPpsNumber())).thenReturn(false);
		when(userRepo.save(user)).thenReturn(user);
		
		UserVO savedUser = userService.saveUser(clientInfo);
		
		assertThat(savedUser.getId()).isEqualTo(1L);
	}

}
