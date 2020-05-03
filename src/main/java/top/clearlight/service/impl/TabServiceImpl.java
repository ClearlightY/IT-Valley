package top.clearlight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import top.clearlight.dao.TabDao;
import top.clearlight.entity.Tab;
import top.clearlight.service.TabService;

@Service
public class TabServiceImpl implements TabService{

	@Autowired
	private TabDao tabDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 查询所有板块
	 */
	@Override
	public List<Tab> selectAll() {
		return tabDao.selectAll();
	}

}
