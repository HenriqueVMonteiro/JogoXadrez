/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import xadrez.controller.Tabuleiro;
import xadrez.model.Peca;

/**
 *
 * @author Greg Mago
 */
public class Board extends JPanel {

    private static int tamanho_do_Quadrado;
    private String path = "src/xadrez/images/";

    public Board() throws Exception {
        super();
        Tabuleiro.inicializaTabuleiro();
    }

    @Override //sobrescrita do metodo paintComponent da classe JPanel
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.LIGHT_GRAY);
        g2.setColor(Color.LIGHT_GRAY);

        Color light_green = new Color(232, 242, 206);
        Color green = new Color(99, 133, 65);
        Color fill_circle = new Color(75, 77, 73,50); // r g b a
        
        float maxWidth = this.getWidth();
        float maxHeight = this.getHeight();
        float boardSize = (maxWidth < maxHeight) ? maxWidth : maxHeight;
        tamanho_do_Quadrado = Math.round(boardSize / 8.0f);

        //preenche tabuleiro com casas pretas e brancas
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                //varia a cor do quadrante
                if (g2.getColor() == green) {
                    g2.setColor(light_green);
                } else {
                    g2.setColor(green);
                }

                //Desenha um quadrado do tabuleiro
                g2.fillRect(i * tamanho_do_Quadrado, j * tamanho_do_Quadrado, (i * tamanho_do_Quadrado) + tamanho_do_Quadrado, (j * tamanho_do_Quadrado) + tamanho_do_Quadrado);
            }

            if (g2.getColor() == green) {
                g2.setColor(light_green);
            } else {
                g2.setColor(green);
            }
        }

        //desenha peca (ou não)
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                Peca peca_temp = Tabuleiro.getPeca(i + 1, j + 1);
                if (peca_temp != null) {
                    //testa se é a peca selecionada no momento
                    if (Tabuleiro.getPosicaoOrigem() != null
                            && peca_temp.getPosition().getX() == Tabuleiro.getPosicaoOrigem().getX()
                            && peca_temp.getPosition().getY() == Tabuleiro.getPosicaoOrigem().getY()) {
                        //colore o fundo da peca selecionada no momento
                        g2.setColor(Color.white);
                        g2.fillRect(i * tamanho_do_Quadrado, j * tamanho_do_Quadrado, tamanho_do_Quadrado, tamanho_do_Quadrado);

                        //colore os movimentos possiveis desta peca
                        peca_temp.calculaMovimentosPossiveis();
                        for (int k = 0; k < peca_temp.getMovimentosPossiveis().size(); k++) {
                            Point movimento_possivel = peca_temp.getMovimentosPossiveis().get(k);
                            g2.setColor(fill_circle);
                            g2.fillOval((int) (movimento_possivel.getX() - 1) * tamanho_do_Quadrado + 20,
                                    (int) (movimento_possivel.getY() - 1) * tamanho_do_Quadrado + 20,
                                    (int) tamanho_do_Quadrado - 40,
                                    (int) tamanho_do_Quadrado - 40);
                        }
                    }

                    try {
                        this.desenha_imagem(g, this.path + peca_temp.getNomeArquivo(), i, j);
                    } catch (IOException ex) {
                        System.out.printf("IO Exception" + ex.getMessage());

                        //desenha imagem alternativa
                        //DESENHA FUNDO (CIRCULO)
                        if (peca_temp.isBranco()) {
                            g2.setColor(Color.ORANGE);
                        } else {
                            g2.setColor(Color.BLACK);
                        }
                        g2.fillOval(i * tamanho_do_Quadrado + 8, j * tamanho_do_Quadrado + 8, tamanho_do_Quadrado - 16, tamanho_do_Quadrado - 16);

                        //desenha texto
                        int text_x_offset = tamanho_do_Quadrado / 2 - 12;
                        int text_y_offset = tamanho_do_Quadrado / 2 + 4;
                        if (peca_temp.isBranco()) {
                            g2.setColor(Color.BLACK);
                        } else {
                            g2.setColor(Color.WHITE);
                        }
                        FontMetrics fm = g.getFontMetrics(g.getFont());
                        g2.drawString(peca_temp.getMyTag(), i * tamanho_do_Quadrado + text_x_offset, (j * tamanho_do_Quadrado) + text_y_offset);

                    }

                }
            }
        }

    }

    public static int getSquareSize() {
        return tamanho_do_Quadrado;
    }

    private void desenha_imagem(Graphics g, String image_location, int i, int j) throws IOException {
        ImageIcon image_temp = new ImageIcon(image_location);
        switch (image_temp.getImageLoadStatus()) {
            case MediaTracker.COMPLETE:
                Image image = image_temp.getImage();
                g.drawImage(image, i * tamanho_do_Quadrado, j * tamanho_do_Quadrado, 80, 80, null);
                break;
            default: 
                throw new IOException("Imagem não encontrada em " + image_location);

        }

    }

}
