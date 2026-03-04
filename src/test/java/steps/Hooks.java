package steps;

import io.cucumber.java.*;

public class Hooks {

    @Before // runs before each scenario
    public void setup(){
        System.out.println("this is before scenario");
    }

    @After // runs after each scenario
    public void cleanup(){
        System.out.println("this is after scenario");
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
