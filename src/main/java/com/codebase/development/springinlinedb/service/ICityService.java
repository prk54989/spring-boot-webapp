package com.codebase.development.springinlinedb.service;

import java.util.List;

import com.codebase.development.springinlinedb.model.City;

public interface ICityService {

    List<City> findAll();
    City findById(Long id);
}
