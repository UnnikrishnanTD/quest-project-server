package com.quest.app.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.app.service.user.UserService;
import com.quest.app.vo.UserVO;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<UserVO>> getUsers(){
		List<UserVO> users= userService.getAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping("addUser")
	public ResponseEntity<UserVO> addUser(@RequestBody UserVO user) {
		UserVO savedUser = userService.saveUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	@PutMapping("updateUser")
	public ResponseEntity<UserVO> updateUser(@RequestBody UserVO user) {
		UserVO savedUser = userService.saveUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

}
