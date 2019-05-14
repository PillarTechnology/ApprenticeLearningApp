package com.pillar.pillarLearningCenter.endToEndTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;

public class CreatePostTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ThisThingCreatesAPost() {
        driver.navigate().to("http://localhost:8080/posts/new");
        driver.findElement(By.name("title")).sendKeys("New Title - in memory");
        driver.findElement(By.name("content")).sendKeys("Content here");
        driver.findElement(By.name("username")).sendKeys("Max");
        driver.findElement(By.name("submit")).click();

        List<WebElement> results = driver.findElements(By.xpath("//h1"));
        String result = results.get(1).getText();

        assertEquals("New Title - in memory", result);
    }
}
