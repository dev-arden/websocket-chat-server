package com.websocket.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.chat.mapper.UserMapper;
import com.websocket.chat.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper usermapper;
	
	public int Login(User user) throws Exception{
		return usermapper.Login(user);
	}
}

/*
 * @Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Override
    public int Login(MemberVO vo) throws Exception {
    
        return memberDao.Login(vo);

    }
}
 * 
 * */
