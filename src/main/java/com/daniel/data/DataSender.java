package com.daniel.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.vano.clientserver.NavigationData;

public class DataSender {

	private static final String URI = "http://wifinavigatorapp-municipalpayment.rhcloud.com/WiFiNavigatorSite/downloadPlan";
	private static final int NetworkConnectionTimeout_ms = 500000;

	
	 public void sendNavigationData(NavigationData nd) throws IOException{
		    HttpParams params = new BasicHttpParams();    
		    HttpConnectionParams.setStaleCheckingEnabled(params, false);
		    HttpConnectionParams.setConnectionTimeout(params, NetworkConnectionTimeout_ms);
		    HttpConnectionParams.setSoTimeout(params, NetworkConnectionTimeout_ms);
		    DefaultHttpClient httpClient = new DefaultHttpClient(params);

	    // create post method
		    HttpPost postMethod = new HttpPost(URI);

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
}
