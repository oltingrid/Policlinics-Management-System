import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
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
//import view.users.SQLException;

public class JavaSample_1 {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaSample_1 window = new JavaSample_1();
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
	public JavaSample_1() {
		initialize();
	    Connect();
	    table_load();
	    comboFunctiaLoad();
	    comboTipLoad();
	    comboDepartamentLoad();
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
		frmUtilizatori.setTitle("Utilizatori");
		frmUtilizatori.setBounds(100, 100, 922, 711);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Utilizatori");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		lblNewLabel.setBounds(347, 0, 272, 50);
		frmUtilizatori.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 61, 312, 600);
		frmUtilizatori.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CNP:");
		lblNewLabel_1.setBounds(10, 42, 94, 17);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Nume:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 78, 83, 17);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prenume:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 122, 83, 17);
		panel.add(lblNewLabel_1_2);
		
		textCNP = new JTextField();
		textCNP.setBounds(114, 42, 166, 20);
		panel.add(textCNP);
		textCNP.setColumns(10);
		
		textNume = new JTextField();
		textNume.setColumns(10);
		textNume.setBounds(114, 78, 166, 20);
		panel.add(textNume);
		
		textPrenume = new JTextField();
		textPrenume.setColumns(10);
		textPrenume.setBounds(114, 122, 166, 20);
		panel.add(textPrenume);
		
		JButton btnSave = new JButton("Salvare");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {           
				cmdSave();
			}
		});
		btnSave.setBounds(10, 550, 89, 23);
		panel.add(btnSave);
		
		JButton btnNou = new JButton("Nou");
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdNew();
			}
		});
		btnNou.setBounds(114, 550, 89, 23);
		panel.add(btnNou);
		
		JButton btnClear = new JButton("Anulare");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAnulare(); 
			}
		});
		btnClear.setBounds(213, 550, 89, 23);
		panel.add(btnClear);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Adresa:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(10, 164, 83, 17);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(10, 201, 83, 17);
		panel.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("IBAN:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1.setBounds(10, 271, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Contract:");
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1.setBounds(10, 299, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Data angajare:");
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(10, 334, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1);
		
		textAdresa = new JTextField();
		textAdresa.setColumns(10);
		textAdresa.setBounds(114, 164, 166, 20);
		panel.add(textAdresa);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(114, 201, 166, 20);
		panel.add(textEmail);
		
		textIBAN = new JTextField();
		textIBAN.setColumns(10);
		textIBAN.setBounds(114, 271, 166, 20);
		panel.add(textIBAN);
		
		textContract = new JTextField();
		textContract.setColumns(10);
		textContract.setBounds(114, 299, 166, 20);
		panel.add(textContract);
		
		textDataAngajare = new JTextField();
		textDataAngajare.setColumns(10);
		textDataAngajare.setBounds(114, 334, 166, 20);
		panel.add(textDataAngajare);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Telefon:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_2.setBounds(10, 243, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_2);
		
		textTelefon = new JTextField();
		textTelefon.setColumns(10);
		textTelefon.setBounds(114, 240, 166, 20);
		panel.add(textTelefon);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		textID.setBounds(114, 11, 166, 20);
		panel.add(textID);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 14, 94, 17);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1 = new JLabel("Parola:");
		lblNewLabel_1_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1.setBounds(10, 376, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1);
		
		textParola = new JTextField();
		textParola.setColumns(10);
		textParola.setBounds(114, 376, 166, 20);
		panel.add(textParola);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1 = new JLabel("Functia:");
		lblNewLabel_1_2_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1.setBounds(10, 411, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1);
		
		JComboBox<ItemCombo> comboBoxFunctia = new JComboBox<ItemCombo>();
		
		comboBoxFunctia.setBounds(114, 410, 166, 22);
		panel.add(comboBoxFunctia);
		cmbFunctie = comboBoxFunctia;
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1 = new JLabel("Tip utilizator:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1.setBounds(10, 451, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1_1_1_1 = new JLabel("Departament:");
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1_1_1_1_1_1_1.setBounds(10, 498, 107, 17);
		panel.add(lblNewLabel_1_2_1_1_1_1_1_1_1_1_1);
		
		JComboBox<ItemCombo> comboBoxTip = new JComboBox<ItemCombo>();
		comboBoxTip.setBounds(114, 450, 166, 22);
		panel.add(comboBoxTip);
		cmbTip = comboBoxTip;
		
		JComboBox<ItemCombo> comboBoxDepartament = new JComboBox<ItemCombo>();
		comboBoxDepartament.setBounds(114, 497, 166, 22);
		panel.add(comboBoxDepartament);
		cmbDepartament = comboBoxDepartament;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 157, 517, 282);
		frmUtilizatori.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(332, 61, 517, 96);
		frmUtilizatori.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cauta:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 39, 78, 17);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		textSearchText = new JTextField();
		textSearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

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
		textSearchText.setBounds(76, 39, 293, 20);
		panel_1.add(textSearchText);
		
		JButton btnUpdate = new JButton("Actualizare/Detalii");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setMode(Mode.UPDATE); 
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				loadSelectdRecord(value);
				
				
			}
		});
		btnUpdate.setBounds(696, 450, 150, 23);
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
				String sqlText = "SELECT * FROM utilizatori";
				if(!textSearchText.getText().isEmpty()) {
					sqlText = "SELECT * FROM utilizatori "+" WHERE nume LIKE '"+textSearchText.getText()+"%'"+
					 " OR prenume LIKE '"+textSearchText.getText()+"%'"+
							" OR CNP LIKE '"+textSearchText.getText()+"%'"; 
				}
				
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_utilizator"),rset.getString("CNP"),rset.getString("nume"), rset.getString("prenume")};
		 			dtm.insertRow(0, record);
		 		}

		 		pstmt.close();
		 		
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 

	
	private void loadSelectdRecord(String selectedID) {
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatori WHERE id_utilizator="+selectedID;
				
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
			 		textDataAngajare.setText(dateFormat.format(dataAngajare));
			 		
			 		textParola.setText(rset.getString("parola"));
			 		
			 		
			 		// cautam functia curenta din lista de functii din combo 
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
			 		for(int i=0;i<cmbDepartament.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbDepartament.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_departament")) {
				 			cmbDepartament.setSelectedIndex(i);
				 			break;
				 		}
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
				

	    
				String UPDATE = "UPDATE  utilizatori SET CNP=?,nume=?,prenume=?,adresa=?,telefon=?,email=?,cont_iban=?,numar_contract=?,"
						+ "data_angajare=?,parola=?,id_functie=?,id_tip_utilizator=?,id_departament=?  WHERE id_utilizator=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);


				//pstmt.setString(1, textID.getText());
				
				pstmt.setString(1, textCNP.getText());
				pstmt.setString(2, textNume.getText());
				pstmt.setString(3, textPrenume.getText());
				pstmt.setString(4, textAdresa.getText());
				pstmt.setString(5, textTelefon.getText());

				pstmt.setString(6, textEmail.getText());
				pstmt.setString(7, textIBAN.getText());
				pstmt.setString(8, textContract.getText());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAngajare = dateFormat.parse(textDataAngajare.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataAngajare.getTime());
				pstmt.setDate(9, sqlDate);
				
				pstmt.setString(10, textParola.getText());
				
				ItemCombo fa = (ItemCombo)cmbFunctie.getSelectedItem();	
				pstmt.setLong(11, fa.getId());
				
				ItemCombo ta = (ItemCombo)cmbTip.getSelectedItem();	
				pstmt.setLong(12, ta.getId());
				
				ItemCombo du = (ItemCombo)cmbDepartament.getSelectedItem();	
				pstmt.setLong(13, du.getId());
				
				Long id_utilizator = Long.parseLong(textID.getText());
				pstmt.setLong(14, id_utilizator);
				
				
				pstmt.executeUpdate();

				ResultSet rset = pstmt.getGeneratedKeys();

				rset.next();


				pstmt.close();
				
		 		//refresh 
		 		table_load(); 
		 		
				JOptionPane.showMessageDialog(null, "Utilizator salvat cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
				setMode(Mode.VIEW);
				
				clearFields();

				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot insera aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	 private void cmdSaveNew(){
			try {
				

	    
				String INSERT = "INSERT INTO utilizatori (CNP,nume,prenume,adresa,telefon,email,cont_iban,numar_contract,"
						+ "data_angajare,parola,id_functie) VALUES (?, ?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);


				//pstmt.setString(1, textID.getText());
				
				pstmt.setString(1, textCNP.getText());
				pstmt.setString(2, textNume.getText());
				pstmt.setString(3, textPrenume.getText());
				pstmt.setString(4, textAdresa.getText());
				pstmt.setString(5, textTelefon.getText());

				pstmt.setString(6, textEmail.getText());
				pstmt.setString(7, textIBAN.getText());
				pstmt.setString(8, textContract.getText());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAngajare = dateFormat.parse(textDataAngajare.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataAngajare.getTime());
				pstmt.setDate(9, sqlDate);
				
				pstmt.setString(10, textParola.getText());
				
				ItemCombo fa = (ItemCombo)cmbFunctie.getSelectedItem();
				pstmt.setLong(11, fa.getId());
				
				ItemCombo tu = (ItemCombo)cmbTip.getSelectedItem();
				pstmt.setLong(11, tu.getId());
				
				ItemCombo du = (ItemCombo)cmbDepartament.getSelectedItem();
				pstmt.setLong(11, du.getId());
				
				
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				rset.next();
				pstmt.close();
				
				JOptionPane.showMessageDialog(null, "Utilizator inserat cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.VIEW);
		 		table_load(); 
				clearFields();
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot insera aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
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
	 
	 private void enableFields(boolean b) {
		 
			textID.setEnabled(b);
	 		textCNP.setEnabled(b);
	 		textNume.setEnabled(b);
	 		textPrenume.setEnabled(b);
	 		textAdresa.setEnabled(b);
	 		textTelefon.setEnabled(b);
	 		textEmail.setEnabled(b);
	 		textIBAN.setEnabled(b);
	 		textContract.setEnabled(b);
	 		textDataAngajare.setEnabled(b);
	 		textParola.setEnabled(b);
	 		
	 }
	 
	 private void setMode(Mode mod) {
		 crtMode=mod;
		 
		 switch (crtMode) {
		 case VIEW:
			 clearFields();
			 enableFields(false);
			 break;
		 case NEW:
			 clearFields();
			 enableFields(true);
			 break;
		 case UPDATE:
			 enableFields(true);
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
		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	 }
}
