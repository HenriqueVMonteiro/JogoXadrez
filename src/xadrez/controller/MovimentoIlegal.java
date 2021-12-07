/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xadrez.controller;

import java.awt.Point;

/**
 *
 * @author Greg Mago
 */
public class MovimentoIlegal extends Exception{
    private Point origem, destino;
    
    public MovimentoIlegal() {
        this("Movimento Ilegal",null,null);
    }
    public MovimentoIlegal(String msg) {
        this(msg,null,null);
    }
    public MovimentoIlegal(Point origem, Point destino) {
        this("Movimento Ilegal",origem, destino);
    }
    public MovimentoIlegal(String msg, Point origem, Point destino) {
        super(msg);
        this.origem = origem;
        this.destino = destino;
    }

}
