import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsultatieForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldData;
	private JTextField textFieldConsultatieID;
	private JTextField textFieldOraInceput;
	private JTextField textFieldOraSfarsit;

	private JComboBox<ItemCombo> cmbUnitate;  
	private JComboBox<ItemCombo> cmbMedic;  
	private JComboBox<ItemCombo> cmbPacient;  
	private JComboBox<ItemCombo> cmbServiciuMedical;  
	
	private JButton btnButtonSalvare ;
	private JButton btnRezultate;
	private JButton btnRaportMedical;
	private JButton btnIstoric; 
	
	long loginUserTd=-1;
	int loginUserAccessCode = -1; 
	
	private JLabel lblOperatieCurenta;
	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	private Mode crtMode=Mode.VIEW;
	Connection con;
	private JTable tableServicii;
	private JTextField textFieldSpecializare;
	private JTextField textFieldPretTotal;
	private JTextField textFieldDurataTotala;
	private JTextField textFieldBonFiscal;
	JButton btnBonFiscal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultatieForm frame = new ConsultatieForm(7,4,(long)9);
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
	public ConsultatieForm(long userId,int accessCode,long id_consultatie) {
		
		loginUserAccessCode = accessCode; 
		loginUserTd = userId;
		
		setTitle("Consultatie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 699);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 91, 51, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDdmmyyyy.setBounds(107, 71, 90, 17);
		contentPane.add(lblDdmmyyyy);
		
		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldData.setColumns(10);
		textFieldData.setBounds(107, 89, 89, 20);
		contentPane.add(textFieldData);
		
		JLabel lblUnitate = new JLabel("Unitate");
		lblUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnitate.setBounds(20, 203, 56, 17);
		contentPane.add(lblUnitate);
		
		JComboBox comboBoxUnitate = new JComboBox();
		comboBoxUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxUnitate.setBounds(107, 200, 158, 22);
		contentPane.add(comboBoxUnitate);
		cmbUnitate = comboBoxUnitate;
		
		JLabel lblPacient = new JLabel("Pacient:");
		lblPacient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPacient.setBounds(285, 91, 77, 17);
		contentPane.add(lblPacient);
		
		JLabel lblMedic = new JLabel("Medic:");
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedic.setBounds(20, 232, 56, 17);
		contentPane.add(lblMedic);
		
		JComboBox comboBoxMedic = new JComboBox();
		comboBoxMedic.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cmdChangeMedic();
			       }
			}
		});
		 
		comboBoxMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMedic.setBounds(107, 231, 158, 22);
		contentPane.add(comboBoxMedic);
		cmbMedic = comboBoxMedic;
		
		JLabel lblConsultatie = new JLabel("Consultatie #:");
		lblConsultatie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultatie.setBounds(10, 32, 90, 17);
		contentPane.add(lblConsultatie);
		
		textFieldConsultatieID = new JTextField();
		textFieldConsultatieID.setEditable(false);
		textFieldConsultatieID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldConsultatieID.setColumns(10);
		textFieldConsultatieID.setBounds(97, 30, 90, 20);
		contentPane.add(textFieldConsultatieID);
		
		JLabel lblDeLaOra = new JLabel("De la ora:");
		lblDeLaOra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeLaOra.setBounds(20, 124, 90, 17);
		contentPane.add(lblDeLaOra);
		
		textFieldOraInceput = new JTextField();
		textFieldOraInceput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldOraInceput.setColumns(10);
		textFieldOraInceput.setBounds(107, 122, 90, 20);
		contentPane.add(textFieldOraInceput);
		
		JLabel lblPanaLaOra = new JLabel("Pana la ora:");
		lblPanaLaOra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPanaLaOra.setBounds(20, 154, 90, 17);
		contentPane.add(lblPanaLaOra);
		
		textFieldOraSfarsit = new JTextField();
		textFieldOraSfarsit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldOraSfarsit.setColumns(10);
		textFieldOraSfarsit.setBounds(107, 152, 90, 20);
		contentPane.add(textFieldOraSfarsit);
		
		JButton btnPacientNou = new JButton("+");
		btnPacientNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PacientiForm frame = new PacientiForm();
					frame.SetVisible(true);
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
			}
		});
		btnPacientNou.setBounds(461, 124, 51, 21);
		contentPane.add(btnPacientNou);
		
		 btnButtonSalvare = new JButton("Salvare");
		btnButtonSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSave();
			}
		});
		btnButtonSalvare.setBounds(20, 609, 89, 23);
		contentPane.add(btnButtonSalvare);
		
		JLabel lblOperatie = new JLabel("Operatie curenta");
		lblOperatie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOperatie.setBounds(10, 7, 245, 14);
		contentPane.add(lblOperatie);
		lblOperatieCurenta = lblOperatie;
		
		JComboBox comboBoxPacient = new JComboBox();
		comboBoxPacient.setBounds(354, 90, 158, 22);
		contentPane.add(comboBoxPacient);
		cmbPacient = comboBoxPacient;
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPacientiLoad(); 
			}
		});
		btnRefresh.setBounds(364, 124, 71, 21);
		contentPane.add(btnRefresh);
		
		JLabel lblServiciu = new JLabel("Serviciu:");
		lblServiciu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblServiciu.setBounds(20, 341, 77, 17);
		contentPane.add(lblServiciu);
		
		JComboBox comboBoxServiciu = new JComboBox();
		comboBoxServiciu.setBounds(107, 340, 158, 22);
		contentPane.add(comboBoxServiciu);
		cmbServiciuMedical = comboBoxServiciu;
		
		JButton btnServiciuAdauga = new JButton("+");
		btnServiciuAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdServiciiAdd();
			}
		});
		btnServiciuAdauga.setBounds(285, 340, 51, 21);
		contentPane.add(btnServiciuAdauga);
		
		JButton btnServiciuSterge = new JButton("-");
		btnServiciuSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdServiciiDelete();
			}
		});
		btnServiciuSterge.setBounds(354, 341, 51, 21);
		contentPane.add(btnServiciuSterge);
		
		 btnRezultate = new JButton("Rezultate analize...");
		btnRezultate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
					AnalizeRaportForm frame = new AnalizeRaportForm(id_consultatie);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnRezultate.setBounds(119, 609, 146, 23);
		contentPane.add(btnRezultate);
		
		 btnBonFiscal = new JButton("Bon fiscal");
		btnBonFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdEmitereBonFiscal();
			}
		});
		btnBonFiscal.setBounds(274, 609, 118, 23);
		contentPane.add(btnBonFiscal);
		
		 btnRaportMedical = new JButton("Raport medical...");
		btnRaportMedical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
					ConsultatieRaportForm frame = new ConsultatieRaportForm(id_consultatie);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});

		btnRaportMedical.setBounds(420, 609, 130, 23);
		contentPane.add(btnRaportMedical);
		
		JScrollPane scrollPaneServicii = new JScrollPane();
		scrollPaneServicii.setBounds(20, 369, 385, 116);
		contentPane.add(scrollPaneServicii);
		
		tableServicii = new JTable();
		scrollPaneServicii.setViewportView(tableServicii);
		
		JLabel lblSpecializare = new JLabel("Specializare:");
		lblSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecializare.setBounds(20, 260, 77, 17);
		contentPane.add(lblSpecializare);
		
		textFieldSpecializare = new JTextField();
		textFieldSpecializare.setEditable(false);
		textFieldSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSpecializare.setColumns(10);
		textFieldSpecializare.setBounds(107, 264, 158, 20);
		contentPane.add(textFieldSpecializare);
		
		JLabel lblPretTotal = new JLabel("Pret total:");
		lblPretTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPretTotal.setBounds(228, 498, 90, 17);
		contentPane.add(lblPretTotal);
		
		textFieldPretTotal = new JTextField();
		textFieldPretTotal.setEditable(false);
		textFieldPretTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPretTotal.setColumns(10);
		textFieldPretTotal.setBounds(315, 496, 90, 20);
		contentPane.add(textFieldPretTotal);
		
		JLabel lblDurataTotala = new JLabel("Durata totala:");
		lblDurataTotala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDurataTotala.setBounds(228, 528, 90, 17);
		contentPane.add(lblDurataTotala);
		
		textFieldDurataTotala = new JTextField();
		textFieldDurataTotala.setEditable(false);
		textFieldDurataTotala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldDurataTotala.setColumns(10);
		textFieldDurataTotala.setBounds(315, 526, 90, 20);
		contentPane.add(textFieldDurataTotala);
		
		 btnIstoric = new JButton("Istoric rapoarte medicale...");
		btnIstoric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ItemCombo med = (ItemCombo)cmbPacient.getSelectedItem();	
					IstoricRapoartePacientForm frame = new IstoricRapoartePacientForm(med.getId());
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnIstoric.setBounds(354, 153, 158, 23);
		contentPane.add(btnIstoric);
		
		textFieldBonFiscal = new JTextField();
		textFieldBonFiscal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBonFiscal.setEditable(false);
		textFieldBonFiscal.setColumns(10);
		textFieldBonFiscal.setBounds(315, 556, 90, 20);
		contentPane.add(textFieldBonFiscal);
		
		JLabel lblBonFiscal = new JLabel("Bon fiscal #: ");
		lblBonFiscal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBonFiscal.setBounds(228, 556, 90, 17);
		contentPane.add(lblBonFiscal);

		
	    Connect();
	    comboBoxUnitatiLoad();
	    comboBoxMediciLoad();
	    comboBoxPacientiLoad();
	    
	    cmdLoadSelectdRecord((long)id_consultatie); 
	    if(id_consultatie<0)
	    	setMode(Mode.NEW);
	    else
	    	setMode(Mode.UPDATE);
	    
	    seteazaDrepturi();
	    
	    
	}
	
	private void seteazaDrepturi() {
		
		// 13 -  access restrictionat/ aici inseamna ca e vb de receptioner care are doar sa salveze pacient si sa tipareasca bon 
		if(this.loginUserAccessCode==13) {
			btnRezultate.setEnabled(false);
			btnRaportMedical.setEnabled(false);
			btnIstoric.setEnabled(false);
			btnButtonSalvare.setEnabled(true);
			btnBonFiscal.setEnabled(true);
		}
		
		// 14 -  access restrictionat / aici inseamna ca e vb de asistenta care are doar sa adaauge raport analize medicale 
		if(this.loginUserAccessCode==14) {
			btnButtonSalvare.setEnabled(false);
			btnRaportMedical.setEnabled(false);
			btnIstoric.setEnabled(false);
			btnRezultate.setEnabled(true);
			btnBonFiscal.setEnabled(false);
		}
		
		// 15 -  access restrictionat / aici inseamna ca e vb de medic care are doar sa adaauge raport medical / istoric raport  medicale 
				if(this.loginUserAccessCode==15) {
					btnButtonSalvare.setEnabled(false);
					btnRaportMedical.setEnabled(true);
					btnIstoric.setEnabled(true);
					btnRezultate.setEnabled(false);
					btnBonFiscal.setEnabled(false);
				}
				
	}
	
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
	
	public void comboBoxUnitatiLoad()
	 {
		 
		 try {

				// formate the SQL
				String sqlText = "SELECT * FROM unitati";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_unitate"),rset.getString("denumire"));
		 			cmbUnitate.addItem(fa);
		 		}
		 		if(cmbUnitate!=null)
		 			cmbUnitate.setSelectedIndex(0);//valoare implicita

		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	
	public void comboBoxMediciLoad()
	 {
		 
		 try {
			 



				// formate the SQL
				String sqlText = "SELECT * FROM medici_specializari_view";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_utilizator"),rset.getString("medic_nume")+" " + rset.getString("medic_prenume"));
		 			cmbMedic.addItem(fa);
		 		}
		 		if(cmbMedic!=null)
		 			cmbMedic.setSelectedIndex(0);//valoare implicita

		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	
	public void comboBoxPacientiLoad()
	 {
		 
		 try {

				// formate the SQL
				String sqlText = "SELECT * FROM pacienti";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_pacient"),rset.getString("nume")+" " + rset.getString("prenume"));
		 			cmbPacient.addItem(fa);
		 		}
		 		if(cmbPacient!=null)
		 			cmbPacient.setSelectedIndex(0);//valoare implicita

		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	
 	
 		
 		
	public void comboBoxServiciiLoad(long idSpecializare)
	 {
		 
		 try {

			 
			 	cmbServiciuMedical.removeAllItems();
			 	textFieldSpecializare.setText("");

				// formate the SQL
				String sqlText = "SELECT * FROM servicii_specializari_view where id_specializare=?";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		
				pstmt.setLong(1,idSpecializare);
				
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_cod_serviciu"),rset.getString("serviciu_medical_denumire"));
		 			cmbServiciuMedical.addItem(fa);

		 			if(textFieldSpecializare.getText().isBlank())
		 				textFieldSpecializare.setText(rset.getString("specializare_denumire"));
		 		}
		 		
		 		if(cmbServiciuMedical!=null && cmbServiciuMedical.getItemCount()>0)
		 			cmbServiciuMedical.setSelectedIndex(0);//valoare implicita


		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	
	private void cmdServiciiAdd() {
		
		if(this.crtMode==Mode.NEW) {
			JOptionPane.showMessageDialog(null, "Salvati prima data programarea si apoi adaugati servicii! ", "Atentie !", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		 Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
		 
		 
		 ItemCombo servMed = (ItemCombo)cmbServiciuMedical.getSelectedItem();	
		 long idCodServiciuMedical = servMed.getId(); 
		 
			try {

				String INSERT = "INSERT INTO consultatie_servicii (id_consultatie,id_cod_serviciu) VALUES (?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setLong(1,id_consultatie);
				pstmt.setLong(2,idCodServiciuMedical);
								
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				if( rset.next()) {
					Long id_consult = rset.getLong(1);
					if(id_consult!=null)
						textFieldConsultatieID.setText(id_consult.toString());
					else 
						textFieldConsultatieID.setText("");
				}
				
	 			
				pstmt.close();
				
				//JOptionPane.showMessageDialog(null, "Date inserat cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.UPDATE);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
			
		 
		// re-incarcam serviciile medicale dupa operatiunea de adaugare
		 this.table_servicii_load(id_consultatie);
	}
	
	private void serviciiDelete(Long id_consultatie, Long idCodServiciuMedical) {
		 
			try {

				String INSERT = "DELETE FROM consultatie_servicii  WHERE id_consultatie=? AND id_cod_serviciu=?";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setLong(1,id_consultatie);
				pstmt.setLong(2,idCodServiciuMedical);
								
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				if( rset.next()) {
					Long id_consult = rset.getLong(1);
					if(id_consult!=null)
						textFieldConsultatieID.setText(id_consult.toString());
					else 
						textFieldConsultatieID.setText("");
				}
				
	 			
				pstmt.close();
				
				//JOptionPane.showMessageDialog(null, "Date sterse cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.UPDATE);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot sterge aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
			
		 
		// re-incarcam serviciile medicale dupa operatiunea de adaugare
		 this.table_servicii_load(id_consultatie);
	}
	
	private void cmdServiciiDelete() {
		
		 Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
		 
		 
		int row = this.tableServicii.getSelectedRow();
		String value = tableServicii.getModel().getValueAt(row, 0).toString();
		Long idCodServiciuMedical = Long.parseLong(value);
		
		serviciiDelete(id_consultatie,idCodServiciuMedical);
		 
//			try {
//
//				String INSERT = "DELETE FROM consultatie_servicii  WHERE id_consultatie=? AND id_cod_serviciu=?";
//				
//				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
//
//				pstmt.setLong(1,id_consultatie);
//				pstmt.setLong(2,idCodServiciuMedical);
//								
//				pstmt.executeUpdate();
//				ResultSet rset = pstmt.getGeneratedKeys();
//				if( rset.next()) {
//					Long id_consult = rset.getLong(1);
//					if(id_consult!=null)
//						textFieldConsultatieID.setText(id_consult.toString());
//					else 
//						textFieldConsultatieID.setText("");
//				}
//				
//	 			
//				pstmt.close();
//				
//				JOptionPane.showMessageDialog(null, "Date sterse cu success!", "", JOptionPane.INFORMATION_MESSAGE);
//				
//		 		//refresh 
//				setMode(Mode.UPDATE);
//				
//			} catch (Exception e) {
//				
//				JOptionPane.showMessageDialog(null, "Nu pot sterge aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
//				//e.printStackTrace();
//			} 
			
		 
		// re-incarcam serviciile medicale dupa operatiunea de adaugare
		 this.table_servicii_load(id_consultatie);
	}
	
	
	private void cmdLoadSelectdRecord(Long idConsultatie) {
		 try {
			   	
				if(idConsultatie<0)
					return;
				
				// formate the SQL
				String sqlText = "SELECT * FROM programari_consultatii_view WHERE id_consultatie="+idConsultatie;
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()==true) {
		 			textFieldConsultatieID.setText(rset.getString("id_consultatie"));
		 			
			 		cmbUnitate.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbUnitate.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbUnitate.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_unitatate")) {
				 			cmbUnitate.setSelectedIndex(i);
				 			break;
				 		}
			 		}
			 		
		 			
		 			Date dataProg = rset.getDate("data_programare");
			 		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 		if(dataProg!=null)
			 			textFieldData.setText(dateFormat.format(dataProg));
			 		else 
			 			textFieldData.setText("");
			 		
		 			
		 			cmbMedic.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbMedic.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbMedic.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_medic")) {
				 			cmbMedic.setSelectedIndex(i);
				 			
				 			// alegem specializarile medicului curent
						    comboBoxServiciiLoad((rset.getLong("id_specializare")));
				 			break;
				 		}
			 		}
		 			
		 			
			 		textFieldOraInceput.setText(rset.getString("ora_inceput"));
			 		textFieldOraSfarsit.setText(rset.getString("ora_sfarsit"));
			 		
		 			cmbPacient.setSelectedIndex(0);//valoare implicita
			 		for(int i=0;i<cmbPacient.getItemCount();i++) {
				 		ItemCombo fa = (ItemCombo) cmbPacient.getItemAt(i);
				 		if(fa.getId()==rset.getLong("id_pacient")) {
				 			cmbPacient.setSelectedIndex(i);
				 			break;
				 		}
			 		}
			 		
			 		Long idBon = rset.getLong("id_bon");
			 		if(idBon!=null && idBon>0) {
			 			// a fost deja generat un bon 
			 			textFieldBonFiscal.setText(idBon.toString());
			 			btnBonFiscal.setEnabled(false);//dezactivam butonul de generare a unui bon nou 
			 			btnButtonSalvare.setEnabled(false);
			 			
			 		}
			 		
				   	this.table_servicii_load(idConsultatie);
							
		 		} else {
		 			
		 			textFieldConsultatieID.setText("");
		 			textFieldData.setText("");
		 			textFieldOraInceput.setText("");
		 			textFieldOraSfarsit.setText("");
			 		cmbUnitate.setSelectedIndex(0);
			 		cmbMedic.setSelectedIndex(0);
			 		cmbPacient.setSelectedIndex(0);
		 			textFieldBonFiscal.setText("");
		 			btnBonFiscal.setEnabled(true);
		 			btnButtonSalvare.setEnabled(true);
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
				
	    
				String UPDATE = "UPDATE  programari_consultatii SET id_unitatate=?,data_programare=?,id_medic=?,ora_inceput=?,ora_sfarsit=?,id_pacient=? WHERE id_consultatie=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

				ItemCombo unit = (ItemCombo)cmbUnitate.getSelectedItem();	
				pstmt.setLong(1,unit.getId());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataProgramare = dateFormat.parse(textFieldData.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataProgramare.getTime());
				pstmt.setDate(2, sqlDate);			
				
				ItemCombo med = (ItemCombo)cmbMedic.getSelectedItem();	
				pstmt.setLong(3,med.getId());
				
				pstmt.setString(4,textFieldOraInceput.getText());
				pstmt.setString(5,textFieldOraSfarsit.getText());
				
				ItemCombo pacient = (ItemCombo)cmbPacient.getSelectedItem();	
				pstmt.setLong(6,pacient.getId());
				
				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(7, id_consultatie);
				
				pstmt.executeUpdate();

				ResultSet rset = pstmt.getGeneratedKeys();

				rset.next();


				pstmt.close();
				
		 		
				JOptionPane.showMessageDialog(null, "Date salvate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
				setMode(Mode.UPDATE);
				

				
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
	    
				String INSERT = "INSERT INTO programari_consultatii (id_unitatate,data_programare,id_medic,ora_inceput,ora_sfarsit,id_pacient) VALUES (?,?,?,?,?,?)";
				
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				ItemCombo unit = (ItemCombo)cmbUnitate.getSelectedItem();	
				pstmt.setLong(1,unit.getId());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataProgramare = dateFormat.parse(textFieldData.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataProgramare.getTime());
				pstmt.setDate(2, sqlDate);			
				
				ItemCombo med = (ItemCombo)cmbMedic.getSelectedItem();	
				pstmt.setLong(3,med.getId());
				
				pstmt.setString(4,textFieldOraInceput.getText());
				pstmt.setString(5,textFieldOraSfarsit.getText());
				
				ItemCombo pacient = (ItemCombo)cmbPacient.getSelectedItem();	
				pstmt.setLong(6,pacient.getId());
								
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				if( rset.next()) {
					Long id_consult = rset.getLong(1);
					if(id_consult!=null)
						textFieldConsultatieID.setText(id_consult.toString());
					else 
						textFieldConsultatieID.setText("");
				}
				
	 			
				pstmt.close();
				
				JOptionPane.showMessageDialog(null, "Date inserat cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.UPDATE);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	 private void cmdEmitereBonFiscal() {
		 
			try {
				
				if(!validareDate()) {
					JOptionPane.showMessageDialog(null, "Verificati datele introduse!", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(textFieldPretTotal.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Consultatia nu are pret!", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return; 
				}
				if(textFieldConsultatieID.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Consultatia nu este salvata! Salvati prima data consultatia.", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return; 
				}
				
	    
				String INSERT = "INSERT INTO bonuri_fiscale (id_consultatie,valoare,data) VALUES (?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(1,id_consultatie);
				
				Float pretTotal = Float.parseFloat(textFieldPretTotal.getText());
				pstmt.setFloat(2, pretTotal);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataProgramare = dateFormat.parse(textFieldData.getText());
				java.sql.Date sqlDate = new java.sql.Date(dataProgramare.getTime());
				pstmt.setDate(3, sqlDate);			
								
				pstmt.executeUpdate();
				
				ResultSet rset = pstmt.getGeneratedKeys();
				String bonNouID="n/a";
				
				if( rset.next()) {
					Long id_bon = rset.getLong(1);
					if(id_bon!=null)
						bonNouID = id_bon.toString();
				}
				textFieldBonFiscal.setText(bonNouID);
				btnBonFiscal.setEnabled(false); 
	 			btnButtonSalvare.setEnabled(false);
				
	 			String info = "Bonul # "+bonNouID+" a fost generat/salvat/tiparit cu success!";
				pstmt.close();
				
				JOptionPane.showMessageDialog(null, info, "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.UPDATE);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot genera bonul fiscal! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 		 

	 }
	 
	 public void table_servicii_load(long idConsultatie)
	 {
		 
		 try {
			 	if(idConsultatie<0) {
			 		tableServicii.removeAll();
			 		return;
			 	}
			 	
			 	
				DefaultTableModel dtm = new DefaultTableModel();
				tableServicii.setModel(dtm);
				dtm.addColumn("COD_SEERVICIU");
				dtm.addColumn("SPECIALIZARE");
				dtm.addColumn("SERVICIU MEDICAL");
				dtm.addColumn("DURATA");
				dtm.addColumn("PRET");
		 
				tableServicii.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableServicii.getColumnModel().getColumn(0).setPreferredWidth(30);
				tableServicii.getColumnModel().getColumn(1).setPreferredWidth(120);
				tableServicii.getColumnModel().getColumn(2).setPreferredWidth(120);
				tableServicii.getColumnModel().getColumn(3).setPreferredWidth(60);
				tableServicii.getColumnModel().getColumn(4).setPreferredWidth(60);
	

				// formate the SQL
				String sqlText = "SELECT * FROM consultatie_servicii_view WHERE id_consultatie=?";
				
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);

				pstmt.setLong(1,idConsultatie);

	 			float pretTotal=0; 
	 		 	int durataServicii=0; 
	 		 	
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_cod_serviciu"),rset.getString("specializare_denumire"),rset.getString("serviciu_medical_denumire"), rset.getString("durata_serviciu"), rset.getString("pret_serviciu")};
		 			dtm.insertRow(0, record);

		 			pretTotal+=rset.getFloat("pret_serviciu");
		 			durataServicii+=rset.getFloat("durata_serviciu");

		 		}

	 		 	textFieldPretTotal.setText(Float.toString(pretTotal));
	 		 	textFieldDurataTotala.setText(Long.toString(durataServicii));
	 		 	
		 		pstmt.close();
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 
	 long getMedicSpecializare(long idMedic ) {
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM medici_specializari WHERE id_utilizator="+idMedic;
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()==true) {
		 			return rset.getLong("id_specializare"); 
							
		 		} 
		 		
		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
		 
		 return -1;
	 }
	 
	 // Cand se schimba medicul curent trebuie sa re-incarcam specializarea si serviciile corespunzatoare specializarii lui
	 // In plus daca au fost salvate servicii pentru medicul anterior - acestea vor trebui sterse din lista de servicii vechi
	 void cmdChangeMedic() {
		 
		 	Long id_consultatie = (long)-1;
		 	
		 
		 	if(! textFieldConsultatieID.getText().isBlank()) {
		 		id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
		 	}
		 	
		 
			// s-a ales un alt medic
			ItemCombo med = (ItemCombo)cmbMedic.getSelectedItem();	
			 long idSpecializareMedic = getMedicSpecializare(med.getId() ); 
			 
			 // sterge serviciile salvate pentru medicul anterior 

			while (tableServicii.getRowCount()>0) {
				String value = tableServicii.getModel().getValueAt(0, 0).toString();
				Long idCodServiciuMedical = Long.parseLong(value);
				serviciiDelete(id_consultatie,idCodServiciuMedical);
			}
			
			 
			// incarcam serviciile medicale pentru specializarile noului medicului ales 
			//if(tableServicii.getRowCount()>0)
			  comboBoxServiciiLoad(idSpecializareMedic);
			  
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
	 
	 private void clearFields() {
	 		 		
	 }
	 
	 private void enableFields(boolean b) {
		 
	 }
	 
	 private boolean validareDate() {
		 

		 //return !textDataAngajare.getText().isBlank();
		 return true; 
	 }
}

