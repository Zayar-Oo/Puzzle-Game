import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.DatabaseMetaData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Window.Type;

public class Level extends JFrame implements MouseListener {

	private JPanel contentPane;
	JLabel LvlOne, lblTwo, lvlThree,back;
	private String Data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Level frame = new Level(null);
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
	public Level(String data) {

		this.Data = data;
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//music();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				setBounds(0, 0, screenSize.width, screenSize.height);
				setExtendedState(MAXIMIZED_BOTH);

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lvlThree = new JLabel("5 x 5");
		lvlThree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lvlThree.setForeground(Color.RED);
		lvlThree.setFont(new Font("Verdana", Font.BOLD, 30));
		lvlThree.setBounds(673, 521, 88, 44);
		contentPane.add(lvlThree);
		lvlThree.addMouseListener(this);

		lblTwo = new JLabel("4 x 4");
		lblTwo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTwo.setForeground(Color.RED);
		lblTwo.setFont(new Font("Verdana", Font.BOLD, 30));
		lblTwo.setBounds(673, 405, 88, 44);
		contentPane.add(lblTwo);
		lblTwo.addMouseListener(this);

		LvlOne = new JLabel("3 x 3");
		LvlOne.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LvlOne.setForeground(new Color(255, 0, 0));
		LvlOne.setFont(new Font("Verdana", Font.BOLD, 30));
		LvlOne.setBounds(673, 285, 88, 44);
		contentPane.add(LvlOne);
		LvlOne.addMouseListener(this);

		JLabel lblNewLabel = new JLabel("ZERO TO HERO");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 27));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(132, 11, 248, 44);
		contentPane.add(lblNewLabel);

		JPanel redBar = new JPanel();
		redBar.setBackground(new Color(255, 0, 0));
		redBar.setBounds(0, 80, 1368, 3);
		contentPane.add(redBar);
		redBar.setLayout(null);

		JLabel Logo = new JLabel("");
		Logo.setBounds(10, 0, 88, 62);
		ImageIcon icon1 = new ImageIcon("Image\\logo.png");
		Image img1 = icon1.getImage();
		Image imgScale1 = img1.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(imgScale1);
		Logo.setIcon(scaledIcon1);
		contentPane.add(Logo);

		JLabel level = new JLabel("");
		level.setBounds(568, 243, 282, 375);
		ImageIcon levelIcon = new ImageIcon("Image\\level.png");
		Image levelIcon1 = levelIcon.getImage();
		Image lvlScale1 = levelIcon1.getScaledInstance(level.getWidth(), level.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon lvlscaledIcon1 = new ImageIcon(lvlScale1);
		level.setIcon(lvlscaledIcon1);
		contentPane.add(level);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(98, 46, 160, 185);
		ImageIcon l1 = new ImageIcon("Image\\dec.png");
		Image i1 = l1.getImage();
		Image s1 = i1.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc1 = new ImageIcon(s1);
		lblNewLabel_1.setIcon(sc1);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(20, 389, 160, 185);
		ImageIcon l2 = new ImageIcon("Image\\dec.png");
		Image i2 = l2.getImage();
		Image s2 = i2.getScaledInstance(lblNewLabel_1_1.getWidth(), lblNewLabel_1_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc2 = new ImageIcon(s2);
		lblNewLabel_1_1.setIcon(sc2);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(446, 599, 160, 185);
		ImageIcon l3 = new ImageIcon("Image\\dec.png");
		Image i3 = l3.getImage();
		Image s3 = i3.getScaledInstance(lblNewLabel_1_2.getWidth(), lblNewLabel_1_2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc3 = new ImageIcon(s3);
		lblNewLabel_1_2.setIcon(sc3);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("New label");
		lblNewLabel_1_3.setBounds(1010, 599, 160, 185);
		ImageIcon l4 = new ImageIcon("Image\\dec.png");
		Image i4 = l4.getImage();
		Image s4 = i4.getScaledInstance(lblNewLabel_1_3.getWidth(), lblNewLabel_1_3.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc4 = new ImageIcon(s4);
		lblNewLabel_1_3.setIcon(sc4);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("New label");
		lblNewLabel_1_4.setBounds(1141, 271, 160, 185);
		ImageIcon l5 = new ImageIcon("Image\\dec.png");
		Image i5 = l5.getImage();
		Image s5 = i2.getScaledInstance(lblNewLabel_1_4.getWidth(), lblNewLabel_1_4.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc5 = new ImageIcon(s5);
		lblNewLabel_1_4.setIcon(sc5);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("New label");
		lblNewLabel_1_5.setBounds(715, 30, 160, 185);
		ImageIcon l6 = new ImageIcon("Image\\dec.png");
		Image i6 = l6.getImage();
		Image s6 = i6.getScaledInstance(lblNewLabel_1_5.getWidth(), lblNewLabel_1_5.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc6 = new ImageIcon(s2);
		lblNewLabel_1_5.setIcon(sc6);
		contentPane.add(lblNewLabel_1_5);

		back = new JLabel("");
		back.setBounds(1276, 11, 68, 44);
		ImageIcon b1 = new ImageIcon("Image\\back.png");
		Image bi = b1.getImage();
		Image bs = bi.getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bsc = new ImageIcon(bs);
		back.setIcon(bsc);
		contentPane.add(back);
		back.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == LvlOne) {
			Play p = new Play(3, Data);
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == lblTwo) {
			Play p = new Play(4, Data);
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == lvlThree) {
			Play p = new Play(5, Data);
			p.setVisible(true);
			dispose();
		}
		if(e.getSource()==back) {
			Choose choose = new Choose();
			choose.setVisible(true);
			dispose();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public static void music() {
		try {
			String filepath="Image\\BGAudio.wav";
			AudioInputStream au = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(au);
				clip.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
