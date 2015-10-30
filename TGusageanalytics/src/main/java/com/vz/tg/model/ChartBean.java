package com.vz.tg.model;

import java.util.TreeMap;

import org.json.JSONObject;

public class ChartBean {
	private JSONObject productsJSON;
	private JSONObject smartphoneAndTablets;
	private JSONObject sentimentDataObject;
	
	private TreeMap<String,String> dataListRecords = new TreeMap<String,String>();
	private JSONObject dataUsageList;
	
	public TreeMap<String, String> getDataListRecords() {
		return dataListRecords;
	}
	public void setDataListRecords(TreeMap<String, String> dataListRecords) {
		this.dataListRecords = dataListRecords;
	}
	public JSONObject getDataUsageList() {
		return dataUsageList;
	}
	public void setDataUsageList(JSONObject dataUsageList) {
		this.dataUsageList = dataUsageList;
	}
	public JSONObject getSentimentDataObject() {
		return sentimentDataObject;
	}
	public void setSentimentDataObject(JSONObject sentimentDataObject) {
		this.sentimentDataObject = sentimentDataObject;
	}
	public JSONObject getProductsJSON() {
		return productsJSON;
	}
	public void setProductsJSON(JSONObject productsJSON) {
		this.productsJSON = productsJSON;
	}
	public JSONObject getSmartphoneAndTablets() {
		return smartphoneAndTablets;
	}
	public void setSmartphoneAndTablets(JSONObject smartphoneAndTablets) {
		this.smartphoneAndTablets = smartphoneAndTablets;
	}
}
