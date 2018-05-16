package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleniumApplication {
	private WebDriver driver;
	
	public void startSelenium() {
		System.setProperty("webdriver.chrome.driver", "/home/dhan/chromedriver"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
	    driver = new ChromeDriver(); // Driver 생성
        driver.get("http://192.168.0.39:8002/application/quick-check-new.do"); // URL로 접속하기
        driver.findElement(By.id("monthly")).click();
        System.out.println(driver.findElement(By.id("setDatepicker")).getAttribute("value"));
        driver.quit();
	}
	
	public static void main(String[] args) {
		
		SeleniumApplication TestSel = new SeleniumApplication();
		
		TestSel.startSelenium();
		
		SpringApplication.run(SeleniumApplication.class, args);
		
	}
}
