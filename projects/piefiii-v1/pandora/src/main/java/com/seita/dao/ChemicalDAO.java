/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.dao;

import com.seita.models.Chemical;
import com.seita.models.Material;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author 201511038
 */
public interface ChemicalDAO extends CrudRepository<Chemical, Integer>{
    public Chemical findByName(String name);
}
