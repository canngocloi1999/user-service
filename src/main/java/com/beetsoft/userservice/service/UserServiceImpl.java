package com.beetsoft.userservice.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.beetsoft.userservice.ValidateUtil;
import com.beetsoft.userservice.exception.ValidateException;
import com.beetsoft.userservice.model.entity.User;
import com.beetsoft.userservice.model.enums.RoleEnum;
import com.beetsoft.userservice.model.request.UserRequest;
import com.beetsoft.userservice.model.response.UserResponse;
import com.beetsoft.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValidateUtil validateUtil;

	@Override
	public UserResponse getList(String role, String name, Integer offset, Integer limit) {
		List<User> listUser = new ArrayList<User>();
		offset = (offset == null) ? 0 : offset;
		limit = (limit == null) ? Integer.MAX_VALUE : limit;
		System.out.println(RoleEnum.fromValue(role));
		try {
			Page<User> page = userRepository.findUser(RoleEnum.valueOf(role), name, PageRequest.of(offset, limit));
			listUser = page.toList();
			return new UserResponse(200, "success", listUser.size(), listUser, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new UserResponse(500, "unsuccessful", listUser.size(), null, e.getMessage());
		}
	}

	@Override
	public UserResponse getDetail(Long id) {
		List<User> listUser = new ArrayList<User>();
		try {
			listUser.add(userRepository.findById(id).get());
			return new UserResponse(200, "success", listUser.size(), listUser, null);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new UserResponse(404, "Not found", listUser.size(), null, e.getMessage());
		}
	}

	@Override
	public UserResponse create(UserRequest userReq) throws ValidateException {

		User user = validateUtil.validateUser(mapper.map(userReq, User.class));
		List<User> listUser = new ArrayList<User>();
		try {
			listUser.add(userRepository.save(user));
			return new UserResponse(200, "success", listUser.size(), listUser, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new UserResponse(500, "unsuccessful", listUser.size(), null, e.getMessage());
		}
	}

	@Override
	public UserResponse update(Long id, UserRequest userReq) throws ValidateException {
		ObjectMapper oMapper = new ObjectMapper();
		User userUpdate = validateUtil.validateUser(mapper.map(userReq, User.class));
		List<User> listUser = new ArrayList<User>();
		try {
			User user = userRepository.findById(id).get();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = oMapper.convertValue(userUpdate, Map.class);
			map.forEach((key, value) -> {
				if (value != null) {
					Field field = ReflectionUtils.findField(userUpdate.getClass(), key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, user, oMapper.convertValue(value, field.getType()));
				}
			});
			listUser.add(userRepository.save(user));
			return new UserResponse(200, "success", 1, listUser, null);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new UserResponse(404, "not found", 0, null, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new UserResponse(500, "unsuccessful", 0, null, e.getMessage());
		}
	}

	@Override
	public UserResponse delete(Long[] ids) {
		int count = 0;
		List<User> listUser = new ArrayList<User>();
		for (Long id : ids) {
			Optional<User> user = userRepository.findById(id);
			if (user.isEmpty()) {
				continue;
			}
			listUser.add(user.get());
			count++;
		}
		
		if (count > 0) {
			try {
				for (User user : listUser) {
					userRepository.delete(user);
				}
				return new UserResponse(200, "delete success", count, null, null);
			} catch (Exception e) {
				e.printStackTrace();
				return new UserResponse(500, "delete unsuccessful", 0, null, e.getMessage());
			}
		}
		return new UserResponse(404, "delete unsuccessful", count, null, null);
	}

//	public Map<Field, Object> getFieldsUpdate(Map<String, Object> map, Object obj) {
//
//		Map<Field, Object> fields = new HashMap<Field, Object>();
//
//		map.forEach((key, value) -> {
//			if (value != null) {
//				Field field = ReflectionUtils.findField(obj.getClass(), key);
//				if (field != null) {
//					fields.put(field, value);
//				}
//			}
//		});
//		return fields;
//	}

}
