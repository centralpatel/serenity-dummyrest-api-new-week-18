package com.restapiexample.userinfo;

import com.restapiexample.employeeinfo.EmployeeSteps;
import com.restapiexample.testbase.TestBase;
import com.restapiexample.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class EmployeeCURDTestWithSteps extends TestBase {
    static String employee_name = "Nehal" + TestUtils.getRandomValue();
    static String employee_salary = "70000";
    static String employee_age="32";
    static int employeeId;

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        ValidatableResponse response=employeeSteps.createEmployee(employee_name,employee_salary,employee_age);
        response.log().all().statusCode(200);
    }

    @Title("Verify if the employee was added to the application")
    @Test
    public void test002() {
        employee_name="Garrett Winters";
        HashMap<String, Object> employeeMap = employeeSteps.getEmployeeInfoByEmployeeName(employee_name);
        Assert.assertThat(employeeMap, hasValue(employee_name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);
    }
    @Title("Update the employee information and verify the updated information")
    @Test
    public void test003(){
        employee_name = "Neha";
        employee_salary="85000";
        employee_age="33";
        employeeId=5;
        employeeSteps.updateEmployee(employeeId,employee_name,employee_salary,employee_age).log().all().statusCode(200);
    }

    @Title("Delete the employee and verify if the employee is deleted!")
    @Test
    public void test004(){
        employeeSteps.deleteEmployee(employeeId).statusCode(200).log().status();

    }

}
