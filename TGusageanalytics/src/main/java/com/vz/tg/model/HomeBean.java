package com.vz.tg.model;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.json.JSONObject;

public class HomeBean {
	private long resultCount;
	private long positiveCount;
	private long negativeCount;
	private long neutralCount;
	private JSONObject ageGroup;
	private JSONObject categoryData;
	private int endRecord;
	
	
	private TreeMap<String,Long> dataListRecords = new TreeMap<String,Long>();
	private JSONObject dataUsageList;
	private JSONObject appUsageList;
	
	public JSONObject getAppUsageList() {
		return appUsageList;
	}

	public void setAppUsageList(JSONObject appUsageList) {
		this.appUsageList = appUsageList;
	}

	public JSONObject getDataUsageList() {
		return dataUsageList;
	}

	public void setDataUsageList(JSONObject dataUsageList) {
		this.dataUsageList = dataUsageList;
	}

	public TreeMap<String, Long> getDataListRecords() {
		return dataListRecords;
	}

	public void setDataListRecords(TreeMap<String, Long> dataListRecords) {
		this.dataListRecords = dataListRecords;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}

	LinkedHashMap<String, String> tweetList = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getTweetList() {
		return tweetList;
	}

	public void setTweetList(LinkedHashMap<String, String> tweetList) {
		this.tweetList = tweetList;
	}

	public JSONObject getCategoryData() {
		return categoryData;
	}

	public void setCategoryData(JSONObject categoryData) {
		this.categoryData = categoryData;
	}

	public JSONObject getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(JSONObject ageGroup) {
		this.ageGroup = ageGroup;
	}

	public long getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(long positiveCount) {
		this.positiveCount = positiveCount;
	}

	public long getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(long negativeCount) {
		this.negativeCount = negativeCount;
	}

	public long getNeutralCount() {
		return neutralCount;
	}

	public void setNeutralCount(long neutralCount) {
		this.neutralCount = neutralCount;
	}

	public long getResultCount() {
		return resultCount;
	}

	public void setResultCount(long resultCount) {
		this.resultCount = resultCount;
	}
}
