    package com.infinity.EmployeeManagement.Service;

import com.infinity.EmployeeManagement.DTO.EmployeeDTO;
import com.infinity.EmployeeManagement.Exceptions.EmployeeNotFoundException;
import com.infinity.EmployeeManagement.Model.Employee;
import com.infinity.EmployeeManagement.Repository.EmployeeRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepo repo;
    public EmployeeService(EmployeeRepo repo){
        this.repo=repo;
    }


    public List<Employee> getAllEmployees(){
      return repo.findAll();
    }

    public Employee addEmployee(Employee employee) {
      boolean validation= validateName(employee.getName());
      if(validation){
          return repo.save(employee);
      }
      else {
          throw new RuntimeException("Invalid Employee Name");
      }
    }

    private boolean validateName(String name){
        return !name.isEmpty()&&name!=null;
    }

    public EmployeeDTO getEmployeeById(int id) {
      Employee employee= repo.findById(id)
              .orElseThrow(()->new EmployeeNotFoundException("Employee with "+id+" is not found"));
      EmployeeDTO emp=new EmployeeDTO();
      emp.setName(employee.getName());
      emp.setDept(employee.getDept());
        return emp;
    }

    public List<Employee> getEmployeesBydept(String dept) {
        List<Employee> employees=repo.findByDept(dept);
        if(employees.isEmpty()){
            throw new EmployeeNotFoundException("There no employee in the department "+dept);
        }
        return employees;
    }

    public void updateEmployee(int id) {
        Employee employee=repo.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee with id: "+id+" not found"));
        double hike=employee.getSalary()*0.1;
        employee.setSalary(employee.getSalary()+hike);
        repo.save(employee);
    }

    public void deleteEmployee(int id) {
//     Employee employee=repo.findById(id)
//             .orElseThrow(()->new EmployeeNotFoundException("Employee with id: "+id+" not found"));
      repo.deleteById(id);
    }

    public List<Employee> fetchEmployees(Pageable pageable,String search) {
        if(search==null){
            return repo.findAll(pageable).getContent();
        }else{
           return repo.findByNameContaining(search,pageable).getContent();
        }
    }
}
