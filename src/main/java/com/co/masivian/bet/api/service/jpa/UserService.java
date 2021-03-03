package com.co.masivian.bet.api.service.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.masivian.bet.api.entity.Users;
import com.co.masivian.bet.api.repository.UserRepository;
import com.co.masivian.bet.api.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository repoUser;

	@Override
	public Users search(int id) {
		Optional<Users> userFinded = repoUser.findById(id);
		Users user = new Users();
		if(userFinded.isPresent()) {
			user = userFinded.get();
			return user;
		}
		return user;
	}

	@Override
	public void update(Users user) {
		repoUser.save(user);
	}

}
