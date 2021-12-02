package sample.jdbc.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.DatabaseMetaData;


public class StudentExample {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/";
	   
	   private static final String url = "jdbc:mysql://localhost:3306/db_world";
	    private static final String user = "root";
	    private static final String password = "root";
	    
	    Connection con;
	    PreparedStatement prSt;
	    Statement stmt;
		
     
	   public static void main(String[] args) {

		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(url, user, password);

		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      stmt = conn.createStatement();
		      
		      DatabaseMetaData dbm = (DatabaseMetaData) conn.getMetaData();
	             ResultSet rs = dbm.getTables(null, "APP", "STUDENT", null);
	             if (!rs.next()) {
	            	 
	         	      String sql = "CREATE TABLE student " +
	                   "(sid INTEGER not NULL, " +
	                   " name VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " city VARCHAR(255), " + 	                  
	                   " PRIMARY KEY ( sid ))"; 
		      stmt.executeUpdate(sql);
	             }
		      
		      System.out.println("Created table in given database...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Have A Nice Day");
		   
		   //Instantiate the class and call the non static methods .
		   StudentExample sse = new StudentExample();
	    	
	        Scanner input = new Scanner(System.in);
	    	
	        System.out.println("=============PREPARED STATEMENT MENU=================");
	        System.out.println("1. Insert the new student Record"); 
	        System.out.println("2. Update the student Record");
	        System.out.println("3. Delete the student Record");
	        System.out.println("====================================================");
	    	System.out.println("Enter your choice from (1-3): ");
	    	
	    	int number = input.nextInt();
	    	System.out.println("You entered option" + number);
	    	
	    	switch(number)
	    	{
	    	case 1: sse.insertPSRecord();
	    	        break;
	    	
	    	case 2: sse.updatePSRecord();
	    	        break;
	    	        
	    	case 3: sse.deletePSRecord();
	    	        break;
	    	}	
	        	
			    
		}//end main

//-------------------------- Inserting The Record-----------------------------//
	public void insertPSRecord()
  {  
		String query = "insert into student(sid,name,age,city) values(?,?,?,?)";
		
	      try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, user, password);
	           
	            prSt = con.prepareStatement(query);
	            
	            // Take user input for insertion
	            Scanner input = new Scanner(System.in);	
	            
	            System.out.print("Enter the student id:  ");
	            int sid = input.nextInt();
	            input.nextLine();
	            
	            System.out.print("Enter the student name:  ");
	            String name = input.nextLine();
	            
	            
	            System.out.print("Enter the student age:  ");
	            int age = input.nextInt();
	            input.nextLine();
	            
	            System.out.print("Enter the student city:  ");
	            String city = input.nextLine();
	          
	            
	            
	            prSt.setInt(1, sid);
	            prSt.setString(2, name);
	            prSt.setInt(3, age);
	            prSt.setString(4, city);
	            
	            
	            int count = prSt.executeUpdate();
	            System.out.println(count+" records inserted"); 
	            
	            //show the number of records
	            stmt = con.createStatement();
	            
	            String query1 = "select * from student";
	            ResultSet rs =  stmt.executeQuery(query1);
	            System.out.println("Id    Name    age   city");
	            
	            while (rs.next()) {
	               int id = rs.getInt("sid");
	               String sname = rs.getString("name");
	               int sage = rs.getInt("age");
	               String scity = rs.getString("city");
	               System.out.println(id + "    " + sname+"    "+sage+"        "+scity);
	            }    
	            
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally{
	            try{
	                if(prSt != null) prSt.close();
	                if(con != null) con.close();
	            } catch(Exception ex){}
	        }
	}
	
	//-------------------------- Updating The Record-----------------------------//
	public void updatePSRecord()
  { 
		String sqlUpdate = "UPDATE student SET city = ? WHERE sid = ?";
		
		try {
          try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
          prSt = con.prepareStatement(sqlUpdate);
          
          // Take user input for insertion
          Scanner input = new Scanner(System.in);	
          
          System.out.print("Enter the student id:  ");
          int eid = input.nextInt();
          input.nextLine();
          
          System.out.print("Enter the student city:  ");
          String esal = input.nextLine();
                   
          prSt.setString(1, esal);
          prSt.setInt(2, eid);

          int rowAffected = prSt.executeUpdate();
          System.out.println(String.format("Row affected %d", rowAffected));
/*
          // update another record -reuse the prepared statement
          System.out.print("Enter the another student id:  ");
          int eid1 = input.nextInt();
          input.nextLine();
          
          System.out.print("Enter the another student city:  ");
          String esall = input.nextLine();
                  
          prSt.setString(1, esall);
          prSt.setInt(2, eid1);

          rowAffected = prSt.executeUpdate();
          System.out.println(String.format("Row affected %d", rowAffected));
*/
		}
		catch (SQLException ex) {
          System.out.println(ex.getMessage());
      }		
  }
	
	//-------------------------- Deleting The Record-----------------------------//
		public void deletePSRecord()
	    { 
			String sqlUpdate = "DELETE from student where sid=?";
			
			try {
	            try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	            prSt = con.prepareStatement(sqlUpdate);
	            
	            // Take user input for insertion
	            Scanner input = new Scanner(System.in);	
	            
	            System.out.print("Enter the student id:  ");
	            int eid = input.nextInt();
	            System.out.println();
	            
	            prSt.setInt(1, eid);
	            prSt.executeUpdate();
	            
	            System.out.println("Record deleted successfully");
			}
			catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }				
	    }
}
