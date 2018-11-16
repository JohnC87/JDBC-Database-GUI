package com.jdbc;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.ApplicationFrame;


@SuppressWarnings("serial")
public class JDBCMainWindowContent extends JInternalFrame implements ActionListener
{
	// DB Connectivity Attributes
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private Container content;
	
	private JPanel detailsPanel;
	private JPanel exportButtonPanel;
	private JPanel graphButtonPanel;
	private JPanel deletePanel;
//	private JPanel exportConceptDataPanel;
	private JScrollPane dbContentsPanel;
//	private JPanel AuditTablePanel;
	
	private Border lineBorder;
	
	private JLabel yearly=new JLabel("Year:                 ");
	private JLabel totalUsageRevenue=new JLabel("Total Revenue:               ");
	private JLabel avgSMSRevenue=new JLabel("Average SMS Revenue:      ");
	private JLabel avgDataRevenue=new JLabel("Average Data Revenue:        ");
	private JLabel sMSTraffic=new JLabel("SMS Traffic:                 ");
	private JLabel dataTraffic=new JLabel("Data Traffic:               ");
	private JLabel dataId=new JLabel("Data ID:        ");
	private JLabel voiceMinutes=new JLabel("Voice Minutes:      ");
	private JLabel networkName=new JLabel("Network Name:       ");
	
	private JTextField yearlyf= new JTextField(20);
	private JTextField totalUsageRevenuef=new JTextField(20);
	private JTextField avgSMSRevenuef=new JTextField(20);
	private JTextField avgDataRevenuef=new JTextField(20);
	private JTextField sMSTrafficf=new JTextField(20);
	private JTextField dataTrafficf=new JTextField(20);
	private JTextField dataIdf=new JTextField(20);
	private JTextField voiceMinutesf=new JTextField(20);
	private JTextField networkNamef=new JTextField(20);

			
	private static QueryTableModel TableModel = new QueryTableModel();
	private static QueryTableModel AuditTableModel = new QueryTableModel();
	
	//Add the models to JTabels
	private JTable TableofDBContents=new JTable(TableModel);
//	private JTable AuditTable = new JTable(AuditTableModel);
	
	//Buttons for inserting, and updating members
	//also a clear button to clear details panel
	private JButton updateButton = new JButton("Update");
	private JButton insertButton = new JButton("Insert");
	private JButton exportButton  = new JButton("Export");
	private JButton deleteButton  = new JButton("Delete by ID");
	private JButton clearInputFieldsButton  = new JButton("Clear Fields");
//	private JButton auditTableButton = new JButton("Audit Table");

//	private JButton createPieChart  = new JButton("Generate Pie Chart");
//	private JTextField last3LossRatesTF  = new JTextField(12);
	private JButton marketShares  = new JButton("Export Market Share");
	private JButton justTraffic  = new JButton("Export SMS and Data Traffic");
	private JButton vodafone = new JButton("Export Vodafone Data");
	private JButton eir = new JButton("Export Eir Data");
	private JButton three = new JButton("Export Three Data");
	private JButton tescoMobile = new JButton("Export Tesco Mobile Data");
//	private JTextField avgofRSSTF  = new JTextField(12);
	private JButton dataProgression  = new JButton("Data Increase");
	private JButton smsDecline = new JButton("SMS Decrease");
//	private JButton overLappingChannels  = new JButton("AP Channel");
	private JButton viewRevenueAndShares  = new JButton("Provider Market Share");
//	private JButton viewNumbersByType  = new JButton("View Numbers by Type");
//	private JButton viewNumbersByLocation  = new JButton("View Numbers by Type");


	
	int year = Calendar.getInstance().get(Calendar.YEAR);
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int day = Calendar.getInstance().get(Calendar.DATE);
	Calendar currentDate = Calendar.getInstance();
	String currentDateString = day + String.format("-%02d-", month + 1) + year;

