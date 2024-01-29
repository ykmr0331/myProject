package com.fifa.user.generator;


import java.security.NoSuchAlgorithmException; // NoSuchAlgorithmException을 사용하기 위한 import문
import java.security.SecureRandom; // SecureRandom을 사용하기 위한 import문

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%^&*()_+";

    // 랜덤한 문자열을 생성하는 메서드
    public static String generateRandomString() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom(); // 보안적으로 안전한 난수 생성기 생성
        StringBuilder sb = new StringBuilder(11); // 길이가 11인 StringBuilder 생성

        // 최소 하나의 영문, 숫자 및 특수 문자를 추가
        sb.append(CHARACTERS.charAt(random.nextInt(26))); // 영문 대문자 추가
        sb.append(CHARACTERS.charAt(52 + random.nextInt(26))); // 영문 소문자 추가
        sb.append(CHARACTERS.charAt(2 * 26 + random.nextInt(10))); // 숫자 추가
        sb.append(CHARACTERS.charAt(2 * 26 + 10 + random.nextInt(7))); // 특수 문자 추가

        // 나머지 문자를 무작위로 선택하여 추가
        for (int i = 4; i < 10; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        // 생성된 문자열을 무작위로 섞음
        String shuffledString = RandomStringGenerator.shuffleString(sb.toString());
        return shuffledString; // 무작위로 섞인 문자열 반환
    }

    // 문자열을 무작위로 섞는 메서드
    private static String shuffleString(String input) throws NoSuchAlgorithmException {
        char[] characters = input.toCharArray(); // 문자열을 문자 배열로 변환
        for (int i = characters.length - 1; i > 0; i--) {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG"); // SHA1PRNG 알고리즘 사용하는 SecureRandom 생성
            int index = secureRandom.nextInt(i + 1); // 0부터 i까지의 랜덤한 수 생성
            char temp = characters[i];
            characters[i] = characters[index]; // 문자열을 무작위로 섞음
            characters[index] = temp;
        }
        return new String(characters); // 무작위로 섞인 문자열 반환
    }
}
