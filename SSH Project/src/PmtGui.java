 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.*;  
  
public class PmtGui extends JFrame {  
  
    private JPanel contentPane;  
    private JTextField textFieldHN;  
    private JTextField textFieldPW;  
  
    /** 
     * Launch the application. 
     */  
    public static void main(String[] args) {  
        EventQueue.invokeLater(new Runnable() {  
            public void run() {  
                try {  
                    PmtGui frame = new PmtGui();  
                    frame.setVisible(true);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
    }  
  
    /** 
     * Create the frame. 
     */  
    public PmtGui() {  
        setTitle("Performance Monitor Tool for 8950 SAM");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setBounds(100, 100, 450, 300);  
        contentPane = new JPanel();  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
        setContentPane(contentPane);  
        contentPane.setLayout(null);  
          
        JLabel lblHostName = new JLabel("Host Name:");  
        lblHostName.setFont(new Font("Tahoma", Font.BOLD, 12));  
        lblHostName.setBounds(117, 83, 83, 14);  
        contentPane.add(lblHostName);  
          
        JLabel lblPassword = new JLabel("Password:");  
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));  
        lblPassword.setBounds(117, 127, 67, 14);  
        contentPane.add(lblPassword);  
          
        textFieldHN = new JTextField();  
        textFieldHN.setBounds(210, 81, 122, 20);  
        contentPane.add(textFieldHN);  
        textFieldHN.setColumns(10);  
          
        JButton btnConnect = new JButton("Connect");  
        btnConnect.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent arg0) {  
                String host = textFieldHN.getText();              
                String passwd = textFieldPW.getText();  
                String user = host.substring(0, host.indexOf('@'));  
                host = host.substring(host.indexOf('@')+1);  
                try {  
                    JSch jsch=new JSch();  
                    Session session=jsch.getSession(user, host, 22);  
                    session.setHost(host);  
                    session.setPassword(passwd);  
                    session.connect();  
                      
                    Channel channel = session.openChannel("shell");  
                    channel.setInputStream(System.in);  
                    channel.setOutputStream(System.out);  
  
                    channel.connect();  
                }  
                catch (Exception e){  
                    System.out.println(e);  
                }  
                  
                  
            }  
        });  
        btnConnect.setBounds(117, 193, 89, 23);  
        contentPane.add(btnConnect);  
          
        JButton btnCancel = new JButton("Cancel");  
        btnCancel.setBounds(234, 193, 89, 23);  
        contentPane.add(btnCancel);  
          
        JLabel lblConnectToServer = new JLabel("Connect to Server");  
        lblConnectToServer.setFont(new Font("Tahoma", Font.BOLD, 13));  
        lblConnectToServer.setBounds(148, 29, 146, 14);  
        contentPane.add(lblConnectToServer);  
          
        textFieldPW = new JPasswordField(20);  
        textFieldPW.setBounds(210, 125, 122, 20);  
        contentPane.add(textFieldPW);  
        textFieldPW.setColumns(10);  
    }  
  
  
}  