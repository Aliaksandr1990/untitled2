import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import data.LOCALE;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestRRRdTEst {


   @BeforeAll
   static void setUp(){
       System.setProperty("browser", "chrome");
       UIDriver.configure();
    }

    @Test
    @Tag("API")
    void test111111() {
        open("https://github.com/");
        $("span[data-target='qbsearch-input.inputButtonText']").shouldHave(text("Search or jump to..."));

    }


    @Test
    @Tag("UI")
    void test2222222() {
        open("https://github.com/");
        $("span[data-target='qbsearch-input.inputButtonText']").shouldHave(text("Search or jump to..."));

    }
}
