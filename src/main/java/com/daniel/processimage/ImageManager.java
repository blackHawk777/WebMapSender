/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.processimage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.entity.*;


/**
 *
 * @author Admin
 */
public class ImageManager {
    
    
private ConnectionConfig.Builder connectionConfig;
private ConnectionConfig config;
ByteArrayEntity byteEntity;
HttpPost post = new HttpPost("uri");
    
    
     public byte[] readImage(Part file) throws FileNotFoundException, IOException
    {
        /*InputStream is = file.getInputStream();
        BufferedImage bufferedImage  = ImageIO.read(is);
	ByteArrayOutputStream byteMas = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteMas);
        oos.writeObject(bufferedImage);
        byteEntity = new ByteArrayEntity(byteMas.toByteArray());
        byteEntity.setContentType(MediaType.MULTIPART_FORM_DATA);
        post.setEntity(byteEntity);
        byteEntity.writeTo(byteMas);
        byteMas.flush();
        byte[] imageInBytes = byteMas.toByteArray();
        byteMas.close();*/
        InputStream is = file.getInputStream();
        BufferedImage bufferedImage  = ImageIO.read(is);
	ByteArrayOutputStream byteMas = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteMas);
        byteMas.flush();
        byte[] imageInBytes = byteMas.toByteArray();
        byteMas.close();
        
        return imageInBytes;
    }

    /**
     * @return the connectionConfig
     */
    public ConnectionConfig.Builder getConnectionConfig() {
        return connectionConfig;
    }

    /**
     * @param connectionConfig the connectionConfig to set
     */
    public void setConnectionConfig(ConnectionConfig.Builder connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    /**
     * @return the config
     */
    public ConnectionConfig getConfig() {
        return config;
    }

  
}
