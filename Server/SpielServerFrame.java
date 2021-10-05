import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.InetAddress;
import java.awt.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0
  * @author       kDaniel, Kai
  */

public class SpielServerFrame extends JFrame {
  // Anfang Attribute
  private JButton ServerButton = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JNumberField PortField = new JNumberField();
  private SpielServer spielserver;
  private JButton jButton1 = new JButton();
  private JTextArea jTextArea1 = new JTextArea("");
    private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JNumberField ipField = new JNumberField();
  private JNumberField jDeck = new JNumberField();
  // Ende Attribute
  
  public SpielServerFrame(String title) { 
    // Frame-Initialisierung
    super(title);
    //setUndecorated(true); //08.03.17 -> sorgt dafür dass die obere Leiste bei der GUI ausgeblendet wird.
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 361; 
    int frameHeight = 354;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);          
    // Anfang Komponenten
    
    ServerButton.setBounds(8, 16, 115, 65);
    ServerButton.setText("Server starten");
    ServerButton.setMargin(new Insets(2, 2, 2, 2));
    ServerButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ServerButton_ActionPerformed(evt);
      }
    });
    cp.add(ServerButton);
    jLabel1.setBounds(144, 16, 59, 27);
    jLabel1.setText("Port:");                 
    cp.add(jLabel1);
    PortField.setBounds(240, 16, 99, 28);
    PortField.setText("100");
    PortField.setBackground(Color.WHITE);
    cp.add(PortField);
    jButton1.setBounds(8, 272, 329, 33);
    jButton1.setText("Server beenden");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    jTextArea1ScrollPane.setBounds(8, 120, 329, 145);
    cp.add(jTextArea1ScrollPane);
    jLabel2.setBounds(96, 88, 155, 25);
    jLabel2.setText("Serverlog:");
    cp.add(jLabel2);
    jLabel3.setBounds(144, 48, 78, 27);
    jLabel3.setText("IP-Adresse:");
    cp.add(jLabel3);
    ipField.setBounds(240, 48, 99, 28);
    ipField.setText("");
    ipField.setBackground(Color.WHITE);
    cp.add(ipField);
    jDeck.setBounds(264, 88, 73, 25);
    jDeck.setText("");
    jDeck.setVisible(false);
    cp.add(jDeck);
    // Ende Komponenten
    
    setVisible(true);
    
    //setzt die IP-Adresse:
    ipField.setText(getIp());
    ipField.setEditable(false);
    
    //key listener
    
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    manager.addKeyEventDispatcher(new MyDispatcher(this));
  } // end of public SpielServerFrame
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    JFrame f = new SpielServerFrame("TheGame Spielserver");
    //disabled die funktion des stadard schließ buttons
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    
  } // end of main
  
  public void ServerButton_ActionPerformed(ActionEvent evt) {
    int kartenZahl=-1;
    
    try {
      if ((jDeck.getInt()>=10)&&(jDeck.getInt()<=99)) {
        kartenZahl = jDeck.getInt();
      } // end of if
      else {
        kartenZahl = 99; 
      } // end of if-else
    } catch(Exception e) {
      kartenZahl = 99;
    }    
    try {
      SpielServer spielserver = new SpielServer(PortField.getInt(),jTextArea1,kartenZahl);
      jTextArea1.setText("+Server gestartet");
      String pIp = getIp();
      jTextArea1.append("\nIP-Adresse: "+pIp);
      jTextArea1.append("\nPort: "+PortField.getInt());
      PortField.setEditable(false);
      ServerButton.setEnabled(false);
    } catch(Exception e) {
      jTextArea1.append("Fehler beim Starten des Servers\n");
    }
     
  } // end of ServerButton_ActionPerformed
  public String getIp(){
    String pIp = "Error";
    try {
      pIp = (""+InetAddress.getLocalHost());
      if (pIp.contains("/")) {
        String[] tempIp = pIp.split("/");
        pIp = tempIp[1];
      } // end of if
    } catch(Exception e) {
      
    } 
    
    return pIp;
  }
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    int eingabe = JOptionPane.showConfirmDialog(null,"Möchtest du den Server wirklich schließen?","Bestätigung",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE); //Daniel 19.02 - GameV13 -> fügt eine zustäzliche Bestätigung für den Benutzer hinzu.
    if (eingabe == 0) {
       try {
      //force closed den server um den port wieder frei zu stellen
      Runtime.getRuntime()
      .exec("taskkill /F /FI \"WINDOWTITLE eq "+getTitle()+"\"");
    } catch (Exception e) {
      e.printStackTrace();
    }
    } // end of if    
  } // end of jButton1_ActionPerformed
  
  public void dispatchKeyEvent(KeyEvent e) {
    if (e.getID() == e.KEY_PRESSED) {
      if (e.getKeyCode() == e.VK_F1) {
        if (jDeck.isVisible()) {
          jDeck.setVisible(false);
        } else {
          jDeck.setVisible(true);
        }
      } // end of if
    } // end of if
  }
  
  
  // Ende Methoden
} // end of class SpielServerFrame

class MyDispatcher implements KeyEventDispatcher {
  private SpielServerFrame sv;
  
  public MyDispatcher(SpielServerFrame sv) {
    this.sv = sv;
  }
  
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {
    sv.dispatchKeyEvent(e);
    
    return false;
  }
}