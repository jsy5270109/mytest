package com.itheima.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.dao.ProductDao;
import com.itheima.pojo.ResultModel;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Integer PAGE_SIZE = 60;
	@Autowired
	private ProductDao productDao;
	
	
	public ResultModel query(String queryString, String catalog_name, String price, String sort, Integer page)
			throws Exception {
	
		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.set("df", "product_keywords");
		
		//设置查询关键字
		
		if(queryString!=null&&!"".equals(queryString)){
			
			solrQuery.setQuery(queryString);
			
			
		}else{
			solrQuery.setQuery("*:*");
			
		}
		
		//设置过滤条件按照分类名称进行过滤
				if(catalog_name != null && !"".equals(catalog_name)){
					solrQuery.addFilterQuery("product_catalog_name:" + catalog_name);
				}
				//设置过滤条件按照价格进行过滤
				if(price != null && !"".equals(price)){
					String[] split = price.split("-");
					if(split != null && split.length > 1){
						solrQuery.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
					}
				}
				
				//设置排序
				if("1".equals(sort)){
					
					solrQuery.addSort("product_price",ORDER.asc);
					
				}else{
					
					solrQuery.addSort("product_price", ORDER.desc);
					
				}
		
				
				//设置分页
				
				if(page==null){
					
					page=1;
					
				}
				Integer start=(page-1)*PAGE_SIZE;
				
				solrQuery.setStart(start);
				
				solrQuery.setRows(PAGE_SIZE);
				
				solrQuery.setHighlight(true);
				
				solrQuery.addHighlightField("product_name");
				
				solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
				
				solrQuery.setHighlightSimplePost("</span>");
				
				ResultModel resultModel = productDao.queryProduct(solrQuery);
				
				resultModel.setCurPage(Long.parseLong(page.toString()));
				System.out.println(resultModel.getRecordCount()+"-------------");
				Long pageCount=resultModel.getRecordCount()/PAGE_SIZE;
				
				if(resultModel.getRecordCount() % PAGE_SIZE > 0){
					pageCount ++;
				}
				System.out.println(pageCount+"-------------");
				resultModel.setPageCount(pageCount);
				return resultModel;
	}

}
