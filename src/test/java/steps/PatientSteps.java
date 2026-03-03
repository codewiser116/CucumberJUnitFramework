package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PatientsPage;
import utils.BaseUI;
import utils.Driver;

import java.util.List;

public class PatientSteps extends BaseUI {

    WebDriver driver = Driver.getDriver();
    PatientsPage patientsPage = new PatientsPage();

    @Given("user navigates to Patients page")
    public void user_navigates_to_patients_page() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().to(PatientsPage.URL);
    }

    @When("user selects {string} in All Genders dropdown")
    public void user_selects_in_all_genders_dropdown(String gender) {
        patientsPage.selectGender(gender);
    }

    @Then("verify {string} patients are displayed")
    public void verify_patients_are_displayed(String gender) {
        for (WebElement actualGender : patientsPage.patientsGenderList){
            Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
        }
    }

    @When("user selects gender verify patients display correct gender")
    public void user_selects_gender_verify_patients_display_correct_gender(DataTable dataTable) {
        List<String> genders = dataTable.asList();

        for (String gender : genders){
            patientsPage.selectGender(gender);

            for (WebElement actualGender : patientsPage.patientsGenderList){
                Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
            }
        }
    }
}
