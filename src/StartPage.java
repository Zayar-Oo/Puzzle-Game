import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartPage extends JFrame {

	private JPanel contentPane;
	StartPage frame;
	JLabel lblExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		StartPage frame = new StartPage();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public StartPage() {
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				setBounds(0, 0, screenSize.width, screenSize.height);
				setExtendedState(MAXIMIZED_BOTH);
			}
		});
		setResizable(false);
		setTitle("Zero To Hero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1380, 745);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStart = new JLabel("START");
		lblStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStart.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblStart.setForeground(Color.GRAY);
			}

			public void mouseClicked(MouseEvent e) {

				Choose ch = new Choose();
				ch.setVisible(true);
				dispose();
			}
		});

		lblStart.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblStart.setBounds(648, 276, 124, 44);
		contentPane.add(lblStart);

		lblExit = new JLabel("EXIT");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.GRAY);
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(1);
				;
			}
		});
		lblExit.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblExit.setBounds(662, 458, 124, 44);
		contentPane.add(lblExit);

		JLabel lblScoreBoard = new JLabel("BOARD");
		lblScoreBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblScoreBoard.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblScoreBoard.setForeground(Color.GRAY);
			}

			public void mouseClicked(MouseEvent e) {
				Score score = new Score();
				score.setVisible(true);
				dispose();
			}
		});
		lblScoreBoard.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblScoreBoard.setBounds(637, 365, 156, 44);
		contentPane.add(lblScoreBoard);

		JLabel lblNewLabel = new JLabel("ZERO TO HERO");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 60));
		lblNewLabel.setBounds(519, 36, 470, 76);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1_5 = new JLabel("New label");
		lblNewLabel_1_5.setBounds(-37, -14, 160, 185);
		ImageIcon l6 = new ImageIcon("Image\\dec.png");
		Image i6 = l6.getImage();
		Image s6 = i6.getScaledInstance(lblNewLabel_1_5.getWidth(), lblNewLabel_1_5.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sc6 = new ImageIcon(s6);
		lblNewLabel_1_5.setIcon(sc6);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_5_1 = new JLabel("New label");
		lblNewLabel_1_5_1.setBounds(0, 352, 160, 185);
		ImageIcon l7 = new ImageIcon("Image\\dec.png");
		Image i7 = l7.getImage();
		Image s7 = i7.getScaledInstance(lblNewLabel_1_5_1.getWidth(), lblNewLabel_1_5_1.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc7 = new ImageIcon(s7);
		lblNewLabel_1_5_1.setIcon(new ImageIcon("Image\\dec1.png"));
		contentPane.add(lblNewLabel_1_5_1);

		JLabel lblNewLabel_1_5_2 = new JLabel("New label");
		lblNewLabel_1_5_2.setBounds(159, 582, 160, 185);
		ImageIcon l8 = new ImageIcon("Image\\dec.png");
		Image i8 = l8.getImage();
		Image s8 = i8.getScaledInstance(lblNewLabel_1_5_2.getWidth(), lblNewLabel_1_5_2.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc8 = new ImageIcon(s8);
		lblNewLabel_1_5_2.setIcon(sc8);
		contentPane.add(lblNewLabel_1_5_2);

		JLabel lblNewLabel_1_5_3 = new JLabel("New label");
		lblNewLabel_1_5_3.setBounds(725, 593, 160, 185);
		ImageIcon l9 = new ImageIcon("Image\\dec.png");
		Image i9 = l9.getImage();
		Image s9 = i9.getScaledInstance(lblNewLabel_1_5_3.getWidth(), lblNewLabel_1_5_3.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc9 = new ImageIcon(s9);
		lblNewLabel_1_5_3.setIcon(new ImageIcon("Image\\dec1.png"));
		contentPane.add(lblNewLabel_1_5_3);

		JLabel lblNewLabel_1_5_4 = new JLabel("New label");
		lblNewLabel_1_5_4.setBounds(1155, 482, 160, 185);
		ImageIcon l10 = new ImageIcon("Image\\dec.png");
		Image i10 = l10.getImage();
		Image s10 = i10.getScaledInstance(lblNewLabel_1_5_4.getWidth(), lblNewLabel_1_5_4.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc10 = new ImageIcon(s10);
		lblNewLabel_1_5_4.setIcon(sc10);
		contentPane.add(lblNewLabel_1_5_4);

		JLabel lblNewLabel_1_5_5 = new JLabel("New label");
		lblNewLabel_1_5_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_5_5.setBounds(1045, 157, 160, 185);
		ImageIcon l11 = new ImageIcon("Image\\dec.png");
		Image i11 = l11.getImage();
		Image s11 = i11.getScaledInstance(lblNewLabel_1_5_5.getWidth(), lblNewLabel_1_5_5.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc11 = new ImageIcon(s11);
		lblNewLabel_1_5_5.setIcon(new ImageIcon("Image\\dec1.png"));
		contentPane.add(lblNewLabel_1_5_5);

		JLabel lblNewLabel_1_5_6 = new JLabel("New label");
		lblNewLabel_1_5_6.setBounds(1226, -39, 160, 185);
		ImageIcon l12 = new ImageIcon("Image\\dec.png");
		Image i12 = l12.getImage();
		Image s12 = i12.getScaledInstance(lblNewLabel_1_5_6.getWidth(), lblNewLabel_1_5_6.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc12 = new ImageIcon(s12);
		lblNewLabel_1_5_6.setIcon(sc12);
		contentPane.add(lblNewLabel_1_5_6);

		JLabel lblNewLabel_1_5_7 = new JLabel("New label");
		lblNewLabel_1_5_7.setBounds(662, -68, 160, 185);
		ImageIcon l13 = new ImageIcon("Image\\dec.png");
		Image i13 = l13.getImage();
		Image s13 = i13.getScaledInstance(lblNewLabel_1_5_7.getWidth(), lblNewLabel_1_5_7.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc13 = new ImageIcon(s13);
		lblNewLabel_1_5_7.setIcon(sc13);
		contentPane.add(lblNewLabel_1_5_7);

		JLabel lblNewLabel_1_5_8 = new JLabel("New label");
		lblNewLabel_1_5_8.setBounds(379, 186, 160, 185);
		ImageIcon l14 = new ImageIcon("Image\\dec.png");
		Image i14 = l14.getImage();
		Image s14 = i14.getScaledInstance(lblNewLabel_1_5_8.getWidth(), lblNewLabel_1_5_8.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon sc14 = new ImageIcon(s14);
		lblNewLabel_1_5_8.setIcon(new ImageIcon("Image\\dec1.png"));
		contentPane.add(lblNewLabel_1_5_8);

		JLabel Logo = new JLabel("");
		Logo.setBounds(380, 11, 129, 95);
		ImageIcon Logoicon = new ImageIcon("Image\\logo.png");
		Image logo1 = Logoicon.getImage();
		Image imgScaleLogo = logo1.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIconLogo = new ImageIcon(imgScaleLogo);
		Logo.setIcon(scaledIconLogo);
		contentPane.add(Logo);
	}

}
