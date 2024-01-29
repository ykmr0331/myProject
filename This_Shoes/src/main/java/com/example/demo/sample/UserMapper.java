package com.example.demo.sample;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    int insert(User user);
    
    int update(User user);
    
    int delete(String u_id);
    
    User findUser(String u_id);
    
    List<User> findUserList();
    
    int countByUserId(String u_id);
	
 // 추가: 사용자 이름과 비밀번호를 기준으로 u_id 조회하는 메서드
    String findUserIdByNamePhone( Map<String, Object> parameters);
    
 // 추가: 사용자 이름과 비밀번호를 기준으로 u_pass 조회하는 메서드
    String findUserPassByNamePhone( Map<String, Object> parametersr);
}
