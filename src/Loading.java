import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Loading extends JFrame {

    private JPanel contentPane;
    static JProgressBar LoadingBar = new JProgressBar();

    public static void main(String[] args) {
        Loading frame = new Loading();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        try {
            for (int i = 0; i <= 117; i++) {
                Thread.sleep(44);
                LoadingBar.setValue(i);
                if (i == 117) {
                    new StartPage().setVisible(true);
                    frame.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Loading() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                music();
            }
        });

        setType(Type.UTILITY);
        setUndecorated(true);
        setFont(new Font("Malgun Gothic",Font.BOLD, 15));
        setBackground(new Color(0, 0, 0));
        setForeground(new Color(0, 0, 0));
        setTitle("ZeroToHero");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 795, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        LoadingBar = new JProgressBar();
        LoadingBar.setBounds(0, 441, 795, 20);
        LoadingBar.setForeground(new Color(128, 128, 128));
        LoadingBar.setBackground(new Color(29, 29, 29));
        contentPane.add(LoadingBar);

        JLabel LoadingPic = new JLabel("");
        LoadingPic.setBounds(0, 0, 795, 449);
        LoadingPic.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
        LoadingPic.setIcon(new ImageIcon("Image/ZeroToHero.gif"));
        contentPane.add(LoadingPic);
    }

    public static void music() {
        try {
            String filepath = "Image\\LoadingAudio.wav";
            AudioInputStream au = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(au);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}