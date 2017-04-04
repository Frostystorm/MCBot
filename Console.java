import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;
import java.awt.Font;


public class Console {

	private JFrame mainPanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField commandBox;
	private static JScrollPane scroll;
	private static JTextArea chatBox;
	private static JLabel loginError;
		
	
	private JFrame partyPanel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Initialize everything here
		WhitelistHandler.readWhitelist();
    	Ranks.initialize();

    	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.mainPanel.setVisible(true);
					window.partyPanel.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void loginError(){ //Change login sucess indicator text on login error
		loginError.setText("Login Failed");
	}
	
	public static void loginSuccess(){ //Change login sucess indicator text on success of login
		loginError.setText("Successful Login");
	}
	
	
	private void initialize() {
		mainPanel = new JFrame();
		mainPanel.setTitle("FrostyMaingo Console");
		mainPanel.setBounds(100, 100, 1000, 600);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(117, 68, 308, 26);
		mainPanel.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(117, 106, 308, 26);
		mainPanel.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 73, 93, 16);
		mainPanel.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(12, 111, 93, 16);
		mainPanel.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(172, 144, 117, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Log in to ECC
				MCBotMain.recieveCredentials(usernameField.getText(),passwordField.getText());
			}
		});
		mainPanel.getContentPane().add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 175, 450, 12);
		mainPanel.getContentPane().add(separator);
		
		JLabel lblBotAccountLogin = new JLabel("Bot Account Login");
		lblBotAccountLogin.setBounds(154, 23, 135, 33);
		lblBotAccountLogin.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.getContentPane().add(lblBotAccountLogin);
     	
     	commandBox = new JTextField();
     	commandBox.setBounds(12, 524, 540, 26);
     	mainPanel.getContentPane().add(commandBox);
     	commandBox.setColumns(10);
     	
     	JButton btnEnter = new JButton("Enter");
     	btnEnter.setBounds(555, 524, 117, 29);
     	btnEnter.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) { //Send command from console
     			
     			MCBotMain.sendMessage(commandBox.getText());
     			commandBox.setText("");
     			
     		}
     	});
     	mainPanel.getContentPane().add(btnEnter);
     	
     	scroll = new JScrollPane();
     	scroll.setBounds(12, 199, 982, 313);
     	mainPanel.getContentPane().add(scroll);
     	
     	chatBox = new JTextArea();
     	chatBox.setLineWrap(true);
     	chatBox.setEditable(false);
     	scroll.setViewportView(chatBox);
     	
     	loginError = new JLabel("");
     	loginError.setBounds(301, 149, 179, 16);
     	mainPanel.getContentPane().add(loginError);
     	
     	JButton btnPartyManager = new JButton("Party Manager");
     	btnPartyManager.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			partyPanel.setVisible(true);
     			partyPanel.toFront();
     		}
     	});
     	btnPartyManager.setBounds(468, 26, 117, 29);
     	mainPanel.getContentPane().add(btnPartyManager);
     	     	     	
     
     	     	
     	
     	DefaultCaret caret = (DefaultCaret) chatBox.getCaret();
     	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	
     	
     	
     	
     	
     	
		partyPanel = new JFrame();
		partyPanel.setTitle("Party Manager");
		partyPanel.setBounds(100, 100, 500, 300);
		partyPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		partyPanel.getContentPane().setLayout(null);

     	
				
	}
	
	public static void updateChat(String incomingMessage){ //Update chat box with incoming input
		chatBox.append("\n");
		chatBox.append(incomingMessage);
	}
}
