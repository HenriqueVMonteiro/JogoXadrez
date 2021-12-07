package xadrez2d;

import java.util.logging.Level;
import java.util.logging.Logger;
import xadrez.view.FrameTabuleiro;

public class Main {

    private static Inicio ini;
    private static FrameTabuleiro frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inicio ini = new Inicio();
        ini.setVisible(true);
    }

}
