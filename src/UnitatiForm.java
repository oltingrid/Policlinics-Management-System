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

public class UnitatiForm {

	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	
	private Mode crtMode=Mode.VIEW;
	
	private JFrame frmUtilizatori;
	private JTable table;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					UnitatiForm window = new UnitatiForm();
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
	public UnitatiForm() {
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
	private JTextField textSearchText;


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
		frmUtilizatori.setTitle("Unitati");
		//frmUtilizatori.setBounds(100, 100, 662, 711);
		frmUtilizatori.setBounds(100, 100, 662, 550);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unitati");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		lblNewLabel.setBounds(347, 0, 272, 50);
		frmUtilizatori.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 636, 282);
		frmUtilizatori.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 61, 636, 96);
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

		btnUpdate.setBounds(469, 450, 150, 23);
		frmUtilizatori.getContentPane().add(btnUpdate);
		
	}
	
	 public void table_load()
	 {
		 
		 try {
				DefaultTableModel dtm = new DefaultTableModel();
				
				table.setModel(dtm);
				dtm.addColumn("ID");
				dtm.addColumn("Denumire");
				dtm.addColumn("Adresa");
				dtm.addColumn("Descriere");
		 
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(27);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				
				// formate the SQL
				String sqlText = "SELECT * FROM unitati";
				if(!textSearchText.getText().isEmpty()) {
					sqlText = "SELECT * FROM unitati "+" WHERE denumire LIKE '%"+textSearchText.getText()+"%'"+
					 " OR adresa LIKE '%"+textSearchText.getText()+"%'"+
							" OR descriere LIKE '%"+textSearchText.getText()+"%'"; 
				}
				
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		

		 		ResultSet rset = pstmt.executeQuery();
		 		while (rset.next()){
		 			String[] record = new String[] {rset.getString("id_unitate"),rset.getString("denumire"),rset.getString("adresa"), rset.getString("descriere")};
		 			dtm.insertRow(0, record);
		 		}

		 		pstmt.close();
		 		
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 

	
	
	
	 
	
	 
	
}
