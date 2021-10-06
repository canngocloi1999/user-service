package com.beetsoft.userservice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import com.beetsoft.userservice.exception.ValidateException;
import com.beetsoft.userservice.model.entity.User;


public class ValidateUtil {
	
	
	public User validateUser(User user) throws ValidateException {
		Map<String, String> errors = new HashMap<String, String>();
		String phonePattern = "^(0|\\+84)\\d{9}$";
		String phone = user.getPhoneNO();
		String mobile = user.getMobileNO();
		String namePattern = "[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]+(([ ][a-zA-Z ])?[a-zA-Z]*)*$";
		
		String nameOut = "";
		String nameIn = user.getFullname().trim();
		String[] nameArray = nameIn.split(" ");
		for (String string : nameArray) {
				if (!string.equals("")) {
					String name1 = string.substring(0, 1).toUpperCase();
					name1 += string.substring(1).toLowerCase();
					nameOut += name1+" ";
				}
		}
		nameOut = nameOut.trim();
		user.setFullname(nameOut);
		
		if (!Pattern.matches(namePattern, nameOut)) {
			errors.put("name", "Incorrect name format");
			throw new ValidateException(400, errors);
		}
		
		if (! Pattern.matches(phonePattern, phone)) {
			errors.put("phone", "Incorrect phone number format");
			throw new ValidateException(400, errors);
		}
		if (mobile != null) {
			if (! Pattern.matches(phonePattern, mobile)) {
				errors.put("MobielNO", "Incorrect phone number format");
				throw new ValidateException(400, errors);
			}
		}
		return user;
	}


}
