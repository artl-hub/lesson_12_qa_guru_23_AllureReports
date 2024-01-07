package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeAll
    static void beforAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
//        Configuration.timeout = 5000;
    }

    @Test
    public void testIssuSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("[data-target=\"qbsearch-input.inputButtonText\"]").click();
        $("input[id='query-builder-test']").sendKeys("eroshenkoam/allure-example");
        $("input[id='query-builder-test']").submit();


        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#84")).should(Condition.exist);
    }
}
