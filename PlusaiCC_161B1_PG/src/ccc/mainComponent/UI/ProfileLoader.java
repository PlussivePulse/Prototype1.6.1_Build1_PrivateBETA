package ccc.mainComponent.UI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProfileLoader {

	private static JFrame frame;
	private static JTextField textField;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setTitle("Profile Panel");
		frame.setBounds(100, 100, 783, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel pictureFrame = new JLabel();
		pictureFrame.setBounds(10, 11, 184, 276);
		Image image = ccc.mainComponent.UIComponents.ProfileLoader.image;
		image = image.getScaledInstance(pictureFrame.getWidth(), pictureFrame.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		pictureFrame.setIcon(icon);
		frame.getContentPane().add(pictureFrame);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 299, 184, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setVisible(true);
	}
}
