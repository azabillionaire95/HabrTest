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
    public void testVkLinkExists() {
        // Переход на страницу "Разработка"
        WebElement developLink = driver.findElement(By.xpath("//nav//a[contains(text(),'Разработка')]"));
        developLink.click();

        // Переход на страницу "Компании"
        WebElement companyLink = driver.findElement(By.xpath("//span//a[contains(text(),'Компании ')]"));
        companyLink.click();

        // Проверка наличия VK на странице компаний
        assertTrue(driver.findElement(By.xpath("//a//span[contains(text(),'VK')]")).isDisplayed(), "VK не найден");
    }

    @Test
    public void testVkPageContents() {
        // Переход на страницу "Разработка"
        WebElement developLink = driver.findElement(By.xpath("//nav//a[contains(text(),'Разработка')]"));
        developLink.click();

        // Переход на страницу "Компании"
        WebElement companyLink = driver.findElement(By.xpath("//span//a[contains(text(),'Компании ')]"));
        companyLink.click();

        // Нажатие на ссылку VK
        WebElement vkLink = driver.findElement(By.xpath("//a//span[contains(text(),'VK')]"));
        vkLink.click();

        // Проверка наличия информации на странице VK
        assertTrue(driver.findElement(By.xpath("//dl//dt[contains(text(),'Информация')]")).isDisplayed(), "Информация не найдена");

        // Проверка наличия раздела "Ссылки"
        assertTrue(driver.findElement(By.xpath("//div//h2[contains(text(),'Ссылки')]")).isDisplayed(), "Ссылки не найдены");
    }
}
