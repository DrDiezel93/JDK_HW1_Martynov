import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ChatClient extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POS_X = 800;
    private static final int WINDOW_POS_Y = 200;

    private static final int WINDOW = 100;


    JButton btnLogin = new JButton("login");
    JButton btnSend = new JButton("send");

    JTextField txtMsg = new JTextField();

    JTextField txtId = new JTextField("127.0.0.1");
    JTextField txtMask = new JTextField("8189");
    JLabel labTemp = new JLabel();
    JTextField txtLogin = new JTextField("DrDiezel");
    JPasswordField txtPass = new JPasswordField("123456");

    public ChatClient(ChatServer chatServ) {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setTitle("ChatClient");
        setResizable(false);
        JPanel panelReg1 = new JPanel(new GridLayout(2, 3));
        panelReg1.add(txtId);
        panelReg1.add(txtMask);
        panelReg1.add(labTemp);
        panelReg1.add(txtLogin);
        panelReg1.add(txtPass);
        panelReg1.add(btnLogin);
        add(panelReg1, BorderLayout.NORTH);
        JPanel panelReg2 = new JPanel();
        panelReg2.setLayout(new BoxLayout(panelReg2, BoxLayout.X_AXIS));
        panelReg2.add(txtMsg);
        panelReg2.add(btnSend);
        add(panelReg2, BorderLayout.SOUTH);

        txtMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chatServ.stopConst){
                    try {
                        addMessage(chatServ.panelMessage);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else System.out.println("Сервер не отвечает");
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chatServ.stopConst){
                    try {
                        addMessage(chatServ.panelMessage);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else System.out.println("Сервер не отвечает");
            }
        });

        setVisible(true);
    }

    public void addMessage (JPanel jPanel) throws Exception{
        String str = txtMsg.getText();
        if(str.length() == 0) throw new Exception("Пустая строка");
        else{
            JLabel chatMessage = new JLabel();
            jPanel.add(chatMessage);
            chatMessage.setText(txtLogin.getText() + ": " + str);
            addTextFile(txtLogin.getText() + ": " + str);
            jPanel.add(new JLabel(""));
            txtMsg.setText("");
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
