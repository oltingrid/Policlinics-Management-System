import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinanciarMenuForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinanciarMenuForm frame = new FinanciarMenuForm();
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
	public FinanciarMenuForm() {
		setTitle("Financiar Meniu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalarii = new JButton("SALARII");
		btnSalarii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FinanciarRaportSalariiForm frame = new FinanciarRaportSalariiForm(-1,2);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnSalarii.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalarii.setBounds(10, 11, 358, 23);
		contentPane.add(btnSalarii);
		
		JButton btnProfitMedici = new JButton("PROFIT MEDICI");
		btnProfitMedici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FinanciarRaportProfitMediciForm frame = new FinanciarRaportProfitMediciForm(8,5);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnProfitMedici.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfitMedici.setBounds(10, 56, 358, 23);
		contentPane.add(btnProfitMedici);
		
		JButton btnProfitPoliclinici = new JButton("PROFIT POLICLINICI");
		btnProfitPoliclinici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FinanciarRaportProfitForm frame = new FinanciarRaportProfitForm();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnProfitPoliclinici.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfitPoliclinici.setBounds(10, 104, 358, 23);
		contentPane.add(btnProfitPoliclinici);
	}
}
