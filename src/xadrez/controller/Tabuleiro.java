package xadrez.controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import xadrez.model.*;
import xadrez.view.FrameTabuleiro;
import xadrez2d.Main;
import xadrez2d.Inicio;

public class Tabuleiro {

    private static ArrayList<ArrayList> posicoes;
    private static ArrayList<ArrayList> posicoes_backup;
   
    
    private static boolean pode_reverter = false;
    private static boolean vez_do_branco;// = true;
    private static Point posicao_selecionada_origem;// = null;
    public static int num_jogadas = 0;
 
    
    public static void inicializaTabuleiro() throws Exception{
      
       
        posicoes = new ArrayList<ArrayList>();
        posicoes_backup = new ArrayList<ArrayList>();
        vez_do_branco = true;
        posicao_selecionada_origem = null;
        
        inicializarPosicoes();
        iniciarPecas();

    }
            
    public static Point getPosicaoOrigem() {
        return posicao_selecionada_origem;
    }

    public static void setPosicaoOrigem(Point posicao) throws MovimentoIlegal {
        if (getPeca(posicao)== null //tem alguma peca nessa posicao?
                || 
            getPeca(posicao).isBranco() != vez_do_branco //essa peca eh minha?
                ) {
            throw new MovimentoIlegal("Movimento Não Permitido");
        }
        System.out.println("Peca da posição ("+posicao.getX()+","+posicao.getY()+") selecionada");
        getPeca(posicao).calculaMovimentosPossiveis();
        posicao_selecionada_origem = posicao;
    }

    public static void setPosicaoDestino(Point posicao_clicada) throws MovimentoIlegal, Exception {
        Peca peca = getPeca(posicao_clicada);
        
        if(getPosicaoOrigem() == null){ //a origem já foi selecionada?
            throw new MovimentoIlegal("Movimento Não Permitido");
        }
        if (getPeca(posicao_clicada) != null 
                && getPeca(getPosicaoOrigem()).isBranco() == getPeca(posicao_clicada).isBranco()) { //selecionei uma peca minha de novo
            setPosicaoOrigem(posicao_clicada);
            return;
        } 
        if (!getPeca(getPosicaoOrigem()).isMovimentoPermitido(posicao_clicada)) { //peca com habilidade de me mover pra ca
            throw new MovimentoIlegal("Movimento Não Permitido", getPosicaoOrigem(), posicao_clicada);
        } 
        
         //faz backup antes de efetivar a jogada
        backup();
        pode_reverter = false;
        num_jogadas++;
        
        Peca porigem = getPeca(getPosicaoOrigem());
        Peca pdestino = getPeca(posicao_clicada);
        
        porigem.setPosition(posicao_clicada);
        Tabuleiro.setPeca(porigem, posicao_clicada); //coloca a peca de origem no destino
        Tabuleiro.setPeca(null, getPosicaoOrigem()); //agora a posicao de origem da peca fica vazia
        
        if(isJogadorAtualEmCheque(posicoes)){
            //o jogador atual SE colocou em cheque
            JOptionPane.showMessageDialog(
                Inicio.getFrame(),
                "Ação Ilegal seu rei esta em cheque!",
                "Ação ilegal",
                JOptionPane.OK_OPTION);
            
            //reverte modificações
            Tabuleiro.setPeca(porigem, getPosicaoOrigem());
            Tabuleiro.setPeca(pdestino, posicao_clicada);
            num_jogadas--;
            backup();//caso o jogador tenha feito a jogada e se colcado em cheque, essa jogada eh invalida. Portanto este comando REFAZ O BACKUP  e elimina a jogada invalida recem feita. Mas backup fica com a jogada atual, e não com a anterior. Portanto neste caso o undo nao funcionará mais (apenas para essa jogada).
            porigem.setPosition(getPosicaoOrigem());
        }else{
           
            //eh jogada valida => efetiva a jogada E testa se o adversario foi colocado em cheque
            Inicio.getFrame().Atualizar_Log(Peca.getPecaTag(porigem)+(vez_do_branco?" branco":" preto")+" movimentado "
                    + "de "+getPositionLabel(getPosicaoOrigem())+" para "+getPositionLabel(posicao_clicada));
            
            vez_do_branco = !vez_do_branco; //muda jogador
            posicao_selecionada_origem = null;
            
            if(isJogadorAtualEmCheque(posicoes) && isJogadorAtualEmChequeMate(posicoes)){
               
                JOptionPane.showMessageDialog(
                    Inicio.getFrame(),
                    "Cheque Mate! Fim do jogo! O jogador"+(!vez_do_branco?" BRANCO":" PRETO")+" ganhou!",
                    "Ok",
                    JOptionPane.OK_OPTION);
                System.out.println("CHEQUE MATE!!!!!!");
                Inicio.getFrame().dispose();
                Main.main(new String[0]);
            }
            else if(isJogadorAtualEmCheque(posicoes)){
              
                JOptionPane.showMessageDialog(
                    Inicio.getFrame(),
                    "Cheque!",
                    "Ok",
                    JOptionPane.OK_OPTION);
                Inicio.getFrame().Atualizar_Log("CHEQUE!");
            }
        }       
    }

