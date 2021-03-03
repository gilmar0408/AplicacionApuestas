package com.co.masivian.bet.api.service;

import com.co.masivian.bet.api.entity.Users;

public interface IUserService {
	
	void update(Users user);
	Users search(int id);

}
