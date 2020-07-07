/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.controllers;

import com.seita.dao.ChemicalDAO;
import com.seita.models.Chemical;
import com.seita.models.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 201511038
 */
@Controller
public class ChemicalController {

    @RequestMapping("/chemical/create")
    @ResponseBody
    public String create(Integer materialId, String formula, String unit, Date expirationDate, String safetySheet) {
        String id = "";
        try {
//            Chemical chemical = new Chemical(materialId, formula, unit, expirationDate, safetySheet);
//            chemicalDAO.save(chemical);
        } catch (Exception ex) {
            return "Erro ao criar o produto químico: " + ex.toString();
        }
        return "Produto químico criado corretamente com o id = " + id;
    }

    @RequestMapping("/chemical/search")
    @ResponseBody
    public String search(Integer id) {
        try {
           // Chemical chemical = chemicalDAO.findById(id);
        } catch (Exception ex) {
            return "Produto químico não encontrado";
        }
        return "O id do produto químico é: " + id;
    }

    /*@RequestMapping("/chemical/update")
    @ResponseBody
    public String update(Integer materialId, String formula, String unit, Date expirationDate, String safetySheet) {
        try {
            Chemical chemical = chemicalDAO.findOne(id);
            chemical.setName(name);
            chemical.setLogin(login);
            chemical.setPassword(password);
            chemical.setPermission(permission);
            chemicalDAO.save(user);
        } catch (Exception ex) {
            return "Erro ao atualizar o usuário: " + ex.toString();
        }
        return "Usuário atualizado com sucesso!";
    }*/

    @Autowired
    private ChemicalDAO chemicalDAO;
}
