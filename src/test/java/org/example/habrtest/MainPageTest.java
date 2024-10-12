package org.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");


    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void vkTest() {


        WebElement developLink = driver.findElement(By.xpath("//a[contains(text(),'Разработка')]"));
        developLink.click();

        WebElement companyLink = driver.findElement(By.xpath("//a[contains(text(),'Компании ')]"));
        companyLink.click();

        assertTrue(driver.findElement(By.xpath("//span[contains(text(),'VK')]")).isDisplayed(), "VK не найден");

        WebElement vkLink = driver.findElement(By.xpath("//span[contains(text(),'VK')]"));
        vkLink.click();

        assertTrue(driver.findElement(By.xpath("//dt[contains(text(),'Информация')]")).isDisplayed(), "Информация не найдена");

        assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Ссылки')]")).isDisplayed(), "Ссылки не найдены");
    }

}
