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
package com.amazon.aws.isvlabs.samples.idcardmanager.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Simple JavaBean domain object representing an Employee.
 *
 */
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    protected String firstName;

    @Column(name = "surname")
    @NotEmpty
    protected String surname;

    @Column(name = "corporate_email")
    @NotEmpty
    private String corporateEmail;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "card_number")
    @NotEmpty
    protected String cardNumber;

    @Column(name = "photo")
    @NotEmpty
    protected String photo;

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCorporateEmail() {
        return this.corporateEmail;
    }
    public void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Return the Employee with the given name, or null if none found for this Owner.
     *
     * @param surname to test
     * @return true if Employee surname is already in use
     *
    public Employee getEmployee(String surname) {
        return getEmployee(surname, false);
    }
    */

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     *
    public Employee getEmployee(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Employee employee : getEmployeesInternal()) {
            if (!ignoreNew || !employee.isNew()) {
                String compName = employee.getSurname();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return employee;
                }
            }
        }
        return null;
    }
*/

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", this.getId())
            .append("firstName", this.getFirstName())
            .append("surname", this.getSurname())
            .append("corporateEmail", this.corporateEmail)
            .append("birthDate", this.birthDate)
            .append("card_number", this.cardNumber)
            .append("photo", this.photo)
            .toString();
    }
}