	public JDBCMainWindowContent( String aTitle)
	{	
		//setting up the GUI
		super(aTitle, false,false,false,false);
		setEnabled(true);
		
		initiate_db_conn();
		//add the 'main' panel to the Internal Frame
		content=getContentPane();
		content.setLayout(null);
		content.setBackground(new java.awt.Color(217, 217, 217));
		lineBorder = BorderFactory.createEtchedBorder(15, Color.red, Color.black);
	
		//setup details panel and add the components to it
		detailsPanel=new JPanel();
		detailsPanel.setLayout(new GridLayout(11,2));
		detailsPanel.setBackground(Color.lightGray);
		detailsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "DB Details"));
			
		detailsPanel.add(yearly);			
		detailsPanel.add(yearlyf);
		detailsPanel.add(totalUsageRevenue);		
		detailsPanel.add(totalUsageRevenuef);
		detailsPanel.add(avgSMSRevenue);	
		detailsPanel.add(avgSMSRevenuef);
		detailsPanel.add(avgDataRevenue);		
		detailsPanel.add(avgDataRevenuef);
		detailsPanel.add(sMSTraffic);
		detailsPanel.add(sMSTrafficf);
		detailsPanel.add(dataTraffic);
		detailsPanel.add(dataTrafficf);
//		detailsPanel.add(dataId);
//		detailsPanel.add(dataIdf);
		detailsPanel.add(voiceMinutes);
		detailsPanel.add(voiceMinutesf);
		detailsPanel.add(networkName);
		detailsPanel.add(networkNamef);
		
		//setup details panel and add the components to it
		exportButtonPanel=new JPanel();
		exportButtonPanel.setLayout(new GridLayout(3,2));
		exportButtonPanel.setBackground(Color.lightGray);
		exportButtonPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Export Data"));
		
		exportButtonPanel.add(eir);
		exportButtonPanel.add(vodafone);
		exportButtonPanel.add(three);
		exportButtonPanel.add(tescoMobile);
		exportButtonPanel.add(marketShares);
		exportButtonPanel.add(justTraffic);
		exportButtonPanel.setSize(550, 200);
		exportButtonPanel.setLocation(58, 410);
//		exportButtonPanel.add(viewRevenueAndShares);
//		exportButtonPanel.add(viewNumbersByType);
//		exportButtonPanel.add(viewNumbersByLocation);
		content.add(exportButtonPanel);
		
		
		deletePanel=new JPanel();
		deletePanel.setSize(360, 100);
		deletePanel.setLocation(2, 300);
		deletePanel.setLayout(new GridLayout(3,2));
		deletePanel.setBackground(Color.lightGray);
		deletePanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Delete Rows"));
		deletePanel.add(dataId);
		deletePanel.add(dataIdf);
		
		
//		AuditTablePanel=new JPanel();
//		AuditTablePanel.setSize(180, 100);
//		AuditTablePanel.setLocation(720, 300);
//		AuditTablePanel.setLayout(new GridLayout(3,2));
//		AuditTablePanel.setBackground(Color.lightGray);
//		AuditTablePanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Audit Table"));
//		AuditTablePanel.add(auditTableButton);
		

		
		graphButtonPanel=new JPanel();
		graphButtonPanel.setLayout(new GridLayout(3,2));
		graphButtonPanel.setBackground(Color.lightGray);
		graphButtonPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Visualise Data"));
//		graphButtonPanel.add(createPieChart);
//		exportButtonPanel.add(last3LossRatesTF);
		graphButtonPanel.add(viewRevenueAndShares);
		graphButtonPanel.add(smsDecline);
//		exportButtonPanel.add(avgofRSSTF);
		graphButtonPanel.add(dataProgression);
//		graphButtonPanel.add(overLappingChannels);
		graphButtonPanel.setSize(550, 200);
		graphButtonPanel.setLocation(615, 410);
