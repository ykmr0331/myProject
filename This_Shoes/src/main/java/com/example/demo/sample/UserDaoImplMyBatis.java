package com.example.demo.sample;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoImplMyBatis implements UserDao {
	
	
	private SqlSessionFactory sqlSessionFactory;

	//생성자임
	public UserDaoImplMyBatis()  throws Exception{
		String resource = "mybatis-config.xml"; // mybatis 설정 파일 경로
	    InputStream inputStream = Resources.getResourceAsStream(resource);
	    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Override
	public int update(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : update() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.update(user);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public User findUser(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUser() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		User user=userMapper.findUser(userId);
		System.out.println("userDaoImplMyBatis에서 findUser로 찾은 user는 "+ user);
		sqlSession.close();
		return user;
	}
	
	
	@Override
	public List<User> findUserList()  throws Exception{
		System.out.println("#### UserDaoImplMyBatis : findUserList 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		System.out.println("sqlSession생성 sqlSession은 "+sqlSession);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		System.out.println("userMapper생성 userMapper은 "+userMapper);
		List<User> userList=userMapper.findUserList();        ///////////
		System.out.println("userMapper.findUserList()생성 userMapper.findUserList()은 "+userMapper.findUserList());
		sqlSession.close();
		return userList;
	}
	
	@Override
	public int insert(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : insert() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.insert(user);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public int delete(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : delete() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.delete(userId);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public int countByUserId(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : countByUserId 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.countByUserId(userId);
		sqlSession.close();
		return rowCount;
	}
	
	@Override
	public String findUserIdByNamePhone( Map<String, Object> parameters) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUserIdByNamePhone 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

		String findId = userMapper.findUserIdByNamePhone(parameters);
		sqlSession.close();
		return findId;
	}
	
	public String findUserPassByNamePhone(Map<String, Object> parameters) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUserPassByNamePhone 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		String findPass = userMapper.findUserPassByNamePhone(parameters);
		sqlSession.close();
		return findPass;
	}
}
