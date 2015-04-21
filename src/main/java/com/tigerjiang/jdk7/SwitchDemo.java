package com.tigerjiang.jdk7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SwitchDemo {
	public static void main(String[] args) {
		//1.switch可以接受string类型而不像以前仅仅是int
		switchDemo();
		
		//2.异常catch可以一次处理完
		catchDemo();
	}
	
	public static void catchDemo(){
		
	}
	
	public static void switchDemo(){
		String key = "add";
		switch (key) {
		case "add":
			System.out.println("add");
			break;
		case "del":
			break;
		default:
			break;
		}
	}

}
