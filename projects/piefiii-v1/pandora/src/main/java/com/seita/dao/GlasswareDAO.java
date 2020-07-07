/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.dao;

import com.seita.models.Glassware;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author 201511038
 */
public interface GlasswareDAO extends CrudRepository<Glassware, Integer>{
    public Glassware findByName(String name);
}
