package com.bosonit.trainig.mongodb.controller;

import com.bosonit.trainig.mongodb.entity.PersonaINputDto;
import com.bosonit.trainig.mongodb.entity.PersonaOUTputDto;

import com.bosonit.trainig.mongodb.service.ServicePersona;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    ServicePersona servicepersona;

    @PostMapping("/addperson")
    public PersonaOUTputDto guardarpersona(@RequestBody PersonaINputDto personaINputDto) throws Exception {
        return servicepersona.loadpersona(personaINputDto);
    }

    @GetMapping("get/{id}")
    public PersonaOUTputDto show(@PathVariable String id) throws Exception {

        return servicepersona.viewId(id);

    }

    @PutMapping("/put/{id}")
    public PersonaOUTputDto modificarpersona(@PathVariable String id,@RequestBody PersonaINputDto personaINputDto) throws Exception{

        return servicepersona.update(id,personaINputDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deletepersona(@PathVariable String id) throws Exception {
        servicepersona.delete(id);
        }

    @GetMapping("/getall")
    public List<PersonaOUTputDto> viewall(){
        return (List<PersonaOUTputDto>) servicepersona.viewall();
    }

    @GetMapping("/getall/{page}")
    Page<PersonaOUTputDto> getAllPeople(@PathVariable Integer page){
        return servicepersona.personaPageable(page);
    }

}

