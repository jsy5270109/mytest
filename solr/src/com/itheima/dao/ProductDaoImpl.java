package com.itheima.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itheima.pojo.ProductModel;
import com.itheima.pojo.ResultModel;

@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SolrServer solrServer;

	@Override
	public ResultModel queryProduct(SolrQuery query) throws Exception {

		//查看并获取查询响应
		QueryResponse queryResponse=solrServer.query(query);
		
		//从响应中获取查询结果
		SolrDocumentList results = queryResponse.getResults();
		ResultModel resultModel = new ResultModel();
		
		List<ProductModel> productList = new ArrayList<ProductModel>();
		
		if(results!=null){
			resultModel.setRecordCount(results.getNumFound());
			
			for (SolrDocument doc : results) {
				ProductModel product = new ProductModel();
				
				product.setPid(String.valueOf(doc.get("id")));
				
				//获取高亮
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				
				if(highlighting!=null){
					
					List<String> list = highlighting.get(doc.get("id")).get("product_name");
					
					if(list!=null&&list.size()>0){
						
						product.setName(list.get(0));
						
					}else{
						
						product.setName(String.valueOf(doc.get("product_name")));
						
						
					}
					
				}else{
					
					product.setName(String.valueOf(doc.get("product_name")));
					
				}
				
				if(doc.get("product_price")!=null&&!"".equals(doc.get("product_name"))){
					
					product.setPrice(Float.valueOf(doc.get("product_price").toString()));
					
				}
				product.setCatalog_name(String.valueOf(doc.get("product_catalog_name")));
				product.setPicture(String.valueOf(doc.get("product_picture")));
				productList.add(product);
			}
			
			System.out.println(productList.size()+"------------------");
			resultModel.setProductList(productList);
			
		}
		
		return resultModel;
	}
	
	
}
