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
import javax.swing.SwingConstants;

public class AnalizeRaportForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldData;
	private JTextField textFieldConsultatieID;
	private JCheckBox chckbxParafat;
	
	private JLabel lblOperatieCurenta;
	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	private Mode crtMode=Mode.VIEW;
	Connection con;
	private JTextField textFieldSpecializare;
	private boolean raportValidat = false; 
	private boolean rezultatPozitiv = false; 
	private JTextField textFieldPacientNume;
	private JTextField textFieldUnitate;
	private JTextField textFieldMedic;
	private JButton btnIstoric;
	private Long idPacientCurent; 
	private JTextField textFieldRezultatValoare;
	private JLabel lblRezultatValoare;
	
	private JTextArea textAreaRezultatDescriere;
	private JCheckBox chckbxRezultatPozitiv ;
	private JButton btnButtonSalvare;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalizeRaportForm frame = new AnalizeRaportForm((long)9);
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
	public AnalizeRaportForm(long id_consultatie) {
		setTitle("Analize medicale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 491);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(206, 31, 51, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDdmmyyyy.setBounds(292, 6, 90, 17);
		contentPane.add(lblDdmmyyyy);
		
		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldData.setColumns(10);
		textFieldData.setBounds(293, 29, 89, 20);
		contentPane.add(textFieldData);
		
		JLabel lblUnitate = new JLabel("Unitate");
		lblUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnitate.setBounds(10, 72, 56, 17);
		contentPane.add(lblUnitate);
		
		JLabel lblPacient = new JLabel("Pacient:");
		lblPacient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPacient.setBounds(35, 174, 56, 17);
		contentPane.add(lblPacient);
		
		JLabel lblMedic = new JLabel("Medic:");
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedic.setBounds(10, 101, 56, 17);
		contentPane.add(lblMedic);
		
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
		
		 btnButtonSalvare = new JButton("Salvare");
		btnButtonSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSave();
			}
		});
		btnButtonSalvare.setBounds(320, 413, 89, 23);
		contentPane.add(btnButtonSalvare);
		
		JLabel lblOperatie = new JLabel("Operatie curenta");
		lblOperatie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOperatie.setBounds(10, 7, 245, 14);
		contentPane.add(lblOperatie);
		lblOperatieCurenta = lblOperatie;
		
		JLabel lblSpecializare = new JLabel("Specializare:");
		lblSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecializare.setBounds(10, 129, 77, 17);
		contentPane.add(lblSpecializare);
		
		textFieldSpecializare = new JTextField();
		textFieldSpecializare.setEditable(false);
		textFieldSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSpecializare.setColumns(10);
		textFieldSpecializare.setBounds(97, 133, 158, 20);
		contentPane.add(textFieldSpecializare);
		
		 chckbxParafat = new JCheckBox("Parafat");
		 chckbxParafat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 chckbxParafat.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 			raportValidat=(e.getStateChange() == ItemEvent.SELECTED);
		 	}
		 });
		chckbxParafat.setBounds(171, 360, 97, 23);
		contentPane.add(chckbxParafat);
		
		textFieldPacientNume = new JTextField();
		textFieldPacientNume.setEditable(false);
		textFieldPacientNume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPacientNume.setColumns(10);
		textFieldPacientNume.setBounds(101, 172, 189, 20);
		contentPane.add(textFieldPacientNume);
		
		textFieldUnitate = new JTextField();
		textFieldUnitate.setEditable(false);
		textFieldUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUnitate.setColumns(10);
		textFieldUnitate.setBounds(97, 73, 189, 20);
		contentPane.add(textFieldUnitate);
		
		textFieldMedic = new JTextField();
		textFieldMedic.setEditable(false);
		textFieldMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMedic.setColumns(10);
		textFieldMedic.setBounds(97, 102, 189, 20);
		contentPane.add(textFieldMedic);
		
		btnIstoric = new JButton("Istoric analize...");
		btnIstoric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (idPacientCurent!=null) {
						IstoricAnalizeRaportForm frame = new IstoricAnalizeRaportForm(idPacientCurent);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
					}

				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnIstoric.setBounds(320, 173, 111, 23);
		contentPane.add(btnIstoric);
		
		textFieldRezultatValoare = new JTextField();
		textFieldRezultatValoare.setBounds(172, 281, 111, 20);
		contentPane.add(textFieldRezultatValoare);
		textFieldRezultatValoare.setColumns(10);
		
		lblRezultatValoare = new JLabel("Rezultat valoare:");
		lblRezultatValoare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRezultatValoare.setBounds(58, 284, 102, 17);
		contentPane.add(lblRezultatValoare);
		
		 chckbxRezultatPozitiv = new JCheckBox("Pozitiv");
		 chckbxRezultatPozitiv.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
	 			rezultatPozitiv=(e.getStateChange() == ItemEvent.SELECTED);
		 	}
		 });
		chckbxRezultatPozitiv.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxRezultatPozitiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxRezultatPozitiv.setBounds(172, 308, 111, 23);
		contentPane.add(chckbxRezultatPozitiv);
		
		JLabel lblRezultatPozitivnegativ = new JLabel("Rezultat pozitiv/negativ:");
		lblRezultatPozitivnegativ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRezultatPozitivnegativ.setBounds(10, 314, 150, 17);
		contentPane.add(lblRezultatPozitivnegativ);
		
		 textAreaRezultatDescriere = new JTextArea();
		textAreaRezultatDescriere.setBounds(168, 221, 225, 41);
		contentPane.add(textAreaRezultatDescriere);
		
		JLabel lblRezultatDescriere = new JLabel("Rezultat descriere:");
		lblRezultatDescriere.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRezultatDescriere.setBounds(21, 223, 123, 17);
		contentPane.add(lblRezultatDescriere);

		
	    Connect();

	    
	    cmdLoadSelectdRecord((long)id_consultatie); 

	    
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
	
	
	
	
	
	private void cmdLoadSelectdRecord(Long idConsultatie) {
		 try {
			   	
				if(idConsultatie<0)
					return;
				
				// formate the SQL
				String sqlText = "SELECT * FROM rapoarte_analize_view WHERE id_consultatie="+idConsultatie;
				
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
			 		
			 		idPacientCurent = rset.getLong("id_pacient");
	
			 		
			 		textFieldSpecializare.setText(rset.getString("medic_specializare_denumire"));			 		
			 		
			 		
			 		if(rset.getString("rezultat_valoare")!=null) {
			 			setMode(Mode.UPDATE);
			 			textFieldRezultatValoare.setText(rset.getString("rezultat_valoare"));
			 		} else {
			 			setMode(Mode.NEW);
			 			textFieldRezultatValoare.setText("");
			 		}
			 		
			 		if(rset.getString("rezultat_pozitiv")!=null) {
			 			chckbxRezultatPozitiv.setSelected(rset.getBoolean("rezultat_pozitiv"));
			 			enableFields(!rset.getBoolean("rezultat_pozitiv"));
			 		} else {
			 			chckbxRezultatPozitiv.setSelected(false);
			 		}	
			 		
			 		
			 		if(rset.getString("rezultat_descriere")!=null) {
			 			textAreaRezultatDescriere.setText(rset.getString("rezultat_descriere"));
			 		} else {
			 			textAreaRezultatDescriere.setText("");
			 		}
			 		
			 		
			 		if(rset.getString("parafat")!=null) {
			 			chckbxParafat.setSelected(rset.getBoolean("parafat"));
			 			enableFields(!rset.getBoolean("parafat"));
			 		} else {
			 			chckbxParafat.setSelected(false);
			 		}				   	
				   	
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
				
	    
				String UPDATE = "UPDATE  rapoarte_analize SET rezultat_valoare=?,rezultat_pozitiv=?,rezultat_descriere=?,parafat=? WHERE id_consultatie=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

				
				pstmt.setString(1, textFieldRezultatValoare.getText());
				pstmt.setBoolean(2, rezultatPozitiv);	
				pstmt.setString(3, textAreaRezultatDescriere.getText());	
				pstmt.setBoolean(4, this.raportValidat);	
				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(5, id_consultatie);
				
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
	    
				String INSERT = "INSERT INTO rapoarte_analize (id_consultatie,rezultat_valoare,rezultat_pozitiv,rezultat_descriere,parafat) VALUES (?,?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
				Long id_consultatie = Long.parseLong(textFieldConsultatieID.getText());
				pstmt.setLong(1, id_consultatie);
				pstmt.setString(2, textFieldRezultatValoare.getText());
				pstmt.setBoolean(3, rezultatPozitiv);	
				pstmt.setString(4, textAreaRezultatDescriere.getText());	
				pstmt.setBoolean(5, this.raportValidat);	

				
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
		 textAreaRezultatDescriere.setEditable(b);
		 textFieldRezultatValoare.setEditable(b);
			chckbxRezultatPozitiv.setEnabled(b);
			chckbxParafat.setEnabled(b);
			btnButtonSalvare.setEnabled(b);
	 }
	 
	 private boolean validareDate() {
		 

		 //return !textDataAngajare.getText().isBlank();
		 return true; 
	 }
}

