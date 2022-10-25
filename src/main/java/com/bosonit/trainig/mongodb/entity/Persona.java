package com.bosonit.trainig.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Persona  {

    @Id
    private String id_persona;
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



    public Boolean getActive() {
        return true;
    }
}






