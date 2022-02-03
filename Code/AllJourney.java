package Airline_Management_System;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.io.*;
import java.sql.*;
public class AllJourney extends JFrame implements ActionListener{
	JLabel l1,l2,l3;
	JTextField t1,t2;
	JButton b1,b2;
	Font f1;
	JTable table;
	PreparedStatement preparedStatement;
	Statement statement;
	Connection con;
	ResultSet result;
	String sql;
	String conUrl="jdbc:ucanaccess://C:\\Users\\Home\\Documents\\Login.accdb";
	AllJourney(){
		super("All Bookings");
		setSize(850,550);
		setVisible(true);
		setLayout(null);
		setLocation(400,100);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1=new Font("Tahoma",Font.PLAIN,17);
		l1=new JLabel("All Bookings");
		l1.setBounds(60, 20, 250, 27);
		l1.setFont(new Font("Tahoma",Font.PLAIN,26));
		add(l1);
		l2=new JLabel("Passport No");
		l2.setFont(f1);
		l2.setBounds(60,75,170,27);
		add(l2);
		t1=new JTextField(10);
		t1.setBounds(200,75,150,27);
		add(t1);
		table=new JTable();
		table.setBounds(23,180,800,300);
	    JScrollPane pane=new JScrollPane(table);
	    pane.setBounds(23,180,800,300);
	    pane.setBackground(Color.WHITE);
	    add(pane);
	    b1=new JButton("SHOW");
	    b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
	    b2=new JButton("View All");
	    b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b1.setBounds(220,120,120,30);
		b2.setBounds(60,120,120,30);
		add(b1);
		add(b2);
		getContentPane().setBackground(Color.WHITE);
	}
	public void actionPerformed(ActionEvent a)
	{
		String s=a.getActionCommand();
		String str1=t1.getText();
		if(s.equals("SHOW"))
		{
			sql="select UserName, PassportNo, Dated, FlightCode, ClassCode, Source, Destination, DepartureTime, SeatNo from BookedPers, BookedFli where BookedPers.PassportNo=BookedFli.PassportNo and BookedPers.Dated=BookedFli.Dated and PassportNo='"+str1+"'";
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				 con=DriverManager.getConnection(conUrl);
				 
				if(con!=null) {
					System.out.println("Connected Sucessfully");
					
					statement=con.createStatement();
					result=statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(result));
				}}catch(Exception e)
			{
					System.out.println(e);
				}
				}
		else if(s.equals("View All"))
		{
			sql="select UserName, PassportNo, Dated, FlightCode, ClassCode, Source, Destination, DepartureTime, SeatNo from BookedPers, BookedFli where BookedPers.PassportNo=BookedFli.PassportNo and BookedPers.Dated=BookedFli.Dated ";
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				 con=DriverManager.getConnection(conUrl);
				 
				if(con!=null) {
					System.out.println("Connected Sucessfully");
					
					statement=con.createStatement();
					result=statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(result));
				}}catch(Exception e)
			{
					System.out.println(e);
				}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AllJourney();
			}
		});
	}

}
