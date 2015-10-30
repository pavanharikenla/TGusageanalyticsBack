package com.vz.tg.connectors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;


public class SolrConnector {
	public static SolrClient getConnection(){
		String url = "http://ec2-52-10-143-10.us-west-2.compute.amazonaws.com:8080/solr/core0";
		SolrClient solr = new HttpSolrClient(url);
		return solr;
	}
}
