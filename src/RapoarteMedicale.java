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

public class RapoarteMedicale {

	enum Mode {
		  UPDATE,
		  NEW,
		  VIEW
		}
	
	private Mode crtMode=Mode.VIEW;
	
	private JFrame frmUtilizatori;
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
					RapoarteMedicale window = new RapoarteMedicale(8,5);
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
	public RapoarteMedicale(long userId,int accessCode) {
		
		loginUserAccessCode = accessCode; 
		loginUserTd = userId;
		
		initialize();
	    Connect();
	    table_load();
	   
	    
	}
	
	public final  static int getModuleCode() {
		return 900; 
	}
	

	public void SetVisible(boolean visiable) {
		frmUtilizatori.setVisible(visiable);
		frmUtilizatori.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textSearchText;

	private JComboBox<ItemCombo> cmbFunctie;  
	private JComboBox<ItemCombo> cmbTip;  
	private JComboBox<ItemCombo> cmbDepartament;  
	private JComboBox<ItemCombo> cmbMedicGrad;  
	private JComboBox<ItemCombo> cmbMedicTitlu;  
	private JComboBox<ItemCombo> cmbPostDidactic;  

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
		frmUtilizatori.setTitle("Istoric rapoarte medicale pe pacient");
		//frmUtilizatori.setBounds(100, 100, 922, 711);
		frmUtilizatori.setBounds(100, 100, 922, 300);
		frmUtilizatori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUtilizatori.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Alege un pacient:");
		lblNewLabel.setBounds(20, 4, 272, 50);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frmUtilizatori.getContentPane().add(lblNewLabel);

		
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
		
		JButton btnUpdate = new JButton("Arata istoric ...");
		btnUpdate.setBounds(746, 215, 150, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				cmdloadSelectdRecord(value);
	
			}
		});
		frmUtilizatori.getContentPane().add(btnUpdate);
		
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
		 		//con.close();
		 		
		        dtm.fireTableDataChanged();
		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	        
	 }
	 

	
	private void cmdloadSelectdRecord(String selectedID) {
		
		
		Long id_pacient = Long.parseLong(selectedID);
		
		IstoricRapoartePacientForm frame = new IstoricRapoartePacientForm(id_pacient);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		

	}
	 
	
}
