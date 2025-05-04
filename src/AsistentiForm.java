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
//import view.users.SQLException;

public class AsistentiForm {

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
					AsistentiForm window = new AsistentiForm(7,4);
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
	public AsistentiForm(long userId,int accessCode) {
		
		loginUserAccessCode = accessCode; 
		loginUserTd = userId;
		
		initialize();
	    Connect();
	    table_load();
	    comboFunctiaLoad();
	    comboTipLoad();
	    comboDepartamentLoad();
	    comboBoxGradAsistentLoad();
	    comboBoxTipAsistentLoad();
	    
	}

	public final  static int getModuleCode() {
		return 400; 
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
	
	private JComboBox<ItemCombo> cmbTipAsistent;  
	private JComboBox<ItemCombo> cmbGradAsistent;  
	
	private JTextField textSalar;
	private JTextField textNormaOre;

	private JLabel lblOperatieCurenta;
	

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
		frmUtilizatori.setTitle("Asistenti");
		//frmUtilizatori.setBounds(100, 100, 922, 711);
		frmUtilizatori.setBounds(100, 100, 922, 922);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Asistenti:");
		lblNewLabel.setBounds(20, 4, 272, 50);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		frmUtilizatori.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 265, 880, 602);
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
		btnSave.setBounds(550, 550, 89, 23);
		panel.add(btnSave);
		
