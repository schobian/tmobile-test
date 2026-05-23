package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            System.out.println("Test nie przeszedł: " + scenario.getName());
        }

        DriverManager.quitDriver();
    }
}