//		graphButtonPanel.add(viewNumbersByType);
//		graphButtonPanel.add(viewNumbersByLocation);
		content.add(graphButtonPanel);
		
	
		insertButton.setSize(100, 30);
		updateButton.setSize(100, 30);
		exportButton.setSize (100, 30);
		deleteButton.setSize (100, 30);
		clearInputFieldsButton.setSize (100, 30);
		
		insertButton.setLocation(370, 20);
		updateButton.setLocation(370, 70);
		exportButton.setLocation (370, 120);
		clearInputFieldsButton.setLocation (370, 170);
		deleteButton.setLocation (370, 340);

		
		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		exportButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearInputFieldsButton.addActionListener(this);
		marketShares.addActionListener(this);
		justTraffic.addActionListener(this);
		vodafone.addActionListener(this);
		eir.addActionListener(this);
		three.addActionListener(this);
		tescoMobile.addActionListener(this);
		viewRevenueAndShares.addActionListener(this);
		dataProgression.addActionListener(this);
		smsDecline.addActionListener(this);
//		auditTableButton.addActionListener(this);
		

		
		content.add(insertButton);
		content.add(updateButton);
		content.add(exportButton);
		content.add(deleteButton);
		content.add(clearInputFieldsButton);
		
				
		TableofDBContents.setPreferredScrollableViewportSize(new Dimension(900, 300));
