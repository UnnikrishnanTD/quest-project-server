package com.quest.app.repo.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.quest.app.entity.User;

@DataJpaTest
public class UserRepoTest {

	@Autowired
	private UserRepo userRepo;
	
	User user;
	
	@BeforeEach
	void setup() {
		user = new User(1L, "Sample", "12345", new Date(), null);
		userRepo.save(user);
	}
	
	@Test
	void testExistsByppsNumer_valid() {
		Boolean isPPSNExists = userRepo.existsByppsNumer("12345");
		assertThat(isPPSNExists).isEqualTo(true)	;
	}
	
	@Test
	void testExistsByppsNumer_notFound() {
		Boolean isPPSNExists = userRepo.existsByppsNumer("111");
		assertThat(isPPSNExists).isEqualTo(false)	;
	}
}
