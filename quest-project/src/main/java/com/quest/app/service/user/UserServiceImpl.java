package com.quest.app.service.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.quest.app.entity.User;
import com.quest.app.exception.BussinessException;
import com.quest.app.repo.user.UserRepo;
import com.quest.app.vo.UserVO;

@Component
public class UserServiceImpl implements UserService{

	private static String BAD_REQUEST = "400";

	@Autowired
	private UserRepo userRepo;
	
	

	public UserServiceImpl( UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<User> users = userRepo.findAll();
		if (users == null || users.isEmpty()) {
			return null;
		}
		List<UserVO> userInfo = users.stream()
				.map(u -> new UserVO(u.getId(), u.getName(), u.getPpsNumer(), u.getDob(), u.getMobile()))
				.sorted(Comparator.comparing(UserVO::getId).reversed()) // Sort by ID
				.collect(Collectors.toList());
		return userInfo;

	}

	@Override
	public UserVO saveUser(UserVO userInfo) {
		if (userInfo.getName() == null || userInfo.getPpsNumber() == null || userInfo.getDob() == null) {
			throw new BussinessException(BAD_REQUEST, "Mandatory fields are missing..");
		}
		// Maximum length check
		if (userInfo.getName().length() > 25) {
			throw new BussinessException(BAD_REQUEST, "Maximum length exceeded for Name");
		}
		// Convert Date to LocalDate
		LocalDate birthDate = convertToLocalDateViaInstant(userInfo.getDob());
		LocalDate currentDate = LocalDate.now();
		// Check if the date is in the future
		if (birthDate.isAfter(currentDate)) {
			throw new BussinessException(BAD_REQUEST, "Date of birth cannot be in the future.");
		}
		// Check if the person is at least 16 years old
		if (Period.between(birthDate, currentDate).getYears() < 16) {
			throw new BussinessException(BAD_REQUEST, "User must be at least 16 years old.");
		}
		//validateDatePattern(userInfo.getDob().toString());
		if (userInfo.getMobile() != null && !userInfo.getMobile().startsWith("08")) {
			throw new BussinessException(BAD_REQUEST, "Mobile number must begin with 08.");
		}
		Boolean isPPSNExists = userRepo.existsByppsNumer(userInfo.getPpsNumber());
		if (isPPSNExists && userInfo.getId() == null) {
			throw new BussinessException(BAD_REQUEST, "PPSN already exists.");
		}
		User user = new User(userInfo.getId(), userInfo.getName(), userInfo.getPpsNumber(), userInfo.getDob(),
				userInfo.getMobile());
		User savedUser = userRepo.save(user);
		if(savedUser !=null) {
			userInfo.setId(savedUser.getId());
		}
		return userInfo;
	}

	// Utility method to convert java.util.Date to java.time.LocalDate
	private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Helper method to validate the date format and parse it
//	private Date validateDatePattern(String dateStr) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		dateFormat.setLenient(false);
//		try {
//			// Parse and validate the pattern
//			return dateFormat.parse(dateStr);
//		} catch (Exception e) {
//			throw new BussinessException(BAD_REQUEST, "Invalid date format. The correct format is dd/MM/yyyy.");
//		}
//	}

}
