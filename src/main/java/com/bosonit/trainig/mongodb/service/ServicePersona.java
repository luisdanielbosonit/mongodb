package com.bosonit.trainig.mongodb.service;

import com.bosonit.trainig.mongodb.entity.PersonaINputDto;
import com.bosonit.trainig.mongodb.entity.PersonaOUTputDto;

import org.springframework.data.domain.Page;


import java.util.List;

public interface ServicePersona {

    public PersonaOUTputDto viewId(String id) throws Exception; //ok

    PersonaOUTputDto update(String id, PersonaINputDto personaINputDto) throws Exception;//ok

    public void delete(String id) throws Exception; //ok

    public List<PersonaOUTputDto> viewall();

    public PersonaOUTputDto loadpersona(PersonaINputDto personaINputDto)throws Exception; //ok

//    public Page<PersonaOUTputDto> personaPageable(PersonaPage personaPage, PersonaINputDto personaINputDto);
//
    Page<PersonaOUTputDto> personaPageable(Integer page);

}
