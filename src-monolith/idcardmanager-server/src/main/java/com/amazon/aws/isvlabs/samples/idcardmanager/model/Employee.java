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
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Simple JavaBean domain object representing an Employee.
 */
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "firstName")
    @NotEmpty
    protected String firstName;

    @Column(name = "Surname")
    @NotEmpty
    protected String surname;

    @Column(name = "corporateEmail")
    @NotEmpty
    private String corporateEmail;

    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "cardNumber")
    @NotEmpty
    protected Integer cardNumber;

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

    public Integer getCardNumber() {
        return this.cardNumber;
    }
    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", this.getId())
            .append("firstName", this.getFirstName())
            .append("surname", this.getSurname())
            .append("corporateEmail", this.corporateEmail)
            .append("birthDate", this.birthDate)
            .append("cardNumber", this.cardNumber)
            .append("photo", this.photo)
            .toString();
    }
}
