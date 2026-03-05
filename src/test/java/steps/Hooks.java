package steps;

import io.cucumber.java.*;
import pages.DashboardPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class Hooks extends BaseUI {

    @Before
    public void setup(){
        Driver.getDriver().get(ConfigurationReader.getProperty("loginURL"));
    }

    @After // runs after each scenario
    public void cleanup(){
        waitAndClick(new DashboardPage().logoutButton);
    }

    @BeforeAll
    public static void beforeAll(){
        System.out.println("this is BEFORE ALL ======= ");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("this is AFTER ALL ======= ");
    }

    @BeforeStep
    public void beforeStep(){
        System.out.println("this is before step ------ ");
    }

    @AfterStep
    public void afterStep(){
        System.out.println("this is after step ------ ");
    }


}
