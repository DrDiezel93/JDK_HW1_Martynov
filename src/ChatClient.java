import javax.swing.*;
import java.awt.*;
import java.io.File;

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

    public ChatClient() {
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
    }

}
