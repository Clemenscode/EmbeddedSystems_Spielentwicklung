import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 24.05.2016
  * @author Daniel, Kai
  */
public class clientGui extends JFrame {
  // Anfang Attribute
  private JButton jButton1 = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JNumberField jTextField2 = new JNumberField();
  private JButton jButton2 = new JButton();
  private JTextField jTextField3 = new JTextField();
  private EchoConnection echoClient;
  private JTextArea jTextArea1 = new JTextArea("  ");
  private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
  private JButton selBtn1 = new JButton();
    private ImageIcon selBtn1Icon = new ImageIcon("images/karte.png");
  private JButton selBtn2 = new JButton();
    private ImageIcon selBtn2Icon = new ImageIcon("images/karte.png");
  private JButton selBtn3 = new JButton();
    private ImageIcon selBtn3Icon = new ImageIcon("images/karte.png");
  private JButton selBtn4 = new JButton();
    private ImageIcon selBtn4Icon = new ImageIcon("images/karte.png");
  private JButton selBtn5 = new JButton();
    private ImageIcon selBtn5Icon = new ImageIcon("images/karte.png");
  private JButton selBtn6 = new JButton();
    private ImageIcon selBtn6Icon = new ImageIcon("images/karte.png");
  private JButton selBtn7 = new JButton();
    private ImageIcon selBtn7Icon = new ImageIcon("images/karte.png");
  private JButton selBtn8 = new JButton();
    private ImageIcon selBtn8Icon = new ImageIcon("images/karte.png");
  private JButton aufhoerenBtn = new JButton();
  private JButton stapelBtn1 = new JButton();
    private ImageIcon stapelBtn1Icon = new ImageIcon("images/card_1.png");
  private JButton stapelBtn2 = new JButton();
    private ImageIcon stapelBtn2Icon = new ImageIcon("images/card_1.png");
  private JButton stapelBtn3 = new JButton();
    private ImageIcon stapelBtn3Icon = new ImageIcon("images/card_100G.png");
  private JButton stapelBtn4 = new JButton();
    private ImageIcon stapelBtn4Icon = new ImageIcon("images/card_100G.png");
  private JTextField nameTf = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JLabel Label5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JComboBox<String> jComboBox1 = new JComboBox<String>();
  private DefaultComboBoxModel<String> jComboBox1Model = new DefaultComboBoxModel<String>();
  private JButton jButton3 = new JButton();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private int kartenNr = -1;
  private JButton jButton4 = new JButton();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JComboBox<String> jComboBox2 = new JComboBox<String>();
  private DefaultComboBoxModel<String> jComboBox2Model = new DefaultComboBoxModel<String>();
  private JLabel jLabel9 = new JLabel();
  private JButton jButton7 = new JButton();
  private JLabel StapelNummer2 = new JLabel();
  private JLabel stapelNummer1 = new JLabel();
  private JLabel stapelNummer3 = new JLabel();
  private JLabel stapelNummer4 = new JLabel();
  private int aktPhase = 0;
  private int farbLimit = 0;
  private JButton jButton6 = new JButton();
  private long start2Time = 0;
  private JLabel jConnectLb = new JLabel();
  private int liveTimerSec;
  private int liveTimerMin;
  private int pAktStapel;
  private MouseListener mouseL;
  private boolean connectTimeout = false;
  private String pAktStapelValue;
  private boolean startDone = false;
  private java.util.Timer timer;
  private java.util.Timer timer2;
  private java.util.Timer timer3;
  private java.util.Timer timer4;
  private java.util.Timer timer5;
  private java.util.Timer timer6;
  private java.util.Timer timer7;
  private java.util.Timer timerv1;
  private java.util.Timer timerv2;
  private java.util.Timer timerv3;
  private java.util.Timer timerv4;
  private java.util.Timer timerv5;
  private java.util.Timer timerv6;
  private java.util.Timer timerv7;
  private java.util.Timer timerv8;
  private java.util.Timer timer0;
  private java.util.Timer timer01;
  private int Spieleranzahl = 0;
  private JButton jRestart = new JButton();
  private JButton btnLeave = new JButton();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel14 = new JLabel();
  private JLabel jLabel15 = new JLabel();
  private JList jList1 = new JList();
    private DefaultListModel jList1Model = new DefaultListModel();
    private JScrollPane jList1ScrollPane = new JScrollPane(jList1);
  private int index;  
  private JLabel jLabel10 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JLabel jLabel18 = new JLabel();
  private JButton rank1 = new JButton();
  private JButton rank2 = new JButton();
  private JButton rank3 = new JButton();
  private JButton rank4 = new JButton();
  private JButton rank5 = new JButton();
  private JButton rank6 = new JButton();
  private JButton rank7 = new JButton();
  private JButton rank8 = new JButton();
  private JButton rank9 = new JButton();
  private JButton rank10 = new JButton();
  private JButton jButton8 = new JButton();
  private JLabel jLabel19 = new JLabel();
    private ImageIcon jLabel19Icon = new ImageIcon("images/thegamespiel1.jpg");
  private JTextField jTextField4 = new JTextField();
  private JButton jButton5 = new JButton();
  // Ende Attribute
  public clientGui(String title) {
    // Frame-Initialisierung
    super(title);
    //setUndecorated(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    int frameWidth = 1269; 
    int frameHeight = 846;
    setSize(frameWidth, frameHeight);
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    
    Container cp = getContentPane();
    cp.setLayout(null);
    
    //jFrame icon neu setzen
    try {
      Image image = ImageIO.read(new File("images/icon.png"));
      setIconImage(image);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    } // end of try
    
    
    
    //Buttons am Anfang 2
    hideSelBtns();
    jComboBox2.setVisible(false);
    jRestart.setVisible(false);
    jComboBox1.setVisible(false);
    jLabel9.setVisible(false);
    rank1.setVisible(false);
    rank2.setVisible(false);
    rank3.setVisible(false);
    rank4.setVisible(false);
    rank5.setVisible(false);
    rank6.setVisible(false);
    rank7.setVisible(false);
    rank8.setVisible(false);
    rank9.setVisible(false);
    rank10.setVisible(false);
    jLabel11.setVisible(false);
    stapelBtn1.setEnabled(false);
    stapelBtn2.setEnabled(false);
    stapelBtn3.setEnabled(false);
    stapelBtn4.setEnabled(false);
    jConnectLb.setVisible(false);
    jLabel6.setVisible(false);
    nameTf.setVisible(false);
    jButton4.setVisible(false);
    jButton7.setVisible(false);
    jButton3.setVisible(false);
    
    // Anfang Komponenten
    cp.addMouseListener(new MouseAdapter() {               //Kai07.03.
      public void mouseClicked(MouseEvent e) {
        if (startDone == false) {
          clickedWhileStart();
        } // end of if
      } 
    });
    
    
    
    
    jLabel19.setBounds(0, 0, 1266, 820);
    btnLeave.setVisible(false);
    jButton6.setVisible(false);                     
    jButton1.setVisible(false);
    jTextField1.setVisible(false);
    jTextField2.setVisible(false);                               
    jLabel19.setText("click to skip                                                                                                by Daniel & Kai");
    jLabel19.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
    jLabel19.setForeground(Color.WHITE);
    jLabel19.setVerticalTextPosition(SwingConstants.BOTTOM);    
    jLabel19.setIcon(jLabel19Icon);
    jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel19.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel19.setBackground(new Color(0xBC5844));                                                     
    jLabel19.setOpaque(true);                                                                  
    cp.add(jLabel19);
    startTimer0();  
    //getColor("",255255255)
    //jLabel19.setBackground(Color.WHITE);
    
    jButton1.setBounds(16, 16, 187, 41);
    jButton1.setText("Mit Server verbinden");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton1_ActionPerformed(evt);
      }
    });
    jButton1.setForeground(Color.BLACK);
    jButton1.setRolloverEnabled(true);           
    cp.add(jButton1);
    jLabel1.setBounds(544, 40, 22, 20);
    jLabel1.setText("IP");
    jLabel1.setForeground(Color.WHITE);
    cp.add(jLabel1);
    jLabel2.setBounds(806, 40, 30, 20);
    jLabel2.setText("Port");
    jLabel2.setForeground(Color.WHITE);
    cp.add(jLabel2);
    jTextField1.setBounds(600, 40, 160, 20);
    jTextField1.setText("localhost");
    jTextField1.setForeground(Color.BLACK);
    cp.add(jTextField1);
    jTextField2.setBounds(862, 40, 38, 20);
    jTextField2.setText("100");
    jTextField2.setForeground(Color.BLACK);
    cp.add(jTextField2);
    jButton2.setBounds(600, 776, 83, 28);
    jButton2.setVisible(false);
    jButton2.setText("jButton5");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton2_ActionPerformed(evt);
      }
    });
    jButton2.setForeground(Color.BLACK);
    cp.add(jButton2);
    jTextField3.setBounds(296, 776, 302, 28);
    jTextField3.setForeground(Color.BLACK);
    jTextField3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jTextField3_ActionPerformed(evt);
      }
    });
    jTextField3.setVisible(false);
    jButton8.setVisible(false);
    cp.add(jTextField3);
    jTextArea1ScrollPane.setBounds(736, 496, 496, 263);
    jTextArea1.setLineWrap(true);
    jTextArea1.setEditable(false);
    jTextArea1.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
    cp.add(jTextArea1ScrollPane);
    selBtn1.setBounds(32, 248, 134, 198);
    selBtn1.setContentAreaFilled(false);
    selBtn1.setText("");
    selBtn1.setMargin(new Insets(2, 2, 2, 2));
    selBtn1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn1_ActionPerformed(evt);
      }
    });
    selBtn1.setEnabled(true);
    selBtn1.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn1.setIcon(selBtn1Icon);
    selBtn1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn1.setForeground(Color.WHITE);
    selBtn1.setVerticalTextPosition(SwingConstants.CENTER);
    cp.add(selBtn1);
    selBtn2.setBounds(160, 248, 134, 198);
    selBtn2.setContentAreaFilled(false);
    selBtn2.setText("");
    selBtn2.setMargin(new Insets(2, 2, 2, 2));
    selBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn2_ActionPerformed(evt);
      }
    });
    selBtn2.setEnabled(true);
    selBtn2.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn2.setIcon(selBtn2Icon);
    selBtn2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn2.setForeground(Color.WHITE);
    cp.add(selBtn2);
    selBtn3.setBounds(288, 248, 134, 198);
    selBtn3.setText("");
    selBtn3.setMargin(new Insets(2, 2, 2, 2));
    selBtn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn3_ActionPerformed(evt);
      }
    });
    selBtn3.setEnabled(true);
    selBtn3.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn3.setIcon(selBtn3Icon);
    selBtn3.setContentAreaFilled(false);
    selBtn3.setForeground(Color.WHITE);
    selBtn3.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn3);
    selBtn4.setBounds(416, 248, 134, 198);
    selBtn4.setText("");
    selBtn4.setMargin(new Insets(2, 2, 2, 2));
    selBtn4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn4_ActionPerformed(evt);
      }
    });
    selBtn4.setEnabled(true);
    selBtn4.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn4.setIcon(selBtn4Icon);
    selBtn4.setContentAreaFilled(false);
    selBtn4.setForeground(Color.WHITE);
    selBtn4.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn4);
    selBtn5.setBounds(544, 248, 134, 198);
    selBtn5.setText("");
    selBtn5.setMargin(new Insets(2, 2, 2, 2));
    selBtn5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn5_ActionPerformed(evt);
      }
    });
    selBtn5.setEnabled(true);
    selBtn5.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn5.setIcon(selBtn5Icon);
    selBtn5.setContentAreaFilled(false);
    selBtn5.setForeground(Color.WHITE);
    selBtn5.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn5);
    selBtn6.setBounds(672, 248, 134, 198);
    selBtn6.setText("");
    selBtn6.setMargin(new Insets(2, 2, 2, 2));
    selBtn6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn6_ActionPerformed(evt);
      }
    });
    selBtn6.setEnabled(true);
    selBtn6.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn6.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn6.setIcon(selBtn6Icon);
    selBtn6.setForeground(Color.WHITE);
    selBtn6.setContentAreaFilled(false);
    selBtn6.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn6);
    selBtn7.setBounds(800, 248, 134, 198);
    selBtn7.setText("");
    selBtn7.setMargin(new Insets(2, 2, 2, 2));
    selBtn7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn7_ActionPerformed(evt);
      }
    });
    selBtn7.setEnabled(true);
    selBtn7.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn7.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn7.setIcon(selBtn7Icon);
    selBtn7.setForeground(Color.WHITE);
    selBtn7.setContentAreaFilled(false);
    selBtn7.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn7);
    selBtn8.setBounds(928, 248, 134, 198);
    selBtn8.setText("");
    selBtn8.setMargin(new Insets(2, 2, 2, 2));
    selBtn8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selBtn8_ActionPerformed(evt);
      }
    });
    selBtn8.setEnabled(true);
    selBtn8.setHorizontalTextPosition(SwingConstants.CENTER);
    selBtn8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    selBtn8.setIcon(selBtn8Icon);
    selBtn8.setForeground(Color.WHITE);
    selBtn8.setContentAreaFilled(false);
    selBtn8.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(selBtn8);
    aufhoerenBtn.setBounds(483, 464, 195, 65);
    aufhoerenBtn.setText("Zug beenden");
    aufhoerenBtn.setMargin(new Insets(2, 2, 2, 2));
    aufhoerenBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        aufhoerenBtn_ActionPerformed(evt);
      }
    });
    aufhoerenBtn.setFont(new Font("Dialog", Font.BOLD, 22));
    cp.add(aufhoerenBtn);
    stapelBtn1.setBounds(32, 568, 134, 198);
    stapelBtn1.setText("");
    stapelBtn1.setIcon(stapelBtn1Icon);
    stapelBtn1.setContentAreaFilled(false);
    stapelBtn1.setForeground(Color.WHITE);
    stapelBtn1.setMargin(new Insets(2, 2, 2, 2));
    stapelBtn1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        stapelBtn1_ActionPerformed(evt);
      }
    });
    //stapelBtn1.setEnabled(true);
    stapelBtn1.setHorizontalTextPosition(SwingConstants.CENTER);
    stapelBtn1.setHorizontalAlignment(SwingConstants.CENTER);
    stapelBtn1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    cp.add(stapelBtn1);
    stapelBtn2.setBounds(184, 568, 134, 198);
    stapelBtn2.setText("");
    stapelBtn2.setContentAreaFilled(false);
    stapelBtn2.setIcon(stapelBtn2Icon);
    stapelBtn2.setForeground(Color.WHITE);
    stapelBtn2.setMargin(new Insets(2, 2, 2, 2));
    stapelBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        stapelBtn2_ActionPerformed(evt);
      }
    });
    //stapelBtn2.setEnabled(true);
    stapelBtn2.setHorizontalTextPosition(SwingConstants.CENTER);
    stapelBtn2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    cp.add(stapelBtn2);
    stapelBtn3.setBounds(392, 568, 134, 198);
    stapelBtn3.setText("");
    stapelBtn3.setForeground(Color.WHITE);
    stapelBtn3.setContentAreaFilled(false);
    stapelBtn3.setIcon(stapelBtn3Icon);
    stapelBtn3.setMargin(new Insets(2, 2, 2, 2));
    stapelBtn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        stapelBtn3_ActionPerformed(evt);
      }
    });
    //stapelBtn3.setEnabled(true);
    stapelBtn3.setHorizontalTextPosition(SwingConstants.CENTER);
    stapelBtn3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    cp.add(stapelBtn3);
    stapelBtn4.setBounds(544, 568, 134, 198);
    stapelBtn4.setText("");
    stapelBtn4.setForeground(Color.WHITE);
    stapelBtn4.setContentAreaFilled(false);
    stapelBtn4.setIcon(stapelBtn4Icon);
    stapelBtn4.setMargin(new Insets(2, 2, 2, 2));
    stapelBtn4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        stapelBtn4_ActionPerformed(evt);
      }
    });
    //stapelBtn4.setEnabled(true);
    stapelBtn4.setHorizontalTextPosition(SwingConstants.CENTER);
    stapelBtn4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 60));
    cp.add(stapelBtn4);
    nameTf.setBounds(600, 64, 160, 20);
    nameTf.setForeground(Color.BLACK);
    cp.add(nameTf);
    jLabel4.setBounds(544, 64, 37, 20);
    jLabel4.setText("Name");
    jLabel4.setForeground(Color.WHITE);
    cp.add(jLabel4);
    
    jLabel6.setBounds(32, 208, 126, 28);
    jLabel6.setText("Deine Karten");
    jLabel6.setFont(new Font("Dialog", Font.BOLD, 20));
    jLabel6.setForeground(Color.WHITE);
    cp.add(jLabel6);
    jComboBox1.setModel(jComboBox1Model);
    jComboBox1.setBounds(600, 125, 222, 33);
    jComboBox1Model.addElement("1. Stapel freihalten");
    jComboBox1Model.addElement("2. Stapel freihalten");
    jComboBox1Model.addElement("3. Stapel freihalten");
    jComboBox1Model.addElement("4. Stapel freihalten");
    jComboBox1Model.addElement("1. Stapel unbedingt freihalten");
    jComboBox1Model.addElement("2. Stapel unbedingt freihalten");
    jComboBox1Model.addElement("3. Stapel unbedingt freihalten");
    jComboBox1Model.addElement("4. Stapel unbedingt freihalten");
    jComboBox1Model.addElement("1. Stapel wieder freigeben");
    jComboBox1Model.addElement("2. Stapel wieder freigeben");
    jComboBox1Model.addElement("3. Stapel wieder freigeben");
    jComboBox1Model.addElement("4. Stapel wieder freigeben");
    jComboBox1.setMaximumRowCount(12);
    jComboBox1.setForeground(Color.BLACK);
    cp.add(jComboBox1);
    jButton3.setBounds(830, 126, 33, 33);
    jButton3.setText("GO");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton3_ActionPerformed(evt);
      }
    });
    jButton3.setForeground(Color.BLACK);
    cp.add(jButton3);
    jLabel7.setBounds(32, 512, 78, 28);
    jLabel7.setText("Stapel");
    jLabel7.setFont(new Font("Dialog", Font.BOLD, 20));
    jLabel7.setForeground(Color.WHITE);
    cp.add(jLabel7);
    jLabel3.setBounds(736, 456, 143, 36);
    jLabel3.setText("Informationen");
    jLabel3.setFont(new Font("Dialog", Font.BOLD, 20));
    jLabel3.setForeground(Color.WHITE);
    cp.add(jLabel3);
    
    jButton4.setBounds(768, 64, 33, 20);
    jButton4.setText("GO");
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton4_ActionPerformed(evt);
      }
    });
    jButton4.setForeground(Color.BLACK);
    cp.add(jButton4);
    jLabel5.setBounds(544, 147, 50, 21);
    jLabel5.setText("Ranking");
    jLabel5.setForeground(Color.WHITE);
    cp.add(jLabel5);
    jLabel8.setBounds(944, 32, 191, 28);
    jLabel8.setText("Spielerliste");
    jLabel8.setFont(new Font("Dialog", Font.BOLD, 20));
    jLabel8.setForeground(Color.WHITE);
    cp.add(jLabel8);
    jComboBox2.setModel(jComboBox2Model);
    jComboBox2.setBounds(600, 88, 222, 33);
    jComboBox2Model.addElement("Spielstart");
    jComboBox2Model.addElement("Hilfe");
    jComboBox2.setSelectedIndex(0);
    jComboBox2.setForeground(Color.BLACK);
    cp.add(jComboBox2);
    jLabel9.setBounds(544, 94, 50, 20);
    jLabel9.setText("Befehle");
    jLabel9.setForeground(Color.WHITE);
    cp.add(jLabel9);
    jButton7.setBounds(830, 88, 33, 33);
    jButton7.setText("GO");
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton7_ActionPerformed(evt);
      }
    });
    jButton7.setForeground(Color.BLACK);
    cp.add(jButton7);
    StapelNummer2.setBounds(198, 548, 108, 22);
    StapelNummer2.setText("Stapel 2 | UP to 99");
    StapelNummer2.setFont(new Font("Dialog", Font.BOLD, 12));
    StapelNummer2.setForeground(Color.WHITE);
    cp.add(StapelNummer2);
    stapelNummer1.setBounds(46, 548, 108, 22);
    stapelNummer1.setText("Stapel 1 | UP to 99");
    stapelNummer1.setFont(new Font("Dialog", Font.BOLD, 12));
    stapelNummer1.setForeground(Color.WHITE);
    cp.add(stapelNummer1);
    stapelNummer3.setBounds(400, 548, 118, 22);
    stapelNummer3.setText("Stapel 3 | DOWN to 2");
    stapelNummer3.setFont(new Font("Dialog", Font.BOLD, 12));
    stapelNummer3.setForeground(Color.WHITE);
    cp.add(stapelNummer3);
    stapelNummer4.setBounds(552, 548, 118, 22);
    stapelNummer4.setText("Stapel 4 | DOWN to 2");
    stapelNummer4.setFont(new Font("Dialog", Font.BOLD, 12));
    stapelNummer4.setForeground(Color.WHITE);
    cp.add(stapelNummer4);
    jButton6.setBounds(16, 64, 187, 41);
    jButton6.setText("Client schließen");
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton6_ActionPerformed(evt);
      }
    });
    jButton6.setForeground(Color.BLACK);
    cp.add(jButton6);
    jConnectLb.setBounds(16, 128, 187, 41);
    jConnectLb.setText("text");
    jConnectLb.setBackground(Color.GREEN);
    jConnectLb.setOpaque(true);
    jConnectLb.setFont(new Font("Dialog", Font.BOLD, 25));
    jConnectLb.setHorizontalAlignment(SwingConstants.CENTER);
    jConnectLb.setVisible(false);
    cp.add(jConnectLb);
    jRestart.setBounds(600, 154, 201, 57);
    jRestart.setText("RESTART!");
    jRestart.setMargin(new Insets(2, 2, 2, 2));
    jRestart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jRestart_ActionPerformed(evt);
      }
    });
    jRestart.setForeground(Color.BLACK);
    cp.add(jRestart);
    btnLeave.setBounds(16, 16, 187, 41);
    btnLeave.setText("Verbindung trennen");
    btnLeave.setMargin(new Insets(2, 2, 2, 2));
    btnLeave.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnLeave_ActionPerformed(evt);
      }
    });
    btnLeave.setForeground(Color.BLACK);
    cp.add(btnLeave);
    jLabel11.setBounds(944, 202, 199, 48);
    jLabel11.setText("Spielzeit: 0:00");
    jLabel11.setFont(new Font("Dialog", Font.BOLD, 23));
    jLabel11.setForeground(Color.WHITE);
    cp.add(jLabel11);
    jLabel12.setBounds(312, 16, 136, 70);
    jLabel12.setText("THE");
    jLabel12.setOpaque(false);
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel12.setFont(new Font("MS Gothic", Font.BOLD, 20));
    jLabel12.setForeground(new Color(0xC0C0C0));
    cp.add(jLabel12);
    jLabel13.setBounds(200, 36, 365, 132);
    jLabel13.setText("GAME");
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setFont(new Font("MS Gothic", Font.BOLD, 90));
    jLabel13.setForeground(Color.WHITE);
    cp.add(jLabel13);
    jLabel14.setBounds(286, 46, 70, 9);
    jLabel14.setText("");
    jLabel14.setForeground(Color.RED);
    jLabel14.setBackground(Color.RED);
    jLabel14.setOpaque(true);
    cp.add(jLabel14);
    jLabel15.setBounds(404, 46, 70, 9);
    jLabel15.setText("");
    jLabel15.setFont(new Font("Italianate", Font.BOLD, 12));
    jLabel15.setBackground(Color.RED);
    jLabel15.setOpaque(true);
    cp.add(jLabel15);
    jList1.setModel(jList1Model);
    jList1ScrollPane.setBounds(944, 64, 240, 145);
    jList1.setFont(new Font("Dialog", Font.BOLD, 18));
    cp.add(jList1ScrollPane);
    jList1.setSelectionBackground(Color.WHITE);
    jList1.addListSelectionListener(new ListSelectionListener(){
      @Override
      public void valueChanged(ListSelectionEvent arg0){
        if (!arg0.getValueIsAdjusting()) {
          jList1.setSelectedIndex(index);
        } // end of if
      }
    });
    jLabel10.setBounds(192, 608, 118, 116);
    jLabel10.setText("");
    jLabel10.setFont(new Font("LetterOMatic!", Font.BOLD, 70));
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(jLabel10);
    jLabel16.setBounds(40, 608, 118, 116);
    jLabel16.setText("");
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel16.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel16.setFont(new Font("LetterOMatic!", Font.BOLD, 70));
    cp.add(jLabel16);
    jLabel17.setBounds(400, 608, 118, 116);
    jLabel17.setText("");
    jLabel17.setFont(new Font("LetterOMatic!", Font.BOLD, 70));
    jLabel17.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel17.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel17);
    jLabel18.setBounds(552, 608, 118, 116);
    jLabel18.setText("");
    jLabel18.setFont(new Font("LetterOMatic!", Font.BOLD, 70));
    jLabel18.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel18);
    rank1.setBounds(600, 128, 33, 33);
    rank1.setText("1");
    rank1.setMargin(new Insets(2, 2, 2, 2));
    rank1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank1_ActionPerformed(evt);
      }
    });
    cp.add(rank1);
    rank2.setBounds(632, 128, 33, 33);
    rank2.setText("2");
    rank2.setMargin(new Insets(2, 2, 2, 2));
    rank2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank2_ActionPerformed(evt);
      }
    });
    cp.add(rank2);
    rank3.setBounds(664, 128, 33, 33);
    rank3.setText("3");
    rank3.setMargin(new Insets(2, 2, 2, 2));
    rank3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank3_ActionPerformed(evt);
      }
    });
    cp.add(rank3);
    rank4.setBounds(696, 128, 33, 33);
    rank4.setText("4");
    rank4.setMargin(new Insets(2, 2, 2, 2));
    rank4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank4_ActionPerformed(evt);
      }
    });
    cp.add(rank4);
    rank5.setBounds(728, 128, 33, 33);
    rank5.setText("5");
    rank5.setMargin(new Insets(2, 2, 2, 2));
    rank5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank5_ActionPerformed(evt);
      }
    });
    cp.add(rank5);
    rank6.setBounds(600, 160, 33, 33);
    rank6.setText("6");
    rank6.setMargin(new Insets(2, 2, 2, 2));
    rank6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank6_ActionPerformed(evt);
      }
    });
    cp.add(rank6);
    rank7.setBounds(632, 160, 33, 33);
    rank7.setText("7");
    rank7.setMargin(new Insets(2, 2, 2, 2));
    rank7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank7_ActionPerformed(evt);
      }
    });
    cp.add(rank7);
    rank8.setBounds(664, 160, 33, 33);
    rank8.setText("8");
    rank8.setMargin(new Insets(2, 2, 2, 2));
    rank8.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank8_ActionPerformed(evt);
      }
    });
    cp.add(rank8);
    rank9.setBounds(696, 160, 33, 33);
    rank9.setText("9");
    rank9.setMargin(new Insets(2, 2, 2, 2));
    rank9.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank9_ActionPerformed(evt);
      }
    });
    cp.add(rank9);
    rank10.setBounds(728, 160, 33, 33);
    rank10.setText("10");
    rank10.setMargin(new Insets(2, 2, 2, 2));
    rank10.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rank10_ActionPerformed(evt);
      }
    });
    cp.add(rank10);
    jButton8.setBounds(1144, 8, 107, 49);                
    jButton8.setText("StapelEnabled");
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton8.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton8_ActionPerformed(evt);
      }
    });
    cp.add(jButton8);
    
    jTextField4.setBounds(736, 768, 377, 33);
    jTextField4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jTextField4_ActionPerformed(evt);
      }
    });
    cp.add(jTextField4);
    jButton5.setBounds(1128, 768, 65, 33);
    jButton5.setText("Send");
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton5_ActionPerformed(evt);
      }
    });
    jButton5.setVisible(false);
    cp.add(jButton5);
    // Ende Komponenten
    setVisible(true);
    
    aufhoerenBtn.setVisible(false);
    
    //jProgressBar1.setBackground(new Color(0,0,0,0));
    new Thread(new resetNameRunnable()).start();
    
    /*Drawer dr = new Drawer();
    dr.setBounds(520, -50, 500, 500);
    getContentPane().add(dr);         */
    
    BackgroundDrawer brd = new BackgroundDrawer();
    brd.setBounds(0, 0, 1265, 820);
    getContentPane().add(brd);
    
    //Zu Beginn nur Buttons zum Connecten.
    jLabel5.setVisible(false);
    jLabel4.setVisible(false);
    nameTf.setVisible(false);
    toggleRank(false);
    jRestart.setVisible(false);             
    jButton4.setVisible(false);
    jLabel9.setVisible(false);
    jComboBox2.setVisible(false);
    jButton7.setVisible(false);
    jComboBox1.setVisible(false);
    jButton3.setVisible(false);
    hideSelBtns(); //Kai12.02.
    jLabel6.setVisible(false);
    stapelBtn1.setEnabled(false);
    stapelBtn2.setEnabled(false);
    stapelBtn3.setEnabled(false);
    stapelBtn4.setEnabled(false);
    btnLeave.setVisible(false);
    jLabel11.setVisible(false);
    jLabel16.setVisible(false);
    jLabel10.setVisible(false);
    jLabel17.setVisible(false);
    jLabel18.setVisible(false);
    
    selBtn1.setBorderPainted(false);           //Kai01.03. ggf. auch Border painten?
    selBtn2.setBorderPainted(false);
    selBtn3.setBorderPainted(false);
    selBtn4.setBorderPainted(false);
    selBtn5.setBorderPainted(false);
    selBtn6.setBorderPainted(false);
    selBtn7.setBorderPainted(false);
    selBtn8.setBorderPainted(false);
    stapelBtn1.setBorderPainted(false);
    stapelBtn2.setBorderPainted(false);
    stapelBtn3.setBorderPainted(false);
    stapelBtn4.setBorderPainted(false);
    
    stapelBtn1.setBackground(new JButton().getBackground()); //erzeugt default-background
    stapelBtn2.setBackground(new JButton().getBackground());
    stapelBtn3.setBackground(new JButton().getBackground());
    stapelBtn4.setBackground(new JButton().getBackground());
    
    
    
    //key listener
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    manager.addKeyEventDispatcher(new MyDispatcher(this));
  } // end of public clientGui
  
  // Anfang Methoden
  public static void main(String[] args) {                                                              
    JFrame f = new clientGui("The Game");
    //disabled die funktion des stadard schließ buttons
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    if (connectTimeout == false) {
      jTextArea1.setText("");
      echoClient = new EchoConnection(jTextField1.getText(),
      Integer.parseInt(jTextField2.getText()),                                
      this);                                              //Kai16.02.
      if (!jTextArea1.getText().startsWith("Error")) {
        
      } // end of if
      connectTimeout = true;
      startTimer7();
    } // end of if
    
  } // end of jButton1_ActionPerformed
  
  public void clickedWhileStart() {               //Kai07.03.
      jLabel19.setVisible(false);
      jButton1.setVisible(true);
      jButton6.setVisible(true);
      jTextField1.setVisible(true);
      jTextField2.setVisible(true);
      timer0.cancel();
      timer01.cancel();
      startDone = true;
  }
  
  
  public void jButton2_ActionPerformed(ActionEvent evt) {
    echoClient.send(jTextField3.getText());
  } // end of jButton2_ActionPerformed
  
  public void jTextField3_ActionPerformed(ActionEvent evt) {
    echoClient.send(jTextField3.getText());
  } // end of jButton2_ActionPerformed
  
  public void selBtn1_ActionPerformed(ActionEvent evt) { //Kai12.02. alle Btn_ActionPerformed-Methoden vereinfacht
    if (aktPhase == 2) {
      kartenNr = 1;
      ColourSelBtnWhite();
      selBtn1.setBackground(Color.RED);
      selBtn1.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn1_ActionPerformed
  
  public void selBtn2_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 2;
      ColourSelBtnWhite();
      selBtn2.setBackground(Color.RED);
      selBtn2.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn2_ActionPerformed
  
  public void selBtn3_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 3;
      ColourSelBtnWhite();
      selBtn3.setBackground(Color.RED);
      selBtn3.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn3_ActionPerformed
  
  public void selBtn4_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 4;
      ColourSelBtnWhite();
      selBtn4.setBackground(Color.RED);
      selBtn4.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn4_ActionPerformed
  
  public void selBtn5_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 5;
      ColourSelBtnWhite();
      selBtn5.setBackground(Color.RED);
      selBtn5.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn5_ActionPerformed
  
  public void selBtn6_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 6;
      ColourSelBtnWhite();
      selBtn6.setBackground(Color.RED);
      selBtn6.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn6_ActionPerformed
  
  public void selBtn7_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 7;
      ColourSelBtnWhite();
      selBtn7.setBackground(Color.RED);
      selBtn7.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn7_ActionPerformed
  
  public void selBtn8_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 2) {
      kartenNr = 8;
      ColourSelBtnWhite();
      selBtn8.setBackground(Color.RED);
      selBtn8.setContentAreaFilled(true);
    } // end of if
  } // end of selBtn8_ActionPerformed
  
  public void aufhoerenBtn_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    echoClient.send("+ZUGBEENDEN");
    aufhoerenBtn.setVisible(false);
    showSelBtns();
    ColourSelBtnWhite();  //Kai06.03.
  } // end of aufhoerenBtn_ActionPerformed
  
  public void hideSelBtns() {
    selBtn1.setVisible(false);
    selBtn2.setVisible(false);
    selBtn3.setVisible(false);
    selBtn4.setVisible(false);
    selBtn5.setVisible(false);
    selBtn6.setVisible(false);
    selBtn7.setVisible(false);
    selBtn8.setVisible(false);
  }
  
  public void showSelBtns() {
    selBtn1.setVisible(true);
    selBtn2.setVisible(true);
    selBtn3.setVisible(true);
    selBtn4.setVisible(true);
    selBtn5.setVisible(true);
    selBtn6.setVisible(true);
    selBtn7.setVisible(true);
    selBtn8.setVisible(true);
  }
  
  public void toggleSelBtns(boolean b) {
    selBtn1.setEnabled(b);
    selBtn2.setEnabled(b);
    selBtn3.setEnabled(b);
    selBtn4.setEnabled(b);
    selBtn5.setEnabled(b);
    selBtn6.setEnabled(b);
    selBtn7.setEnabled(b);
    selBtn8.setEnabled(b);
  }
  
  public void toggleStapelBtns(boolean b) {
    stapelBtn1.setEnabled(b);
    stapelBtn2.setEnabled(b);
    stapelBtn3.setEnabled(b);
    stapelBtn4.setEnabled(b);
  }
  public void toggleStapelBtnsVisible(boolean b) {
    stapelBtn1.setVisible(b);
    stapelBtn2.setVisible(b);
    stapelBtn3.setVisible(b);
    stapelBtn4.setVisible(b);
  }
  public void ColourSelBtnWhite() {
    selBtn1.setBackground(new JButton().getBackground()); //erzeugt default-background
    selBtn2.setBackground(new JButton().getBackground());
    selBtn3.setBackground(new JButton().getBackground());
    selBtn4.setBackground(new JButton().getBackground());
    selBtn5.setBackground(new JButton().getBackground());
    selBtn6.setBackground(new JButton().getBackground());
    selBtn7.setBackground(new JButton().getBackground());
    selBtn8.setBackground(new JButton().getBackground());
    selBtn1.setContentAreaFilled(false);
    selBtn2.setContentAreaFilled(false);
    selBtn3.setContentAreaFilled(false);
    selBtn4.setContentAreaFilled(false);
    selBtn5.setContentAreaFilled(false);
    selBtn6.setContentAreaFilled(false);
    selBtn7.setContentAreaFilled(false);
    selBtn8.setContentAreaFilled(false);
  }
  
  public void resetSelBtn() {
    selBtn1.setBackground(new JButton().getBackground()); //erzeugt default-background
    selBtn2.setBackground(new JButton().getBackground());
    selBtn3.setBackground(new JButton().getBackground());
    selBtn4.setBackground(new JButton().getBackground());
    selBtn5.setBackground(new JButton().getBackground());
    selBtn6.setBackground(new JButton().getBackground());
    selBtn7.setBackground(new JButton().getBackground());
    selBtn8.setBackground(new JButton().getBackground());
    //Setzt den Text zurück.
    selBtn1.setText("");
    selBtn2.setText("");
    selBtn3.setText("");
    selBtn4.setText("");
    selBtn5.setText("");
    selBtn6.setText("");
    selBtn7.setText("");
    selBtn8.setText("");
    selBtn1.setContentAreaFilled(false);
    selBtn2.setContentAreaFilled(false);
    selBtn3.setContentAreaFilled(false);
    selBtn4.setContentAreaFilled(false);
    selBtn5.setContentAreaFilled(false);
    selBtn6.setContentAreaFilled(false);
    selBtn7.setContentAreaFilled(false);
    selBtn8.setContentAreaFilled(false);
  }
  
  public void stapelBtn1_ActionPerformed(ActionEvent evt) {
    handleStapelButtonClick(1, kartenNr);
    ColourSelBtnWhite();
    kartenNr = -1;
  } // end of stapelBtn1_ActionPerformed
  
  public void stapelBtn2_ActionPerformed(ActionEvent evt) {
    handleStapelButtonClick(2, kartenNr);
    ColourSelBtnWhite();
    kartenNr = -1;
  } // end of stapelBtn2_ActionPerformed
  
  public void stapelBtn3_ActionPerformed(ActionEvent evt) {
    handleStapelButtonClick(3, kartenNr);
    ColourSelBtnWhite();
    kartenNr = -1;
  } // end of stapelBtn3_ActionPerformed
  
  public void stapelBtn4_ActionPerformed(ActionEvent evt) {
    handleStapelButtonClick(4, kartenNr);
    ColourSelBtnWhite();
    kartenNr = -1;
  } // end of stapelBtn4_ActionPerformed
  
  public void handleStapelButtonClick(int stapel, int karte) {
    echoClient.send("+KARTELEGEN <" + karte + "," + stapel + ">");
    echoClient.send("+ZEIGEKARTEN");
    //echoClient.send("+ZEIGESTAPEL");
  }
  
  public void jButton3_ActionPerformed(ActionEvent evt) {
    switch (jComboBox1.getSelectedIndex() + 1) {
      case 9:
      echoClient.send("+MSG -STAPEL<1>");
      
      break;
      
      case 10:
      echoClient.send("+MSG -STAPEL<2>");
      
      break;
      
      case 11:
      echoClient.send("+MSG -STAPEL<3>");
      
      break;
      
      case 12:
      echoClient.send("+MSG -STAPEL<4>");
      
      break;
      
      }
    
    if ((jComboBox1.getSelectedIndex() + 1) <= 8 ) {
      long end2Time = System.nanoTime(); //Kai13.02. Spamblocker
    long duration2 = (end2Time - start2Time) / 1000000000;
    
    if ((duration2 > 5) || (duration2 < 0)) {
      switch (jComboBox1.getSelectedIndex() + 1) {
        case 1:
        echoClient.send("+MSG STAPEL<1>");
        
        break;
        
        case 2:
        echoClient.send("+MSG STAPEL<2>");
        
        break;
        
        case 3:
        echoClient.send("+MSG STAPEL<3>");
        
        break;
        
        case 4:
        echoClient.send("+MSG STAPEL<4>");
        
        break;
        
        case 5:
        echoClient.send("+MSG !STAPEL<1>");
        
        break;
        
        case 6:
        echoClient.send("+MSG !STAPEL<2>");
        
        break;
        
          case 7:
        echoClient.send("+MSG !STAPEL<3>");
        
        break;
        
        case 8:
        echoClient.send("+MSG !STAPEL<4>");
        
        break;
      } // end of switch  
      
      start2Time = System.nanoTime();
    } else {
        jTextArea1.append("Nicht spammen! Warte ein paar Sekunden...\n");
    }
    
    
      
      // end of if 
    } // end of if
    
  } // end of jButton3_ActionPerformed
  
  public void jButton4_ActionPerformed(ActionEvent evt) {
    echoClient.send("+NAME<" + nameTf.getText() + ">");
  } // end of jButton4_ActionPerformed
  
  public void jButton7_ActionPerformed(ActionEvent evt) {
    if (aktPhase == 0) {
      switch (jComboBox2.getSelectedIndex() + 1) {
        case 1:
        echoClient.send("+SPIELSTART");
        
        break;
        
        case 2:
        echoClient.send("+HELP");
        
        break;
      }
    } else if (aktPhase == 1) {
      switch (jComboBox2.getSelectedIndex() + 1) {
        case 1:
        echoClient.send("+SPIELSTART");
        
        break;
        
        case 2:
        echoClient.send("+HELP");
        
        break;
        
        case 3:
        echoClient.send("+AKTPHASE");
        
        break;
      }
    } else if (aktPhase == 2) {
      switch (jComboBox2.getSelectedIndex() + 1) {
        case 1:
        echoClient.send("+HELP");
        
        break;
        
        case 2:
        echoClient.send("+AKTPHASE");
        
        break;
        
        /*case 3:
        echoClient.send("+AMZUG");
        
        break;   */
        
        case 4:
        int eingabe = JOptionPane.showConfirmDialog(null,"Möchtest du das Spiel wirklich verlassen?","Bestätigung",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE); //Daniel 19.02 - GameV13 -> fügt eine zustäzliche Bestätigung für den Benutzer hinzu.
        if (eingabe==0) {
          try {
            jTextArea1.setText("");
            echoClient.send("+VERLASSEN");
            jTextArea1.setText("");
          } catch (Exception e) {
            
          }
          
          try {
            //force closed den server um den port wieder frei zu stellen
            Runtime.getRuntime()
            .exec("taskkill /F /FI \"WINDOWTITLE eq " + getTitle() + "\"");
            jTextArea1.setText("");
          } catch (Exception e) {
            e.printStackTrace();
          }
        } // end of if
        break;
        
        case 3:
        echoClient.send("+Zeige_ZIEHSTAPEL");
        
        break;
      }
    } else if (aktPhase == 3) {
      switch (jComboBox2.getSelectedIndex() + 1) {
        case 1:
        echoClient.send("+HELP");
        
        break;
        
        case 2:
        echoClient.send("+AKTPHASE");
        
        break;
        
        case 3:
        echoClient.send("+RESTKARTEN");
        
        break;
        
        case 6:
        int eingabe = JOptionPane.showConfirmDialog(null,"Möchtest du das Spiel wirklich verlassen?","Bestätigung",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE); //Daniel 19.02 - GameV13 -> fügt eine zustäzliche Bestätigung für den Benutzer hinzu.
        if (eingabe==0) {
          try {
            jTextArea1.setText("");
            echoClient.send("+VERLASSEN");
            jTextArea1.setText("");
          } catch (Exception e) {
            
          }
          
          try {
            //force closed den server um den port wieder frei zu stellen
            Runtime.getRuntime()
            .exec("taskkill /F /FI \"WINDOWTITLE eq " + getTitle() + "\"");
            jTextArea1.setText("");
          } catch (Exception e) {
            e.printStackTrace();
          }
        } // end of if
        
        break;
        
        case 4:
        echoClient.send("+SPIELZEIT");
        
        break;
        
        case 5:
        echoClient.send("+SCORE");
        
        break;
        //case 6:
        //echoClient.send("+RESTART");
        
        //break;  
      } // end of switch
    } // end of if-else
  } // end of jButton7_ActionPerformed
  
  public void jButton6_ActionPerformed(ActionEvent evt) {
    int eingabe = JOptionPane.showConfirmDialog(null,"Möchtest du das Spiel wirklich verlassen?","Bestätigung",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE); //Daniel 19.02 - GameV13 -> fügt eine zustäzliche Bestätigung für den Benutzer hinzu.
    if (eingabe==0) {
      try {
        jTextArea1.setText("");
        echoClient.send("+VERLASSEN");
        jTextArea1.setText("");
      } catch (Exception e) {
        
      }
      
      try {
        //force closed den server um den port wieder frei zu stellen
        Runtime.getRuntime() 
        .exec("taskkill /F /FI \"WINDOWTITLE eq " + getTitle() + "\""); 
        jTextArea1.setText("");  
      } catch (Exception e) {
        e.printStackTrace();
      }
    } // end of if
    
  } // end of jButton6_ActionPerformed
  
  public void jRestart_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RESTART");
  } // end of jRestart_ActionPerformed
  
  public void btnLeave_ActionPerformed(ActionEvent evt) {                             //Kai16.02.
    int eingabe = JOptionPane.showConfirmDialog(null,"Möchtest du den Server wirklich verlassen?","Bestätigung",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE); //Daniel 19.02 - GameV13 -> fügt eine zustäzliche Bestätigung für den Benutzer hinzu.
    if (eingabe==0) {
      echoClient.send("+VERLASSEN");
      echoClient.close(); 
      jButton1.setVisible(true);
      defaultClient(0);
      jConnectLb.setVisible(true);
      jConnectLb.setText("disconnected");
      jConnectLb.setBackground(Color.RED);
      //jTextArea1.setText("Verbindung zum Server unterbrochen!\nDu musst dich neu verbinden!");
      //defaultClient(0);
      timer.cancel();
      btnLeave.setVisible(false);
      echoClient.close(); 
      jTextArea1.setText("Verbindung zum Server unterbrochen!\nDu musst dich neu verbinden!");
    } // end of if
    
  } // end of btnLeave_ActionPerformed
  
  public void rank1_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<1>");
  } // end of rank1_ActionPerformed
  
  public void rank2_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<2>");
  } // end of rank2_ActionPerformed
  
  public void rank3_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<3>");
  } // end of rank3_ActionPerformed
  
  public void rank4_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<4>");
  } // end of rank4_ActionPerformed
  
  public void rank5_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<5>");
  } // end of rank5_ActionPerformed
  
  public void rank6_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<6>");
  } // end of rank6_ActionPerformed
  
  public void rank7_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<7>");
  } // end of rank7_ActionPerformed
  
  public void rank8_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<8>");
  } // end of rank8_ActionPerformed
  
  public void rank9_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<9>");
  } // end of rank9_ActionPerformed
  
  public void rank10_ActionPerformed(ActionEvent evt) {
    echoClient.send("+RANKING<10>");
  } // end of rank10_ActionPerformed
  
  public void jButton8_ActionPerformed(ActionEvent evt) {
    toggleStapelBtns(true);
  } // end of jButton8_ActionPerformed
  
  public void jButton5_ActionPerformed(ActionEvent evt) {
    echoClient.send("+SAY<"+jTextField4.getText()+">");
    jTextField4.setText("");
  } // end of jButton5_ActionPerformed
   public void jTextField4_ActionPerformed(ActionEvent evt) {
    echoClient.send("+SAY<"+jTextField4.getText()+">");
    jTextField4.setText("");
  } // end of jButton5_ActionPerformed
  // Ende Methoden
  public class Drawer extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      try {
        g.drawImage(ImageIO.read(new File("./images/thegamespiel1.jpg")), 0,
        0, 500, 202, null);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
      } // end of try
    }
  }         
  
  public class BackgroundDrawer extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      try {
        g.drawImage(ImageIO.read(new File("./images/farb.jpg")), 0, 0,
        1265, 820, null);                                                                      
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
      } // end of try
    }
  }
  
  public class resetNameRunnable implements Runnable {
    public void run() {
      while (true) {
        if (!nameTf.hasFocus() &
        (nameTf.getText().equals("") || (nameTf.getText() == null))) {
          nameTf.setText("Spieler1");
          
          try {
            Thread.sleep(100);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
          } // end of try
        } // end of if 
      } // end of while
    }
  }
  
  class Task extends TimerTask {                                                    //Kai20.02. --> Task als Klasse für den Timer zur Anzeige der Spielzeit
    @Override public void run() {
      liveTimerSec = liveTimerSec +1;
      if (liveTimerSec == 60) {
        liveTimerMin = liveTimerMin + 1;
        liveTimerSec = 0;
      } // end of if
      if (liveTimerSec < 10) {
        jLabel11.setText("Spielzeit: " + liveTimerMin + ":0" + liveTimerSec);
      }else{
        jLabel11.setText("Spielzeit: " + liveTimerMin + ":" + liveTimerSec);
      }
      // end of if
    }
  }
  
  public void startTimer() {
    timer = new java.util.Timer();
    timer.schedule(new Task(), 1000, 1000);
  }
  
  public void cancelTimer() {
    timer.cancel();
  }
  
  class Task0 extends TimerTask {           //Kai07.03.
    public void run() {
      jLabel19.setVisible(false);
      btnLeave.setVisible(true);
      jButton6.setVisible(true);
      jButton1.setVisible(true);
      jTextField1.setVisible(true);
      jTextField2.setVisible(true);
      startDone = true;
    }
  }
  
  class Task01 extends TimerTask {              //Kai07.03.
    public void run() {
      int b = jLabel19.getBackground().getRGB();
      //System.out.println(b);
      b= b + 1;                                                                                                       //"0x"+Integer.toHexString(b).substring(2).toUpperCase()
      jLabel19.setBackground(Color.getColor("",b));    //wechselt 92x alle 10 Millisekunden die Farbe (Farbcode + 1)   
      if (farbLimit < 92) {
        timer01 = new java.util.Timer();
        timer01.schedule(new Task01(), 10);                                                             
        farbLimit++;
      } // end of if
      
    }
    
  }
  
  
  class Task2 extends TimerTask {
    public void run() {
      jLabel16.setVisible(false);
      stapelBtn1.setVisible(true);
    }
  } 
  
  class Task3 extends TimerTask {
    public void run() {
      jLabel10.setVisible(false);
      stapelBtn2.setVisible(true);
    }
  } 
  
  class Task4 extends TimerTask {
    public void run() {
      jLabel17.setVisible(false);
      stapelBtn3.setVisible(true);
    }
  } 
  
  class Task5 extends TimerTask {
    public void run() {
      jLabel18.setVisible(false);                
      stapelBtn4.setVisible(true);
    }
  }        
  
  class Task6 extends TimerTask {
    public void run() {
      stapelBtn1.setForeground(Color.WHITE); //erzeugt default-foreground
      stapelBtn2.setForeground(Color.WHITE);
      stapelBtn3.setForeground(Color.WHITE);
      stapelBtn4.setForeground(Color.WHITE);
    }
  }
  
  class Task7 extends TimerTask {
    public void run() {
      connectTimeout = false;
    }
  }
  
  class TaskV1 extends TimerTask {                           //Kai26.02.Diese TimerTasks sind für den sukzessiven Aufbau der VERLOREN-Anzeige, damit man noch schnell die Karten sehen kann
    public void run() {
      selBtn1.setText("V");
      selBtn1.setVisible(true);
      selBtn1.setBackground(new Color(0x731111));
      selBtn1.setIcon(null);
      selBtn1.setContentAreaFilled(true);
    }
  }
  
  class TaskV2 extends TimerTask {
    public void run() {
      selBtn2.setText("E");
      selBtn2.setVisible(true);
      selBtn2.setBackground(new Color(0x000000));
      selBtn2.setIcon(null);
      selBtn2.setContentAreaFilled(true);
    }
  }
  
  class TaskV3 extends TimerTask {
    public void run() {
      selBtn3.setText("R");
      selBtn3.setVisible(true);
      selBtn3.setBackground(new Color(0x731111));
      selBtn3.setIcon(null);
      selBtn3.setContentAreaFilled(true);
    }
  }
  
  class TaskV4 extends TimerTask {
    public void run() {
      selBtn4.setText("L");
      selBtn4.setVisible(true);
      selBtn4.setBackground(new Color(0x000000));
      selBtn4.setIcon(null);
      selBtn4.setContentAreaFilled(true);
    }
  }
  
  class TaskV5 extends TimerTask {
    public void run() {
      selBtn5.setText("O");
      selBtn5.setVisible(true);
      selBtn5.setBackground(new Color(0x731111));
      selBtn5.setIcon(null);
      selBtn5.setContentAreaFilled(true);
    }
  }
  
  class TaskV6 extends TimerTask {
    public void run() {
      selBtn6.setText("R");
      selBtn6.setVisible(true);
      selBtn6.setBackground(new Color(0x000000));
      selBtn6.setIcon(null);
      selBtn6.setContentAreaFilled(true);
    }
  }
  
  class TaskV7 extends TimerTask {
    public void run() {
      selBtn7.setText("E");
      selBtn7.setVisible(true);
      selBtn7.setBackground(new Color(0x731111));
      selBtn7.setIcon(null);
      selBtn7.setContentAreaFilled(true);
    }
  }
  
  class TaskV8 extends TimerTask {
    public void run() {
      selBtn8.setText("N");
      selBtn8.setVisible(true);
      selBtn8.setBackground(new Color(0x000000));
      selBtn8.setIcon(null);
      selBtn8.setContentAreaFilled(true);
      jRestart.setVisible(true); 
    }
  }
  
  
  
  //TimerTask-Aufrufmethoden
  public void startTimer0() {                //Kai07.03.
    timer0 = new java.util.Timer();
    timer0.schedule(new Task0(), 2500);
    timer01 = new java.util.Timer();
    timer01.schedule(new Task01(), 800);
  }
  
  
  public void startTimer21() {
    timer2 = new java.util.Timer();
    timer2.schedule(new Task2(), 600);
  }
  
  public void startTimer22() {
    timer3 = new java.util.Timer();
    timer3.schedule(new Task3(), 600);
  }
  
  public void startTimer23() {
    timer4 = new java.util.Timer();
    timer4.schedule(new Task4(), 600);
  }
  
  public void startTimer24() {
    timer5 = new java.util.Timer();
    timer5.schedule(new Task5(), 600);
  }
  
  public void startTimer6() {
    timer6 = new java.util.Timer();
    timer6.schedule(new Task6(), 600);
  }
  
  public void startTimer7() {
    timer7 = new java.util.Timer();
    timer7.schedule(new Task7(), 1000);
  }
  
  public void timerVerl1() {
    timerv1 = new java.util.Timer();
    timerv1.schedule(new TaskV1(), 2000);
  }
  
  public void timerVerl2() {
    timerv2 = new java.util.Timer();
    timerv2.schedule(new TaskV2(), 2200);
  }
  
  public void timerVerl3() {
    timerv3 = new java.util.Timer();
    timerv3.schedule(new TaskV3(), 2400);
  }
  
  public void timerVerl4() {
    timerv4 = new java.util.Timer();
    timerv4.schedule(new TaskV4(), 2600);
  }
  
  public void timerVerl5() {
    timerv5 = new java.util.Timer();
    timerv5.schedule(new TaskV5(), 2800);
  }
  
  public void timerVerl6() {
    timerv6 = new java.util.Timer();
    timerv6.schedule(new TaskV6(), 3000);
  }
  
  public void timerVerl7() {
    timerv7 = new java.util.Timer();
    timerv7.schedule(new TaskV7(), 3200);
  }
  
  public void timerVerl8() {
    timerv8 = new java.util.Timer();
    timerv8.schedule(new TaskV8(), 3400);
  }
  
  
  public void processNewMessage(String pMessage) {
    if (pMessage.startsWith("Handkarten:")) {
      String karten = pMessage.substring(12);
      updateSelBtns(karten);
      echoClient.send("+VERLOREN");
      //ColourSelBtnWhite();
      pMessage = "";
      System.out.println("test");
    } else if (pMessage.startsWith("Willkommen auf")) {
      jTextField1.setEditable(false);
      jTextField2.setEditable(false);
      jButton1.setEnabled(false);
      nameTf.setVisible(true);
      nameTf.setEditable(true);
      jLabel4.setVisible(true);                                        
      jButton4.setVisible(true);
      jComboBox2.setVisible(true);
      jButton7.setVisible(true);
      jLabel9.setVisible(true);
      jButton1.setVisible(false);            //Neu16.02.
      btnLeave.setVisible(true);
      echoClient.send("+SPIELERLISTE"); 
      jComboBox2.removeAllItems();                     //Kai26.02.
      jComboBox2Model.addElement("Spielstart");
      jComboBox2Model.addElement("Hilfe");
      jTextField4.setVisible(true); 
    } // end of if-else
    else if (pMessage.contains("Wähle einen neuen Namen")||pMessage.contains("Fehlerhafte Syntax, halte dich ans Protokoll!")) {
      if (aktPhase==0) {
        nameTf.setEditable(true);
        jButton4.setVisible(true); 
      } // end of if
      if (aktPhase==1) {
      } // end of if
    } // end of if
    else if (pMessage.startsWith("Verbindung zu ")) { //neu11.02.17 Anzeige ob eine Verbindung besteht
      jConnectLb.setVisible(true);
      jConnectLb.setText("disconnected");
      jConnectLb.setBackground(Color.RED);
      
      jTextArea1.setText("Verbindung zum Server unterbrochen!\nDu musst dich neu verbinden!");
      
      
      pMessage = "";
      defaultClient(0);
      timer.cancel();
    } // end of if-else
    else if (pMessage.startsWith("Verbindung : ")) {
      jConnectLb.setVisible(true);
      jConnectLb.setText("connected");
      jConnectLb.setBackground(Color.GREEN);
      pMessage = "";
    } // end of if-else
    else if (pMessage.startsWith("Das Spiel läuft..")) {
      toggleStapelBtnsVisible(true);
      nameTf.setEditable(false);
      jButton4.setVisible(false);
      toggleRank(false);
      jLabel5.setVisible(false);
      aktPhase = 2;
      echoClient.send("+SPIELERLISTE");
      echoClient.send("+ZEIGESTAPEL");
      echoClient.send("+ZEIGEKARTEN");
      echoClient.send("+AMZUG");
      echoClient.send("+AKTSPIELER");
      jComboBox1.setVisible(true); //Kai12.02.
      jButton3.setVisible(true);
      jLabel6.setVisible(true);
      jLabel11.setVisible(true);
      jComboBox2.removeAllItems();
      jComboBox2Model.addElement("Hilfe");
      jComboBox2Model.addElement("Aktuelle Phase");
      //jComboBox2Model.addElement("Wer ist am Zug?");
      jComboBox2Model.addElement("Anzahl Restkarten");
      jComboBox2Model.addElement("Verlassen");
      jComboBox2.setSelectedIndex(0);
      toggleSelBtns(true);
      startTimer();
      jTextField4.setVisible(false);
    } else if (pMessage.startsWith("Die Rankingphase")) {
      aktPhase = 1;
      nameTf.setEditable(false);
      jButton4.setVisible(false);
      toggleRank(true);
      jLabel5.setVisible(true);
      //send.setVisible(true);
      echoClient.send("+ZEIGEKARTEN");
      echoClient.send("+SPIELERLISTE");
      //neu
      jComboBox2Model.addElement("Aktuelle Phase");
      jComboBox2.setSelectedIndex(0);
      //toggleSelBtns(false); //Kai13.02.
      toggleSelBtns(true);
      jTextField4.setVisible(false);
    } else if (pMessage.startsWith("Dein Ranking von")) {
      toggleRank(false);
      jLabel5.setVisible(false);
    }else if( (pMessage.startsWith("Auf dem Server ist schon")) || (pMessage.startsWith("Du kommst zu spät")) || (pMessage.startsWith("Der Server ist voll")) || (pMessage.startsWith("Das Spiel hat schon begonnen"))|| (pMessage.startsWith("Es gibt bereits einen Spieler mit deiner IP-Adresse auf dem Server"))){   //Kai15.02.
      jConnectLb.setVisible(true);
      jConnectLb.setText("disconnected");
      jTextArea1.setText(pMessage + "\n\n");
      jConnectLb.setBackground(Color.RED);
      jTextArea1.append("Verbindung zum Server unterbrochen!\nDu musst dich neu verbinden!\n");
      pMessage = "";
      btnLeave.setVisible(false);
      jButton1.setVisible(true);
      defaultClient(0);
      timer.cancel();
      
      echoClient.close();         
    }
    else if (pMessage.startsWith("Stapel: ")) {
      String stapel = pMessage.substring(8);
      updateStapelBtns(stapel);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("Deine Karte wurde abgelegt")) {
      echoClient.send("+GEWONNEN");
      echoClient.send("+VERLOREN"); //Kai14.02. Reihenfolge geändert. 
      echoClient.send("+ZUGDARFBEENDEN");
      pMessage = "";
    } else if (pMessage.contains("Du darfst deinen Zug noch nicht beenden")) {
      echoClient.send("+GEWONNEN");
      echoClient.send("+VERLOREN");
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("hat seinen/ihren Zug")) {
      echoClient.send("+AMZUG");
      echoClient.send("+ZEIGESTAPEL");
      echoClient.send("+ZEIGEKARTEN");
      echoClient.send("+AKTSPIELER");
      aufhoerenBtn.setVisible(false);
      toggleStapelBtns(false);
    } else if (pMessage.contains("Du darfst deinen Zug beenden")) {
      aufhoerenBtn.setVisible(true);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("hat soeben das Spiel verlassen. Wie schade!")) { //neu 11.02.17
      if (aktPhase<3) {  //Daniel 19.02.17 - GameV13 -> verhindert die unnötigen Anfragen nach Spielende.
        
        echoClient.send("+AMZUG");
        echoClient.send("+ZEIGESTAPEL");
        echoClient.send("+ZEIGEKARTEN");
        echoClient.send("+AKTSPIELER");
      } // end of if
      echoClient.send("+SPIELERLISTE");
    } // end of if-else
    else if (pMessage.startsWith("Aktuell spielen:")) {
      upgdatePlayerList(pMessage);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("kann nicht genügend Karten ablegen")) {
      aktPhase = 3;
      jComboBox1.setVisible(false);
      verlorenBtns();
      toggleSelBtns(false);
      toggleStapelBtns(false);
      jButton3.setVisible(false);
      
      jComboBox2.removeAllItems();
      jComboBox2Model.addElement("Hilfe");
      jComboBox2Model.addElement("Aktuelle Phase");
      jComboBox2Model.addElement("Anzahl Restkarten");
      jComboBox2Model.addElement("Spieldauer");
      jComboBox2Model.addElement("Score");
      //jComboBox2Model.addElement("Restart");
      jComboBox2Model.addElement("Verlassen");
      jComboBox2.setSelectedIndex(0); 
      timer.cancel();
      jTextField4.setVisible(true);
    } // end of if-el
    else if (pMessage.equals("Du bist an der Reihe!")){
      toggleStapelBtns(true);
      toggleStapelBtns(true);
    } // end of if-else
    else if (pMessage.contains("< ist an der Reihe.")){
      String temp[] = pMessage.split("<");
      markAktSpieler(temp[0]);
      pMessage="";
    } // end of if-else
    else if (pMessage.startsWith("Ungültiger Zug")) {
      stapelBtn1.setForeground(Color.RED);
      stapelBtn2.setForeground(Color.RED);
      stapelBtn3.setForeground(Color.RED);
      stapelBtn4.setForeground(Color.RED);
      startTimer6();
    } // end of if-else
    
    else if (pMessage.contains("Gewonnen! Wow!")) {
      aktPhase = 3;
      aufhoerenBtn.setVisible(false);
      jComboBox1.setVisible(false);
      gewonnenBtns();
      toggleSelBtns(false);
      toggleStapelBtns(false);
      jButton3.setVisible(false);
      
      jComboBox2.removeAllItems();
      jComboBox2Model.addElement("Hilfe");
      jComboBox2Model.addElement("Aktuelle Phase");
      jComboBox2Model.addElement("Anzahl Restkarten");
      jComboBox2Model.addElement("Spieldauer");                        
      jComboBox2Model.addElement("Score");
      jComboBox2Model.addElement("Verlassen");
      
      jComboBox2.setSelectedIndex(0);
      jRestart.setVisible(true);  
      timer.cancel();
      jTextField4.setVisible(true);
    } // end of if-else
    else if (pMessage.startsWith("Das Spiel wird neu gestartet!")) {   //daniel15.02.17
      //stellt den Anfangs Zustand her.
      defaultClient(1);
    } // end of if-else
    
    else if (pMessage.contains("Stapel 1 bitte fr") /*&& stapelBtn1.getBackground() != Color.RED*/) {     //Kai28.02.                          
      stapelBtn1.setBackground(Color.ORANGE);
      stapelBtn1.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 2 bitte fr")/*&& stapelBtn2.getBackground() != Color.RED*/){
      stapelBtn2.setBackground(Color.ORANGE);
      stapelBtn2.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 3 bitte fr")/*&& stapelBtn3.getBackground() != Color.RED*/){
      stapelBtn3.setBackground(Color.ORANGE);
      stapelBtn3.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 4 bitte fr")/*&& stapelBtn4.getBackground() != Color.RED*/){
      stapelBtn4.setBackground(Color.ORANGE);
      stapelBtn4.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 1 bitte UNBED")) {                               
      stapelBtn1.setBackground(Color.RED);
      stapelBtn1.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 2 bitte UNBED")){
      stapelBtn2.setBackground(Color.RED);
      stapelBtn2.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 3 bitte UNBED")){
      stapelBtn3.setBackground(Color.RED);
      stapelBtn3.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Stapel 4 bitte UNBED")){
      stapelBtn4.setBackground(Color.RED);
      stapelBtn4.setContentAreaFilled(true);
    } // end of if-else
    else if (pMessage.contains("Rot1 bitte weg machen")){
      stapelBtn1.setBackground(new JButton().getBackground());
      stapelBtn1.setContentAreaFilled(false);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("Rot2 bitte weg machen")){
      stapelBtn2.setBackground(new JButton().getBackground());
      stapelBtn2.setContentAreaFilled(false);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("Rot3 bitte weg machen")){
      stapelBtn3.setBackground(new JButton().getBackground());
      stapelBtn3.setContentAreaFilled(false);
      pMessage = "";
    } // end of if-else
    else if (pMessage.contains("Rot4 bitte weg machen")){
      stapelBtn4.setBackground(new JButton().getBackground());
      stapelBtn4.setContentAreaFilled(false);
      pMessage = "";
    } // end of if-else
    
    if (pMessage != "" && !pMessage.equals("ID_MSG")) {           //muss so bleiben (1x != und 1x equals)
      if (jTextArea1.getText() == null) {
        jTextArea1.append(pMessage);
      } else {
        jTextArea1.append(pMessage + "\n");
      } // end of if-else
    }
    
    jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
  }
  
  private void toggleRank(boolean i) {
    rank1.setVisible(i);
    rank2.setVisible(i);
    rank3.setVisible(i);
    rank4.setVisible(i);
    rank5.setVisible(i);
    rank6.setVisible(i);
    rank7.setVisible(i);
    rank8.setVisible(i);
    rank9.setVisible(i);
    rank10.setVisible(i);
  }
  
  
  private void updateSelBtns(String pHandkartenString) {
    List<String> list = new ArrayList<String>();
    
    while (pHandkartenString.contains(",")) {
      for (int i = 0; i < pHandkartenString.length(); i++) { //fügt alle mit kommas abgetrennten zahlen zu eienr liste hinzu.
        
        if (pHandkartenString.charAt(i) == ',') {
          list.add(pHandkartenString.substring(0, i));
          pHandkartenString = pHandkartenString.substring(i + 1);
          
          break;
        } // end of if                                 
      } // end of for
    } // end of for    
    
    list.add(pHandkartenString); //fügt die letzte zahl die hinten kein komma mehr aufweist hinzu.
    
    int stopPosition = -1;
    
    for (int i = 0; i < list.size(); i++) { //updatet alle select buttons mit den richtigen zahlen. 
      
      switch (i) {
        case 0:
        selBtn1.setText(list.get(i));
        selBtn1.setVisible(true);
        
        break;
        
        case 1:
        selBtn2.setText(list.get(i));
        selBtn2.setVisible(true);
        
        break;
        
        case 2:
        selBtn3.setText(list.get(i));
        selBtn3.setVisible(true);
        
        break;
        
        case 3:
        selBtn4.setText(list.get(i));
        selBtn4.setVisible(true);
        
        break;
        
        case 4:
        selBtn5.setText(list.get(i));
        selBtn5.setVisible(true);
        
        break;
        
        case 5:
        selBtn6.setText(list.get(i));
        selBtn6.setVisible(true);
        
        break;
        
        case 6:
        selBtn7.setText(list.get(i));
        selBtn7.setVisible(true);
        
        break;
        
        case 7:
        selBtn8.setText(list.get(i));
        selBtn8.setVisible(true);
        
        break;
      } // end of switch
      
      stopPosition = i;
    } // end of for   
    
    for (int i = stopPosition + 1; i < 8; i++) { //löscht den text aller nicht genutzen btns und macht sie unsichtbar.
      
      switch (i) {
        case 0:
        selBtn1.setText("");
        selBtn1.setVisible(false);
        
        break;
        
        case 1:
        selBtn2.setText("");
        selBtn2.setVisible(false);
        
        break;
        
        case 2:
        selBtn3.setText("");
        selBtn3.setVisible(false);
        
        break;
        
        case 3:
        selBtn4.setText("");
        selBtn4.setVisible(false);
        
        break;
        
        case 4:
        selBtn5.setText("");
        selBtn5.setVisible(false);
        
        break;
        
        case 5:
        selBtn6.setText("");
        selBtn6.setVisible(false);
        
        break;
        
        case 6:
        selBtn7.setText("");
        selBtn7.setVisible(false);
        
        break;
        
        case 7:
        selBtn8.setText("");
        selBtn8.setVisible(false);
        
        break;
      } // end of switch
    } // end of for 
    
    if (pHandkartenString.equals("null")) { //Kai12.02. letzte Karte bleibt so nicht mehr sichtbar mit Inhalt "null"
      selBtn1.setVisible(false);
      aufhoerenBtn.setVisible(false);
      echoClient.send("+GEWONNEN");                      //Kai15.02.
      echoClient.send("+ZUGBEENDEN");                
    } // end of if
  }
  
  private void updateStapelBtns(String pStapelKartenString) {
    List<String> list = new ArrayList<String>();
    
    while (pStapelKartenString.contains(",")) {
      for (int i = 0; i < pStapelKartenString.length(); i++) { //fügt alle mit kommas abgetrennten zahlen zu eienr liste hinzu.
        
        if (pStapelKartenString.charAt(i) == ',') {
          list.add(pStapelKartenString.substring(0, i));
          pStapelKartenString = pStapelKartenString.substring(i + 1);
          
          break;
        } // end of if
      } // end of for
    } // end of for    
    
    list.add(pStapelKartenString); //fügt die letzte zahl die hinten kein komma mehr aufweist hinzu.
    
    int stopPosition = -1;
    
    
    
    for (int i = 0; i < list.size(); i++) { //updatet alle select buttons mit den richtigen zahlen.       //Kai24.02. zeigt Differenz an: rot, wenn zum Ende des Stapels hin und grün, wenn durch 10-Differenz-Regel gelegt
      
      if (Integer.parseInt(list.get(i)) != 100 && Integer.parseInt(list.get(i)) != 1) {
        switch (i) {
          case 0:
          int prevStapel = 0;
          if (!stapelBtn1.getText().equals("")) {
            prevStapel = Integer.parseInt(stapelBtn1.getText());
          }else{
            prevStapel = 1;
          }
          int actStapel = Integer.parseInt(list.get(i));
          int diff = actStapel - prevStapel;
          if (!stapelBtn1.getText().equals(list.get(i))) {
            if (diff > 0) {
              jLabel16.setForeground(Color.RED);
            }else{
              jLabel16.setForeground(Color.GREEN);
              diff = -diff;
            }
            jLabel16.setText(Integer.toString(diff));
            jLabel16.setVisible(true);
            stapelBtn1.setVisible(false);
            
            //pAktStapel = 1;
            pAktStapelValue = list.get(i);
            startTimer21();
          } // end of if
          stapelBtn1.setText(list.get(i));
          stapelBtn1.setIcon(selBtn1Icon);
          
          break;
          
          case 1:
          if (!stapelBtn2.getText().equals("")) {
            prevStapel = Integer.parseInt(stapelBtn2.getText());
          }else{
            prevStapel = 1;
          }
          actStapel = Integer.parseInt(list.get(i));
          diff = actStapel - prevStapel;
          if (!stapelBtn2.getText().equals(list.get(i))) {
            if (diff > 0) {
              jLabel10.setForeground(Color.RED);
            }else{
              jLabel10.setForeground(Color.GREEN);
              diff = -diff;
            }
            jLabel10.setText(Integer.toString(diff));
            jLabel10.setVisible(true);
            stapelBtn2.setVisible(false);
            
            //pAktStapel = 2;
            pAktStapelValue = list.get(i);
            startTimer22();
          } // end of if
          stapelBtn2.setText(list.get(i));
          stapelBtn2.setIcon(selBtn1Icon);
          
          
          
          break;
          
          case 2:
          if (!stapelBtn3.getText().equals("")) {
            prevStapel = Integer.parseInt(stapelBtn3.getText());
          }else{
            prevStapel = 100;
          }
          actStapel = Integer.parseInt(list.get(i));
          diff = prevStapel - actStapel;
          if (!stapelBtn3.getText().equals(list.get(i))) {
            if (diff > 0) {
              jLabel17.setForeground(Color.RED);
            }else{
              jLabel17.setForeground(Color.GREEN);
              diff = -diff;
            }
            jLabel17.setText(Integer.toString(diff));
            jLabel17.setVisible(true);
            stapelBtn3.setVisible(false);
            
            //pAktStapel = 3;
            pAktStapelValue = list.get(i);
            startTimer23();
          } // end of if
          stapelBtn3.setText(list.get(i));
          stapelBtn3.setIcon(selBtn1Icon);
          
          
          
          break;
          
          case 3:
          if (!stapelBtn4.getText().equals("")) {
            prevStapel = Integer.parseInt(stapelBtn4.getText());
          }else{
            prevStapel = 100;
          }
          actStapel = Integer.parseInt(list.get(i));
          diff = prevStapel - actStapel;
          if (!stapelBtn4.getText().equals(list.get(i))) {
            if (diff > 0) {
              jLabel18.setForeground(Color.RED);
            }else{
              jLabel18.setForeground(Color.GREEN);
              diff = -diff;
            }
            jLabel18.setText(Integer.toString(diff));
            jLabel18.setVisible(true);
            stapelBtn4.setVisible(false);
            
            //pAktStapel = 4;
            pAktStapelValue = list.get(i);
            startTimer24();
          } // end of if
          stapelBtn4.setText(list.get(i));
          stapelBtn4.setIcon(selBtn1Icon);
          
          
          
          break;
        } // end of switch
      } // end of if
      
      
    } // end of for                           
  }
  
  private void verlorenBtns() {
    for (int i = 0; i < 8; i++) { //löscht den text aller btns und macht schreibt verloren.
      
      switch (i) {
        case 0:
        //selBtn1.setText("V");
        //selBtn1.setVisible(true);
        //selBtn1.setBackground(new Color(0x731111));
        stapelBtn1.setVisible(true);
        stapelBtn2.setVisible(true);
        stapelBtn3.setVisible(true);
        stapelBtn4.setVisible(true);
        stapelBtn1.setBackground(new Color(0x731111));
        stapelBtn2.setBackground(new Color(0x000000));
        stapelBtn3.setBackground(new Color(0x731111));
        stapelBtn4.setBackground(new Color(0x000000));
        stapelBtn1.setContentAreaFilled(true);
        stapelBtn2.setContentAreaFilled(true);
        stapelBtn3.setContentAreaFilled(true);
        stapelBtn4.setContentAreaFilled(true);
        jLabel16.setVisible(false);
        jLabel10.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        
        
        timerVerl1();
        
        break;
        
        case 1:
        /*selBtn2.setText("E");
        selBtn2.setVisible(true);
        selBtn2.setBackground(new Color(0x000000)); */
        timerVerl2();
        
        break;
        
        case 2:
        /*selBtn3.setText("R");
        selBtn3.setVisible(true);
        selBtn3.setBackground(new Color(0x731111));         */
        timerVerl3();
        
        break;
        
        case 3:
        /*selBtn4.setText("L");
        selBtn4.setVisible(true);
        selBtn4.setBackground(new Color(0x000000));   */
        timerVerl4();
        
        break;
        
        case 4:
        /*selBtn5.setText("O");
        selBtn5.setVisible(true);
        selBtn5.setBackground(new Color(0x731111)); */
        timerVerl5();
        
        break;
        
        case 5:
        /*selBtn6.setText("R");
        selBtn6.setVisible(true);
        selBtn6.setBackground(new Color(0x000000));   */
        timerVerl6();
        
        break;
        
        case 6:
        /*selBtn7.setText("E");
        selBtn7.setVisible(true);
        selBtn7.setBackground(new Color(0x731111));    */
        timerVerl7();
        
        break;
        
        case 7:
        /*selBtn8.setText("N");
        selBtn8.setVisible(true);
        selBtn8.setBackground(new Color(0x000000));     */
        timerVerl8();
        
        break;
      } // end of switch
    }
  }
  
  private void gewonnenBtns() {
    for (int i = 0; i < 8; i++) { //löscht den text aller btns und macht schreibt gewonnen.
      
      switch (i) {
        case 0:
        selBtn1.setText("G");
        selBtn1.setVisible(true);
        selBtn1.setBackground(new Color(0x124211));
        stapelBtn1.setVisible(true);
        stapelBtn2.setVisible(true);
        stapelBtn3.setVisible(true);
        stapelBtn4.setVisible(true);
        stapelBtn1.setBackground(new Color(0x124211));
        stapelBtn2.setBackground(new Color(0x721D7F));
        stapelBtn3.setBackground(new Color(0x124211));
        stapelBtn4.setBackground(new Color(0x721D7F));
        jLabel16.setVisible(false);
        jLabel10.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        selBtn1.setIcon(null);
        selBtn2.setIcon(null);
        selBtn3.setIcon(null);
        selBtn4.setIcon(null);
        selBtn5.setIcon(null);
        selBtn6.setIcon(null);
        selBtn7.setIcon(null);
        selBtn8.setIcon(null);
        selBtn1.setContentAreaFilled(true);
        selBtn2.setContentAreaFilled(true);
        selBtn3.setContentAreaFilled(true);
        selBtn4.setContentAreaFilled(true);
        selBtn5.setContentAreaFilled(true);
        selBtn6.setContentAreaFilled(true);
        selBtn7.setContentAreaFilled(true);
        selBtn8.setContentAreaFilled(true);
        stapelBtn1.setContentAreaFilled(true);
        stapelBtn2.setContentAreaFilled(true);
        stapelBtn3.setContentAreaFilled(true);
        stapelBtn4.setContentAreaFilled(true);
        
        break;
        
        case 1:
        selBtn2.setText("E");
        selBtn2.setVisible(true);                                     
        selBtn2.setBackground(new Color(0x721D7F));
        
        break;
        
        case 2:
        selBtn3.setText("W");
        selBtn3.setVisible(true);
        selBtn3.setBackground(new Color(0x124211));
        
        break;
        
        case 3:
        selBtn4.setText("O");
        selBtn4.setVisible(true);
        selBtn4.setBackground(new Color(0x721D7F));
        
        break;
        
        case 4:
        selBtn5.setText("N");
        selBtn5.setVisible(true);
        selBtn5.setBackground(new Color(0x124211));
        
        break;
        
        case 5:
        selBtn6.setText("N");
        selBtn6.setVisible(true);
        selBtn6.setBackground(new Color(0x721D7F));
        
        break;
        
        case 6:
        selBtn7.setText("E");
        selBtn7.setVisible(true);
        selBtn7.setBackground(new Color(0x124211));
        
        break;
        
        case 7:
        selBtn8.setText("N");
        selBtn8.setVisible(true);
        selBtn8.setBackground(new Color(0x721D7F));
        
        break;
      } // end of switch
    }
  }
  
  public void dispatchKeyEvent(KeyEvent e) { //debug konsole
    
    if (e.getID() == e.KEY_PRESSED) {
      if (e.getKeyCode() == e.VK_F1) {
        if (jTextField3.isVisible()) {
          jTextField3.setVisible(false);
          jButton2.setVisible(false);
          jButton8.setVisible(false);
        } else {
          jTextField3.setVisible(true);
          jButton2.setVisible(true);
          jButton8.setVisible(true);
        }
      } // end of if
    } // end of if
  }
  public void defaultClient(int phase){//Daniel15.02 -> setzt den Client wieder so zurück, als würde man ihn neustarten.
    //Attribute zurücksetzen:
    //aktPhase = phase;
    
    //Handkarten & Stapel zurücksetzen:
    //Stapel:
    stapelBtn1.setBackground(new JButton().getBackground());
    stapelBtn2.setBackground(new JButton().getBackground());
    stapelBtn3.setBackground(new JButton().getBackground());
    stapelBtn4.setBackground(new JButton().getBackground());
    stapelBtn1.setIcon(stapelBtn1Icon);
    stapelBtn2.setIcon(stapelBtn2Icon);
    stapelBtn3.setIcon(stapelBtn3Icon);
    stapelBtn4.setIcon(stapelBtn4Icon);
    selBtn1.setContentAreaFilled(false);
    selBtn2.setContentAreaFilled(false);
    selBtn3.setContentAreaFilled(false);
    selBtn4.setContentAreaFilled(false);
    selBtn5.setContentAreaFilled(false);
    selBtn6.setContentAreaFilled(false);
    selBtn7.setContentAreaFilled(false);
    selBtn8.setContentAreaFilled(false);
    stapelBtn1.setContentAreaFilled(false);
    stapelBtn2.setContentAreaFilled(false);
    stapelBtn3.setContentAreaFilled(false);
    stapelBtn4.setContentAreaFilled(false);
    selBtn1.setIcon(selBtn1Icon);
    selBtn2.setIcon(selBtn2Icon);
    selBtn3.setIcon(selBtn3Icon);
    selBtn4.setIcon(selBtn4Icon);
    selBtn5.setIcon(selBtn5Icon);
    selBtn6.setIcon(selBtn6Icon);
    selBtn7.setIcon(selBtn7Icon);
    selBtn8.setIcon(selBtn8Icon);
    stapelBtn1.setEnabled(false);
    stapelBtn2.setEnabled(false);
    stapelBtn3.setEnabled(false);
    stapelBtn4.setEnabled(false);
    stapelBtn1.setText("");
    stapelBtn2.setText("");
    stapelBtn3.setText("");
    stapelBtn4.setText("");
    //Handkarten:
    hideSelBtns();
    resetSelBtn();
    jComboBox2.removeAllItems();
    
    //resetet die GUI
    switch (phase) {
      case  0:
      //für Phase 0. <- erreicht durch einen Disconnecet
      //Setzt das Menü auf den Anfangszustand.
      jComboBox2.removeAllItems();  
      jComboBox2Model.addElement("Spielstart");
      jComboBox2Model.addElement("Hilfe");
      jComboBox2.setSelectedIndex(0);
      jButton1.setEnabled(true);
      jButton1.setVisible(true);
      btnLeave.setVisible(false);
    jTextField4.setVisible(false);
      jTextField1.setEditable(true);
      jTextField2.setEditable(true);
      //den Rest ausblenden.
      jLabel4.setVisible(false);
      jLabel5.setVisible(false);
      nameTf.setVisible(false);
      jLabel11.setVisible(false);
      nameTf.setEditable(true);
      toggleRank(false);
      hideSelBtns();
      jLabel6.setVisible(false);
      jButton4.setVisible(false);
      jRestart.setVisible(false);
      aufhoerenBtn.setVisible(false);
      jList1Model.removeAllElements();
      jComboBox1.setVisible(false);
      jButton3.setVisible(false);
      jTextField3.setVisible(false);
      jButton2.setVisible(false);
      aktPhase = 0;                 //muss in beiden Fällen 0, daher Paramter phase d. Methode unpassend 
      jComboBox2.setVisible(false);
      jLabel9.setVisible(false);
      jButton7.setVisible(false);
      jLabel11.setText("Spielzeit: 0:00");
      liveTimerSec = 0;
      liveTimerMin = 0;
      index = 0;
      jList1.setSelectionBackground(Color.WHITE);
      break;
      case  1: 
      //für Phase 0   <-erreicht duch einen Neustart
      //Setzt das Menü auf die Anmeldephase
      jComboBox2.removeAllItems();  
      jComboBox2Model.addElement("Spielstart");
      jComboBox2Model.addElement("Hilfe");
      jComboBox2.setSelectedIndex(0);
      
      jConnectLb.setVisible(true);
      jTextField1.setEditable(false);
      jTextField2.setEditable(false);
      hideSelBtns();
      jLabel6.setVisible(false);
      jLabel4.setVisible(true);
      nameTf.setVisible(true);
      nameTf.setEditable(true);
      toggleRank(false);
      jButton4.setVisible(true);
      jRestart.setVisible(false);
      jLabel11.setVisible(false);
      aufhoerenBtn.setVisible(false);
      jComboBox1.setVisible(false);
      jButton3.setVisible(false);
      jLabel5.setVisible(false);
      jTextField3.setVisible(false);
      jButton2.setVisible(false);
      jLabel11.setText("Spielzeit: 0:00");
      liveTimerSec = 0;
      liveTimerMin = 0;
      aktPhase = 0;                //muss in beiden Fällen 0, daher Paramter phase d. Methode unpassend
      index = 0;
      jList1.setSelectionBackground(Color.WHITE);
      break;
      default: 
      
    } // end of switch
  }
  public void upgdatePlayerList(String pString){
    jList1Model.removeAllElements();
    pString = pString.substring(16);
    String[] temp = pString.split(",");
    char c=',';// das zu zählende Zeichen
    int counter=0;// Zähler
    for(int i=pString.length()-1;i>=0;i--){
      if(pString.charAt(i)==c) counter++;
    }
    for (int i = 0 ;i<counter ;i++ ) {
      jList1Model.addElement(temp[i]);  
    } // end of for
    Spieleranzahl = counter;
  }
  public void markAktSpieler(String pSpieler){ // 24.02.17 - GameV18 -> markiert den aktuellen Spieler in der Spielerliste.
    int i = jList1.getModel().getSize();
    String temp;
    pSpieler = " "+pSpieler;
    for (int j = 0;j<i ;j++ ) {
      temp = ""+jList1.getModel().getElementAt(j);
      if (temp.equals(pSpieler)) {
        index = j;
        jList1.setSelectedIndex(j);
        jList1.setSelectionBackground(Color.CYAN);
      } // end of if
    } // end of for
  }  
  
} // end of class clientGui


class MyDispatcher implements KeyEventDispatcher {
  private clientGui cl;
  
  public MyDispatcher(clientGui cl) {
    this.cl = cl;
  }
  
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {
    cl.dispatchKeyEvent(e);
    
    return false;
  }
}

