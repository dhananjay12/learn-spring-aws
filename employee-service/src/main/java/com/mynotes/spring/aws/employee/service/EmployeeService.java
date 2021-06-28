package com.mynotes.spring.aws.employee.service;

import com.mynotes.spring.aws.employee.controller.mapper.EmployeeMapper;
import com.mynotes.spring.aws.employee.dto.EmployeeDTO;
import com.mynotes.spring.aws.employee.exceptions.EntityNotFoundException;
import com.mynotes.spring.aws.employee.exceptions.ValidationException;
import com.mynotes.spring.aws.employee.persistence.EmployeeEntity;
import com.mynotes.spring.aws.employee.persistence.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true) // spring one, not javax
    public Optional<EmployeeEntity> find(int id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity update(int id, EmployeeDTO employeeDTO) throws EntityNotFoundException {

        EmployeeEntity employeeEntity = findEmployeeById(id);
        employeeEntity.setFname(employeeDTO.getFname());
        employeeEntity.setLname(employeeDTO.getLname());
        employeeEntity.setEmail(employeeDTO.getEmail());
        return employeeRepository.save(employeeEntity);
    }

    public void delete(int id) throws EntityNotFoundException {
        EmployeeEntity result = findEmployeeById(id);
        employeeRepository.deleteById(id);
    }


    private EmployeeEntity findEmployeeById(Integer id) throws EntityNotFoundException {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
    }
}
