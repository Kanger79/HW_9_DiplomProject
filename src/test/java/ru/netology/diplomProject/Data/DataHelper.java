package ru.netology.diplomProject.Data;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {

    public static Faker faker = new Faker(new Locale("ru"));

    public static String getApprovedCard() {
        return "4444444444444441";
    }

    public static String getDeclinedCard() {
        return "4444444444444442";
    }

    public static String getNewCardNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String getCardNumber15Digits() {
        return "444444444444444";
    }

    public static String get1Digit() {
        return faker.numerify("#");
    }

    public static String get2Digits() {
        return faker.numerify("##");
    }

    public static String get3Digits() {
        return faker.numerify("###");
    }

    public static String get00() {
        return "00";
    }

    public static String get000() {
        return "000";
    }

    public static String getMonthNumber() {
        LocalDate currentData = LocalDate.now();
        return currentData.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNumberFrom13To99() {
        int randomNumber = ThreadLocalRandom.current().nextInt(13, 100);
        return String.valueOf(randomNumber);
    }

    public static String getCurrentYear() {
        LocalDate currentYear = LocalDate.now();
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getValidYear() {
        double random = Math.random() * 5;
        int rnd = (int) random;
        LocalDate currentData = LocalDate.now();
        LocalDate currentYear = currentData.plusYears(rnd);
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getYearsAfterEndOfExpiration() {
        LocalDate currentData = LocalDate.now();
        LocalDate currentYear = currentData.plusYears(6);
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getYearNumberLessCurrentYear() {
        LocalDate currentData = LocalDate.now();
        LocalDate currentYear = currentData.minusYears(1);
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getMonthNumberLessThanThisMonth() {
        LocalDate currentData = LocalDate.now();
        LocalDate currentMonth = currentData.minusMonths(1);
        return currentMonth.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String transliterate(String text) {
        String[] rus = {"А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я", "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};
        String[] eng = {"A", "B", "V", "G", "D", "E", "Yo", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts", "Ch", "Sh", "Sht", "'", "I", "", "E", "Yu", "Ya", "a", "b", "v", "g", "d", "e", "yo", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sht", "’", "i", "", "e", "yu", "ya"};

        text = StringUtils.replaceEach(text, rus, eng);

        return text;
    }

    public static String getSpecSymbol() {
        String[] specSymbols = {
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "=", "{", "}", "[", "]", "<", ">", "/", "?"
        };
        return specSymbols[new Random().nextInt(specSymbols.length)];
    }

    public static String getShotName() {
        var shotNames = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "W", "Z"
        };
        return shotNames[new Random().nextInt(shotNames.length)];
    }

    public static String getLongName() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return transliterate(firstName)
                + transliterate(firstName)
                + transliterate(firstName)
                + " "
                + transliterate(lastName)
                + transliterate(lastName)
                + transliterate(lastName);
    }

    public static String getNameCardholder() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return transliterate(firstName) + " " + transliterate(lastName);
    }

    public static String getIncorrectCardHolder() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return transliterate(firstName) + getSpecSymbol() + " " + transliterate(lastName) + get2Digits();
    }

    public static String getNameCardholderWithCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + getSpecSymbol() + " " + faker.name().lastName();
    }

    public static String getNameCardholderWithNumbers() {
        return faker.name().firstName() + faker.numerify("#####");
    }

    public static String getNameCardholderWithInvalidSymbol() {
        return faker.name().firstName() + "!@_+*";
    }

}
