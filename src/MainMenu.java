import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class MainMenu {

	private JFrame frmPoliclinici;
	private JTextField textFieldUserID;
	
	private long loginUserId = -1; 
	private long loginUserFunctieId = -1; 
	
	Connection con;
	private JTextField textFieldUserNume;
	private JTextField textFieldUserDepartament;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frmPoliclinici.setVisible(true);
					window.cmdLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
		Connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPoliclinici = new JFrame();
		frmPoliclinici.setTitle("POLICLINICI");
		frmPoliclinici.setBounds(100, 100, 729, 508);
		frmPoliclinici.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPoliclinici.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Resurse Umane");
		mnNewMenu_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Angajati");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowAngajati();
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Utilizatori");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowUtilizatori(); 
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Medici");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowMedici();
			}
		});
		mntmNewMenuItem_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Asistenti");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cmdShowAsistenti();
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu = new JMenu("Financiar");
		mnNewMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Profit policlinici");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowProfitPoliclinici(); 
			}
		});
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Profit medici");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowProfitMedici(); 
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Salarii");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowSalarii(); 
			}
		});
		
		
		mntmNewMenuItem_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("Operational");
		mnNewMenu_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Programare pacienti");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowProgramari();
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Raporte medicale");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowRapoarteMedicale();
			}
		});
		mntmNewMenuItem_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Rapoarte analiza");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowRapoarteAnalize();
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu_2.add(mntmNewMenuItem_7);
		frmPoliclinici.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User ID: ");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 391, 68, 14);
		frmPoliclinici.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1_2 = new JButton("Login...");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cmdLogin(); 

			}
		});
		btnNewButton_1_2.setBounds(577, 389, 136, 23);
		frmPoliclinici.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1 = new JButton("PROGRAMARI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdShowProgramari();
			}
		});
		btnNewButton_1.setBounds(332, 215, 136, 23);
		frmPoliclinici.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("CLINICI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UnitatiForm window = new UnitatiForm();
					window.SetVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(97, 215, 136, 23);
		frmPoliclinici.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nume:");
		lblNewLabel.setIcon(new ImageIcon("D:\\Ingrid\\AA_AN_3\\JavaSample_28\\img\\med_small_2.png"));
		lblNewLabel.setBounds(10, 0, 703, 387);
		frmPoliclinici.getContentPane().add(lblNewLabel);
		
		textFieldUserID = new JTextField();
		textFieldUserID.setEditable(false);
		textFieldUserID.setBounds(0, 416, 50, 20);
		frmPoliclinici.getContentPane().add(textFieldUserID);
		textFieldUserID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nume:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(110, 391, 41, 14);
		frmPoliclinici.getContentPane().add(lblNewLabel_1_1);
		
		textFieldUserNume = new JTextField();
		textFieldUserNume.setEditable(false);
		textFieldUserNume.setColumns(10);
		textFieldUserNume.setBounds(110, 416, 136, 20);
		frmPoliclinici.getContentPane().add(textFieldUserNume);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Departament / functie:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(264, 391, 147, 14);
		frmPoliclinici.getContentPane().add(lblNewLabel_1_1_1);
		
		textFieldUserDepartament = new JTextField();
		textFieldUserDepartament.setEditable(false);
		textFieldUserDepartament.setColumns(10);
		textFieldUserDepartament.setBounds(256, 416, 275, 20);
		frmPoliclinici.getContentPane().add(textFieldUserDepartament);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void cmdLogin() {
		LoginDialog myJDialog =  new  LoginDialog ( ) ; 

		 myJDialog.setUserId(this.loginUserId);
         myJDialog. setModal ( true ) ; 
         myJDialog. setVisible ( true ) ; 
        
        if(this.loginUserId!=  myJDialog.getUserId() ) {
        	this.loginUserId =myJDialog.getUserId();
        	loadCrtUser();
        }
        
        if(myJDialog.getUserId()<0) {
	        frmPoliclinici.dispose();
        }
        
        
	}
	
	public void Connect()
    {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/policlinici", "root", "#Motroscuta19");
        }
      
        catch (SQLException ex) 
        {
               ex.printStackTrace();
        }

    }
	private void loadCrtUser() {
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatori_departament_view WHERE id_utilizator=?";
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		
				pstmt.setLong(1, this.loginUserId);


		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()) {

		 			Long idUser = rset.getLong("id_utilizator");
		 			textFieldUserID.setText(idUser.toString());
		 			
		 			textFieldUserNume.setText(rset.getString("nume")+" "+rset.getString("prenume") );
		 			textFieldUserDepartament.setText(rset.getString("departament_denumire") +" / "+rset.getString("functie_denumire"));
		 			
		 			this.loginUserFunctieId = rset.getLong("id_functie");


		 		} else {
		 			
		 			JOptionPane.showMessageDialog(null, "Atentie utilizator nu are drepturi de access definite! Atasati o functie in modulul angajati! ", "Drepturi de access !", JOptionPane.WARNING_MESSAGE);
		 			
		 			textFieldUserID.setText("");
		 			textFieldUserNume.setText("" );
		 			textFieldUserDepartament.setText("");
		 		}
		 		

		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Eroare citire date !", "Eroare citire date!", JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
		
	}
	
	// Verifica daca utilizatorul logat are drept de access pe modulul cu codul moduleCode
	// return > 0 are access , <0 nu are access 
	private int checkUserAccess(int moduleCode) {
		int rez = -1; 
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM drepturi_access WHERE id_functie=? and cod_access=?";
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		
				pstmt.setLong(1, this.loginUserFunctieId);
				pstmt.setLong(2, moduleCode);


		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if( rset.next()) {
		 			rez = rset.getInt("drept");
		 		}
	
		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Eroare citire date !", "Eroare citire date!", JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
		
		 return rez; 
	}
	
	


	private void cmdShowSalarii() {
		
		try {
			int accessCode = checkUserAccess (FinanciarRaportSalariiForm.getModuleCode());
			
			if ( accessCode>0 ) {
				FinanciarRaportSalariiForm window = new FinanciarRaportSalariiForm(this.loginUserId,accessCode);
				window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				window.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cmdShowProfitMedici() {
		
		try {
			int accessCode = checkUserAccess (FinanciarRaportProfitMediciForm.getModuleCode());
			
			if ( accessCode>0 ) {
				FinanciarRaportProfitMediciForm window = new FinanciarRaportProfitMediciForm(this.loginUserId,accessCode);
				window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				window.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	
	private void cmdShowProfitPoliclinici() {
		
		try {
			
			if ( checkUserAccess (FinanciarRaportProfitForm.getModuleCode())>0 ) {
				FinanciarRaportProfitForm window = new FinanciarRaportProfitForm();
				window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				window.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	private void cmdShowProgramari() {
		
		try {
			int accessCode = checkUserAccess (ProgramariForm.getModuleCode());
			
			if ( accessCode>0 ) {
				ProgramariForm window = new ProgramariForm(this.loginUserId,accessCode);
				window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				window.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cmdShowAsistenti() {
		
		try {
			int accessCode = checkUserAccess (AsistentiForm.getModuleCode());
			
			if ( accessCode>0 ) {
				AsistentiForm window = new AsistentiForm(this.loginUserId,accessCode);
				window.SetVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

private void cmdShowMedici() {
		
		try {
			int accessCode = checkUserAccess (MediciForm.getModuleCode());
			
			if ( accessCode>0 ) {
				MediciForm window = new MediciForm(this.loginUserId,accessCode);
				window.SetVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



	
private void cmdShowAngajati() {
		
		try {
			int accessCode = checkUserAccess (AngajatiForm.getModuleCode());
			
			if ( accessCode>0 ) {
				AngajatiForm window = new AngajatiForm(this.loginUserId,accessCode);
				window.SetVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

private void cmdShowUtilizatori() {
	
	try {
		int accessCode = checkUserAccess (UtilizatoriForm.getModuleCode());
		
		if ( accessCode>0 ) {
			UtilizatoriForm window = new UtilizatoriForm(this.loginUserId,accessCode);
			window.SetVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
		}
	
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}

private void cmdShowRapoarteMedicale() {
	
	try {
		int accessCode = checkUserAccess (RapoarteMedicale.getModuleCode());
		
		if ( accessCode>0 ) {
			RapoarteMedicale window = new RapoarteMedicale(this.loginUserId,accessCode);
			window.SetVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
		}
	
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}
	
private void cmdShowRapoarteAnalize() {
	
	try {
		int accessCode = checkUserAccess (RapoarteAnaliza.getModuleCode());
		
		if ( accessCode>0 ) {
			RapoarteAnaliza window = new RapoarteAnaliza(this.loginUserId,accessCode);
			window.SetVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Nu aveti drept de access la acest modul", "Dreturi de access!", JOptionPane.WARNING_MESSAGE);
		}
	
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}
	
}
