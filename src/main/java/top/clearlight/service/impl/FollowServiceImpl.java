package top.clearlight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.clearlight.dao.FollowDao;
import top.clearlight.dto.PageDataBody;
import top.clearlight.entity.Follow;
import top.clearlight.entity.Topic;
import top.clearlight.entity.User;
import top.clearlight.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	private FollowDao followDao;
	
	/**
	 * 我关注的人
	 */
	@Override
	public PageDataBody<User> page(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = followDao.countByUid(uid);
		List<User> list = followDao.select((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 关注的用户的id
	 */
	/*@Override
	public List<Integer> followUserID(Integer uid) {
		List<Integer> fid = followDao.select(uid);
		return fid;
	}*/


	/**
	 * 关注
	 */
	@Override
	public int insert(Follow follow) {
		return followDao.insert(follow);
	}

	/**
	 * 取消关注
	 */
	@Override
	public int delete(Integer uid, Integer fid) {
		return followDao.delete(uid, fid);
	}

	/**
	 * 我关注的数量
	 */
	@Override
	public int countByUid(Integer uid) {
		return followDao.countByUid(uid);
	}

	/**
	 * 我被关注的数量
	 */
	@Override
	public int countByFid(Integer fid) {
		return followDao.countByFid(fid);
	}

	/**
	 * 我是否已关注了
	 */
	@Override
	public int isFollow(Integer uid, Integer fid) {
		return followDao.isFollow(uid, fid);
	}

	/**
	 * 关注我的人
	 */
	@Override
	public PageDataBody<User> followMe(Integer pageNumber, Integer pageSize, Integer fid) {
		int total = followDao.countByFid(fid);
		List<User> list = followDao.selectByFid((pageNumber - 1) * pageSize, pageSize, fid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 关注的人的主题
	 */
	@Override
	public PageDataBody<Topic> pageTopic(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = followDao.countTopic(uid);
		List<Topic> list = followDao.selectTopic((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

}
