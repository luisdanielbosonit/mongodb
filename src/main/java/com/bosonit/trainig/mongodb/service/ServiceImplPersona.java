package com.bosonit.trainig.mongodb.service;

import com.bosonit.trainig.mongodb.Exception.EntityNotFoundException;
import com.bosonit.trainig.mongodb.Exception.UnprocessableEntityException;
import com.bosonit.trainig.mongodb.entity.Persona;
import com.bosonit.trainig.mongodb.entity.PersonaINputDto;
import com.bosonit.trainig.mongodb.entity.PersonaOUTputDto;

import com.bosonit.trainig.mongodb.repository.PersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ServiceImplPersona implements ServicePersona {

    @Autowired
    private PersonaDao personaDao;

    @Override
    public PersonaOUTputDto viewId(String id) {
        Persona persona= personaDao.findById(id).orElseThrow(()-> {throw new EntityNotFoundException("The person with username, ID:  "+id+" does not exist in the database", HttpStatus.FOUND,new Date());});
        return new PersonaOUTputDto(persona);
    }


    @Override
    public PersonaOUTputDto update(String id, PersonaINputDto personaINputDto) throws Exception{

        Optional<Persona> personaActu= Optional.ofNullable(personaDao.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("The person with Id: " + id + " does not exist in the database", HttpStatus.FOUND, new Date());
        }));


        if(personaActu.isEmpty())
            throw new EntityNotFoundException("Person no exist",HttpStatus.FOUND,new Date());
        if (personaINputDto.getUsername()==null)
            throw new UnprocessableEntityException("User field can not be null",HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getUsername().length() > 10 || personaINputDto.getUsername().length() < 6)
            throw new UnprocessableEntityException("User field length is not between 6 and 10", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getPassword() == null)
            throw new UnprocessableEntityException("Password can not be null",HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getName() == null)
            throw new UnprocessableEntityException("Name can not be null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getSurname() == null)
            throw new UnprocessableEntityException("Surname can not be null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getCompany_email() == null)
            throw new UnprocessableEntityException("Company email is not null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(!personaINputDto.getCompany_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getPersonal_email() == null)
            throw new UnprocessableEntityException("Company email is not null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(!personaINputDto.getPersonal_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getCity() == null)
            throw new UnprocessableEntityException("City can not be null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if(personaINputDto.getCreated_date() == null)
            throw new UnprocessableEntityException("Created_date can not be null", HttpStatus.UNPROCESSABLE_ENTITY,new Date());
        if (personaINputDto.getTermination_date()==null)
            throw new UnprocessableEntityException("Termination_date can not be null",HttpStatus.UNPROCESSABLE_ENTITY,new Date());

        Persona persona = personaActu.get();

        persona.setUsername(personaINputDto.getUsername());
        persona.setPassword(personaINputDto.getPassword());
        persona.setName(personaINputDto.getName());
        persona.setSurname(personaINputDto.getSurname());
        persona.setCompany_email(personaINputDto.getCompany_email());
        persona.setPersonal_email(personaINputDto.getCompany_email());
        persona.setCity(personaINputDto.getCity());
        persona.setActive(personaINputDto.getActive());
        persona.setCreated_date(personaINputDto.getCreated_date());
        persona.setImagen_url(personaINputDto.getImagen_url());
        persona.setTermination_date(personaINputDto.getTermination_date());


        personaDao.save(persona);

        return new PersonaOUTputDto(persona);
    }

    @Override
    public void delete(String id) {

        Persona persona= personaDao.findById(id).orElseThrow(()-> {throw new EntityNotFoundException("The person with Id: "+id+" does not exist in the database", HttpStatus.FOUND,new Date());});
        personaDao.delete(persona);
    }

    @Override
    public List<PersonaOUTputDto> viewall() {
        List<PersonaOUTputDto> listaperson=new ArrayList<>();
        personaDao.findAll().stream().map(PersonaOUTputDto::new).forEach(personOutputDto -> listaperson.add(personOutputDto));
        return listaperson;
    }

    @Override
    public PersonaOUTputDto loadpersona(PersonaINputDto personaINputDto){
        Persona persona= personaINputDto.transformIntoPersona();
        personaDao.save(persona);
        return new PersonaOUTputDto(persona);
    }

    @Override
    public Page<PersonaOUTputDto> personaPageable(Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "surname");
        Pageable pageable = PageRequest.of(page, 3, sort);

        Page<Persona> peoplePage = personaDao.findAll(pageable);
        List<PersonaOUTputDto> personOutputDtoList = peoplePage.stream().map(PersonaOUTputDto::new).collect(Collectors.toList());

        return new PageImpl<>(personOutputDtoList, pageable, personOutputDtoList.size());
    }

}
