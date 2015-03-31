/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.db;


import com.vano.clientserver.NavigationData;
import com.daniel.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.Part;

import com.daniel.model.CompanyData;
import com.daniel.processimage.ImageManager;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Driver;

import java.sql.ResultSetMetaData;

/**
 *
 * @author Admin
 */
public class DBManager {
    private Connection connection;
    private PreparedStatement sqlQuery;
    public Statement st;
   
    public ArrayList<String> SSID = new ArrayList<String>();
    public ArrayList<Integer[]> coordinats = new ArrayList<Integer[]>();
    public ArrayList<ArrayList<Integer>> signals = new ArrayList<ArrayList<Integer>>();
    public ArrayList<Integer> signals2 = new ArrayList<Integer>();
    int columnsCount=0;
    public ImageManager im = new ImageManager();
    private ArrayList<Table> listOfTables = new ArrayList<Table>();
    private ArrayList<CompanyData> companyDatas = new ArrayList<CompanyData>();
    
   
  
    public DBManager() throws SQLException
    {
         DriverManager.registerDriver(new Driver());
    }
    
    
    public ArrayList<Table> fillListOfTables() throws SQLException
    {
    	DatabaseMetaData dmd =(DatabaseMetaData) openConnection().getMetaData();
    	//ResultSet setOfTables = dmd.getTables("", "", "%", null);
    	ResultSet setOfTables = dmd.getTables(null, null, null, null);
    	while (setOfTables.next())
    	{
    		listOfTables.add(new Table(String.valueOf(setOfTables.getObject(3))));
    	}
    	setOfTables.close();
    	return listOfTables;
    }
    
    public ArrayList<CompanyData> fillListOfCompanyData() throws SQLException
    {
    	connection=openConnection();
    	sqlQuery=connection.prepareStatement("SELECT company_name, company_address FROM company_information;");
    	ResultSet set = sqlQuery.executeQuery();
    	while(set.next())
    	{
    		companyDatas.add(new CompanyData(String.valueOf(set.getObject(1)),String.valueOf(set.getObject(2))));
    	}
    	set.close();
		return companyDatas;
    }
    
    public boolean insertCompanyData(CompanyData data) throws SQLException
    {
    	boolean ins_flag = true;
    	try{
    		connection=openConnection();
	    	sqlQuery = connection.prepareStatement("INSERT INTO company_information (company_name,company_address,company_description) VALUES (?,?,?);");
	    	sqlQuery.setString(1, String.valueOf(data.getCompany_name()));
	    	sqlQuery.setString(2, String.valueOf(data.getCompany_address()));
	    	sqlQuery.setString(3, String.valueOf(data.getCompany_description()));
	    	sqlQuery.executeUpdate();
	    	sqlQuery.close();
    	}
    	catch(Exception e)
    	{
    		ins_flag=false;
    		e.printStackTrace();
    	}
    	
    	finally
    	{
    		connection.close();
    	}
    	return ins_flag;
    }
    
    
    public Connection openConnection() throws SQLException
    {
       // setConnection(DriverManager.getConnection("jdbc:mysql://localhost/maps", "root", "root"));
        setConnection(DriverManager.getConnection("jdbc:mysql://127.8.30.2:3306/webmapserveropenshift?useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8", "adminnLWnL6l", "HjcFyIfg5c8s"));
        return getConnection();
        
    }
    
  
    public NavigationData selectDataFromDB(NavigationData data, Part file) throws SQLException
    {
        try
        {
            st=openConnection().createStatement();
            st.execute("select * from " +data.getTableName());
            ResultSet resultSet = st.getResultSet();
            ResultSetMetaData resMeta = (ResultSetMetaData) resultSet.getMetaData();
            columnsCount = resMeta.getColumnCount();
            for(int i=1; i<=columnsCount;i++)
            {
                SSID.add(resMeta.getColumnName(i));
            }

            while(resultSet.next())
            {
                coordinats.add(new Integer[]{resultSet.getInt(2), resultSet.getInt(3)});
                for(int i=4;i<=columnsCount;i++)
                {
                    signals2.add(resultSet.getInt(i));
                }
                signals.add(signals2);
            
            }
            data.setSSID(SSID);
            data.setCoordinats(coordinats);
            data.setSignals(signals);
            data.setPlaceMap(im.readImage(file));
            data.setTableName(data.getTableName());
            
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        finally
        {
            if (openConnection()!=null)
            {
                try
                {
                    openConnection().close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
         }
        return data;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
