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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FinanciarRaportSalariiForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JComboBox<ItemCombo> cmbUnitate;  
	private JComboBox<ItemCombo> cmbMedic;  
	private JComboBox<ItemCombo> cmbSpecializare;  
	private JComboBox comboBoxAn;
	JComboBox comboBoxLuna;
	
	private JTable table;
	Connection con;
	
	public final  static int getModuleCode() {
		return 800; 
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinanciarRaportSalariiForm frame = new FinanciarRaportSalariiForm(7,2);
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
	public FinanciarRaportSalariiForm(long userId,int accessCode) {
		

		
		setTitle("Financiar salarii angajati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1095, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMedic = new JLabel("Angajat:");
		lblMedic.setBounds(658, 50, 56, 17);
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblMedic);
		
		JComboBox comboBoxMedic = new JComboBox();
		comboBoxMedic.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 table_load();
			}
		});
		
		comboBoxMedic.setBounds(724, 47, 158, 22);
		comboBoxMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(comboBoxMedic);
		
		JScrollPane scrollPaneProgramari = new JScrollPane();
		scrollPaneProgramari.setBounds(10, 149, 1066, 200);
		contentPane.add(scrollPaneProgramari);
		
		table = new JTable();
		scrollPaneProgramari.setViewportView(table);
		
		JLabel lblProgramari = new JLabel("Consultatii:");
		lblProgramari.setBounds(20, 121, 171, 17);
		lblProgramari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblProgramari);
		
		JLabel lblSpecializare = new JLabel("Specializare:");
		lblSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecializare.setBounds(370, 50, 89, 17);
		contentPane.add(lblSpecializare);
		
		JComboBox comboBoxSpecializare = new JComboBox();
		comboBoxSpecializare.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 table_load();
			}
		});
		
		comboBoxSpecializare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSpecializare.setBounds(457, 47, 158, 22);
		contentPane.add(comboBoxSpecializare);
		cmbMedic = comboBoxMedic;
		cmbSpecializare = comboBoxSpecializare;
		
		JLabel lblAn = new JLabel("An");
		lblAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAn.setBounds(10, 52, 56, 17);
		contentPane.add(lblAn);
		
		 comboBoxAn = new JComboBox();
		 comboBoxAn.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		 table_load();
		 	}
		 });
		comboBoxAn.setModel(new DefaultComboBoxModel(new String[] {"2024", "2023", "2022"}));
		comboBoxAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxAn.setBounds(61, 49, 83, 22);
		contentPane.add(comboBoxAn);
		
		JLabel lblLuna = new JLabel("Luna");
		lblLuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLuna.setBounds(168, 49, 56, 17);
		contentPane.add(lblLuna);
		
		 comboBoxLuna = new JComboBox();
		 comboBoxLuna.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		 table_load();
		 	}
		 });
		comboBoxLuna.setModel(new DefaultComboBoxModel(new String[] {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"}));
		comboBoxLuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxLuna.setBounds(225, 49, 98, 22);
		contentPane.add(comboBoxLuna);
		
	    Connect();
	    
	    comboBoxMediciLoad();
	    comboBoxSpecializariLoad();
	    
	    table_load();
	    
	    // Set for individual access - 
	    if(accessCode==1)
	    	cmdSelectUser(userId);
	    
	}
	
	private void  cmdSelectUser(long userId) {
		for(int i =0 ; i<cmbMedic.getItemCount();i++ ) {
			ItemCombo med = (ItemCombo)cmbMedic.getItemAt(i);	
			if(med != null && med.getId()==userId) {
				cmbMedic.setSelectedIndex(i);
				cmbMedic.setEnabled(false);
			}
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
	
	
	
	 private void table_load(){
			try {
				DefaultTableModel dtm = new DefaultTableModel();
				table.setModel(dtm);
				dtm.addColumn("ID");
				dtm.addColumn("AN");
				dtm.addColumn("LUNA");
				dtm.addColumn("ANGAJAT");
				dtm.addColumn("SALAR BAZA");

		 
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(30);
				table.getColumnModel().getColumn(1).setPreferredWidth(60);
				table.getColumnModel().getColumn(2).setPreferredWidth(60);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				
				String SQL = "SELECT  *  FROM angajati_salarii_view ";
	    
				String WHERE = "";
				
				
				
				

//				int anul_selectat = Integer.parseInt((String)comboBoxAn.getSelectedItem());
//				if( comboBoxAn.getSelectedIndex()>=0) {
//					WHERE+=(WHERE.isBlank()) ? "":" and ";
//					WHERE+=" (an_activate is null or an_activate=?) ";
//				}
//				
//				int luna_selectata = comboBoxLuna.getSelectedIndex()+1;
//				if( comboBoxLuna.getSelectedIndex()>=0) {
//					WHERE+=(WHERE.isBlank()) ? "":" and ";
//					WHERE+=" (luna_activitate is null or luna_activitate=?) ";
//				}
				
						

				ItemCombo med = (ItemCombo)cmbMedic.getSelectedItem();	
				if(med != null && med.getId()>=0) {
					WHERE+=(WHERE.isBlank()) ? "":" and ";
					WHERE+=" id_utilizator=?";

				}

				
				ItemCombo spec = (ItemCombo)cmbSpecializare.getSelectedItem();	
				if(spec != null && spec.getId()>=0) {
					WHERE+=(WHERE.isBlank()) ? "":" and ";
					WHERE+=" id_specializare=? ";

				}
				
				if(!WHERE.isBlank()) {
					SQL = SQL + " WHERE " + WHERE;
				}
				
				PreparedStatement pstmt = con.prepareStatement(SQL);
		
				int param = 1; 
				
				
//				if( anul_selectat>0) {
//					pstmt.setInt(param++, anul_selectat);
//				}
//				
//				if( luna_selectata>0) {
//					pstmt.setInt(param++, luna_selectata);
//				}
				
				

				
				med = (ItemCombo)cmbMedic.getSelectedItem();	
				if(med != null && med.getId()>=0) {
					pstmt.setLong(param++,med.getId());
				}

				
				spec = (ItemCombo)cmbSpecializare.getSelectedItem();	
				if(spec != null && spec.getId()>=0) {
					pstmt.setLong(param++,spec.getId());
				}
				

				ResultSet rset = pstmt.executeQuery();
				double costuriTotale=0;
				double venitTotal = 0;
				double profitTotal = 0; 
				
				while (rset.next()){
		 			String[] record = new String[] {
		 					rset.getString("id_utilizator"),
		 					(String)comboBoxAn.getSelectedItem() ,
		 					(String)comboBoxLuna.getSelectedItem(),
		 					rset.getString("angajat_prenume")+" " +rset.getString("angajat_nume"),
		 					rset.getString("castig_net_lunar")
		 					};
		 			dtm.insertRow(0, record);
		 			
//		 			costuriTotale+=rset.getFloat("venit_total_angajat");
//		 			venitTotal+=rset.getFloat("total_incasari");

		 		}
				pstmt.close();
				
				profitTotal= venitTotal-costuriTotale;

				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot salva aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	
	
	public void comboBoxMediciLoad()
	 {
		 
		 try {
			 

	 			ItemCombo all = new ItemCombo((long)-1,"*-toti-*");
	 			cmbMedic.addItem(all);

				// formate the SQL
				String sqlText = "SELECT * FROM angajati_salarii_view";
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			//String[] record = new String[] {rset.getString("id_functie")};
		 			ItemCombo fa = new ItemCombo(rset.getLong("id_utilizator"),rset.getString("angajat_prenume")+" " + rset.getString("angajat_nume"));
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
}
