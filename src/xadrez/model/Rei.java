package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;
import xadrez.controller.MovimentoIlegal;
import xadrez.controller.Tabuleiro;
import static xadrez.model.Peca.estaDentroDoTabuleiro;

public class Rei extends Peca {
    public Rei(PecaAtributos peca_json) {
        super(peca_json);
    }
    public Rei(Peca peca){
        super(peca);
    }
    public Rei(Point posicao, boolean eh_branco) throws MovimentoIlegal {
        super(posicao, eh_branco);
        this.setNomeArquivo("rei_" + (eh_branco ? "branco" : "preto") + ".png");
        setTipo_peca("rei");
    }

    

    @Override
    public void calculaMovimentosPossiveis() {
        this.movimentos_possiveis = new ArrayList<Point>();
        Point p_aux;

        p_aux = new Point((int) this.getPosition().getX() + 1, (int) this.getPosition().getY());
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco()) 
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX() + 1, (int) this.getPosition().getY() + 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX() + 1, (int) this.getPosition().getY() - 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX(), (int) this.getPosition().getY() - 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX(), (int) this.getPosition().getY() + 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX() - 1, (int) this.getPosition().getY() + 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX() - 1, (int) this.getPosition().getY() - 1);
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }

        p_aux = new Point((int) this.getPosition().getX() - 1, (int) this.getPosition().getY());
        if ((Tabuleiro.getPeca(p_aux) == null || Tabuleiro.getPeca(p_aux).isBranco() != Tabuleiro.isRodadaJogadorBranco())
                && estaDentroDoTabuleiro(p_aux)) {
            this.movimentos_possiveis.add(p_aux);
        }
    }
}
