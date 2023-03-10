package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

public class TestBase {
  RegistrationPage registrationPage = new RegistrationPage();


  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com/";
    Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("version", "100.0");
    Configuration.remote = "https://user1:1234@" + System.getProperty("baseUrl", "selenoid.autotests.cloud/") + "wd/hub";
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last Screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }
}
