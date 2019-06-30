package com.blj.controller;

import com.blj.dao.DepartmentDao;
import com.blj.dao.EmployeeDao;
import com.blj.entities.Department;
import com.blj.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //员工列表
    @GetMapping("/emps")
    public  String emps(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //来到添加页面
    @GetMapping("/emp")
    public  String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //添加员工
    @PostMapping("/emp")
    public  String addEmp(Employee employee){
        System.out.println("保存的员工信息："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //来到编辑页面
    @GetMapping("/emp/{id}")
    public  String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public  String uodateEmp(Employee employee){
        System.out.println("修改的员工信息："+employee);
       // employeeDao.save(employee);
        return "redirect:/emps";
    }

}
