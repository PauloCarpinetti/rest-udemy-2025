package com.github.paulocarpinetti.rest_udemy_2025.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Relation(collectionRelation = "people")
@JsonPropertyOrder({"id", "address", "firstName", "lastName", "gender"})
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private boolean enabled;

    public PersonDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        if (!super.equals(o)) return false;
        return isEnabled() == personDTO.isEnabled() && Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFirstName(), getLastName(), getAddress(), getGender(), isEnabled());
    }
}
