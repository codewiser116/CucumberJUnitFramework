package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;
import utils.Driver;

import java.util.List;

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
}
