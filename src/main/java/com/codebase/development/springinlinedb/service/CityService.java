package com.codebase.development.springinlinedb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.codebase.development.springinlinedb.model.City;

@Service
public class CityService implements ICityService {

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<City> findAll() {

        String sql = "SELECT * FROM cities";

        return jtm.query(sql, new BeanPropertyRowMapper<>(City.class));
    }

    @Override
    public City findById(Long id) {

        String sql = "SELECT * FROM cities WHERE id = ?";

        return jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(City.class));
    }
}
