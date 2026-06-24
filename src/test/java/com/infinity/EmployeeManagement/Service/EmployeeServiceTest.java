package com.infinity.EmployeeManagement.Service;

import com.infinity.EmployeeManagement.Model.Employee;
import com.infinity.EmployeeManagement.Repository.EmployeeRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepo repo;
    @InjectMocks
    EmployeeService service;

    static Employee employee=null;

    @BeforeAll
    public static void init(){
        System.out.println("Before All");
        employee=new Employee();
        employee.setId(1);
        employee.setName("Nireeksha");
        employee.setDept("AIML");
        employee.setSalary(10000);
    }

    @BeforeEach
    public void initEachTestCases(){
        System.out.println("Before Each");
    }


    @Test
    void addEmployeeShouldaddEmployeeSuccessfully(){

        Mockito.when(repo.save(employee)).thenReturn(employee);
        Employee addedEmployee=service.addEmployee(employee);
        Assertions.assertEquals(employee.getId(),addedEmployee.getId());

    }

    @Test
    void addEmployeeShouldThrowExceptionForInvalidEmployeeName(){

        employee.setName("");
        RuntimeException exception=Assertions.assertThrows(RuntimeException.class,()->
                service.addEmployee(employee));
        Assertions.assertEquals("Invalid Employee Name",exception.getMessage());
        Mockito.verify(repo,Mockito.never()).save(employee);
    }


    @Test
    void deleteEmployeeShoulddeleteEmployeeSuccessfully(){
        Mockito.doNothing().when(repo).deleteById(1);
        service.deleteEmployee(1);
        Mockito.verify(repo,Mockito.times(1)).deleteById(1);
    }

    @Test
    void testPrivateMethod_validName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
      Method validName=EmployeeService.class.getDeclaredMethod("validateName",String.class);
      validName.setAccessible(true);
      Boolean name= (Boolean) validName.invoke(service,"Nireeksha");
      Assertions.assertTrue(name);
    }
    @Test
    void testPrivateMethod_validNameInvalid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validName=EmployeeService.class.getDeclaredMethod("validateName",String.class);
        validName.setAccessible(true);
        Boolean name= (Boolean) validName.invoke(service,"");
        Assertions.assertFalse(name);
    }

    @AfterEach
    void cleanUp(){
        System.out.println("after each");
    }

    @AfterAll
    static void Destroy(){
        System.out.println("After all");
    }
}
