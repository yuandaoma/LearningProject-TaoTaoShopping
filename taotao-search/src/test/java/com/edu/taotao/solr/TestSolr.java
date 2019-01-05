package com.edu.taotao.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description
 */
public class TestSolr {

	@Test
	public void testSolr() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.37.130:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", 1);
		document.addField("name", "淘淘商城");
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void testSolrCloud() throws Exception{
		String zkHost = "192.168.37.130:2181,192.168.37.130:2182,192.168.37.130:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "Solr Cloud Only need 60!");
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void testDelete() throws Exception{
		String zkHost = "192.168.37.130:2181,192.168.37.130:2182,192.168.37.130:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

}

