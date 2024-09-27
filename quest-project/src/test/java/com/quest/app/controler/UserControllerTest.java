package com.quest.app.controler;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quest.app.controller.user.UserController;
import com.quest.app.service.user.UserService;
import com.quest.app.vo.UserVO;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	UserVO userVO;
	UserVO userVO2;
	List<UserVO> users = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		Date dob = Date.from(LocalDateTime.now().minusYears(20).atZone(ZoneId.systemDefault()).toInstant());
		userVO = new UserVO(null, "Sample", "123456", dob, null);
		userVO2 = new UserVO(1L, "Sample2", "987654", dob, null);
		users.add(userVO);
		users.add(userVO2);
		
	}
	
	@Test
	void testGetUsers() throws Exception {
		when(userService.getAllUsers()).thenReturn(users);
		this.mockMvc.perform(get("/api/users/getAll")).andDo(print()).
		andExpect(status().isOk());
	}
	
	@Test
	void testAddUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow =mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(userVO);
		when(userService.saveUser(userVO)).thenReturn(userVO);
		this.mockMvc.perform(
				post("/api/users/addUser").
				contentType(MediaType.APPLICATION_JSON).
				content(requestJson)).
				andDo(print()).andExpect(status().isOk());
	}
	@Test
	void testUpdateUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow =mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(userVO2);
		when(userService.saveUser(userVO2)).thenReturn(userVO2);
		this.mockMvc.perform(
				put("/api/users/updateUser").
				contentType(MediaType.APPLICATION_JSON).
				content(requestJson)).
				andDo(print()).andExpect(status().isOk());
	}
}
