package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;
import xadrez.controller.MovimentoIlegal;
import xadrez.controller.Tabuleiro;

public class Peao extends Peca
{
   public Peao(PecaAtributos peca_json) {
        super(peca_json);
    }
    public Peao(Peca peca){
        super(peca);
    }
    public Peao(Point posicao, boolean eh_branco) throws MovimentoIlegal
    {
        super(posicao, eh_branco); //chama o construtor pai
        this.setNomeArquivo("peao_"+(eh_branco?"branco":"preto")+".png");
        setTipo_peca("peao");
    }
    
    

    @Override
    public void calculaMovimentosPossiveis() { 
        this.movimentos_possiveis = new ArrayList<Point>();
        Point p_aux;
        
       //peoes de baixo (brancos)
        if(this.isBranco()){
           //teste sobre andar 1 casa para frente
           p_aux = new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY());
           if(Tabuleiro.getPeca(p_aux)==null && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);

               //teste sobre andar 2 casa para frente
               p_aux = new Point((int) this.getPosition().getX()+2, (int) this.getPosition().getY());
               if(this.getPosition().getX()==2 && Tabuleiro.getPeca(p_aux)==null && estaDentroDoTabuleiro(p_aux)){
                   this.movimentos_possiveis.add(p_aux);
               }
           }
            
           //testa diagonal nordeste
           p_aux = new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()+1);
           if(Tabuleiro.getPeca(p_aux)!=null && !Tabuleiro.getPeca(p_aux).isBranco() && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);
           }
           
           //testa diagonal sudeste
           p_aux = new Point((int) this.getPosition().getX()+1, (int) this.getPosition().getY()-1);
           if(Tabuleiro.getPeca(p_aux)!=null && !Tabuleiro.getPeca(p_aux).isBranco() && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);
           }
        }else{
           //teste sobre andar 1 casa para frente
           p_aux = new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY());
           if(Tabuleiro.getPeca(p_aux)==null && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);

               //teste sobre andar 2 casa para frente
               p_aux = new Point((int) this.getPosition().getX()-2, (int) this.getPosition().getY());
               if(this.getPosition().getX()==7 && Tabuleiro.getPeca(p_aux)==null && estaDentroDoTabuleiro(p_aux)){
                   this.movimentos_possiveis.add(p_aux);
               }
           }
            
           //testa diagonal sudoeste
           p_aux = new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY()+1);
           if(Tabuleiro.getPeca(p_aux)!=null && Tabuleiro.getPeca(p_aux).isBranco() && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);
           }
           
           //testa diagonal noroeste
           p_aux = new Point((int) this.getPosition().getX()-1, (int) this.getPosition().getY()-1);
           if(Tabuleiro.getPeca(p_aux)!=null && Tabuleiro.getPeca(p_aux).isBranco() && estaDentroDoTabuleiro(p_aux)){
               this.movimentos_possiveis.add(p_aux);
           }
            
        }
       
    }
}
    
    

