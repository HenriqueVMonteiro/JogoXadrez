/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import xadrez.controller.Tabuleiro;
import xadrez2d.Main;
import xadrez2d.Inicio;
import xadrez.controller.Tabuleiro;

public class FrameTabuleiro extends javax.swing.JFrame {

    /**
     * Creates new form FrameTabuleiro
     */
    public static Board board;
    public String nome_1;
    public String nome_2;

    public FrameTabuleiro() throws Exception {

        setVisible(true);
        initComponents();
        atualizar_jogadorDaVez();

        board = new Board();

        Dimension area = new Dimension(boardPanel.getWidth(), boardPanel.getHeight());
        board.setPreferredSize(area);//set dimensao do painel de desenho
        board.setBackground(Color.WHITE);//set cor de fundo        

        board.addMouseListener((MouseListener) new Listener());//Adiciona evento de mouse ao Painel
        board.addMouseMotionListener((MouseMotionListener) new Listener());//Adiciona evento de mouse ao Painel

        this.boardPanel.setLayout(new GridLayout(1, 1));
        this.boardPanel.add(board);
    }

    private void sair() {

        Object[] options = {"Sim",
            "Não"};
        int n = JOptionPane.showOptionDialog(this,
                "Você tem certeza que quer sair?",
                "Sair",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[1]); //default button title

        if (n == JOptionPane.YES_OPTION) {

            System.out.println("Bye!!!");
            System.exit(0);
        } else {

        }

    }

    private void initComponents() {
        boardPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jButton2 = new javax.swing.JButton("Desistir");
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jtexarea_log = new javax.swing.JTextArea(10, 3);
        jscrollpane_log_de_movimentos = new javax.swing.JScrollPane(jtexarea_log);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Xadrez");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        boardPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
                boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
                boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));;

        jButton2.addActionListener(new Listener());

        jtexarea_log.setText("\t        Movimentos\n ");
        jtexarea_log.setEditable(false);
        jtexarea_log.setLineWrap(true);
        jtexarea_log.setBorder(new BevelBorder(BevelBorder.LOWERED));

       
        
        setJMenuBar(jMenuBar1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)                                      
                                        .addComponent(jscrollpane_log_de_movimentos, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                )
                                .addGap(0, 10, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()  
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addGap(47, 47, 47)
                                                .addComponent(jscrollpane_log_de_movimentos)
                                                .addGap(10, 10, 10)                                
                                                .addComponent(jButton2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
        pack();
    }// </editor-fold>                        

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.sair();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        this.sair();
    }

    public void atualizar_jogadorDaVez() {
        
        if (Tabuleiro.num_jogadas == 0) {
            jLabel1.setText(Inicio.Jogador1);
            jLabel1.setBackground(Color.white);
            jLabel1.setForeground(Color.black);
            
        }
        if (Tabuleiro.isRodadaJogadorBranco()) {
            jLabel1.setText(Inicio.Jogador1);
            jLabel1.setBackground(Color.white);
            jLabel1.setForeground(Color.black);
        } else {
            jLabel1.setText(Inicio.Jogador2);
            jLabel1.setBackground(Color.black);
            jLabel1.setForeground(Color.white);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel boardPanel;
    private javax.swing.JLabel jLabel1;//vez branco ou preto

    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;

    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;

    private javax.swing.JTextArea jtexarea_log;
    private javax.swing.JScrollPane jscrollpane_log_de_movimentos;
    // End of variables declaration                   

    public void atualizar() {
        this.atualizar_jogadorDaVez();
    }

    public void Atualizar_Log(String msg) {
        this.jtexarea_log.setText(this.jtexarea_log.getText() + "\n\n" + msg);
    }
}
