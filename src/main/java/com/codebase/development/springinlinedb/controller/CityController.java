package com.codebase.development.springinlinedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.development.springinlinedb.model.City;
import com.codebase.development.springinlinedb.service.ICityService;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private ICityService cityService;

    @RequestMapping("/cities")
    public List<City> findCities() {

        return cityService.findAll();
    }

    @RequestMapping("/cities/{cityId}")
    public City findCity(@PathVariable Long cityId) {
        return cityService.findById(cityId);
    }


}

