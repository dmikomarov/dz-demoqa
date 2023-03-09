package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.randomEmail;
import static utils.RandomUtils.randomString;

public class RegistrationWithRandomUtilsTests extends TestBase {
  String name = randomString(10);
  String lastName = randomString(10);
  String email = randomEmail();

  String gender = "Other";
  String dateOfBirth = "30 July,2008";
  String subject = "Maths";
  String hobbies = "Sports";
  String pictureFIleName = "1.png";
  String stateAndCity = "NCR Delhi";
  String phoneNumber = "8005553535";
  String currentAddress = "tuchkovo";

  @Tag("simple")
  @Test
  void successfulRegistrationTest() {


    open("automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");
    $("#firstName").setValue(name);
    $("#lastName").setValue(lastName);
    $("#genterWrapper").$(byText(gender)).click();
    $("#userNumber").setValue(phoneNumber);
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
            (text(phoneNumber)), (text(currentAddress)), (text(gender)),
            (text(dateOfBirth)), (text(subject)), (text(hobbies)),
            (text(hobbies)), (text(pictureFIleName)), (text(stateAndCity)));

  }
}
