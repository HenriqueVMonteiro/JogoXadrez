/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;

public class PecaAtributos {
      
    private Point posicao; 
    private ArrayList<Point> movimentos_possiveis = new ArrayList<Point>();
    private String nome_arquivo;
    private String tipo_peca;
    private boolean eh_branco; 
    private boolean foi_tocado;
    private boolean vivo = true;
   
    public PecaAtributos importaPeca(Peca p){
        this.setPosicao(p.getPosition());
        this.setMovimentos_possiveis(p.getMovimentosPossiveis());
        this.setNome_arquivo(p.getNomeArquivo());
        this.setTipo_peca(p.getTipo_peca());
        this.setEh_branco(p.isBranco());
        this.setFoi_tocado(p.isTocado());
        this.setVivo(p.isVivo());
        return this;
    }

    /**
     * @return the posicao
     */
    public Point getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the movimentos_possiveis
     */
    public ArrayList<Point> getMovimentos_possiveis() {
        return movimentos_possiveis;
    }

    /**
     * @param movimentos_possiveis the movimentos_possiveis to set
     */
    public void setMovimentos_possiveis(ArrayList<Point> movimentos_possiveis) {
        this.movimentos_possiveis = movimentos_possiveis;
    }

    /**
     * @return the nome_arquivo
     */
    public String getNome_arquivo() {
        return nome_arquivo;
    }

    /**
     * @param nome_arquivo the nome_arquivo to set
     */
    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    /**
     * @return the tipo_peca
     */
    public String getTipo_peca() {
        return tipo_peca;
    }

    /**
     * @param tipo_peca the tipo_peca to set
     */
    public void setTipo_peca(String tipo_peca) {
        this.tipo_peca = tipo_peca;
    }

    /**
     * @return the eh_branco
     */
    public boolean isEh_branco() {
        return eh_branco;
    }

    /**
     * @param eh_branco the eh_branco to set
     */
    public void setEh_branco(boolean eh_branco) {
        this.eh_branco = eh_branco;
    }

    /**
     * @return the foi_tocado
     */
    public boolean isFoi_tocado() {
        return foi_tocado;
    }

    /**
     * @param foi_tocado the foi_tocado to set
     */
    public void setFoi_tocado(boolean foi_tocado) {
        this.foi_tocado = foi_tocado;
    }

    /**
     * @return the vivo
     */
    public boolean isVivo() {
        return vivo;
    }

    /**
     * @param vivo the vivo to set
     */
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
