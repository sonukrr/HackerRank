package com.hacker.Dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hacker.exception.HackerrankException;
import com.hacker.model.User;
import com.hacker.util.ConnectionUtil;
import com.hacker.util.EncryptDecrypt;
import com.mysql.jdbc.Blob;

public class UserDao {
	
	
	


	public int registerUser(User user) throws HackerrankException
	{
		int generatedId = 0;
		int count;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection connObj=null;
		
		String query="insert into user(name,email,password) values(?,?,?)";
		
		try {
			connObj=ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			
			EncryptDecrypt ED=new EncryptDecrypt();
			byte[] cipherByte=ED.encrypt(user.getPassword());
			String cipher=new String(cipherByte);
			
			ps=connObj.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, cipher);
			
			count=ps.executeUpdate();
			if(count>0)
			{
				rs=ps.getGeneratedKeys();
				if(rs.next())
				{
					generatedId=rs.getInt(1);
				}
			}
			connObj.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connObj.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new HackerrankException("SQL exception"+e);
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Prepared Statement"+e);
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Result Set"+e);
				}
			}
		}
		
		
		return generatedId;
		
	}
	public boolean userCheck(User user) throws HackerrankException, UnsupportedEncodingException
	{
		boolean flag = false;
		
		Connection connObj=null;
		
		String query="Select * from user where email= ? and password= ?";
		
		//String query="Select * from user where email= ? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//EncryptDecrypt ED=new EncryptDecrypt();
		//byte[] cipherByte=ED.encrypt(user.getPassword());
		
		//String cipher=new String(cipherByte);
		
		//System.out.println(cipher);
		
		try {
			connObj=ConnectionUtil.getConnection();
			ps=connObj.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new HackerrankException("SQL exception"+e);
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Prepared Statement"+e);
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Result Set"+e);
				}
			}
		}
		
		
		
		
		
		return flag;
		
	}
	public boolean userCheckRegister(User user,Connection connObj) throws HackerrankException
	{
		boolean flag = false;
		
		String query="Select * from user where email= ? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=connObj.prepareStatement(query);
			ps.setString(1, user.getEmail());
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new HackerrankException("SQL exception"+e);
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Prepared Statement"+e);
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Result Set"+e);
				}
			}
		}
		
		
		
		
		
		return flag;
		
	}
	
	
	
	public List<User> fetchAllUser() throws HackerrankException, UnsupportedEncodingException
	{
		
		Connection connObj=null;
		
		List<User> user=new ArrayList<User>();
		
		String query="Select * from user";
		
		//String query="Select * from user where email= ? ";
		PreparedStatement ps=null;
		ResultSet rs=null;

		
		try {
			connObj=ConnectionUtil.getConnection();
			ps=connObj.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int id=Integer.parseInt(rs.getString("id"));
				String name=rs.getString("name");
				String email=rs.getString("email");
				
				User u=new User(id,name,email);
				user.add(u);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new HackerrankException("SQL exception"+e);
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Prepared Statement"+e);
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Result Set"+e);
				}
			}
		}
		
		
		
		
		
		return user;
		
	}
	
	
	public User fetchUserById(int userId) throws HackerrankException, UnsupportedEncodingException
	{
		
		Connection connObj=null;
		
		User user = null;
		
		String query="Select * from user where id=?";
		
		//String query="Select * from user where email= ? ";
		PreparedStatement ps=null;
		ResultSet rs=null;

		
		try {
			connObj=ConnectionUtil.getConnection();
			ps=connObj.prepareStatement(query);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int id=Integer.parseInt(rs.getString("id"));
				String name=rs.getString("name");
				String email=rs.getString("email");
				
				user=new User(id,name,email);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new HackerrankException("SQL exception"+e);
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Prepared Statement"+e);
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new HackerrankException("Cannot close Result Set"+e);
				}
			}
		}
		
		
		
		
		
		return user;
		
	}
	
	
	
	public void registerImage(String url,int uId) throws HackerrankException
	{

		PreparedStatement ps=null;
		Connection connObj=null;

		
		try {
			connObj=ConnectionUtil.getConnection();
			ps=connObj.prepareStatement("insert into userImage(fk_user_Id,image) values(?,?)");
			ps.setInt(1,uId);  
			  
			FileInputStream fin=new FileInputStream(url);  
			ps.setBinaryStream(2,fin,fin.available());  
			int i=ps.executeUpdate();  
			System.out.println(i+" records affected");  
			          
			connObj.close();  
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		  
		
	}
	
	
	public void retriveImage() throws HackerrankException
	{

		PreparedStatement ps=null;
		Connection connObj=null;
		String src="Aritra";

		
		try {
			connObj=ConnectionUtil.getConnection();
			 ps=connObj.prepareStatement("select * from userImage where fk_user_Id=?"); 
			 ps.setInt(1, 1);
			 ResultSet rs=ps.executeQuery();  
			 if(rs.next()){//now on 1st row  
			               
			 Blob b=(Blob) rs.getBlob(2);//2 means 2nd column data  
			 byte barr[]=b.getBytes(1,(int)b.length());//1 means first image  
			               
			// FileOutputStream fout=new FileOutputStream("C:\\Users\\737002\\workspace\\LearningLab\\src\\main\\webapp\\Images\\"+user.getUserName()+".jpg");  
			 
			 
			 FileOutputStream fout=new FileOutputStream("C:\\Users\\737002\\workspace\\Hackerrank\\src\\main\\webapp\\Images\\"+src+".jpg");
			 fout.write(barr);  
			               
			 fout.close();  
			 }//end of if  
			 System.out.println("ok");  
			               
			 connObj.close();  
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		  
		
	}
	
	
	

}
