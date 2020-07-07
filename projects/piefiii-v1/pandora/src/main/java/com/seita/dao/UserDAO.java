/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.dao;

import com.seita.models.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author estevaofay
 */
@Transactional
public interface UserDAO  extends CrudRepository<User, Integer> {
    public User findByIdUser(Integer id);
    public User findByName(String name);
}
