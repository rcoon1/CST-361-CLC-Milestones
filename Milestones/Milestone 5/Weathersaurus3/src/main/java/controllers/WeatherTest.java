package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.WeatherData;
import business.GenerateWeatherData;
import java.sql.*;

/**
 * Servlet implementation class HelloWorldTest
 */
@WebServlet("/WeatherTest")
public class WeatherTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherTest() {
        super();

        
        List<WeatherData> data = new ArrayList<WeatherData>();
        GenerateWeatherData generate = new GenerateWeatherData();
        for(int i = 0; i < 7; i++)
        {
        	data = generate.generateData(data);
        }
        generate.setDays(data);
        System.lineSeparator();
        
        for(int i = 0; i < data.size(); i++)
        {
        	System.out.println("Location: " + data.get(i).getLocation() + " - Description: " + data.get(i).getDescription() + " - Temperature: " + data.get(i).getTemperature()
          + " - Humidity: " + data.get(i).getHumidity() + " - Wind Speed: " + data.get(i).getWindSpeed() + " - Weekday: " + data.get(i).getDay());
        }
        
        try
        {
        	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weathersaurus", "root", "root");
        	Statement myStmt = myConn.createStatement();
        	
        	//SAMPLE INSERT STATEMENT AND EXECUTION
        	String sql = "INSERT INTO users (username, password) VALUES('Jack', 'peanuts')";
        	PreparedStatement prep = myConn.prepareStatement(sql);
        	prep.executeUpdate();
        	
        	//SAMPLE RETRIEVAL STATEMENT AND RESULTSET
        	ResultSet rs = myStmt.executeQuery("select * from users");
        	while(rs.next())
        	{
        		System.out.println("Username: " + rs.getString("username") + " Password: " + rs.getString("password"));
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
