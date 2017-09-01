package com.itheima.service;

import com.itheima.pojo.ResultModel;

public interface ProductService {
	public ResultModel query(String queryString, String catalog_name, String price, 
			String sort, Integer page) throws Exception;
}
