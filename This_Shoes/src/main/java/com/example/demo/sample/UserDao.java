package com.example.demo.sample;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	

	/*사용자 테이블에 새로운 사용자 생성*/
	int insert(User user) throws Exception;
	
	/*기존 사용자의 ID(pk)로 기존의 사용자 정보를 수정*/
	int update(User user) throws Exception;
	
	/*기존의 사용자의 ID(pk)로 회원 탈퇴*/
	int delete(String u_id ) throws Exception;
	
    // 사용자아이디에해당하는 정보를 데이타베이스에서 찾아서 User 도메인클래스에 저장하여 반환	 
	User findUser(String u_id) throws Exception;


	//모든 사용자 정보 찾기
     List<User> findUserList() throws Exception;
     
 	//인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 여부를 판별 
 	int countByUserId(String u_id) throws Exception;
 	
 	//아이디찾기(이름과 전번)
 	String findUserIdByNamePhone( Map<String, Object> parameters) throws Exception ;
 	
 	//비번찾기(이름과 전번)
 	String findUserPassByNamePhone( Map<String, Object> parameters) throws Exception ;
}