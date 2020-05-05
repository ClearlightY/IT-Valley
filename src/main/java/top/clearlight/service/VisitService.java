package top.clearlight.service;

import top.clearlight.dto.DMLExecution;
import top.clearlight.dto.PageDataBody;
import top.clearlight.entity.User;
import top.clearlight.entity.Visit;

public interface VisitService {

	/**
	 * 分页查询访问记录
	 * @param vid 被访问者ID
	 * @param pageNumber 当前页
	 * @param pageSize 每页显示的数据量
	 * @return
	 */
	PageDataBody<User> page(Integer vid, Integer pageNumber, Integer pageSize);
	
	/**
	 * 添加访问记录
	 * @param visit
	 * @return
	 */
	DMLExecution save(Visit visit);
	
	/**
	 * 被访问的次数
	 * @param vid
	 * @return
	 */
	int count(Integer vid);
	
}
