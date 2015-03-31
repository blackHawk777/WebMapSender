package com.daniel.model;

import java.io.Serializable;

public class CompanyData implements Serializable {

	private String company_name;
	private String company_address;
	private String company_description;
	
	public CompanyData()
	{
		
	}
	
	public CompanyData(String company_name, String company_address, String company_description)
	{
		this.company_name=company_name;
		this.company_address=company_address;
		this.company_description=company_description;
		
	}
	
	
	public CompanyData(String company_name, String company_address)
	{
		this.company_name=company_name;
		this.company_address=company_address;
	}
	
	
	
	public String getCompany_description() {
		return company_description;
	}
	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	
}
