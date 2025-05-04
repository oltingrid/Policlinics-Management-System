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


import javax.swing.JOptionPane; 






import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import view.users.SQLException;

public class PacientiForm {

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
					PacientiForm window = new PacientiForm();
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
	public PacientiForm() {
		initialize();
	    Connect();
	    table_load();
	}

	public void SetVisible(boolean visiable) {
		frmUtilizatori.setVisible(visiable);
		frmUtilizatori.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textAdresa;
	private JTextField textSearchText;
	private JTextField textTelefon;
	private JTextField textID;


	private JComboBox<ItemCombo> cmbTip;  


	
	

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
		frmUtilizatori.setTitle("Pacienti");
		frmUtilizatori.setBounds(100, 100, 922, 711);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pacienti");
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
		
		textAdresa = new JTextField();
		textAdresa.setColumns(10);
		textAdresa.setBounds(114, 164, 166, 20);
		panel.add(textAdresa);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Telefon:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_2.setBounds(10, 198, 83, 17);
		panel.add(lblNewLabel_1_2_1_1_2);
		
		textTelefon = new JTextField();
		textTelefon.setColumns(10);
		textTelefon.setBounds(114, 195, 166, 20);
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
				String sqlText = "SELECT * FROM pacienti";
				if(!textSearchText.getText().isEmpty()) {
					sqlText = "SELECT * FROM pacienti "+" WHERE nume LIKE '"+textSearchText.getText()+"%'"+
					 " OR prenume LIKE '"+textSearchText.getText()+"%'"+
							" OR CNP LIKE '"+textSearchText.getText()+"%'"; 
				}
				
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_pacient"),rset.getString("CNP"),rset.getString("nume"), rset.getString("prenume")};
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
				String sqlText = "SELECT * FROM pacienti WHERE id_pacient="+selectedID;
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()==true) {
		 			textID.setText(rset.getString("id_pacient"));
			 		textCNP.setText(rset.getString("CNP"));
			 		textNume.setText(rset.getString("nume"));
			 		textPrenume.setText(rset.getString("prenume"));
			 		textAdresa.setText(rset.getString("adresa"));
			 		textTelefon.setText(rset.getString("telefon"));
		 		} else {
		 			
		 			textID.setText("");
			 		textCNP.setText("");
			 		textNume.setText("");
			 		textPrenume.setText("");
			 		textAdresa.setText("");
			 		textTelefon.setText("");
		 			
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
				

	    
				String UPDATE = "UPDATE  pacienti SET CNP=?,nume=?,prenume=?,adresa=?,telefon=? WHERE id_pacient=?";
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);


				//pstmt.setString(1, textID.getText());
				
				pstmt.setString(1, textCNP.getText());
				pstmt.setString(2, textNume.getText());
				pstmt.setString(3, textPrenume.getText());
				pstmt.setString(4, textAdresa.getText());
				pstmt.setString(5, textTelefon.getText());
				
				Long id_pac = Long.parseLong(textID.getText());
				pstmt.setLong(6, id_pac);
				
				
				pstmt.executeUpdate();


				pstmt.close();
				
		 		//refresh 
		 		table_load(); 
		 		
				JOptionPane.showMessageDialog(null, "Date salvate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
				setMode(Mode.VIEW);
				
				clearFields();

				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Nu pot insera aceste date! ", "Erroare !", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} 
		}
	 
	 private void cmdSaveNew(){
			try {
				String INSERT = "INSERT INTO pacienti (CNP,nume,prenume,adresa,telefon) VALUES (?, ?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		
				pstmt.setString(1, textCNP.getText());
				pstmt.setString(2, textNume.getText());
				pstmt.setString(3, textPrenume.getText());
				pstmt.setString(4, textAdresa.getText());
				pstmt.setString(5, textTelefon.getText());

				
				pstmt.executeUpdate();
				ResultSet rset = pstmt.getGeneratedKeys();
				if(rset.next() ) {
					Long id_pac = rset.getLong(1);
					if(id_pac!=null)
						textID.setText(id_pac.toString());
					else 
						textID.setText("");
				}
				pstmt.close();
				
				JOptionPane.showMessageDialog(null, "Date inserate cu success!", "", JOptionPane.INFORMATION_MESSAGE);
				
		 		//refresh 
				setMode(Mode.UPDATE);
		 		table_load(); 
				//clearFields();
				
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

	 }
	 
	 private void enableFields(boolean b) {
		 
			textID.setEnabled(b);
	 		textCNP.setEnabled(b);
	 		textNume.setEnabled(b);
	 		textPrenume.setEnabled(b);
	 		textAdresa.setEnabled(b);
	 		textTelefon.setEnabled(b);

	 		
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
	 
	
}
