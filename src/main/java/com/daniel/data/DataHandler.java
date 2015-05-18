package com.daniel.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daniel.db.DBManager;
import com.daniel.db.RequestHandlerDB;
import com.daniel.model.RSSIModel;

@SuppressWarnings("serial")
@WebServlet(name="datahandler", urlPatterns={"/rssidata"})
public class DataHandler extends HttpServlet {

ServletOutputStream outStream;

@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		outStream= response.getOutputStream();
		response.setContentType("text/plain");
		outStream.write("Servlet works".getBytes());
		outStream.flush();
		outStream.close();
		System.out.println("Get response");
	}
	
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	try{
	outStream = response.getOutputStream();
	Map param_map = request.getParameterMap();
	if (param_map==null)
	{
		outStream.write("map null".getBytes());
		throw new ServletException("map null in" + getClass().getName());
	}
	else
	{
		RequestHandlerDB.saveRSSISignalsToDB(param_map);
	}
	}
	
	
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
