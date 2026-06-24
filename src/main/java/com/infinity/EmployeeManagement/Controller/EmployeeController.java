package com.infinity.EmployeeManagement.Controller;

import com.infinity.EmployeeManagement.DTO.EmployeeDTO;
import com.infinity.EmployeeManagement.Model.Employee;
import com.infinity.EmployeeManagement.Service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "")
public class EmployeeController {
    private EmployeeService service;
    @Autowired
    public void getEmployeeService(EmployeeService service){
        this.service=service;
    }

    @GetMapping("/employees")
    @Operation(summary = "get all the employees(Dept is optional)")
    public List<Employee> getAllEmployees(@RequestParam (required = false) String dept){
       if(dept!=null){
           List<Employee> employees=service.getEmployeesBydept(dept);
           return employees;
       }
       return service.getAllEmployees();
    }

    @PostMapping("/employees")
    @Operation(summary = "Add new Employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        System.out.println("Enter");
        Employee employee1=service.addEmployee(employee);
        System.out.println("Completed");
        return ResponseEntity.ok(employee1);
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "Search employee by Id")
    public EmployeeDTO getEmployeeById(@PathVariable int id){
        EmployeeDTO employee=service.getEmployeeById(id);
        return employee;
    }

    @PutMapping("/employees/{id}")
    @Operation(summary = "Update the employee")
    public ResponseEntity<?> incrementSalary(@PathVariable int id){
        service.updateEmployee(id);
        return ResponseEntity.ok("Employee with id: "+id+" got 10% hike");
    }

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Delete Employee By Id")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id: "+id+" deleted");
    }

    @GetMapping("/page")
    @Operation(summary = "get BY page")
    public List<Employee> getEmployees(@RequestParam(required = false, defaultValue="1") int page,
                                       @RequestParam (required = false, defaultValue="1") int size,
                                       @RequestParam (required=false,defaultValue="id")String sortBy,
                                       @RequestParam (required=false,defaultValue="ASC")String sortDir,
                                       @RequestParam(required = false) String search){
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }
       return service.fetchEmployees(PageRequest.of(page-1, size,sort),search);
    }

}
