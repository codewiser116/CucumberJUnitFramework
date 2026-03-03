package steps;

import io.cucumber.java.en.*;

import java.util.List;

public class DatatablePracticeSteps {

    @Given("the following clinic departments exist")
    public void the_following_clinic_departments_exist(io.cucumber.datatable.DataTable dataTable) {
        List<String> departments = dataTable.asList();

        for (int i = 0; i < departments.size(); i++){
            System.out.println(departments.get(i));
        }
    }


    @When("patient selects {string} in each department")
    public void patient_selects_in_each_department(String desease, io.cucumber.datatable.DataTable dataTable) {

    }

    @When("patient selects {int} appointments for doctor {string} at hospital {string}")
    public void patient_selects_appointments_for_doctor_at_hospital(Integer numOfAppointments, String doctorName, String hospital) {

    }

    /*
    Sarah	friend
    John	father
    Yzat	husband

    List <List<String>> = list1, list2, list3

    List <String> list1 = Sarah, friend
    List <String> list2 = John, father
    List <String> list3 = Yzat, husband

    =================================

    List<Map<String, String>> = List <map1, map2, map3>
    Map<String, String> map1 = key: Sarah, value: friend
    Map<String, String> map2 = key: John, value: father
    Map<String, String> map3 = key: Yzat, value: husband


     */

    @When("patient selects guests list")
    public void patient_selects_guests_list(io.cucumber.datatable.DataTable dataTable) {

        List<List<String>> data = dataTable.asLists();

        for (List<String> patientInfo : data){
            for (String str : patientInfo){
                System.out.println(str);
            }
        }


    }



}
