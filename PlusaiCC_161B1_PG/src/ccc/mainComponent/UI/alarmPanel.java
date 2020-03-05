package ccc.mainComponent.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import ccc.interaction.internalFeatures.alarmClockBeep;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class alarmPanel {

	public JFrame frame;
	private boolean runned = false;
	private static String alarmName = "Alarm";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					alarmPanel window = new alarmPanel(alarmName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public alarmPanel(String string) {
		alarmName = string;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 335);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAlarm = new JLabel(alarmName);
		lblAlarm.setFont(new Font("Tahoma", Font.PLAIN, 75));
		lblAlarm.setBounds(32, 0, 330, 126);
		frame.getContentPane().add(lblAlarm);
		
		
		JButton okay = new JButton("Stop Alarm");
		okay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 alarmClockBeep.terminator = false;
			}
		});
		okay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		okay.setBounds(32, 162, 170, 104);
		frame.getContentPane().add(okay);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 111, 473, 2);
		frame.getContentPane().add(separator);
	}
}
