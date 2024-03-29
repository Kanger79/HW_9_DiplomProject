package ru.netology.diplomProject.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diplomProject.data.DataHelper;
import ru.netology.diplomProject.data.SQLHelper;
import ru.netology.diplomProject.page.BuyInCredit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.diplomProject.data.SQLHelper.getOrderCount;

public class BuyInCreditTest {

    private BuyInCredit buyInCredit;

    String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
        buyInCredit = new BuyInCredit();
        buyInCredit.buyCredit();
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

    @Test
    @DisplayName("01_Карта одобрена (статус APPROVED)")
    public void shouldSuccessfulPurchase() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.buySuccess();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("02_Карта отклонена (статус DECLINED)")
    public void shouldUnsuccessfulPurchase() {
        buyInCredit.setCardNumber(DataHelper.getDeclinedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.buyError();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("03_Не заполнен номер карты")
    public void shouldErrorEmptyCardNumber() {
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("04_Карта одобрена (статус APPROVED), не заполнен месяц")
    public void shouldErrorEmptyMonth() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("05_Карта одобрена (статус APPROVED), не заполнен год")
    public void shouldErrorEmptyYear() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("06_Карта одобрена (статус APPROVED), не заполнен Владелец")
    public void shouldErrorEmptyCardHolder() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.incorrectFormatHidden();
        buyInCredit.fieldNecessarily();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("07_Карта одобрена (статус APPROVED), не заполнен код CVC")
    public void shouldErrorEmptyCvc() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("08_Не корректный номер карты")
    public void shouldErrorIncorrectCardNumber() {
        buyInCredit.setCardNumber(DataHelper.getCardNumber15Digits());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("09_Карта одобрена (статус APPROVED), срок карты истёк")
    public void shouldErrorCardExpired() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getYearNumberLessCurrentYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormatHidden();
        buyInCredit.cardExpired();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("10_Карта одобрена (статус APPROVED), не валидный месяц")
    public void shouldErrorMonthInvalid() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.get00());
        buyInCredit.setCardYear(DataHelper.getCurrentYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormatHidden();
        buyInCredit.cardExpirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("11_Карта одобрена (статус APPROVED), некорректный месяц")
    public void shouldErrorMonthIncorrect() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.get1Digit());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("12_Карта одобрена (статус APPROVED), некорректный год")
    public void shouldErrorYearIncorrect() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.get1Digit());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("13_Карта одобрена (статус APPROVED), превышен срок карты")
    public void shouldErrorDeadlineExceeded() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getYearsAfterEndOfExpiration());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormatHidden();
        buyInCredit.cardExpirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("14_Карта одобрена (статус APPROVED), некорректный Владелец")
    public void shouldErrorIncorrectCardHolder() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholderWithCyrillic());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("15_Карта одобрена (статус APPROVED), короткое имя Владельца")
    public void shouldErrorShotNameCardHolder() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getShotName());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("16_Карта одобрена (статус APPROVED), длинное имя Владельца")
    public void shouldErrorLongNameCardHolder() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getLongName());
        buyInCredit.setCardCvv(DataHelper.get3Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("17_Карта одобрена (статус APPROVED), некорректный код CVC - нули")
    public void shouldErrorCvcSetNulls() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get000());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    @DisplayName("18_Карта одобрена (статус APPROVED), некорректный код CVC - 2 цифры")
    public void shouldErrorCvcSetTwoDigit() {
        buyInCredit.setCardNumber(DataHelper.getApprovedCard());
        buyInCredit.setCardMonth(DataHelper.getMonthNumber());
        buyInCredit.setCardYear(DataHelper.getValidYear());
        buyInCredit.setCardholder(DataHelper.getNameCardholder());
        buyInCredit.setCardCvv(DataHelper.get2Digits());
        buyInCredit.clickContinueButton();
        buyInCredit.fieldNecessarilyHidden();
        buyInCredit.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

}