//		AuditTable.setPreferredScrollableViewportSize(new Dimension(900, 300));
	
		dbContentsPanel=new JScrollPane(TableofDBContents,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dbContentsPanel.setBackground(Color.lightGray);
		dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Content"));
		
//		AuditTablePanel=new JScrollPane(AuditTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		AuditTablePanel.setBackground(Color.lightGray);
//		AuditTablePanel.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Changes"));
				
		detailsPanel.setSize(360, 300);
		detailsPanel.setLocation(3,0);
		dbContentsPanel.setSize(700, 300);
		dbContentsPanel.setLocation(477, 0);
//		AuditTablePanel.setSize(700, 150);
//		AuditTablePanel.setLocation(477, 310);
		
		content.add(detailsPanel);
		content.add(dbContentsPanel);
		content.add(deletePanel);
//		content.add(AuditTablePanel);
		
		setSize(982,645);
		setVisible(true);
	
		TableModel.refreshFromDB(stmt);
//		AuditTableModel.refreshFromDBAudit(stmt);
		

		
	}
	
	private void writeToFile(ResultSet rs, String filename){
		try{
			FileWriter outputFile = new FileWriter(filename+".csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for(int i=0;i<numColumns;i++){
				printWriter.print(rsmd.getColumnLabel(i+1)+",");
			}
			printWriter.print("\n");
			while(rs.next()){
				for(int i=0;i<numColumns;i++){	
					printWriter.print(rs.getString(i+1)+",");
				}
				printWriter.print("\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void initiate_db_conn()
	{
		try
		{
			// Load the JConnector Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Specify the DB Name
			String url="jdbc:mysql://localhost:3306/voipdata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			// Connect to DB using DB URL, Username and password
			con = DriverManager.getConnection(url, "root", "admin");
			//Create a generic statement which is passed to the TestInternalFrame1
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n"+e.getMessage());
		}
	}
	
	//event handling for members desktop
	public void actionPerformed(ActionEvent e)
	{
		 Object target=e.getSource();
//		 if (target == clearInputFieldsButton)
//		 {
//			 yearly.setText("");
//			 totalUsageRevenue.setText("");
//			 avgSMSRevenue.setText("");
//			 avgDataRevenue.setText("");
//			 sMSTraffic.setText("");
//			 dataTraffic.setText("");
//			 voiceMinutes.setText("");
//			 	 
//		 }
		
		 if (target == insertButton)
		 {		 
	 		try
	 		{
 				String updateTemp ="CALL insert_market_data ("+
 		 				  yearlyf.getText()+",'"+totalUsageRevenuef.getText()+"','"+avgSMSRevenuef.getText()+"','"+avgDataRevenuef.getText()+"','"+sMSTrafficf.getText()+"','"
 		 				 +dataTrafficf.getText()+"', null,'"+voiceMinutesf.getText()+"','"+networkNamef.getText()+"');";
 				System.out.println(updateTemp);
 				
 				ps = con.prepareStatement(updateTemp);
 				ps.executeUpdate();
 				System.out.println("Procedure Called"+ps);
 				
// 				String updateTemp ="INSERT INTO voipdata.marketdata VALUES ("+yearlyf.getText()+",'"+totalUsageRevenuef.getText()+"','"+avgSMSRevenuef.getText()+"','"+avgDataRevenuef.getText()+"','"+sMSTrafficf.getText()+"','"
//		 				 +dataTrafficf.getText()+"','"+voiceMinutesf.getText()+"');";
// 							
//						
//				stmt.executeUpdate(updateTemp);
 			
	 		}
	 		catch (SQLException sqle)
	 		{
	 			System.err.println("Error with members insert:\n"+sqle.toString());
	 		}
	 		finally
	 		{
	 			TableModel.refreshFromDB(stmt);
//	 			AuditTableModel.refreshFromDBAudit(stmt);
			}
		 }
		 if (target == deleteButton)
		 {
		 	
	 		try
	 		{
 				String updateTemp ="DELETE FROM MARKETDATA WHERE DataId = "+dataIdf.getText()+";"; 
 				stmt.executeUpdate(updateTemp);
 			
	 		}
	 		catch (SQLException sqle)
	 		{
	 			System.err.println("Error with delete:\n"+sqle.toString());
	 		}
	 		finally
	 		{
	 			TableModel.refreshFromDB(stmt);
//	 			AuditTableModel.refreshFromDBAudit(stmt);
			}
		 }
		 if (target == updateButton)
		 {	 	
	 		try
	 		{ 			
// 				String updateTemp = "CALL update_market_data(Yearly = " + yearlyf.getText()+"Total_usage_revenue = '"+totalUsageRevenuef.getText()+
//							"', Avg_SMS_revenue = '"+avgSMSRevenuef.getText()+
//							"', Avg_data_revenue = '"+avgDataRevenuef.getText()+
//							"', SMS_traffic = '"+sMSTrafficf.getText()+
//							"', Data_traffic = '"+dataTrafficf.getText()+
//							"', Mobile_voice_minutes = '"+voiceMinutesf.getText()+"');";
 				
 				
 				String updateTemp = "CALL update_market_data ("+
 						yearlyf.getText()+",'"+totalUsageRevenuef.getText()+"','"+avgSMSRevenuef.getText()+"','"+avgDataRevenuef.getText()+"','"+sMSTrafficf.getText()+"','"
		 				 +dataTrafficf.getText()+"', "+dataIdf.getText()+",'"+voiceMinutesf.getText()+"','"+networkNamef.getText()+"');";
 				
 				
 						
// 						"UPDATE MARKETDATA SET Yearly_q2_data = '"+yearlyf.getText()+
// 									
// 									"', Total_usage_revenue = "+
// 									"'"+totalUsageRevenuef.getText()+"'"+
// 									", Avg_SMS_revenue = "+avgSMSRevenuef.getText()+
// 									", Avg_data_revenue = "+avgDataRevenuef.getText()+
// 									", SMS_traffic = "+sMSTrafficf.getText()+
// 									", Data_traffic = "+dataTrafficf.getText()+
// 									", Mobile_voice_minutes = "+
// 									"'"+voiceMinutesf.getText();
 				
 	
 				
 				ps = con.prepareStatement(updateTemp);
 				ps.executeUpdate();
 				System.out.println("Procedure Called"+ps);
 				//these lines do nothing but the table updates when we access the db.
 				rs = stmt.executeQuery("SELECT * from MARKETDATA");
 				rs.next();
 				rs.close();	
 				
 				
 			}
	 		catch (SQLException sqle){
	 			System.err.println("Error with members insert:\n"+sqle.toString());
	 		}
	 		finally{
	 			TableModel.refreshFromDB(stmt);
//	 			AuditTableModel.refreshFromDBAudit(stmt);
			}
		 }
			try{
				if (target.equals(exportButton)){  		
				//set cmd to write out all the table data to the csv
					String updateTemp ="SELECT * FROM MARKETDATA;";
					rs= stmt.executeQuery(updateTemp); 
					writeToFile(rs, "alldata");
					JOptionPane.showMessageDialog(new JFrame(), "Saved AllData.csv successfully.");
			
				}
//				else if (target.equals(chartButton)){  		
//					cmd = "select Record_Description, sum(value)from pm.perf group by Record_Description;";
//					rs= stmt.executeQuery(cmd); 
//					pieGraph(rs, "Network Statistics");				
//				}
//				else if (target.equals(numRecForCellButton)){ 
//					String idOfCell = cellIDTF.getText();
//					//Using the Prepared statement 			
//					ps.setString(1, idOfCell);
//					rs= ps.executeQuery();
//					//Without a Prepared Statement we could have used the following two lines of code
//					/*cmd="select count(*) from pm.perf where Cell_ID = "+idOfCell+";";	
//					rs= stmt.executeQuery(cmd);*/ 	
//					writeToFile(rs);
//
//				} 
//				else if (target.equals(recordsAfterButton)){  
//				// set cmd here 
//					String time = timeTF.getText();
//					cmd="select * from  pm.perf where Date > '"+time+"';";	
//					rs= stmt.executeQuery(cmd); 
//					writeToFile(rs);
//				}
			}
			catch(Exception e1){e1.printStackTrace();}
//		}
			if (target == clearInputFieldsButton)
			 {
				 yearlyf.setText("");
				 totalUsageRevenuef.setText("");
				 avgSMSRevenuef.setText("");
				 avgDataRevenuef.setText("");
				 sMSTrafficf.setText("");
				 dataTrafficf.setText("");
				 voiceMinutesf.setText("");
				 networkNamef.setText("");
				 	 
			 }
//			if (target == viewNumbersByType) {
//				try {
//					rs = stmt.executeQuery("select deviceType, count(*) from devices group by deviceType;");
//					pieGraph(rs, "Device Numbers By Type");
//				} catch (SQLException e1) {
//					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while querying database.\nPlease try again. ");
//				}
//			}
			if (target == marketShares) {
				try {
					rs = stmt.executeQuery("select * from PROVIDERDATA;");
					writeToFile(rs, "MarketShareData");
					JOptionPane.showMessageDialog(new JFrame(), "Saved MarketShareData.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
			if (target == vodafone) {
				try {
					rs = stmt.executeQuery("SELECT * FROM MARKETDATA WHERE NetworkName='Vodafone';");
					writeToFile(rs, "VodafoneData");
					JOptionPane.showMessageDialog(new JFrame(), "Saved VodafoneData.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
			if (target == eir) {
				try {
					rs = stmt.executeQuery("SELECT * FROM MARKETDATA WHERE NetworkName='Eir';");
					writeToFile(rs, "EirData");
					JOptionPane.showMessageDialog(new JFrame(), "Saved EirData.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
			if (target == three) {
				try {
					rs = stmt.executeQuery("SELECT * FROM MARKETDATA WHERE NetworkName='Three';");
					writeToFile(rs, "ThreeData");
					JOptionPane.showMessageDialog(new JFrame(), "Saved ThreeData.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
			if (target == tescoMobile) {
				try {
					rs = stmt.executeQuery("SELECT * FROM MARKETDATA WHERE NetworkName='Tesco Mobile';");
					writeToFile(rs, "TescoMobileData");
					JOptionPane.showMessageDialog(new JFrame(), "Saved TescoMobileData.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
			if (target == justTraffic) {
				try {
					rs = stmt.executeQuery("SELECT * FROM SMSandDataStore;");
					writeToFile(rs, "SMSandDataTraffic");
					JOptionPane.showMessageDialog(new JFrame(), "Saved SMSandDataTraffic.csv successfully.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while saving csv file.\nPlease try again.");
				}
			}
//			if (target == viewNumbersByLocation) {
//				try {
//					rs = stmt.executeQuery("select deviceLocation, count(*) from devices group by deviceLocation;");
//					pieGraph(rs, "Device Numbers By Location");
//				} catch (SQLException e1) {
//					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while querying database.\nPlease try again. ");
//				}
//			}
//			
			if (target == viewRevenueAndShares) {
				try {
					rs = stmt.executeQuery("select NetworkName, MarketShare from ProviderData group by Networkname;");
					pieGraph(rs, "Market Share");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while querying database.\nPlease try again. ");
				}
			}
			
			if (target == dataProgression) {
				try {
					rs = stmt.executeQuery("select DataTr, DataRevenue from MARKETDATA group by YearlyInt;");
					barChartGraph(rs, "Increase in Data Usage");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while querying database.\nPlease try again. ");
				}
			}
			
			if (target == smsDecline) {
				try {
					rs = stmt.executeQuery("select DataTr, SMSRevenue from MARKETDATA group by YearlyInt;");
					barChartGraph2(rs, "Decrease in SMS Usage");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Problem encountered while querying database.\nPlease try again. ");
				}
			}
			
//			if (target == auditTableButton) {
//				try {
//					String updateTemp = "select * from marketdata_audit;";
//					rs = stmt.executeQuery(updateTemp);
//
//				} catch (SQLException sqle) {
//					System.err.println("Error with members insert:\n" + sqle.toString());
//				} finally {
//					TableModel.refreshAuditTableFromDB(stmt);
//				}
//			}
			
	}
	
	public  void pieGraph(ResultSet rs, String title) {
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();

			while (rs.next()) {
				String category = rs.getString(1);
				String value = rs.getString(2);
				dataset.setValue(category+ " "+value, new Double(value));
			}
			JFreeChart chart = ChartFactory.createPieChart(
					title,  
					dataset,             
					false,              
					true,
					true
			);

			ChartFrame frame = new ChartFrame(title, chart);
			chart.setBackgroundPaint(Color.WHITE);
			frame.pack();
			frame.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void barChartGraph(ResultSet rs, String title) {
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while (rs.next()) {
				String provider = rs.getString(1);
				double value = rs.getDouble(2);
				// dataset.setValue(category+ " "+value, new Double(value));
				dataset.setValue(value, provider, "2010                2011                2012                2013                 2014                2015                 2016                 2017                  2018");
			}

			JFreeChart chart = ChartFactory.createBarChart3D(title, "Year", "Data Volume", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			ChartFrame frame = new ChartFrame(title, chart);
			chart.setBackgroundPaint(Color.WHITE);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(1000, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void lineChartGraph(ResultSet rs, String title) {
//		try {
//			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//			while (rs.next()) {
//				String country = rs.getString(1);
//				double value = rs.getDouble(2);
//				// dataset.setValue(category+ " "+value, new Double(value));
//				dataset.setValue(value, country, "Declining SMS Usage Over Time");
//			}
//
//			JFreeChart chart = ChartFactory.createLineChart(title, "Year", "Traffic in MB", dataset, PlotOrientation.VERTICAL, true, true, false);
//
//			ChartFrame frame = new ChartFrame(title, chart);
//			chart.setBackgroundPaint(Color.WHITE);
//			frame.pack();
//			frame.setVisible(true);
//			frame.setSize(1000, 500);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void barChartGraph2(ResultSet rs, String title) {
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while (rs.next()) {
				String provider = rs.getString(1);
				double value = rs.getDouble(2);
//				dataset.setValue(provider+ " "+value, new Double(value));
				dataset.setValue(value, provider, "2010                2011                2012                2013                 2014                2015                 2016                 2017                  2018");
			}

			JFreeChart chart = ChartFactory.createBarChart3D(title, "Year", "SMS Units", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			ChartFrame frame = new ChartFrame(title, chart);
			chart.setBackgroundPaint(Color.WHITE);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(1000, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}