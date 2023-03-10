package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTests extends TestData {
  String gender = "Other";
  String dateOfBirth = "30 July,2008";
  String subject = "Maths";
  String hobbies = "Sports";
  String pictureFIleName = "1.png";
  String stateAndCity = "NCR Delhi";
  //  String name = "Alex";
//  String lastName = "Boom";
//  String email = "egorov@mail.ru";
  String phoneNumber = "8005553535";
  String currentAddress = "tuchkovo";

//  static String name,
//          lastName,
//          email;

  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com/";
    Configuration.browserSize = "1920x1080";
    //  Configuration.holdBrowserOpen = true;

//    name = "Alex";
//    lastName = "Boom";
//    email = "egorov@mail.ru";
  }

  @BeforeEach
  void beforEach() {
//    name = getNewName();
//    lastName = getNewLastName();
//    email = getNewEmail();

  }


  @Test
  @Disabled
  void successfulRegistrationTest() {


    open("automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");
    $("#firstName").setValue(name);
    $("#lastName").setValue(lastName);
    //$("gender-radio-1").parent().click(); good
    //$("label[for=gender-radio-1]").click(); good
    //$(byText("Other")).click(); не очень хороший вариант
    $("#genterWrapper").$(byText(gender)).click();
    $("#userNumber").setValue(phoneNumber);
    $("#userEmail").setValue(email);


    // календарь
    $("#dateOfBirthInput").click();
    $(".react-datepicker__year-select").selectOption("2008");
    $(".react-datepicker__month-select").selectOption("July");
    //$(".react-datepicker__month-select").selectOptionByValue("6");
    $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

    // subjects
    $("#subjectsInput").setValue(subject).pressEnter();

    //hobbies
    $("#hobbiesWrapper").$(byText(hobbies)).click();

    //file
    // $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png")); оба варианта ок
    $("#uploadPicture").uploadFromClasspath("img/1.png");

    //State and city
    $("#currentAddress").setValue(currentAddress);
    //$("#state").click();
    $("#stateCity-wrapper").$(byText("Select State")).click();
    $("#stateCity-wrapper").$(byText("NCR")).click();
    //$("#react-select-3-option-0").click();
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
