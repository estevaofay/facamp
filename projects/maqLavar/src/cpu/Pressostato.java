/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu;

/**
 *
 * @author estevaofay
 */
public class Pressostato {
    boolean lowWaterLevel = false;
    boolean mediumWaterLevel = false;
    boolean highWaterLevel = false;
    
    public Pressostato () {
        this.lowWaterLevel= true;
    }
}
