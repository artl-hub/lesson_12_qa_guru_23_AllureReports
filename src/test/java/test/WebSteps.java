package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final  String ISSUE = "84";

    @Step ("Открываем главную страницу ")
    public  void openMainPage() {
        open("https://github.com");

    }

    @Step("Ищем репозиторий {repo}")
    public void  searchForRepository(String repo){
        $("[data-target=\"qbsearch-input.inputButtonText\"]").click();// У меня отличается селектор
        $("input[id='query-builder-test']").sendKeys(REPOSITORY);
        $("input[id='query-builder-test']").submit();

    }

    @Step ("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(REPOSITORY)).click();

    }

    @Step("Открываем таб Issue")
    public void openIssuesTab() {
        $("#issues-tab").click();

    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void  shouldSeeIssueWithNumber(String issue) {
        $(withText(ISSUE)).should(Condition.exist);

    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


//    @Test
//    public void testAnnotatedStep() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//        WebSteps steps = new WebSteps();
//
//        steps.openMainPage();
//        steps.searchForRepository(REPOSITORY);
//        steps.clickOnRepositoryLink(REPOSITORY);
//        steps.openIssuesTab();
//        steps.openIssuesTab();
//        steps.shouldSeeIssueWithNumber(ISSUE);
//    }



}
