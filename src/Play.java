import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Play extends JFrame implements MouseListener {

	private ArrayList<JLabel> imageLabel;
	private JPanel contentPane;
	ConnectDatabase db = new ConnectDatabase();
	Connection con;
	PreparedStatement ps;
	private String Imgpath;
	private final int row;
	private final int col;
	private final int chunks;
	private final int SPACING = 3;
	private JLabel lasttemp, showMove, back, OriShow, hintCount;
	JPanel PlayingPanel;
	private static Clip backgroundMusic;
	private List<JLabel> originalOrder;
	private JLabel showTime = new JLabel("");
	private int move = 0;
	private int flagHint = 3;
	// Timer
	int eltimer = 0;
	int sec = 0;
	int min = 0;
	int hr = 0;
	String secForm = String.format("%02d", sec);
	String minForm = String.format("%02d", min);
	String hrForm = String.format("%02d", hr);
	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			eltimer = eltimer + 1000;
			hr = (eltimer / 3600000);
			min = (eltimer / 60000) % 60;
			sec = (eltimer / 1000) % 60;
			secForm = String.format("%02d", sec);
			minForm = String.format("%02d", min);
			hrForm = String.format("%02d", hr);
			showTime.setText(hrForm + " : " + minForm + " : " + secForm);
		}
	});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Play frame = new Play(null, null);
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
	public Play(Integer lvl, String path) {
		BGmusic();
		showTime.setText(hrForm + " : " + minForm + " : " + secForm);
		row = lvl;
		col = lvl;
		chunks = row * col;
		Imgpath = path;
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

		OriShow = new JLabel("       Show Hint");
		OriShow.setForeground(new Color(255, 0, 0));
		OriShow.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		OriShow.setBounds(67, 300, 218, 165);
		OriShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flagHint--;
				hintCount.setText(flagHint + "");

				if (flagHint == 2 || flagHint == 1 || flagHint == 0) {
					//eltimer = 1000;
					showHint(path);
				}
				if (flagHint <= -1) {
					JOptionPane.showMessageDialog(Play.this, "No Hints");
				}
			}
		});

		JLabel lblReset = new JLabel("Reset");
		lblReset.setFont(new Font("Verdana", Font.BOLD, 20));
		lblReset.setForeground(new Color(255, 255, 255));
		lblReset.setBounds(515, 611, 72, 23);
		contentPane.add(lblReset);
		lblReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shuffleImages();
				// resetPuzzle();
			}
		});
		contentPane.add(OriShow);
		showTime.setForeground(new Color(255, 255, 255));
		showTime.setFont(new Font("Verdana", Font.BOLD, 22));
		showTime.setBounds(1091, 405, 170, 36);
		contentPane.add(showTime);

		PlayingPanel = new JPanel();
		PlayingPanel.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, new Color(0, 255, 0), null));
		PlayingPanel.setBounds(355, 128, 643, 446);
		contentPane.add(PlayingPanel);

		showMove = new JLabel("0");
		showMove.setForeground(new Color(0, 128, 0));
		showMove.setFont(new Font("Verdana", Font.BOLD, 20));
		showMove.setBounds(1199, 334, 49, 36);
		contentPane.add(showMove);

		JLabel lblNewLabel_1_1 = new JLabel("MOVE : ");
		lblNewLabel_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(1095, 334, 94, 36);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblPause = new JLabel("Pause");
		lblPause.setForeground(Color.WHITE);
		lblPause.setFont(new Font("Verdana", Font.BOLD, 21));
		lblPause.setBounds(747, 610, 88, 23);
		contentPane.add(lblPause);
		lblPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
			}
		});

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

		JLabel Logo = new JLabel("");
		Logo.setBounds(10, 0, 88, 62);
		ImageIcon Logoicon = new ImageIcon("Image\\logo.png");
		Image logo1 = Logoicon.getImage();
		Image imgScaleLogo = logo1.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIconLogo = new ImageIcon(imgScaleLogo);
		Logo.setIcon(scaledIconLogo);
		contentPane.add(Logo);

		JLabel OriPic = new JLabel("");
		OriPic.setBounds(50, 278, 248, 198);
		ImageIcon op = new ImageIcon("Image\\frame.jpg");
		Image oi = op.getImage();
		Image os = oi.getScaledInstance(OriPic.getWidth(), OriPic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon osc = new ImageIcon(os);
		OriPic.setIcon(osc);
		contentPane.add(OriPic);

		JLabel showBoard = new JLabel("");
		showBoard.setBounds(1043, 291, 248, 198);
		ImageIcon sb = new ImageIcon("Image\\frame.jpg");
		Image si = sb.getImage();
		Image oss = si.getScaledInstance(showBoard.getWidth(), showBoard.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon ossc = new ImageIcon(oss);
		showBoard.setIcon(ossc);
		contentPane.add(showBoard);

		JLabel resetBtn = new JLabel("");
		resetBtn.setBounds(408, 490, 267, 264);
		ImageIcon re = new ImageIcon("Image\\reset.png");
		Image ri = re.getImage();
		Image rs = ri.getScaledInstance(resetBtn.getWidth(), resetBtn.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon rsc = new ImageIcon(rs);
		resetBtn.setIcon(rsc);
		contentPane.add(resetBtn);

		JLabel pauseBtn = new JLabel("");
		pauseBtn.setForeground(new Color(255, 255, 255));
		pauseBtn.setBounds(645, 490, 274, 264);
		ImageIcon p = new ImageIcon("Image\\pause.png");
		Image pi = p.getImage();
		Image ps = pi.getScaledInstance(pauseBtn.getWidth(), pauseBtn.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon psc = new ImageIcon(ps);
		pauseBtn.setIcon(psc);
		contentPane.add(pauseBtn);

		hintCount = new JLabel("3");
		hintCount.setForeground(new Color(0, 128, 0));
		hintCount.setFont(new Font("Verdana", Font.BOLD, 20));
		hintCount.setBounds(214, 231, 22, 36);
		contentPane.add(hintCount);

		JLabel lblNewLabel_1 = new JLabel("Hints   : ");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setBounds(91, 231, 101, 36);
		contentPane.add(lblNewLabel_1);

		back = new JLabel("");
		back.setBounds(1268, 5, 72, 62);
		ImageIcon b = new ImageIcon("Image\\back.png");
		Image bi = b.getImage();
		Image bimg = bi.getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sbi = new ImageIcon(bimg);
		back.setIcon(sbi);
		contentPane.add(back);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == back) {

					int option = JOptionPane.showConfirmDialog(Play.this, "Are you sure to leave ?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						StartPage sPage = new StartPage();
						sPage.setVisible(true);
						stopMusic();
						dispose();
					} else {
						stopMusic();
					}

				}

			}
		});
		SplitImage();
		shuffleImages();
	}

	// Show Hint
	private void showHint(String path) {
		// Display a temporary preview of the original image
		ImageIcon op1 = new ImageIcon(path);
		Image oi1 = op1.getImage();
		Image os1 = oi1.getScaledInstance(OriShow.getWidth(), OriShow.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon osc1 = new ImageIcon(os1);
		OriShow.setIcon(osc1);
		Timer hintTimer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OriShow.setIcon(null);
			}
		});
		hintTimer.setRepeats(false);
		hintTimer.start();

	}

	// Shuffle the Images
	private void shuffleImages() {
		Collections.shuffle(imageLabel);
		updateSplitPanel();
	}

	// For Winning Condition
	private boolean checkForWin(JPanel panel) {

		for (int i = 0; i < originalOrder.size(); i++) {
			JLabel piece = originalOrder.get(i);
			int row1 = i / row;
			int col1 = i % col;
			if (getPosition(piece).x != row1 || getPosition(piece).y != col1) {
				return false;
			}
		}
		return true;
	}

	// For testing Start
	private void resetPuzzle() {
		stopMusic();
		PlayingPanel.removeAll();
		for (JLabel jL : originalOrder) {
			PlayingPanel.add(jL);
		}
		PlayingPanel.revalidate();
		PlayingPanel.repaint();
		if (checkForWin(PlayingPanel)) {
			stopMusic();
			timer.stop();
			int lastIndex = originalOrder.size() - 1;
			originalOrder.get(lastIndex).setIcon(lasttemp.getIcon());

			int option = JOptionPane.showConfirmDialog(Play.this,
					"Congratulations! You solved the puzzle!\nDo you want to save?", "Win", JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				String username = JOptionPane.showInputDialog(Play.this, "Enter your username:");
				if (username != null && !username.trim().isEmpty()) {
					saveDataToDatabase(username);
				} else {
					stopMusic();
					JOptionPane.showMessageDialog(Play.this, "Invalid username. Puzzle completion data not saved.",
							"Error", JOptionPane.ERROR_MESSAGE);
					StartPage sPage = new StartPage();
					sPage.setVisible(true);
					dispose();
				}
				stopMusic();
			} else {
				StartPage sPage = new StartPage();
				sPage.setVisible(true);
				dispose();
			}
		}
	}

	// Testing End

	private void SplitImage() {

		BufferedImage[] imgs = getImages();
		PlayingPanel.setLayout(new GridLayout(row, col, SPACING, SPACING));
		originalOrder = new ArrayList<>();
		imageLabel = new ArrayList<>();
		for (int i = 0; i < imgs.length; i++) {

			if (i == imgs.length - 1) {
				// Temporarily store the original Image pieces of the last index (null)
				lasttemp = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[i].getSource())));
				// Add null piece to Playing Panel
				JLabel temp = new JLabel();
				temp.setIcon(null);
				imageLabel.add(temp);
				originalOrder.add(temp);
				PlayingPanel.add(temp);
				temp.addMouseListener(this);
				break;
			}

			JLabel puzzleParts = new JLabel(
					new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[i].getSource())));
			imageLabel.add(puzzleParts);
			PlayingPanel.add(puzzleParts);
			puzzleParts.addMouseListener(this);
			originalOrder.add(puzzleParts);

		}
	}

	private BufferedImage[] getImages() {

		File file = new File(Imgpath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, e);

		}
		BufferedImage images = null;
		try {
			images = ImageIO.read(fis);
			images = resizeImage(images, 643, 446);
		} catch (IOException e) {

			e.printStackTrace();
		}
		int chunkWidth = images.getWidth() / col;
		int chunkHeight = images.getHeight() / row;
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[chunks];

		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, images.getType());

				// draws the image chunk
				Graphics2D gr = imgs[count++].createGraphics();
				gr.drawImage(images, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
						chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
				gr.dispose();
			}
		}

		return imgs;
	}

	// Resize Image Pieces
	private BufferedImage resizeImage(BufferedImage old, int width, int height) {
		BufferedImage resize = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resize.createGraphics();
		g2d.drawImage(old, 0, 0, width, height, null);
		g2d.dispose();
		return resize;
	}

	// Finding the position of piece
	private Point getPosition(JLabel puzzleParts) {
		int partIndex = PlayingPanel.getComponentZOrder(puzzleParts);
		return new Point(partIndex / row, partIndex % col);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			timer.start();
			music();

			JLabel clickedLabel = (JLabel) e.getSource();
			int clickedIndex = -1;
			for (int i = 0; i < imageLabel.size(); i++) {
				if (clickedLabel == imageLabel.get(i)) {
					clickedIndex = i;
					break;
				}
			}
			// get Null Piece
			int emptyIndex = getEmptyIndex();
			// check whether two pieces is side by side or not
			if (isAdjacent(clickedIndex, emptyIndex)) {
				Collections.swap(imageLabel, clickedIndex, emptyIndex);
				move++;
				showMove.setText(move + "");
				updateSplitPanel();
			}

			// Check For Win
			if (checkForWin(PlayingPanel)) {
				stopMusic();
				timer.stop();
				int lastIndex = originalOrder.size() - 1;
				originalOrder.get(lastIndex).setIcon(lasttemp.getIcon());

				int option = JOptionPane.showConfirmDialog(this,
						"Congratulations! You solved the puzzle!\nDo you want to save?", "Win",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {

					String username = JOptionPane.showInputDialog(this, "Enter your username:");
					if (username != null && !username.trim().isEmpty()) {

						saveDataToDatabase(username);
					} else {
						JOptionPane.showMessageDialog(this, "Invalid username. Puzzle completion data not saved.",
								"Error", JOptionPane.ERROR_MESSAGE);
						StartPage sPage = new StartPage();
						sPage.setVisible(true);
						dispose();
					}
					stopMusic();
				} else {
					StartPage sPage = new StartPage();
					sPage.setVisible(true);
					stopMusic();
					dispose();
				}
			}

		}
	}

	// To get the index of null piece
	private int getEmptyIndex() {
		for (int i = 0; i < imageLabel.size(); i++) {
			if (imageLabel.get(i).getIcon() == null) {
				return i;
			}
		}
		return -1;
	}

	// Update Playing Panel
	private void updateSplitPanel() {
		PlayingPanel.removeAll();
		PlayingPanel.setLayout(new GridLayout(row, col, SPACING, SPACING));

		for (JLabel label : imageLabel) {
			PlayingPanel.add(label);
		}

		PlayingPanel.revalidate();
		PlayingPanel.repaint();

	}

	private boolean isAdjacent(int index1, int index2) {

		if (Math.abs(index1 - index2) == 1) {
			return true;
		}

		if (Math.abs(index1 - index2) == col) {
			return true;
		}

		return false;
	}

	// For Challenge Level Save to database
	public String CL(int ROW) {
		String cl = "";
		if (ROW == 3) {
			cl = "3 x 3";
		}
		if (ROW == 4) {
			cl = "4 x 4";
		}
		if (ROW == 5) {
			cl = "5 x 5";
		}

		return cl;

	}

	// Insert Data to Database
	private void saveDataToDatabase(String username) {

		int t = sec + min * 60 + hr * 3600;
		String sql = "INSERT INTO Score VALUES (?, ?, CURDATE(), ?,?)";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, move);
			ps.setInt(3, t);
			ps.setString(4, CL(row));
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				stopMusic();
				JOptionPane.showMessageDialog(Play.this, "Your Info is saved !");
				Score rPage = new Score();
				rPage.setVisible(true);
				dispose();
			} else {
				stopMusic();
				JOptionPane.showMessageDialog(Play.this, "Failed to saved.");
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(Play.this, "Error connecting to the database.");
		}

	}

	public static void music() {
		try {
			String filepath = "Image\\ding.wav";
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

	public static void BGmusic() {
		try {
			String filepath = "Image\\BackgroundSound.wav";
			AudioInputStream au = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

			try {
				backgroundMusic = AudioSystem.getClip();
				backgroundMusic.open(au);
				backgroundMusic.start();
				backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stopMusic() {
		if (backgroundMusic != null && backgroundMusic.isRunning()) {
			backgroundMusic.stop();
			backgroundMusic.close();
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
