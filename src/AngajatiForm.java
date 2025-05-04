import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import java.text.*;


import javax.swing.JOptionPane; 


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
//import view.users.SQLException;

public class AngajatiForm {

	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	
	
	
	private Mode crtMode=Mode.VIEW;
	
	private JFrame frmUtilizatori;
	private JTextField textCNP;
	private JTextField textNume;
	private JTextField textPrenume;
	private JTable table;
	
	long loginUserTd=-1;
	int loginUserAccessCode = -1; 

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AngajatiForm window = new AngajatiForm(6,2);
					window.frmUtilizatori.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AngajatiForm(long userId,int accessCode) {
		
		
		loginUserAccessCode = accessCode; 
		loginUserTd = userId;
		
		initialize();
	    Connect();
	    table_load();
	    comboFunctiaLoad();
	    comboTipLoad();
	    comboDepartamentLoad();
	}
	
	public final  static int getModuleCode() {
		return 200; 
	}

	public void SetVisible(boolean visiable) {
		frmUtilizatori.setVisible(visiable);
		frmUtilizatori.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textAdresa;
	private JTextField textEmail;
	private JTextField textIBAN;
	private JTextField textContract;
	private JTextField textDataAngajare;
	private JTextField textSearchText;
	private JTextField textTelefon;
	private JTextField textID;
	private JTextField textParola;

	private JComboBox<ItemCombo> cmbFunctie;  
	private JComboBox<ItemCombo> cmbTip;  
	private JComboBox<ItemCombo> cmbDepartament;  
	private JTextField textSalar;
	private JTextField textNormaOre;

	private JLabel lblOperatieCurenta;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldOrarDela;
	private JTextField textFieldOrarPanala;
	

	 public void Connect()
	    {
	        try {
	            //Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/policlinici",
	                    "root",
	                    "#Motroscuta19");
	        }
	      
	        catch (SQLException ex) 
	        {
	               ex.printStackTrace();
	        }

	    }

	 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUtilizatori = new JFrame();
		frmUtilizatori.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
		});
		frmUtilizatori.setTitle("Angajati");
		//frmUtilizatori.setBounds(100, 100, 922, 711);
		// frmUtilizatori.setBounds(100, 100, 922, 922);
		frmUtilizatori.setBounds(100, 100, 1100, 922);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Angajati");
		lblNewLabel.setBounds(20, 4, 272, 50);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		frmUtilizatori.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 265, 1000, 600);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmUtilizatori.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CNP:");
		lblNewLabel_1.setBounds(54, 61, 94, 17);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Nume:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(54, 97, 83, 17);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prenume:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(54, 141, 83, 17);
		panel.add(lblNewLabel_1_2);
		
		textCNP = new JTextField();
		textCNP.setEditable(false);
		textCNP.setBounds(158, 61, 166, 20);
		panel.add(textCNP);
		textCNP.setColumns(10);
		
		textNume = new JTextField();
		textNume.setEditable(false);
		textNume.setColumns(10);
		textNume.setBounds(158, 97, 166, 20);
		panel.add(textNume);
		
		textPrenume = new JTextField();
		textPrenume.setEditable(false);
		textPrenume.setColumns(10);
		textPrenume.setBounds(158, 141, 166, 20);
		panel.add(textPrenume);
		
		JButton btnSave = new JButton("Salvare");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {           
				cmdSave();
			}
		});
		btnSave.setBounds(501, 220, 89, 23);
		panel.add(btnSave);
		
		JButton btnNou = new JButton("Nou");
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdNew();
			}
		});
		btnNou.setBounds(605, 220, 89, 23);
		panel.add(btnNou);
		
		JButton btnClear = new JButton("Anulare");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAnulare(); 
			}
		});
		btnClear.setBounds(704, 220, 89, 23);
		panel.add(btnClear);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Adresa:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(54, 183, 83, 17);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(54, 220, 83, 17);
		panel.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("IBAN:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1.setBounds(54, 290, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Contract:");
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1.setBounds(54, 318, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Data angajare:");
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(491, 190, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1);
		
		textAdresa = new JTextField();
		textAdresa.setEditable(false);
		textAdresa.setColumns(10);
		textAdresa.setBounds(158, 183, 166, 20);
		panel.add(textAdresa);
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setBounds(158, 220, 166, 20);
		panel.add(textEmail);
		
		textIBAN = new JTextField();
		textIBAN.setEditable(false);
		textIBAN.setColumns(10);
		textIBAN.setBounds(158, 290, 166, 20);
		panel.add(textIBAN);
		
		textContract = new JTextField();
		textContract.setEditable(false);
		textContract.setColumns(10);
		textContract.setBounds(158, 318, 166, 20);
		panel.add(textContract);
		
		textDataAngajare = new JTextField();
		textDataAngajare.setColumns(10);
		textDataAngajare.setBounds(599, 190, 166, 20);
		panel.add(textDataAngajare);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Telefon:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_2.setBounds(54, 262, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_2);
		
		textTelefon = new JTextField();
		textTelefon.setEditable(false);
		textTelefon.setColumns(10);
		textTelefon.setBounds(158, 259, 166, 20);
		panel.add(textTelefon);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		textID.setBounds(158, 30, 166, 20);
		panel.add(textID);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(54, 33, 94, 17);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1 = new JLabel("Parola:");
		lblNewLabel_1_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1.setBounds(54, 349, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1);
		
		textParola = new JTextField();
		textParola.setEditable(false);
		textParola.setColumns(10);
		textParola.setBounds(158, 349, 166, 20);
		panel.add(textParola);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1 = new JLabel("Functia:");
		lblNewLabel_1_2_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1.setBounds(494, 108, 80, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1);
		
		JComboBox<ItemCombo> comboBoxFunctia = new JComboBox<ItemCombo>();
		
		comboBoxFunctia.setBounds(599, 107, 166, 22);
		panel.add(comboBoxFunctia);
		cmbFunctie = comboBoxFunctia;
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1 = new JLabel("Tip utilizator:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1.setBounds(54, 393, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1 = new JLabel("Departament:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1.setBounds(494, 154, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1);
		
		JComboBox<ItemCombo> comboBoxTip = new JComboBox<ItemCombo>();
		comboBoxTip.setEnabled(false);
		comboBoxTip.setBounds(159, 392, 160, 22);
		panel.add(comboBoxTip);
		cmbTip = comboBoxTip;
		
		JComboBox<ItemCombo> comboBoxDepartament = new JComboBox<ItemCombo>();
		comboBoxDepartament.setBounds(599, 153, 166, 22);
		panel.add(comboBoxDepartament);
		cmbDepartament = comboBoxDepartament;
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Salar:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(494, 48, 50, 17);
		panel.add(lblNewLabel_1_1_1);
		
		textSalar = new JTextField();
		textSalar.setColumns(10);
		textSalar.setBounds(597, 48, 100, 20);
		panel.add(textSalar);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Norma ore / luna:");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(443, 79, 131, 17);
		panel.add(lblNewLabel_1_1_1_2);
		
		textNormaOre = new JTextField();
		textNormaOre.setColumns(10);
		textNormaOre.setBounds(597, 79, 100, 20);
		panel.add(textNormaOre);
		
		JLabel lbLabelOperatiune = new JLabel("Actualizare/Inserare date !");
		lbLabelOperatiune.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbLabelOperatiune.setBounds(491, 20, 250, 17);
		panel.add(lbLabelOperatiune);
		lblOperatieCurenta = lbLabelOperatiune;
		
		JScrollPane scrollPanelConcedii = new JScrollPane();
		scrollPanelConcedii.setBounds(504, 345, 200, 100);
		panel.add(scrollPanelConcedii);
		
		JLabel lblConcedii = new JLabel("Concedii :");
		lblConcedii.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConcedii.setBounds(499, 318, 200, 17);
		panel.add(lblConcedii);
		
		JButton btnStergereConcediu = new JButton("Stergere");
		btnStergereConcediu.setBounds(612, 550, 89, 23);
		panel.add(btnStergereConcediu);
		
		JButton btnAdaugareConcediu = new JButton("Adaugare");
		btnAdaugareConcediu.setBounds(501, 550, 89, 23);
		panel.add(btnAdaugareConcediu);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(575, 488, 120, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(577, 519, 120, 20);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1 = new JLabel("De la data:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1.setBounds(483, 491, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2 = new JLabel("Pana la data:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2.setBounds(483, 522, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2);
		
		JScrollPane scrollPanelOrar = new JScrollPane();
		scrollPanelOrar.setBounds(757, 345, 200, 100);
		panel.add(scrollPanelOrar);
		
		JLabel lblOrar = new JLabel("Orar lucru:");
		lblOrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrar.setBounds(769, 321, 200, 17);
		panel.add(lblOrar);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1 = new JLabel("De la ora:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1.setBounds(757, 491, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2_1 = new JLabel("Pana ora:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2_1.setBounds(757, 522, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_2_1);
		
		textFieldOrarDela = new JTextField();
		textFieldOrarDela.setColumns(10);
		textFieldOrarDela.setBounds(849, 488, 120, 20);
		panel.add(textFieldOrarDela);
		
		textFieldOrarPanala = new JTextField();
		textFieldOrarPanala.setColumns(10);
		textFieldOrarPanala.setBounds(851, 519, 120, 20);
		panel.add(textFieldOrarPanala);
		
		JButton btnAdaugareConcediu_1 = new JButton("Adaugare");
		btnAdaugareConcediu_1.setBounds(771, 550, 89, 23);
		panel.add(btnAdaugareConcediu_1);
		
		JButton btnStergereConcediu_1 = new JButton("Stergere");
		btnStergereConcediu_1.setBounds(882, 550, 89, 23);
		panel.add(btnStergereConcediu_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_3 = new JLabel("Zi:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_3.setBounds(757, 456, 60, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_3);
		
		JComboBox<ItemCombo> comboBoxOrarZile = new JComboBox<ItemCombo>();
		comboBoxOrarZile.setModel(new DefaultComboBoxModel(new String[] {"Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata"}));
		comboBoxOrarZile.setSelectedIndex(0);
		comboBoxOrarZile.setBounds(849, 456, 120, 22);
		panel.add(comboBoxOrarZile);
		
		JComboBox<ItemCombo> comboBoxDepartament_1 = new JComboBox<ItemCombo>();
		//comboBoxDepartament_1.setSelectedIndex(0);
		comboBoxDepartament_1.setBounds(757, 289, 200, 22);
		panel.add(comboBoxDepartament_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_4 = new JLabel("Locatie:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_4.setBounds(757, 262, 60, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_4);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 61, 876, 143);
		frmUtilizatori.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(524, 11, 372, 43);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmUtilizatori.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cauta:");
		lblNewLabel_1_1_1_1.setBounds(10, 11, 78, 17);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_1_1);
		
		textSearchText = new JTextField();
		textSearchText.setBounds(71, 11, 293, 20);
		textSearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				//if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				//	table_load();
				//}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				//table_load();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				table_load();
			}
		});
		textSearchText.setColumns(10);
		panel_1.add(textSearchText);
		
		JButton btnUpdate = new JButton("Actualizare/Detalii");
		btnUpdate.setBounds(746, 215, 150, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setMode(Mode.UPDATE); 
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				cmdloadSelectdRecord(value);
				
				
			}
		});
		frmUtilizatori.getContentPane().add(btnUpdate);
		
		setMode(Mode.VIEW); 
	}
	
	 public void table_load()
	 {
		 
		 try {
				DefaultTableModel dtm = new DefaultTableModel();
				table.setModel(dtm);
				dtm.addColumn("ID");
				dtm.addColumn("CNP");
				dtm.addColumn("NUME");
				dtm.addColumn("PRENUME");
		 
		 		
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatoriangajatiview";
				
				
				if(this.loginUserAccessCode==1) {
					//individual access onky 
					Long id = this.loginUserTd;
					sqlText = "SELECT * FROM utilizatoriangajatiview WHERE id_utilizator="+id.toString(); 
				} else {

					if(!textSearchText.getText().isEmpty()) {
	
						sqlText = "SELECT * FROM utilizatoriangajatiview "+" WHERE nume LIKE '"+textSearchText.getText()+"%'"+
						 " OR prenume LIKE '"+textSearchText.getText()+"%'"+
								" OR CNP LIKE '"+textSearchText.getText()+"%'"; 
					}
				}
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_utilizator"),rset.getString("CNP"),rset.getString("nume"), rset.getString("prenume")};
		 			dtm.insertRow(0, record);

		 		}

		 		pstmt.close();
		 		//con.close();
		 		
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 

	
	private void cmdloadSelectdRecord(String selectedID) {
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatoriangajatiview WHERE id_utilizator="+selectedID;
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()==true) {
		 			textID.setText(rset.getString("id_utilizator"));
			 		textCNP.setText(rset.getString("CNP"));
			 		textNume.setText(rset.getString("nume"));
			 		textPrenume.setText(rset.getString("prenume"));
			 		textAdresa.setText(rset.getString("adresa"));
			 		textTelefon.setText(rset.getString("telefon"));
			 		textEmail.setText(rset.getString("email"));
			 		textIBAN.setText(rset.getString("cont_iban"));
			 		textContract.setText(rset.getString("numar_contract"));
			 		
			 		
			 		Date dataAngajare = rset.getDate("data_angajare");
			 		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 		if(dataAngajare!=null)
			 			textDataAngajare.setText(dateFormat.format(dataAngajare));
			 		else 
			 			textDataAngajare.setText("");
			 		
			 		textParola.setText(rset.getString("parola"));
			 		
			 		// cautam functia curenta din lista de functii din combo 
			 		cmbFunctie.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbFunctie.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbFunctie.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_functie")) {
				 			cmbFunctie.setSelectedIndex(i);
				 			break;
				 		}
			 		}
			 		
			 		// cautam tip curent din lista  din combo 
			 		for(int i=0;i<cmbTip.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbTip.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_tip_utilizator")) {
				 			cmbTip.setSelectedIndex(i);
				 			break;
				 		}
			 		}
			 		
			 		// cautam departament curent din lista  din combo
			 		cmbDepartament.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbDepartament.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbDepartament.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_departament")) {
				 			cmbDepartament.setSelectedIndex(i);
				 			break;
				 		}
			 		}
			 		
					
					textSalar.setText(rset.getString("salar"));
					textNormaOre.setText(rset.getString("norma_lunara_ore"));
		 			
					
			 		
			 		// determinam daca utilizatorul selectat este deja adaugat ca angajat: 
			 		Long idUtilizatorAngjat = rset.getLong("id_angajat");
			 		if(idUtilizatorAngjat==0) {
				 		setMode(Mode.NEW);
			 		} else {
				 		setMode(Mode.UPDATE);
			 		}

			 		
			 		
							
		 		} else {
		 			
		 			textID.setText("");
			 		textCNP.setText("");
			 		textNume.setText("");
			 		textPrenume.setText("");
			 		textAdresa.setText("");
			 		textTelefon.setText("");
			 		textEmail.setText("");
			 		textIBAN.setText("");
			 		textContract.setText("");
			 		textDataAngajare.setText("");
			 		textParola.setText("");
			 		//textFunctie.setText(rset.getLong("id_functie"));
			 		//textCNP.setText(rset.getLong("id_tip_utilizator"));
			 		//textCNP.setText(rset.getLong("id_departament"));
		 			
		 		}
		 		

		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	}
	private void cmdSave() {
		if(crtMode==Mode.UPDATE) {
			cmdSaveUpdate();
		} else if(crtMode==Mode.NEW) {
			cmdSaveNew();
		} else {
			JOptionPane.showMessageDialog(null, "Nimic de salvat!", "", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	 
	 
	 private void cmdSaveUpdate(){
			try {
				
				if(!validareDate()) {
					JOptionPane.showMessageDialog(null, "Verificati datele introduse!", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
	    
				String UPDATE = "UPDATE  angajati SET salar=?,norma_lunara_ore=?,id_functie=?,id_departament=?,data_angajare=? WHERE id_utilizator=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

				
				Long salar = Long.parseLong(textSalar.getText());
				pstmt.setLong(1, salar);
				
				Long ore = Long.parseLong(textNormaOre.getText());
				pstmt.setLong(2, ore);
				
				ItemCombo fa = (ItemCombo)cmbFunctie.getSelectedItem();	
				pstmt.setLong(3, fa.getId());
				
				ItemCombo du = (ItemCombo)cmbDepartament.getSelectedItem();	
				pstmt.setLong(4, du.getId());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAngajare = dateFormat.parse(textDataAngajare.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataAngajare.getTime());
				pstmt.setDate(5, sqlDate);			
				
				Long id_utilizator = Long.parseLong(textID.getText());
				pstmt.setLong(6, id_utilizator);
				
				pstmt.executeUpdate();

				ResultSet rset = pstmt.getGeneratedKeys();

				rset.next();


				pstmt.close();
				
		 		//refresh 
		 		table_load(); 
		 		
				JOptionPane.showMessageDialog(null, "Date salvate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
				setMode(Mode.VIEW);
				
				clearFields();

				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	 private void cmdSaveNew(){
			try {
				if(!validareDate()) {
					JOptionPane.showMessageDialog(null, "Verificati datele introduse!", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
	    
				String INSERT = "INSERT INTO angajati (id_utilizator,salar,norma_lunara_ore,id_functie,id_departament,data_angajare) VALUES (?,?,?,?,?,?)";
				
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);


				Long id_utilizator = Long.parseLong(textID.getText());
				pstmt.setLong(1, id_utilizator);
				
				Long salar = Long.parseLong(textSalar.getText());
				pstmt.setLong(2, salar);
				
				Long ore = Long.parseLong(textNormaOre.getText());
				pstmt.setLong(3, ore);
				
				ItemCombo fa = (ItemCombo)cmbFunctie.getSelectedItem();	
				pstmt.setLong(4, fa.getId());
				
				ItemCombo du = (ItemCombo)cmbDepartament.getSelectedItem();	
				pstmt.setLong(5, du.getId());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAngajare = dateFormat.parse(textDataAngajare.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataAngajare.getTime());
				pstmt.setDate(6, sqlDate);			
		
				
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				rset.next();
				pstmt.close();
				
				JOptionPane.showMessageDialog(null, "Date inserat cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.VIEW);
		 		table_load(); 
				clearFields();
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	 
	 private void cmdNew() {
		 
		 	setMode(Mode.NEW);

	 		
	 }
	 
	 private void cmdAnulare() {
		 	setMode(Mode.VIEW);

	 }
	 
	 private void clearFields() {
	 		
	 		textSalar.setText("");
	 		textNormaOre.setText("");
	 		
	 		if(cmbFunctie!=null && cmbFunctie.getItemCount()>0)
	 			cmbFunctie.setSelectedIndex(0);
	 		
	 		if(cmbDepartament!=null && cmbDepartament.getItemCount()>0)
	 			cmbDepartament.setSelectedIndex(0);
	 		textDataAngajare.setText("");
	 		
	 }
	 
	 private void enableFields(boolean b) {
		 

	 		
	 		textSalar.setEnabled(b);
	 		textNormaOre.setEnabled(b);
	 		
	 		if(cmbFunctie!=null && cmbFunctie.getItemCount()>0)
	 			cmbFunctie.setEnabled(b);
	 		
	 		if(cmbDepartament!=null && cmbDepartament.getItemCount()>0)
	 			cmbDepartament.setEnabled(b);
	 		
	 		textDataAngajare.setEnabled(b);
	 		
	 		
	 }
	 
	
	 
	 
	 private void setMode(Mode mod) {
		 crtMode=mod;
		 
		 switch (crtMode) {
		 case VIEW:
			 clearFields();
			 enableFields(false);
			 lblOperatieCurenta.setText("VIZUALIZARE DATE !");
			 break;
		 case NEW:
			 clearFields();
			 enableFields(true);
			 lblOperatieCurenta.setText("ADUGARE DATE NOI!");
			 break;
		 case UPDATE:
			 enableFields(true);
			 lblOperatieCurenta.setText("ACTUALIZARE DATE EXISTENTE!");
			 break;
		 }
	 }
	 
	 
	 public void comboFunctiaLoad()
	 {
		 
		 try {

				// formate the SQL
				String sqlText = "SELECT * FROM functii_angajati";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_functie"),rset.getString("denumire"));
					cmbFunctie.addItem(fa);
		 		}
		 		cmbFunctie.setSelectedIndex(0);//valoare implicita
		 		

		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 
	 public void comboTipLoad()
	 {
		 
		 try {

				// formate the SQL
				String sqlText = "SELECT * FROM tip_utilizatori";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_tip_utilizator"),rset.getString("denumire"));
					cmbTip.addItem(fa);
		 		}
		 		cmbTip.setSelectedIndex(0);//valoare implicita
		 		
		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 
	 public void comboDepartamentLoad()
	 {
		 
		 try {

				// formate the SQL
				String sqlText = "SELECT * FROM departamente";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_departament"),rset.getString("denumire"));
					cmbDepartament.addItem(fa);
		 		}
		 		cmbDepartament.setSelectedIndex(0);//valoare implicita
		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 
	 private boolean validareDate() {
		 
		 String ss = textDataAngajare.getText();
		 return !textDataAngajare.getText().isBlank();
	 }
}
