package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import data.Genders;
import data.Subjects;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static utils.RandomUtils.birthDayGenerator;
import static utils.RandomUtils.cityGenerator;

public class RegistrationWithPageObjectsTests extends TestBase {


  @Test
  void successfulRegistrationTest() {

    Faker faker = new Faker();

    String name = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String currentAddress = faker.address().fullAddress();
    String phoneNumber = String.valueOf(faker.number().numberBetween(9370000000L, 9379999999L));
    String day = String.format("%02d", faker.number().numberBetween(1, 28));
    String month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    String year = faker.number().numberBetween(1900, 2022) + "";
    String gender = faker.options().option("Male", "Female", "Other");
    String subject = faker.options().option("math", "art", "history");
    String hobbies = "Sports";
    String pictureFIleName = faker.options().option("1.png", "2.png");
    String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    String city = cityGenerator(state);



    registrationPage.openPage()
            .setFirstName(name)
            .setLastName(lastName)
            .setEmail(email)
            .setGender(gender)
            .setPhone(phoneNumber)
            .setBirthDate(day, month, year)
            .setSubjects(subject)
            .setHobbies(hobbies)
            .uploadPhoto(pictureFIleName)
            .setAddress(currentAddress)
            .selectState(state)
            .selectCity(city)
            .clickSubmitButton();


    registrationPage.verifyResultModalAppears()
            .verifyResult("Student Name", name + " " + lastName)
            .verifyResult("Student Email", email)
            .verifyResult("Gender", gender)
            .verifyResult("Mobile", phoneNumber)
            .verifyResult("Date of Birth", day + " " + month + "," + year)
            .verifyResult("Address", currentAddress)
            .verifyResult("Subjects", subject)
            .verifyResult("Hobbies", hobbies)
            .verifyResult("Picture", pictureFIleName)
            .verifyResult("State and City", String.format("%s %s", state, city));
    ;




}}
