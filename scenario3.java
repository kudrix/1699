package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Scenario3 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8888/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testScenario3() throws Exception {
    driver.get(baseUrl + "/recipe/index.php");
    driver.findElement(By.xpath("//div[@id='nav']/table/tbody/tr[4]/td/a/strong")).click();
    driver.findElement(By.name("userid")).clear();
    driver.findElement(By.name("userid")).sendKeys("user2");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("pass2");
    driver.findElement(By.name("password2")).clear();
    driver.findElement(By.name("password2")).sendKeys("pass3");
    driver.findElement(By.name("fullname")).clear();
    driver.findElement(By.name("fullname")).sendKeys("name2");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("name2@mail.com");
    driver.findElement(By.cssSelector("#main > form > input[type=\"submit\"]")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Sorry, the passwords you entered did not match\\.[\\s\\S]*$"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
