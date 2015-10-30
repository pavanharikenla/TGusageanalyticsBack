package com.vz.tg.model;

import org.json.JSONObject;

public class ChartBean {
	private JSONObject productsJSON;
	private JSONObject smartphoneAndTablets;
	private JSONObject sentimentDataObject;
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
