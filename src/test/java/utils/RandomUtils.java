package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
  static Faker faker = new Faker();

  public static void main(String[] args) {
    System.out.println(randomString(10));
    System.out.println(randomInt(10, 100));
    System.out.println(randomEmail());

    String[] names = {"a", "b", "c", "d", "e"};
    System.out.println(randomItem(names));

  }

  public static int randomInt(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  public static String randomString(int len) {
    String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++)
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    return sb.toString();
  }

  public static String randomEmail() {
    return randomString(10) + "@qa.guru";
  }

  public static String randomItem(String[] values) {
    int index = randomInt(0, values.length - 1);
    return values[index];
  }
  public static String[] birthDayGenerator(int minAge, int maxAge) {
    String formattedDate = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(faker.date().birthday(minAge, maxAge));
    return formattedDate.split(" ");
  }

  public static String cityGenerator(String state) {
    String city = null;
    switch (state) {
      case "NCR":
        city = faker.options().option("Delhi", "Gurgaon", "Noida");
        break;
      case "Uttar Pradesh":
        city = faker.options().option("Agra", "Lucknow", "Merrut");
        break;
      case "Haryana":
        city = faker.options().option("Karnal", "Panipat");
        break;
      case "Rajasthan":
        city = faker.options().option("Jaipur", "Jaiselmer");
        break;
    }
    return city;
  }
}


