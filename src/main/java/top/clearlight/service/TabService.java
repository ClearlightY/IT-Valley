package top.clearlight.service;

import java.util.List;

import top.clearlight.entity.Tab;

public interface TabService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