    private static void iniciarPecas() throws Exception {
        ArrayList linha1 = (ArrayList) posicoes.get(1);
        linha1.set(1, new Torre(new Point(1, 1), true));
        linha1.set(2, new Cavalo(new Point(1, 2), true));
        linha1.set(3, new Bispo(new Point(1, 3), true));
        linha1.set(4, new Rainha(new Point(1, 4), true));
        linha1.set(5, new Rei(new Point(1, 5), true));
        linha1.set(6, new Bispo(new Point(1, 6), true));
        linha1.set(7, new Cavalo(new Point(1, 7), true));
        linha1.set(8, new Torre(new Point(1, 8), true));

        ArrayList linha2 = (ArrayList) posicoes.get(2);
        for (int i = 1; i <= 8; i++) {
            linha2.set(i, new Peao(new Point(2, i), true));
        }

        ArrayList linha8 = (ArrayList) posicoes.get(8);
        linha8.set(1, new Torre(new Point(8, 1), false));
        linha8.set(2, new Cavalo(new Point(8, 2), false));
        linha8.set(3, new Bispo(new Point(8, 3), false));
        linha8.set(4, new Rainha(new Point(8, 4), false));
        linha8.set(5, new Rei(new Point(8, 5), false));
        linha8.set(6, new Bispo(new Point(8, 6), false));
        linha8.set(7, new Cavalo(new Point(8, 7), false));
        linha8.set(8, new Torre(new Point(8, 8), false));

        ArrayList linha7 = (ArrayList) posicoes.get(7);
        for (int i = 1; i <= 8; i++) {
            linha7.set(i, new Peao(new Point(7, i), false));
        }

    }

    private static void inicializarPosicoes() {
        Tabuleiro.inicializarPosicoes(posicoes);
        Tabuleiro.inicializarPosicoes(posicoes_backup);
    }
    public  static void inicializarPosicoes(ArrayList<ArrayList> posicoes_r) {
//        posicoes_r = new ArrayList<ArrayList>();
        
        //criar linhas (vazias)
        posicoes_r.add(null);

        for (int i = 1; i <= 8; i++) {
            if(posicoes_r.size()==9){ 
                //ja foi iniciado e esta carregando
                posicoes_r.set(i, new ArrayList<Peca>());
            }else{
                posicoes_r.add(new ArrayList<Peca>());
            }
        }

        for (int i = 1; i <= 8; i++) {
            ArrayList<Peca> linha = posicoes_r.get(i);
            for (int j = 0; j <= 8; j++) {
                if(linha.size()==9){
                    linha.set(j, null);
                }else{
                    linha.add(null);
                }
            }
        }
    }

    public static Peca getPeca(Point p) {
        if(Peca.estaDentroDoTabuleiro(p)){
            return (Peca) posicoes.get((int) p.getX()).get((int) p.getY());
        }else{
            return null;
        }
    }
    public static Peca getPeca(int x, int y) {
        return (Peca) posicoes.get(x).get(y);
    }

    public static boolean isRodadaJogadorBranco() {
        return vez_do_branco;
    }

    private static void setPeca(Peca peca, Point posicao_no_tabuleiro) {
        Tabuleiro.posicoes
                .get((int) posicao_no_tabuleiro.getX()) //pega a linha inteira
                .set((int) posicao_no_tabuleiro.getY(), peca); 
    }
    
