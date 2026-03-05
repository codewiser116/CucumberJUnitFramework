package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseUI;
import utils.Driver;

import java.util.List;

import static utils.Driver.driver;

public class PatientsPage extends BaseUI {

    public PatientsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public static final String URL = "https://codewise-clinic-portal.lovable.app/patients";

    @FindBy(xpath = "//a[text()='Patients']")
    public WebElement patientTab;

    @FindBy(xpath = "//button[@data-testid='filter-gender']")
    public WebElement genderDropdown;

    @FindBy(xpath = "//span[text()='Female']")
    public WebElement femaleOption;

    @FindBy(xpath = "//span[text()='Male']")
    public WebElement maleOption;

    @FindBy(xpath = "//span[text()='Other']")
    public WebElement otherOption;

    @FindBy(xpath = "//tbody[@class='[&_tr:last-child]:border-0']//tr/td[3]")
    public List<WebElement> patientsGenderList;

    public void selectGender(String gender){
        waitAndClick(genderDropdown);
        waitAndClick(Driver.getDriver().findElement(By.xpath("//span[text()='" + gender + "']")));
    }
    @FindBy (xpath = "//button[text()=' New Patient']")
    public WebElement newPatientButton;

    @FindBy (id = "first_name")
    public WebElement firstNameInput;

    @FindBy (id = "last_name")
    public WebElement lastNameInput;

    @FindBy (id = "dob")
    public WebElement dobInput;

    @FindBy (id = "phone")
    public WebElement phoneInput;

    @FindBy (xpath = "//button[text()='Create Patient']")
    public WebElement createPatientButton;

    @FindBy (css = "input[data-testid='patients-search']")
    public WebElement searchPatientInput;

    @FindBy (xpath = "//label[contains(text(),'Gender')]/following-sibling::button")
    public WebElement genderDropdownForNewPatient;

    public boolean isNamePresentInTable(String name){
        explicitWait(5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table//td[text()='" + name + "'])[1]")));
        return driver.findElement(By.xpath("(//table//td[text()='" + name + "'])[1]")).getText().contains(name);
    }

    public void selectGenderForNewPatient(String gender) {
        waitAndClick(genderDropdownForNewPatient);
        By optionLocator = By.xpath("//div[@role='option' and normalize-space()='" + gender + "']");

        WebElement option = explicitWait(20).until(ExpectedConditions.elementToBeClickable(optionLocator));
        jsClick(option);
    }
}
