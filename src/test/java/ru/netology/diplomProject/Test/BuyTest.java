package ru.netology.diplomProject.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diplomProject.data.DataHelper;
import ru.netology.diplomProject.data.SQLHelper;
import ru.netology.diplomProject.page.Buy;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.diplomProject.data.SQLHelper.getOrderCount;

public class BuyTest {

    String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
        buy.buyCard();
    }

    @BeforeAll
    static void setAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanDataBase() {
        SQLHelper.cleanDatabase();
    }

    public static Buy buy = new Buy();

    @Test
    @DisplayName("01_Карта одобрена (статус APPROVED)")
    public void shouldSuccessfulPurchase() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.buySuccess();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("02_Карта отклонена (статус DECLINED)")
    public void shouldUnsuccessfulPurchase() {
        buy.setCardNumber(DataHelper.getDeclinedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.buyError();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("03_Не заполнен номер карты")
    public void shouldErrorEmptyCardNumber() {
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("04_Карта одобрена (статус APPROVED), не заполнен месяц")
    public void shouldErrorEmptyMonth() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("05_Карта одобрена (статус APPROVED), не заполнен год")
    public void shouldErrorEmptyYear() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("06_Карта одобрена (статус APPROVED), не заполнен Владелец")
    public void shouldErrorEmptyCardHolder() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.incorrectFormatHidden();
        buy.fieldNecessarily();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("07_Карта одобрена (статус APPROVED), не заполнен код CVC")
    public void shouldErrorEmptyCvc() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("08_Не корректный номер карты")
    public void shouldErrorIncorrectCardNumber() {
        buy.setCardNumber(DataHelper.getCardNumber15Digits());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("09_Карта одобрена (статус APPROVED), срок карты истёк")
    public void shouldErrorCardExpired() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumberLessThanThisMonth());
        buy.setCardYear(DataHelper.getCurrentYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormatHidden();
        buy.cardExpirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("10_Карта одобрена (статус APPROVED), не валидный месяц")
    public void shouldErrorMonthInvalid() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getNumberFrom13To99());
        buy.setCardYear(DataHelper.getCurrentYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormatHidden();
        buy.cardExpirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("11_Карта одобрена (статус APPROVED), некорректный месяц")
    public void shouldErrorMonthIncorrect() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.get1Digit());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("12_Карта одобрена (статус APPROVED), некорректный год")
    public void shouldErrorYearIncorrect() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.get1Digit());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("13_Карта одобрена (статус APPROVED), превышен срок карты")
    public void shouldErrorDeadlineExceeded() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getYearsAfterEndOfExpiration());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormatHidden();
        buy.cardExpirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("14_Карта одобрена (статус APPROVED), некорректный Владелец")
    public void shouldErrorIncorrectCardHolder() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getIncorrectCardHolder());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("15_Карта одобрена (статус APPROVED), короткое имя Владельца")
    public void shouldErrorShotNameCardHolder() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getShotName());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("16_Карта одобрена (статус APPROVED), длинное имя Владельца")
    public void shouldErrorLongNameCardHolder() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getLongName());
        buy.setCardCvv(DataHelper.get3Digits());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("17_Карта одобрена (статус APPROVED), некорректный код CVC")
    public void shouldErrorIncorrectCvc() {
        buy.setCardNumber(DataHelper.getApprovedCard());
        buy.setCardMonth(DataHelper.getMonthNumber());
        buy.setCardYear(DataHelper.getValidYear());
        buy.setCardholder(DataHelper.getNameCardholder());
        buy.setCardCvv(DataHelper.get000());
        buy.clickContinueButton();
        buy.fieldNecessarilyHidden();
        buy.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

}
