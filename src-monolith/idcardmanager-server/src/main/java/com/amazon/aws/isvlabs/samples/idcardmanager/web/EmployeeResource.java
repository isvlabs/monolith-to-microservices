/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amazon.aws.isvlabs.samples.idcardmanager.web;

import com.amazon.aws.isvlabs.samples.idcardmanager.model.Employee;
import com.amazon.aws.isvlabs.samples.idcardmanager.service.ClinicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class EmployeeResource extends AbstractResourceController {

    private final ClinicService clinicService;

    @Autowired
    public EmployeeResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    private Employee retrieveEmployee(int employeeId) {
        return this.clinicService.findEmployeeById(employeeId);
    }

    /**
     * Create Employee
     */
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwner(@Valid @RequestBody Employee employee) {
    	this.clinicService.saveEmployee(employee);
    }

    /**
     * Read single Employee
     */
    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable("employeeId") int employeeId) {
        return retrieveEmployee(employeeId);
    }

    /**
     * Read List of Employees
     */
    @GetMapping("/employees/list")
    public Collection<Employee> findAll() {
        return clinicService.findAll();
    }

    /**
     * Update Employee
     */
    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable("employeeId") int employeeId, @Valid @RequestBody Employee employeeRequest) {
    	Employee employeeModel = retrieveEmployee(employeeId);

    	// This is done by hand for simplicity purpose. In a real life use-case we should consider using MapStruct.
        employeeModel.setFirstName(employeeRequest.getFirstName());
        employeeModel.setSurname(employeeRequest.getSurname());
        employeeModel.setCorporateEmail(employeeRequest.getCorporateEmail());
        employeeModel.setBirthDate(employeeRequest.getBirthDate());
        employeeModel.setCardNumber(employeeRequest.getCardNumber());
        employeeModel.setPhoto(employeeRequest.getPhoto());

        this.clinicService.saveEmployee(employeeRequest);

        return employeeModel;
    }
}
