package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;

public class TestBase {
  RegistrationPage registrationPage = new RegistrationPage();


  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com/";
    Configuration.browserSize = "1920x1080";
    Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    //  Configuration.holdBrowserOpen = true1;
  }
}
