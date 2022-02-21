package com.websocket.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.websocket.chat.model.User;

@Mapper
public interface UserMapper {
	public int Login(User user);
}

/*
 * @Repository
public class MemberDao {

    @Autowired
    private SqlSessionTemplate mybatis;

    public int Login(MemberVO vo) throws Exception {
        return mybatis.selectOne("Member.Login", vo);
    }

}
 * */
