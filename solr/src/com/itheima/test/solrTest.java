package com.itheima.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrTest {

	
	
	@Test
	public void test01() throws SolrServerException, IOException{
		
		String baseURL="http://127.0.0.1:8090/solr";
		HttpSolrServer solrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("id", "c001");
		document.addField("name", "传智播客2222");
		
		solrServer.add(document);
		solrServer.commit();
		
	}
	@Test
	public void addDocument() throws Exception {
		//和solr服务器创建连接
		//参数：solr服务器的地址
		SolrServer solrServer = new HttpSolrServer("http://localhost:8090/solr");
		//创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域
		//第一个参数：域的名称，域的名称必须是在schema.xml中定义的
		//第二个参数：域的值
		document.addField("id", "c0002");
		document.addField("title_ik", "使用solrJ添加的文档");
		document.addField("content_ik", "文档的内容");
		document.addField("product_name", "商品名称");
		//把document对象添加到索引库中
		solrServer.add(document);
		//提交修改
		solrServer.commit();
		
	}

}
