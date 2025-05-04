import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgramariForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldData;
	
	private JComboBox<ItemCombo> cmbUnitate;  
	private JComboBox<ItemCombo> cmbMedic;  
	private JComboBox<ItemCombo> cmbSpecializare;  
	private JButton btnSelecteaza ;
	private JButton btnNou;
	
	long loginUserTd=-1;
	int loginUserAccessCode = -1; 
	
	private JTable table;
	Connection con;
	
	public final  static int getModuleCode() {
		return 500; 
	}

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramariForm frame = new ProgramariForm(9,1);
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
	public ProgramariForm(long userId,int accessCode) {
		
		loginUserAccessCode = accessCode; 
		loginUserTd = userId;
		
		
		setTitle("Programari");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1095, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data:");
		lblNewLabel.setBounds(0, 51, 51, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		textFieldData = new JTextField();
		textFieldData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		textFieldData.setBounds(61, 49, 89, 20);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(textFieldData);
		textFieldData.setColumns(10);
		
		JLabel lblMedic = new JLabel("Medic:");
		lblMedic.setBounds(737, 49, 56, 17);
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblMedic);
		
		JComboBox comboBoxMedic = new JComboBox();
		comboBoxMedic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		comboBoxMedic.setBounds(803, 46, 158, 22);
		comboBoxMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(comboBoxMedic);
		
		JScrollPane scrollPaneProgramari = new JScrollPane();
		scrollPaneProgramari.setBounds(0, 256, 1066, 200);
		contentPane.add(scrollPaneProgramari);
		
		table = new JTable();
		scrollPaneProgramari.setViewportView(table);
		
		JLabel lblProgramari = new JLabel("Consultatii:");
		lblProgramari.setBounds(10, 228, 171, 17);
		lblProgramari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblProgramari);
		
		 btnSelecteaza = new JButton("Selecteaza");
		btnSelecteaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int column = 0;
					int row = table.getSelectedRow();
					if(row>=0) {
						String value = table.getModel().getValueAt(row, column).toString();
						Long id = Long.parseLong(value);
						ConsultatieForm frame = new ConsultatieForm(loginUserTd,loginUserAccessCode,id);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);					
					} else {
						JOptionPane.showMessageDialog(null, "Alegeti o programare din lista! ", "Alegeti programare", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnSelecteaza.setBounds(977, 467, 89, 23);
		contentPane.add(btnSelecteaza);
		
		JLabel lblSpecializare = new JLabel("Specializare:");
		lblSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecializare.setBounds(449, 49, 89, 17);
		contentPane.add(lblSpecializare);
		
		JComboBox comboBoxSpecializare = new JComboBox();
		comboBoxSpecializare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		comboBoxSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSpecializare.setBounds(536, 46, 158, 22);
		contentPane.add(comboBoxSpecializare);
		
		JLabel lblUnitate = new JLabel("Unitate");
		lblUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnitate.setBounds(163, 49, 56, 17);
		contentPane.add(lblUnitate);
		
		JComboBox comboBoxUnitate = new JComboBox();
		comboBoxUnitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		comboBoxUnitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxUnitate.setBounds(266, 46, 158, 22);
		contentPane.add(comboBoxUnitate);
		

		
		
		//manual code:
		cmbUnitate = comboBoxUnitate;
		
		cmbMedic = comboBoxMedic;
		cmbSpecializare = comboBoxSpecializare;
		
		 btnNou = new JButton("Nou");
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConsultatieForm frame = new ConsultatieForm(loginUserTd,loginUserAccessCode,(long)-1);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnNou.setBounds(770, 467, 89, 23);
		contentPane.add(btnNou);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDdmmyyyy.setBounds(61, 23, 90, 17);
		contentPane.add(lblDdmmyyyy);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		btnRefresh.setBounds(977, 227, 89, 23);
		contentPane.add(btnRefresh);
		
	    Connect();
	    
	    comboBoxUnitatiLoad();
	    comboBoxMediciLoad();
	    comboBoxSpecializariLoad();
	    

	    table_load();
	    
	    seteazaDrepturi();
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
	
	
	
	 private void table_load(){
			try {
				DefaultTableModel dtm = new DefaultTableModel();
				table.setModel(dtm);
				dtm.addColumn("ID");
				dtm.addColumn("UNITATE");
				dtm.addColumn("DATA");
				dtm.addColumn("PACIENT");
				dtm.addColumn("INTERVAL");
				dtm.addColumn("SPECIALIZARE");
				dtm.addColumn("MEDIC");
		 
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(60);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(120);
				table.getColumnModel().getColumn(3).setPreferredWidth(150);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(200);
				table.getColumnModel().getColumn(6).setPreferredWidth(200);
				
				String SQL = "SELECT  *  FROM programari_consultatii_view ";
	    
				String WHERE = "";
						
				if(!textFieldData.getText().isBlank()) {
					WHERE+=" data_programare=?";
				}
				
				ItemCombo med = (ItemCombo)cmbMedic.getSelectedItem();	
				if(med != null && med.getId()>=0) {
					WHERE+=" id_medic=?";

				}
				
				ItemCombo unit = (ItemCombo)cmbUnitate.getSelectedItem();	
				if(unit != null && unit.getId()>=0) {
					WHERE+=" id_unitatate=? ";

				}
				
				ItemCombo spec = (ItemCombo)cmbSpecializare.getSelectedItem();	
				if(spec != null && spec.getId()>=0) {
					WHERE+=" id_specializare=? ";

				}
				
				if(!WHERE.isBlank()) {
					SQL = SQL + " WHERE " + WHERE;
				}
				
				PreparedStatement pstmt = con.prepareStatement(SQL);
		
				int param = 1; 
				
				if(!textFieldData.getText().isBlank()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date dataAngajare = dateFormat.parse(textFieldData.getText());
					java.sql.Date sqlDate = new java.sql.Date(dataAngajare.getTime());
					pstmt.setDate(param++, sqlDate);	
				}
				
				med = (ItemCombo)cmbMedic.getSelectedItem();	
				if(med != null && med.getId()>=0) {
					pstmt.setLong(param++,med.getId());
				}
				
				unit = (ItemCombo)cmbUnitate.getSelectedItem();	
				if(unit != null && unit.getId()>=0) {
					pstmt.setLong(param++,unit.getId());
				}
				
				spec = (ItemCombo)cmbSpecializare.getSelectedItem();	
				if(spec != null && spec.getId()>=0) {
					pstmt.setLong(param++,spec.getId());
				}
				

				ResultSet rset = pstmt.executeQuery();
				
				while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_consultatie"),
		 					rset.getString("unitate_denumire"),rset.getString("data_programare"),
		 					rset.getString("pacient_prenume")+" " +rset.getString("pacient_nume"),
		 					rset.getString("ora_inceput")+"-" +rset.getString("ora_sfarsit"),
		 					rset.getString("medic_specializare_denumire"),
		 					rset.getString("medic_prenume")+" " +rset.getString("medic_nume")
		 					};
		 			dtm.insertRow(0, record);

		 		}
				pstmt.close();
				
				

				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	public void comboBoxUnitatiLoad()
	 {
		 
		 try {
	 			ItemCombo all = new ItemCombo((long)-1,"*-toti-*");
	 			cmbUnitate.addItem(all);

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
			 

	 			ItemCombo all = new ItemCombo((long)-1,"*-toti-*");
	 			cmbMedic.addItem(all);

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
	
	public void comboBoxSpecializariLoad()
	 {
		 
		 try {
	 			ItemCombo all = new ItemCombo((long)-1,"*-toti-*");
	 			cmbSpecializare.addItem(all);

				// formate the SQL
				String sqlText = "SELECT * FROM specializari_medici";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_specializare"),rset.getString("denumire"));
		 			cmbSpecializare.addItem(fa);
		 		}
		 		if(cmbSpecializare!=null)
		 			cmbSpecializare.setSelectedIndex(0);//valoare implicita

		 		pstmt.close();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	
private void seteazaDrepturi() {
		
		// 13 -  access restrictionat/ aici inseamna ca e vb de receptioner care are drept sa vada / si sa adauge / modifice programari
		if(this.loginUserAccessCode==13) {
			btnSelecteaza.setEnabled(true);
			btnNou.setEnabled(true);
		}
		
		// 14 -  access restrictionat / aici inseamna ca e vb de asistenta care are doar sa adaauge raport analize medicale 
		if(this.loginUserAccessCode==14) {
			btnSelecteaza.setEnabled(true);
			btnNou.setEnabled(false);
		}
		
		// 15 -  access restrictionat / aici inseamna ca e vb de medic care are doar sa adaauge raport medical / istoric raport  medicale in consultatie
				if(this.loginUserAccessCode==15) {
					btnSelecteaza.setEnabled(true);
					btnNou.setEnabled(false);
				}
				
	}

}
