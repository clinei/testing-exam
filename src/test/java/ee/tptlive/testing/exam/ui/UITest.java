package ee.tptlive.testing.exam.ui;


import org.junit.Ignore;
import org.junit.Test;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byLinkText;

public class UITest {

    @Test
    public void clickingOnCarIDGoesToCarDetails() {
        // given
        open("http://localhost:8082/carList.jsf");

        // when
        $(byLinkText("1")).click();

        // then
        $("form").shouldHave(text("ID:\t1"));
    }

    @Test
    public void editingCarChangesCarDetails() {
        // given
        open("http://localhost:8082/carDetails.jsf?id=1");

        // when
        $(byLinkText("Edit")).click();
        $(byName("car-form:model")).setValue("9001x");
        $(byName("car-form:year")).setValue("2001");
        $(byName("car-form:type")).selectOption("ELECTRIC");
        $(byName("car-form:available")).setSelected(false);
        $("#submitBtn").click();

        // then
        $("form").shouldHave(text("Model:\t9001x"));
        $("form").shouldHave(text("Year:\t2001"));
        $("form").shouldHave(text("Engine:\tELECTRIC"));
        $("form").shouldHave(text("Available:\tNO"));
    }

    @Test
    public void carDetails_clickingBackGoesToCarList() {
        // given
        open("http://localhost:8082/carDetails.jsf?id=1");

        // when
        $(byLinkText("Back")).click();

        // then
        $("h1").shouldHave(text("CARS"));
    }
}