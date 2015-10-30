package com.vz.tg.services.Impl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vz.tg.connectors.SolrConnector;
import com.vz.tg.services.HomeService;
@Component
public class HomeServiceImpl implements HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
	@Override
	public QueryResponse getServiceResponse(SolrQuery query) {
		logger.info("Enter Service Impl");
		SolrClient cli = SolrConnector.getConnection();
		QueryResponse response = null;
		try {
			response = cli.query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Exit Service Impl");
		return response;
	}

}
