/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.controllers;

import com.seita.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.seita.dao.UserDAO;

/**
 *
 * @author estevaofay
 */
@Controller
public class UserController {

    @RequestMapping("/user/create")
    @ResponseBody
    public String create(String name, String login, String password, int permission) {
        String id = "";
        try {
            User produto = new User(name, login, password, permission);
            userDAO.save(produto);
            id = String.valueOf(produto.getIdUser());
        } catch (Exception ex) {
            return "Erro ao criar o usuário: " + ex.toString();
        }
        return "Usuário criado corretamente com o id = " + id;
    }

    @RequestMapping("/user/search")
    @ResponseBody
    public String search(String name) {
        User user = null;
        try {
            user = userDAO.findByName(name);
        } catch (Exception ex) {
            return "Erro ao buscar o usuário: " + ex.toString();
        }
        return "Usuário buscado com sucesso";
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public String update(Integer id, String name, String login, String password, int permission) {
        try {
            User user = userDAO.findOne(id);
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setPermission(permission);
            userDAO.save(user);
        } catch (Exception ex) {
            return "Erro ao atualizar o usuário: " + ex.toString();
        }
        return "Usuário atualizado com sucesso!";
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            User user = new User();
            user.setIdUser(id);
            userDAO.delete(user);
        } catch (Exception ex) {
            return "Erro ao remover o usuário:" + ex.toString();
        }
        return "Usuário removido com sucesso!";
    }

    @Autowired
    private UserDAO userDAO;
}
