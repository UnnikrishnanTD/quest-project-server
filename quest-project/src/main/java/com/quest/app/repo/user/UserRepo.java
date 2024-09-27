package com.quest.app.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest.app.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	Boolean existsByppsNumer(String ppsNumber);

}
