package com.fifa.user.exception;

public class TransferMarketNotFoundException extends Exception{
	private Object data;   //Object 타입의 데이터를 저장하는 필드로, 예외가 발생한 상황과 관련된 데이터를 저장할 수 있습니다
	public TransferMarketNotFoundException(String msg) {//이 생성자는 예외 메시지를 받아서 부모 클래스인 Exception의 생성자를 호출하여 해당 메시지를 설정
		super(msg);
	}
	public Object getData() {//data 필드에 저장된 데이터를 반환하는 메서드
		return data;
	}
	public void setData(Object data) {//data 필드에 데이터를 설정하는 메서드
		this.data = data;
	}
}
