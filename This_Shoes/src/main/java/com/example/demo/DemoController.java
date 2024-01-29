package com.example.demo;

import com.example.demo.sample.User;
import com.example.demo.sample.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {
    private UserService userService;
    private User user;
    public DemoController(UserService userService){
        this.userService = userService;
    }
    

    //$$$$$$$$$$$$$$$$$$$$$회원가입(1이면 성공 -1이면 중복)
    @GetMapping("/create")
    public int create()throws Exception { //
    	
    	User user2 = new User.Builder("user4", "password423", "John Doe4", "john4.doe@example.com", 24, 4234567, "423MainStreet").build();
    			
    	int result = userService.create(user2);
    	return result;
    }



	// $$$$$$$$$$$$$$$$$$$$$회원로그인(0: 존재안해 1:패스워드 불일치 2:성공) // 이거 findUser 확인하는거임
    @GetMapping("/login")
    public int login()throws Exception { 
    	int result = userService.login("user4","password423" );
    	return result;
    }
 // $$$$$$$$$$$$$$$$$$$$$ 회원 수정
    @GetMapping("/update")
    public int update()throws Exception {
    	User user = new User("user4", "password424","JohnDoe44", "john44.doe@example.com", 44, 444444, "444MainStreet");
    	int result = userService.update(user);
    	return result ;
    }


	// $$$$$$$$$$$$$$$$$$$$$ 회원 탈퇴
    @GetMapping("/delete")
    public int delete()throws Exception { //
    	int result = userService.delete("user4");
    	return result;
    }
    

	
    //$$$$$$$$$$$$$$$$$$$$$아이디로 회원 상세보기 
    @GetMapping("/findUser")
    public User findUser()throws Exception { //
    	User findUser = userService.findUser("jaehong");
    	return findUser;
    }
    
    
    
    //$$$$$$$$$$$$$$$$$$$$$모든 회원 찾기 mapper>> dao>>service
    @GetMapping("/findAll")
    public List<User> findAll()throws Exception { //
    	System.out.println("demoController에서 userService.findAll()는 >>>"+userService.findAll());
        return userService.findAll();
    }


    // $$$$$$$$$$$$$$$$$$$$$(해결)인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 여부를 판별
    @GetMapping("/countByUserId")
    public int countByUserId() throws Exception { //
    			
    	int result = userService.countByUserId("jaehong2");
    	
        return result;
    }
    
  //$$$$$$$$$$$$$$$$$$$$인자로 전달되는 이름과 전화번호로 아이디 찾기
    @GetMapping("/findUserId")
    public String findUserId() throws Exception { //
    			
    	Map<String, Object> parameters = new HashMap<>();
    	parameters.put("u_name", "박지우");
    	parameters.put("u_phone", 1067890123);
    	String userId = userService.findUserId(parameters);
    
        return userId;
        
    }
    //$$$$$$$$$$$$$$$$$$$$인자로 전달되는 아이디와 전화번호로 아이디 찾기
    @GetMapping("/findUserPass")
    public String findUserPass() throws Exception { //
    			
    	Map<String, Object> parameters = new HashMap<>();
    	parameters.put("u_id", "haeun");
    	parameters.put("u_phone", 1078901234);
    	String userId = userService.findUserPass(parameters);
    
        return userId;
        
    }

}
