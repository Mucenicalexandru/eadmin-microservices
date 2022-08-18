package com.eadmin.user.service.service;

import com.eadmin.user.service.model.Department;
import com.eadmin.user.service.repository.DepartmentRepository;
import com.eadmin.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
}
