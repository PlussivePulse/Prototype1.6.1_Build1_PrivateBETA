package ccc.interaction.internalFeatures;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class timerWindow {

	private JFrame timerFrame;
	private static JLabel timeLabel;
	private static int times = 0;
	private static String hour;
	private static String min;
	private static String sec;
	private static soundBoard s = new soundBoard();
	private static boolean countdownMuted = false;
	private static String textTimer = "";
	private static boolean beepMuted = true;
	private JCheckBox voiceMuted;
	private JSeparator separator_1;
	private JLabel lblControl;
	protected static Object commandLine = "self-destruct";
	private static boolean runnable = true;
	private static Thread timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					timerWindow window = new timerWindow(Integer.valueOf(args[0]),args[1]);
					window.timerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param time 
	 */
	public timerWindow(Integer time, String text) {
		this.times = time;
		this.textTimer = text;
		initialize();
	}

	public timerWindow(Integer time, String text, String command) {
		this.times = time;
		this.textTimer = text;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		timerFrame = new JFrame();
		timerFrame.setTitle("TimerModule");
		timerFrame.setResizable(false);
		timerFrame.setBounds(100, 100, 600, 370);
		timerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		timerFrame.getContentPane().setLayout(null);
		
		timeLabel = new JLabel("Timer Initiated");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 95));
		timeLabel.setBounds(28, 103, 537, 119);
		timerFrame.getContentPane().add(timeLabel);
		
		JCheckBox chckbxBeepmuted = new JCheckBox("Beep Muted");
		chckbxBeepmuted.setSelected(true);
		chckbxBeepmuted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxBeepmuted.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chckbxBeepmuted.isSelected()) {
					beepMuted = true;
				}else {
					beepMuted = false;
				}
			}
		});
		chckbxBeepmuted.setBounds(28, 284, 151, 23);
		timerFrame.getContentPane().add(chckbxBeepmuted);
		
		voiceMuted = new JCheckBox("Voice Muted");
		voiceMuted.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(voiceMuted.isSelected()) {
					countdownMuted = true;
				}else {
					countdownMuted = false;
				}
			}
		});
		voiceMuted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		voiceMuted.setBounds(181, 284, 138, 23);
		timerFrame.getContentPane().add(voiceMuted);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 233, 555, 2);
		timerFrame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 90, 555, 2);
		timerFrame.getContentPane().add(separator_1);
		
		lblControl = new JLabel("Timer Setting : ");
		lblControl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblControl.setBounds(20, 246, 270, 31);
		timerFrame.getContentPane().add(lblControl);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(runnable = true) {
					runnable = false;
				}
			}
		});
		stopButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stopButton.setBounds(458, 259, 116, 61);
		timerFrame.getContentPane().add(stopButton);
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setEnabled(false);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timer.wait();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pauseButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pauseButton.setBounds(332, 259, 116, 61);
		timerFrame.getContentPane().add(pauseButton);
		SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception{
				Thread timer = new Thread(new runTimer());
				timer.run();
				return null;
				}
			};
       submitter.execute();
		
	}
	
	class runTimer implements Runnable {
			

			int i = times+1;
			int beepParameter = i;

			
				
			public void run() {
		
				beepParameter = i;
				
				if(beepParameter>400) {
					beepParameter=400;
				}
				
				while(runnable ==true) {
					
					if(i == 0) {
						runnable = false;
						timeLabel.setText("00:00:00");
					}else {
						i--;
						if(beepMuted == false) {
							s.generateBeepSound(1000 - ((beepParameter/500)*1000) , 125, 1);
							if(commandLine.equals("self-destruct")) {
								warningBeep w = new warningBeep(1);
							}
						}
						
						
						int hours = (i/60)/60;
						if(hours>=1) {
							if(hours<10) {
								hour ="0" + hours;
							}else {
								hour = hours + "";
							}
						}else {
							hour = "00";
						}
						
						int mins = (i/60);
						if(mins>=1) {
							if(mins<10) {
								min ="0" + mins;
							}else {
								min = mins + "";
							}
						}else {
							min = "00";
						}
						
						sec =  (i%60) + "";
						
						if(i%60 < 10) {
							sec = "0" + sec;
						}
						
						if(countdownMuted == false) {
							if(i>60 && i%60==0) {
								readLine(i/60 + " minute remaining.");
							}else if(i==60) {
								if(commandLine.equals("")) {
									readLine("one minute remaining.");
								}else if(commandLine.equals("self-destruct")){
									readLine("Warning self-destruct sequence will be initiated in one minute.");
									//readLine("Please cancel this timerModule to stop self-destruct sequence and prevent file and data damage.");
								}
						
							}else if(i==30) {
								readLine("thirty seconds remaining");
							}else if(i==10) {
								readLine("ten seconds");
							}else if(i==5) {
								readLine("five");
							}else if(i==4) {
								readLine("four");
							}else if(i==3) {
								readLine("three");
							}else if(i==2) {
								readLine("two");
							}else if(i==1) {
								readLine("one");
							}
						}
						
						if(soundBoard.muted==false) {
							if(i==0) {
								readLine(textTimer);
							}
						}
						
						
						
						
						timeLabel.setText(hour+":"+min+":"+sec);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
	}
	
	public static void readLine(String text) {
		SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception{
				s.readLineDefaultExpr(text);
				return null;
				}
			};
       submitter.execute();
	}

	public static void start(int timertotal, String timername) {
		// TODO Auto-generated method stub
		
	}
}
