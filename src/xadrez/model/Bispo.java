package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;
import xadrez.controller.MovimentoIlegal;
import xadrez.controller.Tabuleiro;

public class Bispo extends Peca
{
    public Bispo(PecaAtributos peca_json) {
        super(peca_json);
    }
    public Bispo(Peca peca){
        super(peca);
    }
    public Bispo(Point posicao, boolean eh_branco) throws MovimentoIlegal {
        super(posicao, eh_branco);
        this.setNomeArquivo("bispo_"+(eh_branco?"branco":"preto")+".png");
        setTipo_peca("bispo");
    }
    

    

    @Override
    public void calculaMovimentosPossiveis() {
        this.movimentos_possiveis = new ArrayList<Point>();
        Point p_temp; //Ponto que será usado para percorrer trajetos
        
        //1a diagonal (NORDESTE):
        p_temp = new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()+1);
        if(Peca.estaDentroDoTabuleiro(p_temp)){
            while(Tabuleiro.getPeca(p_temp) == null && Peca.estaDentroDoTabuleiro(p_temp)){
                this.movimentos_possiveis.add(new Point(p_temp));
                p_temp.setLocation(p_temp.getX()+1, p_temp.getY()+1);
            }
            if(Peca.estaDentroDoTabuleiro(p_temp) 
                    && Tabuleiro.getPeca(p_temp)!=null
                    && Tabuleiro.getPeca(p_temp).isBranco() != this.isBranco()){
                movimentos_possiveis.add(new Point(p_temp));
            }
        }
        
        //2a diagonal (SUDESTE):
        p_temp = new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()-1);
        if(Peca.estaDentroDoTabuleiro(p_temp)){
            while(Tabuleiro.getPeca(p_temp) == null && Peca.estaDentroDoTabuleiro(p_temp)){
                this.movimentos_possiveis.add(new Point(p_temp));
                p_temp.setLocation(p_temp.getX()+1, p_temp.getY()-1);
            }
            if(Peca.estaDentroDoTabuleiro(p_temp) 
                    && Tabuleiro.getPeca(p_temp)!=null
                    && Tabuleiro.getPeca(p_temp).isBranco() != this.isBranco()){
                movimentos_possiveis.add(new Point(p_temp));
            }
        }
        
        //3a diagonal (SUDOESTE):
        p_temp = new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY()-1);
        if(Peca.estaDentroDoTabuleiro(p_temp)){
            while(Tabuleiro.getPeca(p_temp) == null && Peca.estaDentroDoTabuleiro(p_temp)){
                this.movimentos_possiveis.add(new Point(p_temp));
                p_temp.setLocation(p_temp.getX()-1, p_temp.getY()-1);
            }
            if(Peca.estaDentroDoTabuleiro(p_temp) 
                    && Tabuleiro.getPeca(p_temp)!=null
                    && Tabuleiro.getPeca(p_temp).isBranco() != this.isBranco()){
                movimentos_possiveis.add(new Point(p_temp));
            }
        }
        
        //4a diagonal (NOROESTE):
        p_temp = new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY()+1);
        if(Peca.estaDentroDoTabuleiro(p_temp)){
            while(Tabuleiro.getPeca(p_temp) == null && Peca.estaDentroDoTabuleiro(p_temp)){
                this.movimentos_possiveis.add(new Point(p_temp));
                p_temp.setLocation(p_temp.getX()-1, p_temp.getY()+1);
            }
            if(Peca.estaDentroDoTabuleiro(p_temp) 
                    && Tabuleiro.getPeca(p_temp)!=null
                    && Tabuleiro.getPeca(p_temp).isBranco() != this.isBranco()){
                movimentos_possiveis.add(new Point(p_temp));
            }
        }
        
    }

   
   
}
