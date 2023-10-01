import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ChatServer extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POS_X = 200;
    private static final int WINDOW_POS_Y = 200;
    boolean startConst = true;
    boolean stopConst = true;
    JPanel panelMessage = new JPanel();

    File file = new File("chatServ.txt");

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");

    public ChatServer() {
        super("ChatServer");
        createGUI();
    }

    public void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setResizable(false);
        JPanel panelBtn = new JPanel(new GridLayout(1, 2));
        panelBtn.add(btnStart);
        panelBtn.add(btnStop);
        add(panelBtn, BorderLayout.SOUTH);
        panelMessage.setLayout(new BoxLayout(panelMessage, BoxLayout.Y_AXIS));
        add(panelMessage, BorderLayout.NORTH);
        ActionListener actionListener1 = new btnStart();
        btnStart.addActionListener(actionListener1);
        ActionListener actionListener2 = new btnStop();
        btnStop.addActionListener(actionListener2);
        stopConst = false;
        setVisible(true);
    }
    public class btnStop implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(stopConst){
                panelMessage.removeAll();
                panelMessage.repaint();
                panelMessage.revalidate();
                startConst = true;
                stopConst = false;
            }
            else {
                System.out.println("Сервер уже остановлен");
            }
        }
    }
    public class btnStart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (startConst) {
                if(file.exists()){
                    try {
                        chatArchive(file, panelMessage);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                startConst = false;
                stopConst = true;
            }
            else {
                System.out.println("Сервер уже запущен");
            }
        }
    }

    public static void chatArchive (File file, JPanel jPanel) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            JLabel gg = new JLabel();
            jPanel.add(gg);
            gg.setText(st);
        }
    }
}