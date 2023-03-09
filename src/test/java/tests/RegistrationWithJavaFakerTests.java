package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.randomEmail;
import static utils.RandomUtils.randomString;

public class RegistrationWithJavaFakerTests extends TestBase {


  Faker faker = new Faker(new Locale("it"));

  String name = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String currentAddress = faker.address().streetAddress();
  PhoneNumber phoneNumber = faker.phoneNumber();
  Date dateOfBirth = faker.date().birthday();



  String gender = "Other";
  String subject = "Maths";
  String hobbies = "Sports";
  String pictureFIleName = "1.png";
  String stateAndCity = "NCR Delhi";


  @Owner("Dmitry Komarov")
  @Feature("Issue в репозитории")
  @Story("Проверка Issue")
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Проверка Issue (Allure c Listener)")
  @Test
  void successfulRegistrationTest() {


    open("automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");
    $("#firstName").setValue(name);
    $("#lastName").setValue(lastName);
    $("#genterWrapper").$(byText(gender)).click();
    $("#userNumber").setValue(String.valueOf(phoneNumber));
    $("#userEmail").setValue(email);


    // календарь
    $("#dateOfBirthInput").click();
    $(".react-datepicker__year-select").selectOption("2008");
    $(".react-datepicker__month-select").selectOption("July");
    $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

    // subjects
    $("#subjectsInput").setValue(subject).pressEnter();

    //hobbies
    $("#hobbiesWrapper").$(byText(hobbies)).click();

    //file
    $("#uploadPicture").uploadFromClasspath("img/1.png");

    //State and city
    $("#currentAddress").setValue(currentAddress);
    $("#stateCity-wrapper").$(byText("Select State")).click();
    $("#stateCity-wrapper").$(byText("NCR")).click();
    $("#city").click();
    $("#stateCity-wrapper").$(byText("Delhi")).click();
    $("#submit").click();

    $(".modal-content").should(appear);
    $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    $(".modal-body").shouldHave(text(name + " " + lastName), (text(email)),
            (text(String.valueOf(phoneNumber))), (text(currentAddress)), (text(gender)),
            (text(String.valueOf(dateOfBirth))), (text(subject)), (text(hobbies)),
            (text(hobbies)), (text(pictureFIleName)), (text(stateAndCity)));

  }
}
