package pages;

import com.codeborne.selenide.SelenideElement;
import data.Subjects;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
  CalendarComponent calendarComponent = new CalendarComponent();
  RegistrationResultModal registrationResultModal = new RegistrationResultModal();

  private final String TITLE_TEXT = "Student Registration Form";

  private SelenideElement
          lastNameInput = $("#lastName"),
          firstNameInput = $("#firstName"),
          userEmail = $("#userEmail"),
          gender = $("#genterWrapper"),
          userPhoneNumber = $("#userNumber"),
          dateOfBirthInput = $("#dateOfBirthInput"),
          subjectInput = $("#subjectsInput"),
          hobbiesChoose = $("#hobbiesWrapper"),
          uploadPictureButton = $("#uploadPicture"),

  setAddressInput = $("#currentAddress"),
          selectStatePopUp = $("#state"),
          selectState = $("#stateCity-wrapper"),
          selectCityPopUp = $("#city"),
          selectCity = $("#stateCity-wrapper"),
          submitButton = $("#submit");


  public RegistrationPage openPage() {
    open("automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");
    return this;
  }

  public RegistrationPage setFirstName(String value) {
    firstNameInput.setValue(value);
    return this;
  }

  public RegistrationPage setLastName(String value) {
    lastNameInput.setValue(value);
    return this;
  }

  public RegistrationPage clearLastName() {
    lastNameInput.clear();
    return this;
  }

  public RegistrationPage setEmail(String value) {
    userEmail.setValue(value);
    return this;
  }

  public RegistrationPage setGender(String value) {
    gender.$(byText(value)).click();
    return this;
  }

  public RegistrationPage setPhone(String value) {
    userPhoneNumber.setValue(value);
    return this;
  }

  public RegistrationPage setBirthDate(String day, String month, String year) {
    dateOfBirthInput.click();
    calendarComponent.setDate(day, month, year);
    return this;
  }

  public RegistrationPage verifyResultModalAppears() {
    registrationResultModal.verifyModalAppears();
    return this;
  }

  public RegistrationPage verifyResult(String key, String value) {
    registrationResultModal.verifyResult(key, value);
    return this;
  }

  public RegistrationPage setSubjects(String value) {
    subjectInput.setValue(value).pressEnter();
    return this;
  }

  public RegistrationPage setHobbies(String value) {
    hobbiesChoose.$(byText(value)).click();
    return this;
  }

  public RegistrationPage uploadPhoto(String text) {
    uploadPictureButton.uploadFromClasspath("img/"+text);
    return this;
  }
  public RegistrationPage setAddress(String text) {
    setAddressInput.setValue(text);
    return this;
  }

  public RegistrationPage selectState(String text) {
    selectStatePopUp.click();
    selectState.$(byText(text)).click();
    return this;
  }

  public RegistrationPage selectCity(String text) {
    selectCityPopUp.click();
    selectCity.$(byText(text)).click();
    return this;
  }
  public RegistrationPage clickSubmitButton() {
    submitButton.click();
    return this;
  }

}
