package xadrez.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xadrez.controller.MovimentoIlegal;

public abstract class Peca 
{
    private Point posicao; 
    protected ArrayList<Point> movimentos_possiveis = new ArrayList<Point>();
    private boolean foi_tocado;
    private boolean vivo = true;
    private boolean eh_branco; 
    private String nome_arquivo;
    private String tipo_peca;
    
    public static Peca pecaFactory(PecaAtributos peca_json) {
        Peca peca_return;
        switch(peca_json.getTipo_peca()){
            case "torre":
                peca_return = new Torre(peca_json);
//                Torre peca_return = new Torre(peca_json);
                return peca_return;
            case "cavalo":
                peca_return = new Cavalo(peca_json);
                return peca_return;
            case "bispo":
                peca_return = new Bispo(peca_json);
                return peca_return;
            case "rei":
                peca_return = new Rei(peca_json);
                return peca_return;
            case "rainha":
                peca_return = new Rainha(peca_json);
                return peca_return;
            case "peao":
                peca_return = new Peao(peca_json);
                return peca_return;
            default:
                return null;
        }
    }
    public Peca(){
        super();
    }
    public Peca(PecaAtributos peca_para_ser_copiada){
        
        this.setBranco(peca_para_ser_copiada.isEh_branco());
        this.setNomeArquivo(peca_para_ser_copiada.getNome_arquivo());
        try {
            this.setPosition(peca_para_ser_copiada.getPosicao());
        } catch (MovimentoIlegal ex) {
            Logger.getLogger(Peca.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setTipo_peca(peca_para_ser_copiada.getTipo_peca());
        this.movimentos_possiveis = peca_para_ser_copiada.getMovimentos_possiveis();
        this.foi_tocado = peca_para_ser_copiada.isFoi_tocado();
        this.vivo = peca_para_ser_copiada.isVivo();
    }
    
    public Peca(Peca peca_para_ser_copiada){
        this.posicao = new Point(peca_para_ser_copiada.getPosition());
        for(Point p: peca_para_ser_copiada.getMovimentosPossiveis()){
            this.movimentos_possiveis.add(new Point(p));
        }
//        this.foi_tocado = peca_para_ser_copiada.foiTocado();
//        this.vivo = peca_para_ser_copiada.isVivo();
        this.eh_branco = peca_para_ser_copiada.isBranco();
        this.nome_arquivo = peca_para_ser_copiada.getNomeArquivo();
    }
    public Peca(Point posicao, boolean eh_branco) throws MovimentoIlegal
    {
        if(!estaDentroDoTabuleiro(posicao))
        {
            throw new MovimentoIlegal("Posição Inválida. Peça criada fora do Tabuleiro");
        }
        
         this.posicao = posicao;
         this.eh_branco = eh_branco;
    }
    
    public void setPosition(Point posicao) throws MovimentoIlegal
    {
        if(!estaDentroDoTabuleiro(posicao))
        {
            throw new MovimentoIlegal("Posição Inválida. Posição de destino fora do Tabuleiro");
        }

        this.posicao = posicao;
        
        
    }
    
    public Point getPosition()
    {
        return this.posicao;
    }
    
    
    
    public void setBranco(boolean eh_branco)
    {
        this.eh_branco = eh_branco;
    }
    
    public boolean isBranco()
    {
        return this.eh_branco;
    }
    
    
    public static boolean estaDentroDoTabuleiro(Point posicao)
    {
        if(posicao.getX() < 1 || posicao.getX() > 8)
            return false;
        if(posicao.getY() < 1 || posicao.getY() > 8)
            return false;
        return true;
    }
    
    void morrer()
    {
        vivo = false;
    }
    
    
    public boolean isMovimentoPermitido(Point posicao){
//        return this.movimentos_possiveis.contains(posicao);
        if(this.movimentos_possiveis.contains(posicao)){
            return true;
        }else{
            return false;
        }
    }
    
    public void setNomeArquivo(String nome){
        this.nome_arquivo = nome;
    }
    public String getNomeArquivo(){
        return this.nome_arquivo;
    }
    public ArrayList<Point> getMovimentosPossiveis(){
        return this.movimentos_possiveis;
    }
    public abstract void calculaMovimentosPossiveis();
    
    public static String getPecaTag(Peca peca) {
        if(peca.getClass().equals(Peao.class)){
            return "Peão";
        }
        if(peca.getClass().equals(Torre.class)){
            return "Torre";
        }
        if(peca.getClass().equals(Cavalo.class)){
            return "Cavalo";
        }
        if(peca.getClass().equals(Bispo.class)){
            return "Bispo";
        }
        if(peca.getClass().equals(Rainha.class)){
            return "Rainha";
        }
        if(peca.getClass().equals(Rei.class)){
            return "Rei";
        }
        return "Desconhecida";
    }
    public String getMyTag() {
        return Peca.getPecaTag(this);
    }

    protected String getTipo_peca() {
        return tipo_peca;
    }
    protected void setTipo_peca(String tipo_peca) {
        this.tipo_peca = tipo_peca;
    }

    boolean isTocado() {
        return this.foi_tocado;
    }

    boolean isVivo() {
        return this.vivo;
    }
}
