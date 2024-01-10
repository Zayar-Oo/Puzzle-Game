import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Choose extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JLabel Pic1, Pic2, Pic3, Pic4, Pic5, UserChoice, back;
	File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choose frame = new Choose();
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
	public Choose() {
		setTitle("Zero To Hero");
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

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

		JLabel lblNewLabel = new JLabel("ZERO TO HERO");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 27));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(149, 11, 248, 44);
		contentPane.add(lblNewLabel);

		JPanel redBar = new JPanel();
		redBar.setBackground(new Color(255, 0, 0));
		redBar.setBounds(0, 78, 1368, 3);
		contentPane.add(redBar);
		redBar.setLayout(null);
		// Picture 1
		JPanel ShowPic1 = new JPanel();
		ShowPic1.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic1.setBounds(149, 108, 248, 246);
		contentPane.add(ShowPic1);
		ShowPic1.setLayout(null);

		Pic1 = new JLabel("");
		Pic1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Pic1.setBounds(0, 38, 248, 170);

		ImageIcon icon1 = new ImageIcon("Image\\smurf.jpg");
		Image img1 = icon1.getImage();
		Image imgScale1 = img1.getScaledInstance(Pic1.getWidth(), Pic1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(imgScale1);
		Pic1.setIcon(scaledIcon1);
		ShowPic1.add(Pic1);
		Pic1.addMouseListener(this);

		JLabel lblNewLabel_1 = new JLabel("SMURFS");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(27, 11, 211, 14);
		ShowPic1.add(lblNewLabel_1);

		// Picture 4
		JPanel ShowPic4 = new JPanel();
		ShowPic4.setLayout(null);
		ShowPic4.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic4.setBounds(149, 423, 248, 246);
		contentPane.add(ShowPic4);

		Pic4 = new JLabel("");
		Pic4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Pic4.setBounds(0, 38, 248, 170);
		ImageIcon icon4 = new ImageIcon("Image\\panda.jpg");
		Image img4 = icon4.getImage();
		Image imgScale4 = img4.getScaledInstance(Pic4.getWidth(), Pic4.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon4 = new ImageIcon(imgScale4);
		Pic4.setIcon(scaledIcon4);
		ShowPic4.add(Pic4);
		Pic4.addMouseListener(this);

		JLabel lblNewLabel_1_1 = new JLabel("Panda");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 11, 228, 14);
		ShowPic4.add(lblNewLabel_1_1);

		// Picture 2
		JPanel ShowPic2 = new JPanel();
		ShowPic2.setLayout(null);
		ShowPic2.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic2.setBounds(572, 108, 248, 246);
		contentPane.add(ShowPic2);

		Pic2 = new JLabel("");
		Pic2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Pic2.setBounds(0, 38, 248, 170);
		ImageIcon icon2 = new ImageIcon("Image\\nemo.jpg");
		Image img2 = icon2.getImage();
		Image imgScale2 = img2.getScaledInstance(Pic2.getWidth(), Pic2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(imgScale2);
		Pic2.setIcon(scaledIcon2);
		ShowPic2.add(Pic2);
		Pic2.addMouseListener(this);

		JLabel lblNewLabel_1_2 = new JLabel("NEMO");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_2.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(27, 11, 211, 14);
		ShowPic2.add(lblNewLabel_1_2);

		// Picture 3
		JPanel ShowPic3 = new JPanel();
		ShowPic3.setLayout(null);
		ShowPic3.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic3.setBounds(1001, 108, 248, 246);
		contentPane.add(ShowPic3);

		Pic3 = new JLabel("");
		Pic3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Pic3.setBounds(0, 39, 248, 170);
		ImageIcon icon3 = new ImageIcon("Image\\TandJ.jpg");
		Image img3 = icon3.getImage();
		Image imgScale3 = img3.getScaledInstance(Pic3.getWidth(), Pic3.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon3 = new ImageIcon(imgScale3);
		Pic3.setIcon(scaledIcon3);
		ShowPic3.add(Pic3);
		Pic3.addMouseListener(this);

		JLabel lblNewLabel_1_3 = new JLabel("Tom And Jerry");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_3.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(27, 11, 211, 14);
		ShowPic3.add(lblNewLabel_1_3);

		// Picture 5
		JPanel ShowPic5 = new JPanel();
		ShowPic5.setLayout(null);
		ShowPic5.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic5.setBounds(572, 423, 248, 246);
		contentPane.add(ShowPic5);

		Pic5 = new JLabel("");
		Pic5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Pic5.setBounds(0, 38, 248, 170);
		ImageIcon icon5 = new ImageIcon("Image\\Mrbean.jpg");
		Image img5 = icon5.getImage();
		Image imgScale5 = img5.getScaledInstance(Pic5.getWidth(), Pic5.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon5 = new ImageIcon(imgScale5);
		Pic5.setIcon(scaledIcon5);
		ShowPic5.add(Pic5);
		Pic5.addMouseListener(this);

		JLabel lblNewLabel_1_4 = new JLabel("Mr Bean");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_4.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(27, 11, 211, 14);
		ShowPic5.add(lblNewLabel_1_4);

		// Customize Picture
		JPanel ShowPic6 = new JPanel();
		ShowPic6.setLayout(null);
		ShowPic6.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), null, new Color(255, 0, 0), null));
		ShowPic6.setBounds(1001, 423, 248, 246);
		contentPane.add(ShowPic6);

		UserChoice = new JLabel("");
		UserChoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UserChoice.setBounds(52, 69, 158, 139);
		ImageIcon icon6 = new ImageIcon("Image\\plus.png");
		Image img6 = icon6.getImage();
		Image imgScale6 = img6.getScaledInstance(UserChoice.getWidth(), UserChoice.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon6 = new ImageIcon(imgScale6);
		UserChoice.setIcon(scaledIcon6);
		ShowPic6.add(UserChoice);
		UserChoice.addMouseListener(this);

		JLabel lblNewLabel_1_5 = new JLabel("Customize");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_5.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblNewLabel_1_5.setBounds(27, 11, 211, 14);
		ShowPic6.add(lblNewLabel_1_5);

		JLabel Logo = new JLabel("");
		Logo.setBounds(10, 0, 88, 62);
		ImageIcon Logoicon = new ImageIcon("Image\\logo.png");
		Image logo1 = Logoicon.getImage();
		Image imgScaleLogo = logo1.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIconLogo = new ImageIcon(imgScaleLogo);
		Logo.setIcon(scaledIconLogo);
		contentPane.add(Logo);

		back = new JLabel("");
		back.setBounds(1296, 5, 72, 62);
		ImageIcon b = new ImageIcon("Image\\back.png");
		Image bi = b.getImage();
		Image bimg = bi.getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sbi = new ImageIcon(bimg);
		back.setIcon(sbi);
		contentPane.add(back);
		back.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Pic1) {
			Level p = new Level("Image\\smurf.jpg");
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == Pic2) {
			Level p = new Level("Image\\nemo.jpg");
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == Pic3) {
			Level p = new Level("Image\\TandJ.jpg");
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == Pic4) {
			Level p = new Level("Image\\panda.jpg");
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == Pic5) {
			Level p = new Level("Image\\Mrbean.jpg");
			p.setVisible(true);
			dispose();
		}
		if (e.getSource() == back) {
			StartPage sp = new StartPage();
			sp.setVisible(true);
			dispose();
		}
		if (e.getSource() == UserChoice) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose your file");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int reVal = fileChooser.showOpenDialog(this);
			if (reVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();

				BufferedImage bi;
				try {
					bi = ImageIO.read(file);
					Level p = new Level(file.getPath());
					p.setVisible(true);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
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
}
