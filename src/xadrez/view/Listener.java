/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xadrez.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import xadrez.controller.MovimentoIlegal;
import xadrez.controller.Tabuleiro;
import xadrez2d.Main;
import xadrez2d.Inicio;

/**
 *
 * @author Greg Mago
 */
public class Listener implements MouseListener, MouseMotionListener, ActionListener{

    Listener() {
    }
 @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int index_X = (int) Math.floor(e.getX()/Board.getSquareSize());
        int index_Y = (int) Math.floor(e.getY()/Board.getSquareSize());
        
        System.out.println("X: " + ++index_X + "\tY: " + ++index_Y);
       
        try {
            if(Tabuleiro.getPosicaoOrigem()==null){
                Tabuleiro.setPosicaoOrigem(new Point(index_X,index_Y));
            }else{
                Tabuleiro.setPosicaoDestino(new Point(index_X,index_Y));
            }
        } catch (MovimentoIlegal ex) {
            
            JOptionPane.showMessageDialog(
                Inicio.getFrame(),
                ex.getMessage(),
                "Ok",
                JOptionPane.OK_OPTION);
        } catch (Exception ex) {
          
            JOptionPane.showMessageDialog(
                Inicio.getFrame(),
                "Erro : Message:"+ex.getMessage()+" | StackTrace: "+ex.getStackTrace().toString(),
                "Ok",
                JOptionPane.OK_OPTION);
        }
        Inicio.getFrame().atualizar();
        FrameTabuleiro.board.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //funcionalidades do botao DEITAR O REI
       
        Object[] options = {"Sim",
                    "Não"};
        int n = JOptionPane.showOptionDialog(
                Inicio.getFrame(),
            "Você tem certeza que quer desistir e PERDER O JOGO?",
            "Deitar o Rei",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[1]); //default button title
        
        
        if(n == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(
                Inicio.getFrame(),
                "O JOGADOR "+(Tabuleiro.isRodadaJogadorBranco()?"BRANCO":"PRETO")+" Desistiu da partida e perdeu!",
                "Ok",
                JOptionPane.OK_OPTION);
            
            System.out.println("Perdeu!!!");
            Inicio.getFrame().dispose();
            Main.main(new String[0]);
            
//            System.exit(0);
        }else{
            
            System.out.println("Obrigado por não desistir!!!");
        }
    }
}
