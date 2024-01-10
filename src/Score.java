import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Score extends JFrame {

	private JPanel contentPane;
	JLabel back;
	private JTable scoreTable;
	ConnectDatabase db = new ConnectDatabase();
	Connection con;
	Statement st;
	int no = 1;
	private JComboBox<String> filterComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Score frame = new Score();
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
	public Score() {

		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				setBounds(0, 0, screenSize.width, screenSize.height);
				setExtendedState(MAXIMIZED_BOTH);
				Refresh();
			}

		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Verdana", Font.BOLD, 20));
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setBounds(141, 156, 1125, 479);
		contentPane.add(scrollPane);

		scoreTable = new JTable();
		scoreTable.setFont(new Font("Verdana", Font.PLAIN, 18));
		scoreTable.setRowHeight(30);
		scoreTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		scrollPane.setViewportView(scoreTable);
		scoreTable.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, new Color(0, 255, 0), null));
		scoreTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "Name", "Move", "Date", "Duration", "Challenge Level" }));
		scoreTable.setBackground(new Color(192, 192, 192));
		scoreTable.setBounds(1159, 607, -964, -430);

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

		back = new JLabel("");
		back.setBounds(1276, 11, 68, 44);
		ImageIcon b1 = new ImageIcon("Image\\back.png");
		Image bi = b1.getImage();
		Image bs = bi.getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bsc = new ImageIcon(bs);
		back.setIcon(bsc);
		contentPane.add(back);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPage sPage = new StartPage();
				sPage.setVisible(true);
				dispose();

			}
		});

		JLabel lblScoreBoard = new JLabel("Score Board");
		lblScoreBoard.setForeground(new Color(0, 255, 0));
		lblScoreBoard.setFont(new Font("Verdana", Font.BOLD, 32));
		lblScoreBoard.setBounds(617, 101, 220, 44);
		contentPane.add(lblScoreBoard);

		// Order ComboBox
		filterComboBox = new JComboBox<>();
		filterComboBox.setForeground(new Color(0, 64, 0));
		filterComboBox.setFont(new Font("Verdana", Font.BOLD, 13));
		filterComboBox.setBackground(new Color(192, 192, 192));
		filterComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Duration", "Move", "Date", "Challenge" }));
		filterComboBox.setBounds(10, 158, 108, 22);
		contentPane.add(filterComboBox);

		JLabel lblNewLabel_1 = new JLabel("Order By :");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
		lblNewLabel_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_1.setBounds(10, 115, 121, 27);
		contentPane.add(lblNewLabel_1);

		filterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Refresh();
			}
		});

	}

	private void Refresh() {
		DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
		try {
			con = db.getConnection();
			model.setRowCount(0);
			st = con.createStatement();

			String orderByColumn;
			String filterSelection = (String) filterComboBox.getSelectedItem();
			if ("Duration".equals(filterSelection)) {
				orderByColumn = "Time";
			} else if ("Move".equals(filterSelection)) {
				orderByColumn = "Move";
			} else if ("Challenge".equals(filterSelection)) {
				orderByColumn = "Difficulty DESC";
			} else if ("Date".equals(filterSelection)) {
				orderByColumn = "Date";
			} else {
				// Default for none selected
				orderByColumn = "Time";
			}

			ResultSet rs = st.executeQuery("SELECT * FROM score ORDER BY " + orderByColumn + ", Time ASC");
			no = 1;
			while (rs.next()) {
				Object[] row = new Object[scoreTable.getColumnCount()];
				row[0] = no++;
				row[1] = rs.getString(1);
				row[2] = rs.getInt(2);
				row[3] = rs.getDate(3);
				row[4] = String.format("%02d:%02d:%02d", rs.getInt(4) / 3600, rs.getInt(4) / 60, rs.getInt(4) % 60);
				row[5] = rs.getString(5);
				model.addRow(row);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
