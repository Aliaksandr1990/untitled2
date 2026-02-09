package config;

import org.aeonbits.owner.ConfigFactory;

public class WebDriver {

    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    static {
        System.out.println("Базовый УРЛ " + config.getBaseUrl());
        System.out.println("Browser " + config.getBrowser());
    }
}
