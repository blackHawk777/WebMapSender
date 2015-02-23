/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.mbeans;

import com.vano.clientserver.NavigationData;
import com.daniel.db.DBManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import com.daniel.processimage.ImageManager;
import java.io.ByteArrayOutputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.*;




/**
 *
 * @author Admin
 */
@ManagedBean(name = "mapBean")
@RequestScoped
public class MapsManagedBean implements Serializable {

    
    private NavigationData naviData = new NavigationData();
   
    private Part file;
    ImageManager im = new ImageManager();
    DBManager db;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    private String errorString;
    private boolean checkForConnection=false;
    public static final String WiFiNavigatorUri = "http://wifinavigatorapp-municipalpayment.rhcloud.com/WiFiNavigatorSite/downloadPlan";
    int NetworkConnectionTimeout_ms = 500000;
    
   
    
    public void serializeObjectNavigationData() throws IOException, SQLException
    {
        db =new DBManager();
        /*clientSoc = new ClientSocket();
        clientSoc.connectToServer();
        */
                  
            try
            {
           // oos= new ObjectOutputStream(clientSoc.getClientSocket().getOutputStream());
           // ois = new ObjectInputStream(clientSoc.getClientSocket().getInputStream());
            NavigationData nd = db.selectDataFromDB(naviData, getFile());
            sendNavigationData(nd);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                oos.close();
            }
        
       
        
    }
    
    private void sendNavigationData(NavigationData nd) throws IOException{
    HttpParams params = new BasicHttpParams();    
    HttpConnectionParams.setStaleCheckingEnabled(params, false);
    HttpConnectionParams.setConnectionTimeout(params, NetworkConnectionTimeout_ms);
    HttpConnectionParams.setSoTimeout(params, NetworkConnectionTimeout_ms);
    DefaultHttpClient httpClient = new DefaultHttpClient(params);

    // create post method
    HttpPost postMethod = new HttpPost(WiFiNavigatorUri);

    // create request entity
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(nd);
    ByteArrayEntity req_entity = new ByteArrayEntity(baos.toByteArray());
    req_entity.setContentType("application/octet-stream");

    // associating entity with method
    postMethod.setEntity(req_entity);
    httpClient.execute(postMethod);
    }
    

    
    public String uploadData() throws IOException, SQLException, SocketException, SocketTimeoutException
    {
    //   if((getFile()!=null)&(naviData!=null))
    //   {
           try
           {
               serializeObjectNavigationData();
           }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new NullPointerException();
            }
    //   }
       return "result";
    }
    

    
    

    /**
     * @return the naviData
     */
    public NavigationData getNaviData() {
        return naviData;
    }

    /**
     * @param naviData the naviData to set
     */
    public void setNaviData(NavigationData naviData) {
        this.naviData = naviData;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the errorString
     */
    public String getErrorString() {
        return errorString;
    }

    /**
     * @param errorString the errorString to set
     */
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    /**
     * @return the checkForConnection
     */
    public boolean isCheckForConnection() {
        return checkForConnection;
    }

    /**
     * @param checkForConnection the checkForConnection to set
     */
    public void setCheckForConnection(boolean checkForConnection) {
        this.checkForConnection = checkForConnection;
    }

   

   

    
    
}
