import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;
import net.proteanit.sql.*;

public class UserInfo extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection=null;
private JTable table_1;

	 	/**
	 * Create the frame.
	 */
	public UserInfo() {
		connection=postgresConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 709);
		getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("University Related Data");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblNewLabel.setBounds(284, 0, 295, 48);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Load User Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name","Address", "Email_ID"}, 0);
					String query="Select id,name,address,email_id from \"User\"";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						String d = rs.getString("ID");
					    String m = rs.getString("name");
					    String n=rs.getString("Address");
					    String f = rs.getString("email_id");
					    model.addRow(new Object[]{d, m,n, f});
					}
					
					table_1.setModel(model);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(16, 365, 187, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Population of\nUniversity");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model = new DefaultTableModel(new String[]{"Population"}, 0);
					String query="Select count(*) as Population\n"
							+ "from(\n"
							+ "    select inst_id from instructor\n"
							+ "	\n"
							+ "	union all\n"
							+ "	\n"
							+ "	select std_id from student\n"
							+ ") Population";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						String d = rs.getString("Population");
					   
					    model.addRow(new Object[]{d});
					}
					
					table_1.setModel(model);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(608, 159, 271, 48);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 313, 635, 362);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_2 = new JButton("No. of Stud under advisor");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                    try {
					
					DefaultTableModel model = new DefaultTableModel(new String[]{"Number of Students","Advisor Name"}, 0);
					String query="select count(std_id) as \"Number of Students\",ad_name\n"
							+ "from (select s.std_id,aa.ad_name as ad_name\n"
							+ "	  from student s,advisor aa\n"
							+ "	 where s.ad_id=aa.ad_id) sub\n"
							+ "	 group by ad_name;\n"
							+ "";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						String d = rs.getString("Number of Students");
						String f= rs.getString("ad_name");
					   
					    model.addRow(new Object[]{d,f});
					}
					
					table_1.setModel(model);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_2.setBounds(16, 464, 187, 45);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CS Dept users whose name start with specific letter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m= JOptionPane.showInputDialog("Enter the letter please");
				
                            try {
					
                            	DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name","Address", "Email_ID"}, 0);
					String query="Select id,name,address,email_id from (\n"
							+ "select * from \"User\" u\n"
							+ "where u.name like'"+m+"%' \n"
							+ "	) sub\n"
							+ "where dept_id in (select dept_id from department where dept_name='Computer Science');";
					PreparedStatement pst= connection.prepareStatement(query);
					
					ResultSet rs=pst.executeQuery();
					
					int size=0;
					
					while(rs.next()) {
						String d = rs.getString("ID");
					    String o = rs.getString("name");
					    String n=rs.getString("Address");
					    String f = rs.getString("email_id");
					    model.addRow(new Object[]{d, o,n, f});
					    size++;
					}
					
					if(size==0) {
						JOptionPane.showMessageDialog(null, "There are no users in CS Dept whose name starts with letter "+m);
					}
					
					table_1.setModel(model);
					
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_3.setBounds(6, 72, 339, 36);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Students with GPA between");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m= JOptionPane.showInputDialog("Enter the first value");
				String q= JOptionPane.showInputDialog("Enter the second value");
				
				//System.out.println("Value of M is"+m);
				//System.out.println("Value of Q is"+q);
				
				if((m.isEmpty()|| q.isEmpty()) ||Integer.parseInt(m)>Integer.parseInt(q)) {
					
					JOptionPane.showMessageDialog(null,"Enter the correct range as value 1 is greater than value 2 or null values are not allowed");
				}
				else {
				
                try {
		
                	DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name","Address", "Email_ID"}, 0);
		String query="select id,name,address,email_id from \"User\"\n"
				+ "where id in(select id from student where gpa between "+m+" and "+q+");";
		PreparedStatement pst= connection.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		int size=0;
		
		while(rs.next()) {
			String d = rs.getString("ID");
		    String o = rs.getString("name");
		    String n=rs.getString("Address");
		    String f = rs.getString("email_id");
		    model.addRow(new Object[]{d, o,n, f});
		    size++;
		}
		
		if(size==0) {
			JOptionPane.showMessageDialog(null, "There are no Students within that range");
		}
		
		table_1.setModel(model);
		
		
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
				}
				
			}
		});
		btnNewButton_4.setBounds(600, 238, 279, 48);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Professors who are not advisors");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
						
	                	DefaultTableModel model = new DefaultTableModel(new String[]{"Name"}, 0);
			String query="Select u.name as Name\n"
					+ "from \"User\" u,Instructor i\n"
					+ "where u.ID=i.ID\n"
					+ "and i.inst_id not in (select inst_id from advisor);";
			PreparedStatement pst= connection.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			
			int size=0;
			
			while(rs.next()) {
				
			    String o = rs.getString("name");
			   
			    model.addRow(new Object[]{o});
			    size++;
			}
			
			if(size==0) {
				JOptionPane.showMessageDialog(null, "There are no Students within that range");
			}
			
			table_1.setModel(model);
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
				
				
			}
		});
		btnNewButton_5.setBounds(6, 238, 295, 48);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("No. of people in each building");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
                	DefaultTableModel model = new DefaultTableModel(new String[]{"No_of_people","Building_Name"}, 0);
		String query="select count(*) as No_of_people,building\n"
				+ "from (select u.id,d.building from \"User\" u, department d\n"
				+ "	 where u.dept_id=d.dept_id) B\n"
				+ "	 group by building;";
		PreparedStatement pst= connection.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		int size=0;
		
		while(rs.next()) {
			
			String m=rs.getString("No_of_people");
		    String o = rs.getString("building");
		   
		    model.addRow(new Object[]{m,o});
		    size++;
		}
		
		if(size==0) {
			JOptionPane.showMessageDialog(null, "There are no Students within that range");
		}
		
		table_1.setModel(model);
		
		
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
				
				
			}
		});
		btnNewButton_6.setBounds(325, 159, 271, 48);
		getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Where each professor handles class");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                  try {
					
                	DefaultTableModel model = new DefaultTableModel(new String[]{"Instructor_Name","Building"}, 0);
		String query="Select name as \"Instructor_Name\",building as \"Building\" \n"
				+ "from (select name,building \n"
				+ "	  from \"User\" u, department d\n"
				+ "	  where u.dept_id=d.dept_id\n"
				+ "	  and u.id in (select id from instructor ))Details;";
		PreparedStatement pst= connection.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		int size=0;
		
		while(rs.next()) {
			
			String m=rs.getString("Instructor_Name");
		    String o = rs.getString("Building");
		   
		    model.addRow(new Object[]{m,o});
		    size++;
		}
		
		
		table_1.setModel(model);
		
		
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
				
				
			}
		});
		btnNewButton_7.setBounds(557, 68, 322, 45);
		getContentPane().add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Advisors");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                   try {
					
                	DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Age","Address", "Email_ID"}, 0);
		String query="Select name,age,address,email_id from \"User\"\n"
				+ "where name in (select ad_name from advisor);";
		PreparedStatement pst= connection.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		int size=0;
		
		while(rs.next()) {
			
			String d = rs.getString("name");
		    String o = rs.getString("age");
		    String n=rs.getString("address");
		    String f = rs.getString("email_id");
		    model.addRow(new Object[]{d, o,n, f});
		    
		    size++;
		}
		
		
		table_1.setModel(model);
		
		
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
				
			}
		});
		btnNewButton_8.setBounds(16, 591, 187, 36);
		getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Students below given age");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String m= JOptionPane.showInputDialog("Enter the age");
				
				
				
				
				if(m.isEmpty()) {
					
					JOptionPane.showMessageDialog(null,"Age cannot be Null");
				}
				else {
				
                try {
		
                	DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Age","Address", "Email_ID"}, 0);
		String query="Select name,age,address,email_id from \n"
				+ "\"User\" u\n"
				+ "where u.ID in (select id from Student)\n"
				+ "and u.age<"+m+";";
		PreparedStatement pst= connection.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		int size=0;
		
		while(rs.next()) {
			String d = rs.getString("name");
		    String o = rs.getString("age");
		    String n=rs.getString("address");
		    String f = rs.getString("email_id");
		    model.addRow(new Object[]{d, o,n, f});
		    size++;
		}
		
		if(size==0) {
			JOptionPane.showMessageDialog(null, "There are no Students within below age of "+m);
		}
		
		table_1.setModel(model);
		
		
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
				
				}		
				
			}
		});
		btnNewButton_9.setBounds(6, 159, 271, 48);
		getContentPane().add(btnNewButton_9);
	}
	
}

