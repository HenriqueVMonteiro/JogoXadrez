package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;
import xadrez.controller.MovimentoIlegal;
import xadrez.controller.Tabuleiro;

public class Cavalo extends Peca
{
    public Cavalo(PecaAtributos peca_json) {
        super(peca_json);
    }
    public Cavalo(Peca peca){
        super(peca);
    }
    public Cavalo(Point posicao, boolean eh_branco) throws MovimentoIlegal {
        super(posicao, eh_branco);
        this.setNomeArquivo("cavalo_"+(eh_branco?"branco":"preto")+".png");
        setTipo_peca("cavalo");
    }

    

    @Override
    public void calculaMovimentosPossiveis() {
        movimentos_possiveis = new ArrayList<Point>(); 
        
        ArrayList<Point> movimentos_candidatos = new ArrayList<Point>(); 
        
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY()+2));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()+2));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()-2, (int) this.getPosition().getY()+1));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()+2, (int) this.getPosition().getY()+1));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()-2, (int) this.getPosition().getY()-1));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()-2));
        movimentos_candidatos.add(new Point((int) this.getPosition().getX()+2, (int) this.getPosition().getY()-1));
        
        for(int i=0; i<movimentos_candidatos.size(); i++){ 
            if(Peca.estaDentroDoTabuleiro(movimentos_candidatos.get(i)) 
                && 
               (    Tabuleiro.getPeca(movimentos_candidatos.get(i))==null 
                    || 
                    Tabuleiro.getPeca(movimentos_candidatos.get(i)).isBranco()!=Tabuleiro.isRodadaJogadorBranco()
                )){
                    movimentos_possiveis.add(new Point(movimentos_candidatos.get(i)));
                }
        }
    }

    
    
   
}
