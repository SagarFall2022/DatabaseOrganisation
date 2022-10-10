import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=postgresConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 652);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setBounds(294, 263, 113, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(294, 175, 117, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(471, 170, 190, 36);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="select * from pg_user where usename=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					
					String usrnm=textFieldUN.getText();
					char[] pp=passwordField.getPassword();
						String pswd= String.valueOf(pp);
						
						System.out.println("Username is "+usrnm);
						System.out.println("password is "+pswd);
					String u = null;
					while(rs.next()){
						count=count+1;
						u=rs.getString(1);
						
						
						
					}
					
					if(u.equalsIgnoreCase(usrnm) && pswd.equals("1234")) {
						JOptionPane.showMessageDialog(null, "Username and password is correct");
						frame.dispose();
						UserInfo usr=new UserInfo();
						usr.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and password not correct. Please Try Again");
					}
					
					rs.close();
					pst.close(); //After executing the query these two lines will close the connection with the DB
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnLogin.setBounds(345, 387, 145, 54);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(471, 258, 190, 36);
		frame.getContentPane().add(passwordField);
		
		lblNewLabel_2 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(18, 166, 264, 261);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Registration Database");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(170, 20, 401, 65);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
