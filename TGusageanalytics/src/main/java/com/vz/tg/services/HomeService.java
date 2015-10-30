package com.vz.tg.services;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface HomeService {
	public QueryResponse getServiceResponse(SolrQuery query);
}
