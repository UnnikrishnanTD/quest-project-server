package com.quest.app.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quest.app.vo.UserVO;

@Service
public interface UserService {

	public List<UserVO> getAllUsers();
	
	
	public UserVO saveUser(UserVO userInfo);
}
