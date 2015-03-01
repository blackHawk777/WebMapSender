/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vano.clientserver;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NavigationData implements Serializable {
    private ArrayList<Integer[]> coordinats;
    private ArrayList<ArrayList<Integer>> signals;
    private ArrayList<String> SSID;
    private String tableName;
    private byte[] placeMap;

    /**
     * @return the coordinats
     */
    public ArrayList<Integer[]> getCoordinats() {
        return coordinats;
    }

    /**
     * @param coordinats the coordinats to set
     */
    public void setCoordinats(ArrayList<Integer[]> coordinats) {
        this.coordinats = coordinats;
    }

    /**
     * @return the signals
     */
    public ArrayList<ArrayList<Integer>> getSignals() {
        return signals;
    }

    /**
     * @param signals the signals to set
     */
    public void setSignals(ArrayList<ArrayList<Integer>> signals) {
        this.signals = signals;
    }

    /**
     * @return the SSID
     */
    public ArrayList<String> getSSID() {
        return SSID;
    }

    /**
     * @param SSID the SSID to set
     */
    public void setSSID(ArrayList<String> SSID) {
        this.SSID = SSID;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the placeMap
     */
    public byte[] getPlaceMap() {
        return placeMap;
    }

    /**
     * @param placeMap the placeMap to set
     */
    public void setPlaceMap(byte[] placeMap) {
        this.placeMap = placeMap;
    }

		
}
