package com.bosonit.trainig.mongodb.entity;

import lombok.Data;

import java.util.Date;

/**
 * A DTO for the {@link Persona} entity
 */
@Data
public class PersonaINputDto {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public Persona transformIntoPersona(){
        Persona person = new Persona();
        person.setUsername(this.username);
        person.setPassword(this.password);
        person.setName(this.name);
        person.setSurname(this.surname);
        person.setCompany_email(this.company_email);
        person.setPersonal_email(this.personal_email);
        person.setCity(this.city);
        person.setActive(this.active);
        person.setCreated_date(this.created_date);
        person.setImagen_url(this.imagen_url);
        person.setTermination_date(this.termination_date);

        return person;
    }


    public boolean getPersona() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }
}