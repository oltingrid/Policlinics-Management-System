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
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class IstoricRapoartePacientForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldData;
	private JTextField textFieldConsultatieID;
	private JTextArea textAreaIstoric ;
	private JTextArea textAreaInvestigatii;
	private JTextArea textAreaSimptome;
	private JTextArea textAreaDiagnostic;
	private JTextArea textAreaRecomandari;
	private JCheckBox chckbxParafat;
	
	private JLabel lblOperatieCurenta;
	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	private Mode crtMode=Mode.VIEW;
	Connection con;
	private JTable tableServicii;
	private JTable tableIstoric;
	
	private JTextField textFieldSpecializare;
	private boolean raportValidat = false; 
	private JTextField textFieldPacientNume;
	private JTextField textFieldUnitate;
	private JTextField textFieldMedic;
	JButton btnNewAlege;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IstoricRapoartePacientForm frame = new IstoricRapoartePacientForm((long)6);
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
	public IstoricRapoartePacientForm(long idPacient) {
		setTitle("Istoric medical");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 786);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(206, 161, 51, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDdmmyyyy.setBounds(292, 126, 90, 17);
		contentPane.add(lblDdmmyyyy);
		
		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldData.setColumns(10);
		textFieldData.setBounds(293, 159, 89, 20);
		contentPane.add(textFieldData);
		
		JLabel lblUnitate = new JLabel("Unitate");
		lblUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnitate.setBounds(10, 202, 56, 17);
		contentPane.add(lblUnitate);
		
		JLabel lblPacient = new JLabel("Pacient:");
		lblPacient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPacient.setBounds(410, 161, 77, 17);
		contentPane.add(lblPacient);
		
		JLabel lblMedic = new JLabel("Medic:");
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedic.setBounds(10, 231, 56, 17);
		contentPane.add(lblMedic);
		
		JLabel lblConsultatie = new JLabel("Consultatie #:");
		lblConsultatie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultatie.setBounds(10, 162, 90, 17);
		contentPane.add(lblConsultatie);
		
		textFieldConsultatieID = new JTextField();
		textFieldConsultatieID.setEditable(false);
		textFieldConsultatieID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldConsultatieID.setColumns(10);
		textFieldConsultatieID.setBounds(97, 160, 90, 20);
		contentPane.add(textFieldConsultatieID);
		
		JLabel lblOperatie = new JLabel("Operatie curenta");
		lblOperatie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOperatie.setBounds(10, 127, 245, 14);
		contentPane.add(lblOperatie);
		lblOperatieCurenta = lblOperatie;
		
		JScrollPane scrollPaneServicii = new JScrollPane();
		scrollPaneServicii.setBounds(20, 572, 385, 100);
		contentPane.add(scrollPaneServicii);
		
		tableServicii = new JTable();
		scrollPaneServicii.setViewportView(tableServicii);
		
		JLabel lblSpecializare = new JLabel("Specializare:");
		lblSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecializare.setBounds(10, 259, 77, 17);
		contentPane.add(lblSpecializare);
		
		textFieldSpecializare = new JTextField();
		textFieldSpecializare.setEditable(false);
		textFieldSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSpecializare.setColumns(10);
		textFieldSpecializare.setBounds(97, 263, 158, 20);
		contentPane.add(textFieldSpecializare);
		
		JLabel lblIstoric = new JLabel("Istoric:");
		lblIstoric.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIstoric.setBounds(48, 318, 51, 17);
		contentPane.add(lblIstoric);
		
		textAreaIstoric = new JTextArea();
		textAreaIstoric.setBounds(109, 316, 251, 106);
		contentPane.add(textAreaIstoric);
		
		JLabel lblSimptome = new JLabel("Simptome:");
		lblSimptome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSimptome.setBounds(476, 316, 89, 17);
		contentPane.add(lblSimptome);
		
		 textAreaSimptome = new JTextArea();
		textAreaSimptome.setBounds(575, 314, 251, 106);
		contentPane.add(textAreaSimptome);
		
		JLabel lblInvestigatii = new JLabel("Investigatii:");
		lblInvestigatii.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInvestigatii.setBounds(10, 437, 89, 17);
		contentPane.add(lblInvestigatii);
		
		 textAreaInvestigatii = new JTextArea();
		textAreaInvestigatii.setBounds(109, 435, 251, 106);
		contentPane.add(textAreaInvestigatii);
		
		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiagnostic.setBounds(476, 439, 89, 17);
		contentPane.add(lblDiagnostic);
		
		textAreaDiagnostic = new JTextArea();
		textAreaDiagnostic.setBounds(575, 437, 251, 106);
		contentPane.add(textAreaDiagnostic);
		
		JLabel lblRecomandari = new JLabel("Recomadari:");
		lblRecomandari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRecomandari.setBounds(476, 559, 89, 17);
		contentPane.add(lblRecomandari);
		
		 textAreaRecomandari = new JTextArea();
		textAreaRecomandari.setBounds(575, 557, 251, 106);
		contentPane.add(textAreaRecomandari);
		
		 chckbxParafat = new JCheckBox("Parafat");
		 chckbxParafat.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 			raportValidat=(e.getStateChange() == ItemEvent.SELECTED);
		 	}
		 });
		chckbxParafat.setBounds(292, 687, 97, 23);
		contentPane.add(chckbxParafat);
		
		textFieldPacientNume = new JTextField();
		textFieldPacientNume.setEditable(false);
		textFieldPacientNume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPacientNume.setColumns(10);
		textFieldPacientNume.setBounds(476, 159, 189, 20);
		contentPane.add(textFieldPacientNume);
		
		textFieldUnitate = new JTextField();
		textFieldUnitate.setEditable(false);
		textFieldUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUnitate.setColumns(10);
		textFieldUnitate.setBounds(97, 203, 189, 20);
		contentPane.add(textFieldUnitate);
		
		textFieldMedic = new JTextField();
		textFieldMedic.setEditable(false);
		textFieldMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMedic.setColumns(10);
		textFieldMedic.setBounds(97, 232, 189, 20);
		contentPane.add(textFieldMedic);
		
		 btnNewAlege = new JButton("+");
		btnNewAlege.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAddServiciu();
			}
		});
		btnNewAlege.setBounds(410, 649, 89, 23);
		contentPane.add(btnNewAlege);
		
		JScrollPane scrollPaneIstoricMedical = new JScrollPane();
		scrollPaneIstoricMedical.setBounds(10, 0, 816, 100);
		contentPane.add(scrollPaneIstoricMedical);

		this.tableIstoric = new JTable();
		scrollPaneIstoricMedical.setViewportView(tableIstoric);
		
		JButton btnSelecteaza = new JButton("Selecteaza ");
		btnSelecteaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				cmdLoadSelectdRecord(); 
			}
		});
		btnSelecteaza.setBounds(737, 103, 89, 23);
		contentPane.add(btnSelecteaza);
		
	    Connect();

	    this.table_istoric_load(idPacient);
	    


	    
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
	
	
	
	
	
	private void cmdLoadSelectdRecord() {
		 try {
			 
				int column = 0;
				int row = tableIstoric.getSelectedRow();
				String value = tableIstoric.getModel().getValueAt(row, column).toString();
				Long idConsultatie = Long.parseLong(value);
				
					 
			   	
				if(idConsultatie<0) {
					return;
				}

				// formate the SQL
				String sqlText = "SELECT * FROM rapoarte_medicale_view WHERE id_consultatie="+idConsultatie;
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()==true) {
		 			textFieldConsultatieID.setText(rset.getString("id_consultatie"));
			 		
			 		textFieldUnitate.setText(rset.getString("unitate_denumire"));
			 		
		 			
		 			Date dataProg = rset.getDate("data_programare");
			 		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 		if(dataProg!=null)
			 			textFieldData.setText(dateFormat.format(dataProg));
			 		else 
			 			textFieldData.setText("");
			 		
		 			
			 		textFieldMedic.setText(rset.getString("medic_prenume")+" " + rset.getString("medic_nume"));

			 		textFieldPacientNume.setText(rset.getString("pacient_prenume")+" " + rset.getString("pacient_nume"));
	
			 		
			 		textFieldSpecializare.setText(rset.getString("medic_specializare_denumire"));
			 		
			 		
			 		if(rset.getString("istoric")!=null) {
			 			setMode(Mode.UPDATE);
					   	textAreaIstoric.setText(rset.getString("istoric"));
			 		} else {
			 			setMode(Mode.NEW);
			 			textAreaIstoric.setText("");
			 		}

			 		if(rset.getString("investigatii")!=null) {
			 			textAreaInvestigatii.setText(rset.getString("investigatii"));
			 		} else {
			 			textAreaInvestigatii.setText("");
			 		}
			 		
			 		if(rset.getString("simptome")!=null) {
			 			textAreaSimptome.setText(rset.getString("simptome"));
			 		} else {
			 			textAreaSimptome.setText("");
			 		}
			 		
			 		if(rset.getString("diagnostic")!=null) {
			 			textAreaDiagnostic.setText(rset.getString("diagnostic"));
			 		} else {
			 			textAreaDiagnostic.setText("");
			 		}
			 		
			 		if(rset.getString("recomandari")!=null) {
			 			textAreaRecomandari.setText(rset.getString("recomandari"));
			 		} else {
			 			textAreaRecomandari.setText("");
			 		}
			 		
			 		if(rset.getString("parafat")!=null) {
			 			chckbxParafat.setSelected(rset.getBoolean("parafat"));
			 			enableFields(!rset.getBoolean("parafat"));
			 		} else {
			 			chckbxParafat.setSelected(false);
			 		}				   	
				   	
			 		
				   	this.table_servicii_load(idConsultatie);
		 		} 
		 		
		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	}
	 
	 
	 private void cmdSaveUpdate(){
			try {
				
				if(!validareDate()) {
					JOptionPane.showMessageDialog(null, "Verificati datele introduse!", "Date invalide", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
	    
				String UPDATE = "UPDATE  rapoarte_medicale SET istoric=?,investigatii=?,simptome=?,diagnostic=?,recomandari=?,parafat=? WHERE id_consultatie=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

				

				pstmt.setString(1, textAreaIstoric.getText());
				pstmt.setString(2, textAreaInvestigatii.getText());				
				pstmt.setString(3, textAreaSimptome.getText());	
				pstmt.setString(4, textAreaDiagnostic.getText());	
				pstmt.setString(5, textAreaRecomandari.getText());	
				pstmt.setBoolean(6, raportValidat);	
				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(7, id_consultatie);
				
				pstmt.executeUpdate();

				//ResultSet rset = pstmt.getGeneratedKeys();
				//rset.next();
				pstmt.close();
		 		
				JOptionPane.showMessageDialog(null, "Date salvate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
				setMode(Mode.UPDATE);
				
	 			enableFields(!raportValidat);
				
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
	    
				String INSERT = "INSERT INTO rapoarte_medicale (id_consultatie,istoric,investigatii,simptome,diagnostic,recomandari,parafat) VALUES (?,?,?,?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(1, id_consultatie);
				pstmt.setString(2, textAreaIstoric.getText());
				pstmt.setString(3, textAreaInvestigatii.getText());				
				pstmt.setString(4, textAreaSimptome.getText());	
				pstmt.setString(5, textAreaDiagnostic.getText());	
				pstmt.setString(6, textAreaRecomandari.getText());	
				pstmt.setBoolean(7, raportValidat);	
				pstmt.executeUpdate();
				pstmt.close();
				
	 			enableFields(!raportValidat);
	 			
				JOptionPane.showMessageDialog(null, "Date inserate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
		 		//refresh 
				setMode(Mode.UPDATE);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 private void cmdAddServiciu() {
		 
			int row = this.tableServicii.getSelectedRow();
			String serviciuMedical = tableServicii.getModel().getValueAt(row, 2).toString();
			
			String investigatieCurenta = textAreaInvestigatii.getText();
			investigatieCurenta= investigatieCurenta + "\n<"+serviciuMedical+">\n";
			
			textAreaInvestigatii.setText(investigatieCurenta);
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

	 		 	
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_cod_serviciu"),rset.getString("specializare_denumire"),rset.getString("serviciu_medical_denumire"), rset.getString("durata_serviciu"), rset.getString("pret_serviciu")};
		 			dtm.insertRow(0, record);

		 		}


	 		 	
		 		pstmt.close();
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 
	 public void table_istoric_load(long idPacient)
	 {
		 
		 try {
			 	if(idPacient<0) {
			 		tableIstoric.removeAll();
			 		return;
			 	}
			 	
			 	
				DefaultTableModel dtm = new DefaultTableModel();
				tableIstoric.setModel(dtm);
				dtm.addColumn("COD");
				dtm.addColumn("DATA");
				dtm.addColumn("UNITATEA");
				dtm.addColumn("SPECIALIZARE");
				dtm.addColumn("PACIENT");
				dtm.addColumn("MEDIC");
		 
				tableIstoric.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableIstoric.getColumnModel().getColumn(0).setPreferredWidth(60);
				tableIstoric.getColumnModel().getColumn(1).setPreferredWidth(90);
				tableIstoric.getColumnModel().getColumn(2).setPreferredWidth(150);
				tableIstoric.getColumnModel().getColumn(3).setPreferredWidth(120);
				tableIstoric.getColumnModel().getColumn(4).setPreferredWidth(150);
				tableIstoric.getColumnModel().getColumn(4).setPreferredWidth(150);
	

				// formate the SQL
				String sqlText = "SELECT * FROM rapoarte_medicale_view WHERE id_pacient=?";
				
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);

				pstmt.setLong(1,idPacient);

	 		 	
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_consultatie"),rset.getString("data_programare"),rset.getString("unitate_denumire"),rset.getString("medic_specializare_denumire"),rset.getString("pacient_prenume")+" "+rset.getString("pacient_nume"),rset.getString("medic_prenume")+" "+rset.getString("medic_nume")};
		 			dtm.insertRow(0, record);

		 		}


	 		 	
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
		 
			textAreaIstoric.setEditable(b);
			textAreaInvestigatii.setEditable(b);
			textAreaSimptome.setEditable(b);
			textAreaDiagnostic.setEditable(b);
			textAreaRecomandari.setEditable(b);
			chckbxParafat.setEnabled(b);
			tableServicii.setEnabled(b);
			btnNewAlege.setEnabled(b);
	 }
	 
	 private boolean validareDate() {
		 

		 //return !textDataAngajare.getText().isBlank();
		 return true; 
	 }
}

