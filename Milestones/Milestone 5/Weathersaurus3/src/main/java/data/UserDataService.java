package data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import beans.User;
import business.LoggingInterceptor;
import util.DatabaseException;

import java.sql.*;

@Stateless
@Local(UserDataInterface.class)
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class UserDataService implements UserDataInterface<User>
{
	Connection myConn = null;
	String connURL = "jdbc:mysql://localhost:3306/weathersaurus";
	String username = "root";
	String password = "root";
	
	public UserDataService() 
    {
    }
	
	@Override
	public List<User> findAll() {
		List<User> dbUsers = new ArrayList<User>();
		
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String sqlStatement = "SELECT * FROM users";
			Statement state = myConn.createStatement();
			ResultSet rs = state.executeQuery(sqlStatement);
			
			while(rs.next())
			{
				User user = new User();
				user.setFirstName(user.getFirstName());
				user.setLastName(user.getLastName());
				user.setEmail(user.getEmail());
				user.setGender(user.getGender());
				user.setAge(user.getAge());
				user.setState(user.getState());
				user.setUsername(user.getUsername());
				user.setPassword(user.getPassword());
				
				rs.close();				
				state.close();
				
				dbUsers.add(user);
				myConn.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		return dbUsers;
	}
	
	@Override
	public User findById(int id) {
		
		User foundUser = new User();
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = "SELECT * FROM users WHERE USERID = ?";
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				foundUser.setFirstName(rs.getString("FIRSTNAME"));
				foundUser.setLastName(rs.getString("LASTNAME"));
				foundUser.setEmail(rs.getString("EMAILADDRESS"));
				foundUser.setGender(rs.getString("GENDER"));
				foundUser.setAge(rs.getInt("AGE"));
				foundUser.setState(rs.getString("STATE"));
				foundUser.setUsername(rs.getString("USERNAME"));
				foundUser.setPassword(rs.getString("PASSWORD"));
			}
			
			rs.close();
			
			statement.close();
			
			myConn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		return foundUser;
	}
	
	@Override
	public User findBy(User user) 
	{
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = " SELECT * FROM users WHERE USERNAME = ? " ;
			
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, user.getUsername());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAILADDRESS"));
				user.setGender(rs.getString("GENDER"));
				user.setAge(rs.getInt("AGE"));
				user.setState(rs.getString("STATE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
			}
			System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " 
			+ user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
			
			rs.close();
			
			statement.close();
			
			myConn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		
		return user;
	}
	
	@Override
	public boolean create(User user) {
		boolean created = false;
		
		System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " + user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String createQuery = "INSERT INTO users (FIRSTNAME,LASTNAME,EMAILADDRESS,GENDER,AGE,STATE,USERNAME,PASSWORD) VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement p = myConn.prepareStatement(createQuery);
			
			p.setString(1, user.getFirstName());
			p.setString(2, user.getLastName());
			p.setString(3, user.getEmail());
			p.setString(4, user.getGender());
			p.setInt(5, user.getAge());
			p.setString(6, user.getState());
			p.setString(7, user.getUsername());
			p.setString(8, user.getPassword());
			
			p.executeUpdate();
			
			myConn.close();
			
			created = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DatabaseException();
		}
		return created;
	}
	
	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean find(User user) 
	{	
		System.out.println("INSIDE FIND METHOD");
		boolean found = false;
		
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = " SELECT * FROM users WHERE USERNAME = ? AND PASSWORD = ?";
			
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				found = true;
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAILADDRESS"));
				user.setGender(rs.getString("GENDER"));
				user.setAge(rs.getInt("AGE"));
				user.setState(rs.getString("STATE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				
				UserManagement loggedUser = UserManagement.getInstance();
				
				loggedUser.setUser(user);
				
			}
			System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " + user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
			
			rs.close();
			
			statement.close();
			
			myConn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException();
		}

		return found;
	}
}
