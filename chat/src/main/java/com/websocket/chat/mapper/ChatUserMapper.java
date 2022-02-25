package com.websocket.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatUserMapper {
	public void requestRoom(@Param("roomId") String roomId, @Param("userId") String userId);
	public void acceptRoom(@Param("roomId") String roomId, @Param("userId") String userId);
	public void denyRoom(@Param("roomId") String roomId, @Param("userId") String userId);
	public int checkRoom(@Param("roomId") String roomId, @Param("userId") String userId);
	public int checkAuth(@Param("roomId") String roomId, @Param("userId") String userId);
}
