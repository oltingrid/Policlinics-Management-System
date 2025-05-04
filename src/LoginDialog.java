import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JTextField textFieldParola;
	
	Connection con;
	
	private long userId=-1;

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Utilizator:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(67, 45, 59, 17);
			contentPanel.add(lblNewLabel);
		}
		{
			textFieldUser = new JTextField();
			textFieldUser.setBounds(136, 45, 154, 20);
			contentPanel.add(textFieldUser);
			textFieldUser.setColumns(10);
		}
		{
			JLabel lblParola = new JLabel("Parola:");
			lblParola.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblParola.setBounds(67, 73, 59, 17);
			contentPanel.add(lblParola);
		}
		{
			textFieldParola = new JTextField();
			textFieldParola.setColumns(10);
			textFieldParola.setBounds(136, 73, 154, 20);
			contentPanel.add(textFieldParola);
		}
		{
			JLabel lblnume = new JLabel("(nume) ");
			lblnume.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblnume.setBounds(300, 45, 59, 17);
			contentPanel.add(lblnume);
		}
		{
			JLabel lblparola = new JLabel("(parola) ");
			lblparola.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblparola.setBounds(300, 73, 59, 17);
			contentPanel.add(lblparola);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cmdLogin();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		Connect();
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
	
	public void cmdLogin() {
		
		 try {
				
				// formate the SQL
				String sqlText = "SELECT * FROM utilizatori WHERE nume=? and parola=?";
				
		 		PreparedStatement pstmt = con.prepareStatement(sqlText);
		 		
				pstmt.setString(1, textFieldUser.getText());
				pstmt.setString(2, textFieldParola.getText());

		 		ResultSet rset = pstmt.executeQuery();
		 		
		 		if(rset.next()) {
		 			this.userId = rset.getLong("id_utilizator");
		 			
					//JOptionPane.showMessageDialog(null, "Sucess !", "Login", JOptionPane.INFORMATION_MESSAGE);
			 		dispose(); 
		 		} else {
		 			
					JOptionPane.showMessageDialog(null, "Esec ! !", "Login", JOptionPane.WARNING_MESSAGE);
		 			
		 		}
		 		

		 		pstmt.close();

		         
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Login failed !", "Eroare login!", JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			} 
	}
}
