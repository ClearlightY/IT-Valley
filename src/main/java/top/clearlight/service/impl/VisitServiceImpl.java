package top.clearlight.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.clearlight.dao.VisitDao;
import top.clearlight.dto.DMLExecution;
import top.clearlight.dto.PageDataBody;
import top.clearlight.entity.User;
import top.clearlight.entity.Visit;
import top.clearlight.enums.InsertEnum;
import top.clearlight.enums.UpdateEnum;
import top.clearlight.exception.OperationFailedException;
import top.clearlight.exception.OperationRepeaException;
import top.clearlight.exception.OperationSystemException;
import top.clearlight.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;

	/**
	 * 分页查询访问记录
	 */
	@Override
	public PageDataBody<User> page(Integer vid, Integer pageNumber, Integer pageSize) {
		int totalRow = visitDao.count(vid);
		List<User> list = visitDao.select(vid, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 添加访问记录
	 */
	@Override
	@Transactional
	public DMLExecution save(Visit visit) {
		try {
			if (visit.getUid() == visit.getVid()) {
				throw new OperationRepeaException("访问者与被访问者为同一用户！");
			}
			int isVisit = visitDao.isVisit(visit.getUid(), visit.getVid());
			if (isVisit == 0) {
				int insert = visitDao.insert(visit);
				if (insert <= 0) {
					throw new OperationFailedException("添加失败!");
				} else {
					return new DMLExecution("添加访问记录", InsertEnum.SUCCESS, visit);
				}
			} else {
				int update = visitDao.update(visit);
				if (update <= 0) {
					throw new OperationFailedException("更新失败!");
				} else {
					return new DMLExecution("更新访问记录", UpdateEnum.SUCCESS, visit);
				}
			}
		} catch (OperationRepeaException e2) {
			throw e2;
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("insert into or update visit error " + e.getMessage());
		}
	}

	@Override
	public int count(Integer vid) {
		return visitDao.count(vid);
	}

}
