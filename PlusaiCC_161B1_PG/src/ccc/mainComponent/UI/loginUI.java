package ccc.mainComponent.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ccc.interaction.global.autoNotificationPusher;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private char[] passWord;
	private String userName;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUI window = new loginUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public loginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 285);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Terminal");
		lblNewLabel.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel usrLabel = new JLabel("Username");
		usrLabel.setBounds(24, 66, 67, 14);
		frame.getContentPane().add(usrLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(24, 124, 48, 14);
		frame.getContentPane().add(passwordLabel);
		
		textField = new JTextField();
		textField.setBounds(130, 63, 196, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 121, 196, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passWord = passwordField.getPassword(); 
				userName = textField.getText();
				
			}
		});
		
		btnLogin.setBounds(71, 203, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				passwordField.setText(null);
			}
		});
		
		btnReset.setBounds(170, 203, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(269, 203, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 180, 414, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 26, 414, 2);
		frame.getContentPane().add(separator_1);
	}
}
