package top.clearlight.dao;

import java.util.List;

import top.clearlight.entity.Tab;

/**
 * 父板块数据接口
 */
public interface TabDao {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