		JButton btnClear = new JButton("Anulare");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAnulare(); 
			}
		});
		btnClear.setBounds(753, 550, 89, 23);
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
		textDataAngajare.setEditable(false);
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
		comboBoxFunctia.setEnabled(false);
		
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
		comboBoxTip.setBounds(159, 392, 195, 22);
		panel.add(comboBoxTip);
		cmbTip = comboBoxTip;
		
		JComboBox<ItemCombo> comboBoxDepartament = new JComboBox<ItemCombo>();
		comboBoxDepartament.setEnabled(false);
		comboBoxDepartament.setBounds(599, 153, 166, 22);
		panel.add(comboBoxDepartament);
		cmbDepartament = comboBoxDepartament;
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Salar:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(494, 48, 50, 17);
		panel.add(lblNewLabel_1_1_1);
		
		textSalar = new JTextField();
		textSalar.setEditable(false);
		textSalar.setColumns(10);
		textSalar.setBounds(597, 48, 100, 20);
		panel.add(textSalar);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Norma ore:");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(494, 79, 80, 17);
		panel.add(lblNewLabel_1_1_1_2);
		
		textNormaOre = new JTextField();
		textNormaOre.setEditable(false);
		textNormaOre.setColumns(10);
		textNormaOre.setBounds(597, 79, 100, 20);
		panel.add(textNormaOre);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_2 = new JLabel("Asistent medical:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_2.setBounds(494, 406, 150, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3 = new JLabel("Tip asistent:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3.setBounds(494, 434, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3);
		
		JComboBox<ItemCombo> comboBoxTipAsistent = new JComboBox<ItemCombo>();
		comboBoxTipAsistent.setBounds(599, 433, 166, 22);
		panel.add(comboBoxTipAsistent);
		cmbTipAsistent = comboBoxTipAsistent;
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3_1 = new JLabel("Grad asistent:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3_1.setBounds(494, 462, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1_1_1_3_1);
		
		JComboBox<ItemCombo> comboBoxGradAsistent = new JComboBox<ItemCombo>();
		comboBoxGradAsistent.setBounds(599, 462, 166, 22);
		panel.add(comboBoxGradAsistent);
		cmbGradAsistent = comboBoxGradAsistent;
		
		JLabel lbLabelOperatiune = new JLabel("Actualizare/Inserare date !");
		lbLabelOperatiune.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbLabelOperatiune.setBounds(491, 20, 250, 17);
		panel.add(lbLabelOperatiune);
		lblOperatieCurenta = lbLabelOperatiune;

		
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
				cmdLoadSelectdRecord(value);
				
				
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
				String sqlText = "SELECT * FROM utilizatori_angajati_asistenti_view";
				
				if(this.loginUserAccessCode==1) {
					//individual access onky 
					Long id = this.loginUserTd;
					sqlText = "SELECT * FROM utilizatori_angajati_asistenti_view WHERE id_utilizator="+id.toString(); 
				} else {
					if(!textSearchText.getText().isEmpty()) {

						sqlText = "SELECT * FROM utilizatori_angajati_asistenti_view "+" WHERE nume LIKE '"+textSearchText.getText()+"%'"+
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
	 

	
	private void cmdLoadSelectdRecord(String selectedID) {
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatori_angajati_asistenti_view WHERE id_utilizator="+selectedID;
				
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
		 			
					
					// date referitoare la asistent  
					// comboBoxTipAsistent
			 		cmbTipAsistent.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbTipAsistent.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbTipAsistent.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_asistent_medical_tip")) {
				 			cmbTipAsistent.setSelectedIndex(i);
				 			break;
				 		}
			 		}
					
					// comboBoxGradAsistent
			 		cmbGradAsistent.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbGradAsistent.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbGradAsistent.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_asistent_medical_grad")) {
				 			cmbGradAsistent.setSelectedIndex(i);
				 			break;
				 		}
			 		}			 		
					
			 		
			 		
				
			 		
			 		// determinam daca utilizatorul selectat este deja adaugat ca asistent: 
			 		Long idUtilizatorAngjat = rset.getLong("id_asistent");
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
				
	    
				String UPDATE = "UPDATE  asistenti_medicali SET id_asistent_medical_tip=?,id_asistent_medical_grad=? WHERE id_utilizator=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

				
				ItemCombo mg = (ItemCombo)cmbTipAsistent.getSelectedItem();	
				pstmt.setLong(1,mg.getId());
				
				
				ItemCombo ts = (ItemCombo)cmbGradAsistent.getSelectedItem();	
				pstmt.setLong(2,ts.getId());

				
				Long id_utilizator = Long.parseLong(textID.getText());
				pstmt.setLong(3, id_utilizator);
				
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
	    				
				String INSERT = "INSERT INTO asistenti_medicali (id_utilizator,id_asistent_medical_tip,id_asistent_medical_grad) VALUES (?,?,?)";
				
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);


				Long id_utilizator = Long.parseLong(textID.getText());
				pstmt.setLong(1, id_utilizator);
				
				ItemCombo mg = (ItemCombo)cmbGradAsistent.getSelectedItem();	
				pstmt.setLong(2,mg.getId());
				
				
				ItemCombo ts = (ItemCombo)cmbTipAsistent.getSelectedItem();	
				pstmt.setLong(3,ts.getId());

				
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
	 
	 private void cmdAnulare() {
		 	setMode(Mode.VIEW);

	 }
	 
	 private void clearFields() {
	 		

	 		
	 		if(cmbGradAsistent!=null && cmbGradAsistent.getItemCount()>0)
	 			cmbGradAsistent.setSelectedIndex(0);
	 		
	 		if(cmbTipAsistent!=null && cmbTipAsistent.getItemCount()>0)
	 			cmbTipAsistent.setSelectedIndex(0);
	 		
	 }
	 
	 private void enableFields(boolean b) {
	 		
	 		if(cmbGradAsistent!=null && cmbGradAsistent.getItemCount()>0)
	 			cmbGradAsistent.setEnabled(b);
	 		
	 		if(cmbTipAsistent!=null && cmbTipAsistent.getItemCount()>0)
	 			cmbTipAsistent.setEnabled(b);
	 		
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
	 
	
	 
	
	 
	
	 
	 public void comboBoxTipAsistentLoad()
	 {
		 try {
				// formate the SQL
				String sqlText = "SELECT * FROM asistent_medical_tip";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_tip"),rset.getString("denumire"));
		 			cmbTipAsistent.addItem(fa);
		 		}
		 		cmbTipAsistent.setSelectedIndex(0);//valoare implicita
		 		pstmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	 }
	 
	 public void comboBoxGradAsistentLoad()
	 {
		 try {
				// formate the SQL
				String sqlText = "SELECT * FROM asistent_medical_grad";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_grad"),rset.getString("denumire"));
		 			cmbGradAsistent.addItem(fa);
		 		}
		 		cmbGradAsistent.setSelectedIndex(0);//valoare implicita
		 		pstmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	 }
	 
	 
	 private boolean validareDate() {
		 

		 //return !textDataAngajare.getText().isBlank();
		 return true; 
	 }
}
