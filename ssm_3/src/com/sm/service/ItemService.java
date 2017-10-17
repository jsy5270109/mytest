package com.sm.service;

import java.util.List;

import com.sm.po.Item;

public interface ItemService {

	
	public List<Item> findItemList();

	public Item findItem(Integer id);

	public void updateItem(Item item);
	
}
