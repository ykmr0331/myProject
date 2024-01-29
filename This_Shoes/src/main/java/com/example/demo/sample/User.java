package com.example.demo.sample;

import com.fasterxml.jackson.databind.json.JsonMapper.Builder;

public class User {
	/*
U_ID		VARCHAR2(100 BYTE)	No		1	
U_PASS		VARCHAR2(100 BYTE)	Yes		2	
U_NAME		VARCHAR2(100 BYTE)	Yes		3	
U_EMAIL		VARCHAR2(100 BYTE)	Yes		4	
U_AGE		NUMBER(10,0)	Yes		5	
U_PHONE		NUMBER(10,0)	Yes		6	
U_ADDRESS	VARCHAR2(100 BYTE)	Yes		7	
	 */
	
	private String u_id;
	private String u_pass;
	private String u_name;
	private String u_email;
	private int u_age;
	private int u_phone;
	private String u_address;
	
	public User() {
		
	}

	public User( String u_name,  int u_phone) {
		super();
		this.u_name = u_name;
		this.u_phone = u_phone;
	}
	
	public User(String u_id, String u_pass, String u_name, String u_email, int u_age, int u_phone, String u_address) {
		super();
		this.u_id = u_id;
		this.u_pass = u_pass;
		this.u_name = u_name;
		this.u_email = u_email;
		this.u_age = u_age;
		this.u_phone = u_phone;
		this.u_address = u_address;
	}
	
	private User(Builder builder) {
        this.u_id = builder.u_id;
        this.u_pass = builder.u_pass;
        this.u_name = builder.u_name;
        this.u_email = builder.u_email;
        this.u_age = builder.u_age;
        this.u_phone = builder.u_phone;
        this.u_address = builder.u_address;
    }

    // Getter 메서드들...

    public static class Builder {
        private final String u_id;
        private final String u_pass;
        private final String u_name;
        private final String u_email;
        private int u_age;
        private int u_phone;
        private final String  u_address;

        public Builder(String u_id, String u_pass, String u_name, String u_email, int u_age, int u_phone,String u_address) {
            this.u_id = u_id;
            this.u_pass = u_pass;
            this.u_name = u_name;
            this.u_email = u_email;
            this.u_age = u_age;
            this.u_phone = u_phone;
            this.u_address= u_address;
        }



        public User build() {
            return new User(this);
        }
    }
	
   
    
	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pass() {
		return u_pass;
	}

	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public int getU_age() {
		return u_age;
	}

	public void setU_age(int u_age) {
		this.u_age = u_age;
	}

	public int getU_phone() {
		return u_phone;
	}

	public void setU_phone(int u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_pass=" + u_pass + ", u_name=" + u_name + ", u_email=" + u_email + ", u_age="
				+ u_age + ", u_phone=" + u_phone + ", u_address=" + u_address + "]";
	}
	
	
	
}
