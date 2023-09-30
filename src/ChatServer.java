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
    JPanel panelMesagge = new JPanel();

    File file = new File("chatServ.txt");

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");

    ChatClient chat = new ChatClient();

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
        panelMesagge.setLayout(new BoxLayout(panelMesagge, BoxLayout.Y_AXIS));
        add(panelMesagge, BorderLayout.NORTH);
        ActionListener actionListener1 = new btnStart();
        btnStart.addActionListener(actionListener1);
        ActionListener actionListener2 = new btnStop();
        btnStop.addActionListener(actionListener2);

        setVisible(true);
        chat.setVisible(false);
    }
    public class btnStop implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            System.exit(0);
            panelMesagge.removeAll();
            panelMesagge.repaint();
            panelMesagge.revalidate();
            startConst = true;
            chat.setVisible(false);
        }
    }
    public class btnStart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (startConst) {
                chat.setVisible(true);
                if(file.exists()){
                    try {
                        chatArchive(file, panelMesagge);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                chat.setVisible(true);
                chat.txtMsg.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            addMessage(panelMesagge, chat);
                        } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                    }
                });
                chat.btnSend.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            addMessage(panelMesagge, chat);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                    }
                });
                startConst = false;
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

    public static void addMessage (JPanel jPanel, ChatClient chat) throws Exception{
        String str = chat.txtMsg.getText();
        if(str.length() == 0) throw new Exception("Пустая строка");
        else{
            JLabel chatMessage = new JLabel();
            jPanel.add(chatMessage);
            chatMessage.setText(chat.txtLogin.getText() + ": " + str);
            addTextFile(chat.txtLogin.getText() + ": " + str);
            jPanel.add(new JLabel(""));
            chat.txtMsg.setText("");
        }
    }

    public static void addTextFile(String str){
        try(FileWriter writer = new FileWriter("chatServ.txt", true))
        {
            writer.write(str);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}