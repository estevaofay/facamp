/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.dao;

import com.seita.models.Material;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author 201511038
 */
public interface MaterialDAO extends CrudRepository<Material, Integer> {
    public Material findByIdMaterial(Integer id);
    public Material findByName(String name);
}
