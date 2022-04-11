package loto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Loteria_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_pravy;
	private JButton btnNoveZrebovanie;
	private JTextField textVypis;
	private JButton btnHistoria;
	private JButton btnVypisZreby;
	private JButton btnLoteriaInfo;
	private JPanel panel_dolny;
	private JButton btnNewButton_2;
	private JButton btnPridajZrebManual;
	private JButton btnVyhodnotenie;
	private JButton btnPridajZrebAuto;
	private JTextField textVysledkyZrebovania;
	private JLabel lblNewLabel;
	int pocitadlo1 = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loteria_GUI frame = new Loteria_GUI();
					frame.setTitle("KENO 10 ZREBOVANIE");
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
	public Loteria_GUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_pravy = new JPanel();
		contentPane.add(panel_pravy, BorderLayout.EAST);
		panel_pravy.setLayout(new BoxLayout(panel_pravy, BoxLayout.Y_AXIS));
		
		btnNoveZrebovanie = new JButton("Nove Zrebovanie");
		btnNoveZrebovanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noveZrebovanie();
			}
		});
		panel_pravy.add(btnNoveZrebovanie);


		
		lblNewLabel = new JLabel();
		panel_pravy.add(lblNewLabel);
		
		btnHistoria = new JButton("New button");
		panel_pravy.add(btnHistoria);
		
		btnVypisZreby = new JButton("Vypis Zreby");
		btnVypisZreby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vypisTikety();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_pravy.add(btnVypisZreby);
		
		btnLoteriaInfo = new JButton("Info Loteria");
		btnLoteriaInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loteriaInfo();
			}
		});
		panel_pravy.add(btnLoteriaInfo);
		
		textVypis = new JTextField();
		textVypis.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textVypis, BorderLayout.CENTER);

		
		panel_dolny = new JPanel();
		contentPane.add(panel_dolny, BorderLayout.SOUTH);
		
		btnPridajZrebAuto = new JButton("Pridaj Zreb auto");
		btnPridajZrebAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novyZreb();
			}
		});
		panel_dolny.add(btnPridajZrebAuto);
		
		btnPridajZrebManual = new JButton("Pridaj Zreb manual");
		panel_dolny.add(btnPridajZrebManual);
		
		btnVyhodnotenie = new JButton("Vyhodnotenie");
		panel_dolny.add(btnVyhodnotenie);
		
		btnNewButton_2 = new JButton("New button");
		panel_dolny.add(btnNewButton_2);
		
		textVysledkyZrebovania = new JTextField();
		textVysledkyZrebovania.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textVysledkyZrebovania.setHorizontalAlignment(SwingConstants.LEADING);
		textVysledkyZrebovania.setText("Vysledky Zrebovania");
		contentPane.add(textVysledkyZrebovania, BorderLayout.NORTH);
		textVysledkyZrebovania.setColumns(10);
		textVysledkyZrebovania.setEditable(false);
	}
	
	public void novyZreb() throws Exception {
		Loteria loteria = new Loteria("Loto", 1000, 30, 10);
		Zreb zreb = loteria.pridajZrebovanie();
		textVypis.setText(zreb.getInfo());
		File file = File.createTempFile("temp", ".txt", new File("C:\\Users\\jjass\\eclipse-workspace\\Loteria_s_GUI"));
        System.out.println(file.getAbsolutePath());
        file.deleteOnExit();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tikety.txt", true))) {
            bw.write((zreb.getInfo()));
            bw.newLine();
            bw.flush();
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Tiket zapisany");
            int pocitadlo = +1;
    }


    catch (Exception e){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Do suboru sa  nepodarilo zapisat");
    }
		
	}
	
	public void loteriaInfo() {
		Loteria loteria = new Loteria("Loto", 1000, 30, 10);
		loteria.showInfo();
		textVypis.setText(loteria.showInfo());
	}
	
	public void vypisTikety() throws Exception {
		int zorad  = JOptionPane.showConfirmDialog(this, "Chces vypisat Tikety?");
		if (zorad == JOptionPane.YES_OPTION) {
		File file = new File ("C:\\Users\\jjass\\eclipse-workspace\\Loteria_s_GUI\\Tikety.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		

		String st;
		while((st = br.readLine()) != null) {
			textVypis.setText(st);
		}
		}
	}
	public void noveZrebovanie() {
		Loteria loteria = new Loteria("Loto", 1000, 30, 10);
		loteria.vyzrebujCisla();
	}
	
}
