import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author matej
 */
public class Randomizer {
    static String[] obsah;
    static String[] puvodniobsah;
    static int chyby;
    static int poradi;
    static int pojem;
    static byte[] pole = new byte[1000000];
    static Path soubor = Path.of("slepamapa.txt");
    static Random r = new Random();
    
    static JFrame okno = new JFrame();
    static JLabel zadani = new JLabel();
    static JButton next = new JButton("next");
    
    public static void main(String[] args) throws IOException {
        obsah = Files.readString(soubor).split("[\n#]");
        puvodniobsah = obsah;
        okno.add(zadani, BorderLayout.NORTH);
        okno.add(next, BorderLayout.SOUTH);
        okno.setVisible(true);
        okno.setSize(500, 100);
        next.addActionListener(new PosluchacUdalosti());
    }
    
//    public static void getOperation() throws IOException {
//            if (vstup.getText() == "n") {
//                obsah[pojem] = null;
//                losuj();
//            }
//            else if (vstup.getText() == "m") {
//                chyby++;
//                System.out.println(obsah[pojem]+" tu ještě uvidíš Celkově máš "+chyby+" chyb.");
//                obsah[pojem] = puvodniobsah[pojem];
//                poradi--;
//                losuj();
//            }
//            else if (vstup.getText() == "i") {
//                System.out.println(obsah.length);
//            }
//            else if (vstup.getText() == "e") {
//                System.exit(chyby);
//            }
//            else {
//                System.out.println("Neznámí příkaz");
//            }
//    }
    
    public static void losuj() throws IOException {
        pojem = r.nextInt(0, obsah.length-1);
        if (poradi >= obsah.length) {
            System.out.println("V�born�, dokon�il jsi procvi�ov�n� s "+chyby+" chybami\nStiskni e pro ukon�en�");
            konec();
        }
        else {
            if (obsah[pojem] == null) {
                losuj();
            } else {
                System.out.println(obsah[pojem]);
                poradi++;
            }
        }
    }
    public static void konec() throws IOException {
        System.in.read(pole);
        String vstup = new String(pole);
        vstup = vstup.trim();
        if (vstup.equals("e")) {
            System.exit(chyby);
        }
        konec();
    }
}

class PosluchacUdalosti implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Randomizer.losuj();
        } catch (IOException ex) {
            Logger.getLogger(PosluchacUdalosti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}