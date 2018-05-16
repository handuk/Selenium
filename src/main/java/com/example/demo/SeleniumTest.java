package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeleniumTest {
	
	private WebDriver driver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/dhan/chromedriver"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
        driver = new ChromeDriver(); // Driver 생성
    }
    @After
    public void tearDown(){
        driver.quit();  // Driver 종료
    }

	@Test
    public void test_title(){ //타이틀 확인하는 테스트 코드
        driver.get("http://localhost:8002/application/quick-check-new.do"); // URL로 접속하기
        driver.findElement(By.id("weekly")).click();
        Assert.assertEquals(driver.findElement(By.id("setDatepicker")).getAttribute("value"),DateUtil.formatDateFormat(DateUtil.addDay(DateUtil.getCurrentYMD(), 7),""));
    }

}
