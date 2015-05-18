package com.daniel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.mysql.jdbc.Driver;

public class RequestHandlerDB {
	
	private static Connection connect;
	
	public static void connectDB() throws SQLException
	{
		DriverManager.registerDriver(new Driver());;
		connect = DriverManager.getConnection("jdbc:mysql://127.8.30.2:3306/webmapserveropenshift?useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8", "adminnLWnL6l", "HjcFyIfg5c8s");
	}
	
	public static void saveRSSISignalsToDB(Map map) throws SQLException
    {
		ArrayList<String> valueList = new ArrayList<String>();
		for (int i=0; i<map.size(); i++){
			valueList.add(((String[]) map.get("sql"+i))[0]);
		}
    	String saveQuery="";
    	/*byte[]mas;
    	Iterator iterator = map.entrySet().iterator();
    	mas = new byte[map.size()];
    	while(iterator.hasNext())
    	{
    		Map.Entry entry = (Map.Entry)iterator.next();
    		mas = (byte[])entry.getValue();
    		for (int i=0;i<mas.length;i++)
    		{
    			valueList.add(new String(mas));
    		}
    		
    	}*/
    	connectDB();
    	Statement st = connect.createStatement();
    	st.execute(valueList.get(0));
    	String queryExistsTable="SELECT * FROM RSSI;";
    	ResultSet set = st.executeQuery(queryExistsTable);
    	if(!set.next())
    	{
    		for(int j=1;j<valueList.size();j++)
    		{
    			saveQuery=valueList.get(j);
    			st.execute(saveQuery);
    		}
    	}
    	if(connect!=null)
    	connect.close();
    	
    	
    }
	
	
}
