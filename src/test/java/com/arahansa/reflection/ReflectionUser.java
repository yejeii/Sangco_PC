package com.arahansa.reflection;

import java.lang.reflect.Field;

public class ReflectionUser {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = RefObject.class;
		
		Field[] fields = clazz.getDeclaredFields();  // 필드 get
		
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
        // private 를 풀기 위한 조치
		Field field = clazz.getDeclaredField("numId");
		
		//필드값을 다루기 위해서 객체 생성
		RefObject refObject = new RefObject();
		field.setAccessible(true);
		
		int numid = (int) field.get(refObject);
		System.out.println("numId 는 :"+numid);
		
		field.set(refObject, 3);
		numid = (int) field.get(refObject);
		System.out.println("numId 는 :"+numid);
		
	}
	
}
