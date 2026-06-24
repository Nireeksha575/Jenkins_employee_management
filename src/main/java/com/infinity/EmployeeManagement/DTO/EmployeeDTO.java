package com.infinity.EmployeeManagement.DTO;

public class EmployeeDTO {
    private String name;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String dept;
}
