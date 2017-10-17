package comsm.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.mapper.ItemMapper;
import com.sm.po.Item;
import com.sm.po.ItemExample;
import com.sm.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	public List<Item> findItemList() {
		ItemExample example =new ItemExample();
		
		
		return itemMapper.selectByExampleWithBLOBs(example);
	}

	public Item findItem(Integer id) {
		// TODO Auto-generated method stub
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateItem(Item item) {

		itemMapper.updateByPrimaryKeySelective(item);
		
	}

}