    public static void calculaMovimentoPossiveisDeTodasPecas(ArrayList<ArrayList> tabuleiro){
       
        Peca p_aux;
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                p_aux = (Peca) tabuleiro.get(i).get(j);
                if(p_aux!=null){
                    p_aux.calculaMovimentosPossiveis();
                }
            }
        }
    }
    
    
    public static boolean isJogadorAtualEmCheque(ArrayList<ArrayList> tabuleiro) throws Exception{
       
            
        Peca p_aux;
        
        Tabuleiro.calculaMovimentoPossiveisDeTodasPecas(tabuleiro);
        
        //localiza rei do jogador atual
        Point posicao_rei = null;
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                p_aux = (Peca) tabuleiro.get(i).get(j);
                if(p_aux!=null //checa se eh uma casa nao vazia
                        && p_aux.isBranco()==vez_do_branco //eh peca do Jogador da vez atual?
                        && p_aux instanceof Rei  //eh o Rei?
                        ){
                    posicao_rei = p_aux.getPosition();
                }
            }
        }
        if(posicao_rei==null){
           
            throw new Exception("Posicao do Rei de "+(vez_do_branco?"Branco":"Preto")+" nao encontrada");
        }
        
        //checa se existe alguma peca do oponente que contem a posicao do rei como um de seus movimentos possiveis
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                p_aux = (Peca) tabuleiro.get(i).get(j);
                if(p_aux!=null //checa se eh uma casa nao vazia
                        && p_aux.isBranco()!=vez_do_branco //eh peca do Jogador da vez atual?
                        ){
                    if(p_aux.getMovimentosPossiveis().contains(posicao_rei) == true){
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    private static String getPositionLabel(Point posicao) {
        String y_letter = "";
        switch((int)posicao.getY()){
            case 1: y_letter = "A"; break;
            case 2: y_letter = "B"; break;
            case 3: y_letter = "C"; break;
            case 4: y_letter = "D"; break;
            case 5: y_letter = "E"; break;
            case 6: y_letter = "F"; break;
            case 7: y_letter = "G"; break;
            case 8: y_letter = "H"; break;
        }
        return "("+y_letter+(int)posicao.getX()+")";
    }

    private static boolean isJogadorAtualEmChequeMate(ArrayList<ArrayList> tabuleiro) {
        //ESSA FUNÇÃO SOH SERA CHAMADA CASO O JOGADOR ATUAL JA ESTIVER EM CHEQUE
      
        Peca p_aux;
        boolean cheque_mate = true; //se houver qualquer simulacao em que o jogador SAIA do CHEQUE, CHEQUE_MATE = FALSO
        
        Tabuleiro.calculaMovimentoPossiveisDeTodasPecas(tabuleiro);
        
        //simula todos os movimentos possiveis das peças do jogador atual
        Point peca_aux = null;
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                p_aux = (Peca) tabuleiro.get(i).get(j);
                if(p_aux!=null //checa se eh uma casa nao vazia
                        && p_aux.isBranco()==vez_do_branco //eh peca do Jogador da vez atual?
                        ){
                    //testa todos os movimentos possiveis desta peca
                    for (Point movimento_possivel : p_aux.getMovimentosPossiveis()) {
                        //salva valores originais
                        Peca peca_origem = p_aux;
                        Point pos_origem = p_aux.getPosition();
                        Peca peca_destino = getPeca(movimento_possivel);
                        Point pos_destino = movimento_possivel;
                        
                        //simula mudanca
			Tabuleiro.setPeca(peca_origem, pos_destino);
			Tabuleiro.setPeca(null, pos_origem);
                        try {
                            peca_origem.setPosition(pos_destino);
                        } catch (Exception ex) {
                           
                            System.out.println("Erro ao simular movimento de peça para checar CHEQUE MATE");
                        }
                        
                        //testa se há saída para o CHEQUE ATUAL
                        try {
                            if(!isJogadorAtualEmCheque(tabuleiro))
                                cheque_mate = false;
                        } catch (Exception ex) {
                            
                            System.out.println("Erro ao testar CHEQUE para checar CHEQUE MATE");
                        }
                        
                        //reverte mudanca
			Tabuleiro.setPeca(peca_origem, pos_origem);
			Tabuleiro.setPeca(peca_destino, pos_destino);
                        try {
                            peca_origem.setPosition(pos_origem);
                        } catch (Exception ex) {
                            
                            System.out.println("Erro ao reverter movimento de peça para checar CHEQUE MATE");
                        }
                        
                        if(!cheque_mate){
                            return cheque_mate; // = false
                        }
                    }
                }
            }
        }
        return cheque_mate;// = true
    }
    
    public static void backup(){
      
       ArrayList<ArrayList> posicoes_backup = new ArrayList<ArrayList>(posicoes);
       posicoes_backup.addAll(posicoes);
        
        Tabuleiro.copiar_dados_de_tabuleiros(posicoes, posicoes_backup);
    }
    public static void undo() {
      
        if(num_jogadas>0){
            pode_reverter = true;
            //copia os valores de posicoes (ATUAL) para aux=> PRESERVA VALORES PARA EVENTUAL REDO
            ArrayList<ArrayList> aux = new ArrayList<ArrayList>();
            Tabuleiro.inicializarPosicoes(aux);
            Tabuleiro.copiar_dados_de_tabuleiros(posicoes, aux);

            //copia os valores de posicoes_backup para posicoes => COLOCA A JOGADA ANTERIOR COMO ATUAL
            Tabuleiro.copiar_dados_de_tabuleiros(posicoes_backup, posicoes);

            //copia os valores de aux para posicoes backup => GUARDA A JOGADA ATUAL PARA TALVEZ SER UTILIZADA NO REDO
            Tabuleiro.copiar_dados_de_tabuleiros(aux, posicoes_backup);

            vez_do_branco = !vez_do_branco;
            num_jogadas--;
            Inicio.getFrame().Atualizar_Log("Jogada Desfeita");
           
           
        }else{
          
            Inicio.getFrame().Atualizar_Log("Jogada não pode ser desfeito quando nenhum movimento foi feito ainda.");
        }
    }

    public static void redo() {
        if(pode_reverter){
            //copia os valores de posicoes_backup para posicoes
            Tabuleiro.copiar_dados_de_tabuleiros(posicoes_backup, posicoes);
            vez_do_branco = !vez_do_branco;
            num_jogadas++;
            Inicio.getFrame().Atualizar_Log("Jogada Refeita");
           
            pode_reverter = false;
           
        }else{
            Inicio.getFrame().Atualizar_Log("Jogada não pode ser refeita quando ela não é desfeita primeiro.");
          
            //throw exception 
        }
    }
    private static void copiar_dados_de_tabuleiros(ArrayList<ArrayList> tabuleiro_from, ArrayList<ArrayList> tabuleiro_to){
        
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                Peca p_aux = (Peca) tabuleiro_from.get(i).get(j);
                if(p_aux == null){
                    tabuleiro_to.get(i).set(j, null);
                }else{
                     if(p_aux.getClass().equals(Peao.class)){
                        tabuleiro_to.get(i).set(j, new Peao(p_aux));
                    }
                    if(p_aux.getClass().equals(Torre.class)){
                        tabuleiro_to.get(i).set(j, new Torre(p_aux));
                    }
                    if(p_aux.getClass().equals(Cavalo.class)){
                        tabuleiro_to.get(i).set(j, new Cavalo(p_aux));
                    }
                    if(p_aux.getClass().equals(Bispo.class)){
                       tabuleiro_to.get(i).set(j, new Bispo(p_aux));
                    }
                    if(p_aux.getClass().equals(Rainha.class)){
                       tabuleiro_to.get(i).set(j, new Rainha(p_aux));
                    }
                    if(p_aux.getClass().equals(Rei.class)){
                        tabuleiro_to.get(i).set(j, new Rei(p_aux));
                    }else{
                        //THROW EXCEPTION
                    }
                }
            }
        }
        //copia informações 
    }

   

    static ArrayList<ArrayList> getTabuleiroAtual() {
        return posicoes;
    }

    static void Carregar_Posicoes(ArrayList<Peca> temp_peca) throws Exception {
        inicializarPosicoes();
        
        for(Peca p: temp_peca){
            setPeca(p, p.getPosition());
        }
    } 
}
