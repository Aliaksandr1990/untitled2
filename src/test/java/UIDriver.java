import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import config.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import java.sql.SQLOutput;
import java.util.HashMap;

public class UIDriver {



    public static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = WebDriver.config.getBrowser();
        Configuration.timeout = 5000;
        MutableCapabilities capabilities = new DesiredCapabilities();




        switch (WebDriver.config.getBrowser()) {
            case "chrome":
                setChromeOptions(capabilities);
                break;
            case "safari":
                setSafariOptions(capabilities);
                break;
            default:
                Configuration.browserCapabilities = capabilities;
        }
    }

    public static void setChromeOptions(MutableCapabilities capabilities) {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-notifications")
                .addArguments("--lang=en-US")
                .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"})
                .merge(capabilities);
    }

    public static void setSafariOptions(MutableCapabilities capabilities) {
        SafariOptions options = new SafariOptions()
                .merge(capabilities);

        // ⚠️ Ограничения Safari:
        // - Нет аналогов --no-sandbox, --disable-infobars, --disable-popup-blocking
        // - Язык браузера задаётся через системные настройки macOS или заголовки Accept-Language
        // - Уведомления и попапы управляются вручную через настройки системы

        // Доступные опции Safari:
        options.setAutomaticInspection(false);   // отключить автоматический инспектор WebKit
        options.setAutomaticProfiling(false);    // отключить автоматический профайлинг

        // Пример: принятие небезопасных сертификатов (если поддерживается драйвером)
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        // Для локализации: можно попробовать передать заголовок через прокси или расширение,
        // но напрямую через SafariOptions это не поддерживается.
        // Альтернатива — задать язык системы перед запуском тестов:
        //   defaults write -g AppleLanguages '("en-US")'

        Configuration.browserCapabilities = options;
    }
}
