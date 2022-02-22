package com.websocket.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.websocket.chat.model.Account;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM USER WHERE id=#{id}")
    Account readAccount(@Param("id") String id);

    @Select("SELECT authority_name FROM AUTHORITY WHERE username=#{id}")
    List<String> readAutorities(@Param("id") String id);

    @Insert("INSERT INTO USER VALUES(#{account.id},#{account.password},#{account.isAccountNonExpired},#{account.isAccountNonLocked},#{account.isCredentialsNonExpired},#{account.isEnabled})")
    void insertUser(@Param("account") Account account);

    @Insert("INSERT INTO AUTHORITY VALUES(#{id},#{autority})")
    void insertUserAutority(@Param("id") String id, @Param("autority") String autority);

    @Select("SELECT* FROM USER")
    List<Account> readAllUsers();
    
    @Select("SELECT COUNT(*) FROM USER WHERE id=#{id} AND password=#{password}")
    int login(@Param("id") String id, @Param("password") String password);
}
