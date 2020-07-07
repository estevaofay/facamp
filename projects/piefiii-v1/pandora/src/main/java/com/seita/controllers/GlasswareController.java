/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.controllers;

import com.seita.dao.ChemicalDAO;
import com.seita.dao.GlasswareDAO;
import com.seita.models.Chemical;
import com.seita.models.Glassware;
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
public class GlasswareController {
    
    @RequestMapping("/glassware/create")
    @ResponseBody
    public String create(Integer materialId, int capacity) {
        String id = "";
        try {
            //Glassware glassware = new Glassware(materialId, capacity);
           // glasswareDAO.save(glassware);
        } catch (Exception ex) {
            return "Erro ao criar a vidraria: " + ex.toString();
        }
        return "Vidraria criado corretamente com o id = " + id;
    }
    
    @RequestMapping("/glassware/search")
    @ResponseBody
    public String search(Integer id) {
        try {
            //Glassware glassware = glasswareDAO.findById(id);
        } catch (Exception ex) {
            return "Vidraria não encontrado";
        }
        return "O id da vidraria é: " + id;
    }
    
    @Autowired
    private GlasswareDAO glasswareDAO;
}
