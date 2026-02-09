package data;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadTest {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void dowloadTest() throws IOException {
        open("https://github.com/NataliaGrischenkova/QaseAutoTests/blob/main/TEST_CASES.md");
        File download = $("[data-testid=\"raw-button\"]").download();
        try (InputStream is = new FileInputStream(download)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes);
            assertThat(textContent).contains("Successful login with valid credentials");
        }
    }

    @Test
    void uploadFile(){
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("JCA0ptvAFs.png");
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text("JCA0ptvAFs.png"));
    }
}
