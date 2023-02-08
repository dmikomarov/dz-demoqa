package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationWithPageObjectsTests extends TestBase {


  @Test
  void successfulRegistrationTest() {
    //strings
    String gender = "Other";
    String dateOfBirth = "30 July,2008";
    String subject = "Maths";
    String hobbies = "Sports";
    String pictureFIleName = "img/1.png";
    String stateAndCity = "NCR Delhi";
    String name = "Alex";
    String lastName = "Boom";
    String email = "egorov@mail.ru";
    String phoneNumber = "8005553535";
    String currentAddress = "tuchkovo";
    String state = "NCR";
    String city = "Delhi";

    registrationPage.openPage()
            .setFirstName(name)
            .setLastName()
            .setEmail(email)
            .setGender("Other")
            .setPhone(phoneNumber)
            .setBirthDate("30", "July", "2008")
            .setSubjects("Maths")
            .setHobbies("Sports")
            .uploadPhoto(pictureFIleName)
            .setAddress(currentAddress)
            .selectState(state)
            .selectCity(city)
            .clickSubmitButton();


    registrationPage.verifyResultModalAppears()
            .verifyResult("Student Name", name + "Egorov")
            .verifyResult("Student Email", email)
            .verifyResult("Gender", gender)
            .verifyResult("Mobile", phoneNumber)
            .verifyResult("Date of Birth", dateOfBirth)
            .verifyResult("Address", currentAddress)
            .verifyResult("Subjects", subject)
            .verifyResult("Hobbies", hobbies)
            .verifyResult("Picture", pictureFIleName)
            .verifyResult("State and City", stateAndCity);
    ;




}}
