package top.clearlight.service;

import top.clearlight.dto.PageDataBody;
import top.clearlight.entity.Follow;
import top.clearlight.entity.Topic;
import top.clearlight.entity.User;

import java.util.List;

public interface FollowService {

	/**
	 * 我关注的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<User> page(Integer pageNumber, Integer pageSize, Integer uid);

	/**
	 * 关注的人的id
	 */
	// List<Integer> followUserID(Integer uid);
	
	/**
	 * 关注人的主题
	 * @param pageNumber
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	PageDataBody<Topic> pageTopic(Integer pageNumber, Integer pageSize, Integer uid);
	
	/**
	 * 关注我的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<User> followMe(Integer pageNumber, Integer pageSize,Integer fid);
	
	/**
	 * 添加关注
	 * @param follow
	 * @return
	 */
	int insert(Follow follow);
	
	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int delete(Integer uid,Integer fid);
	
	/**
	 * 统计用户关注的数量
	 * @param uid
	 * @return
	 */
	int countByUid(Integer uid);
	
	/**
	 * 统计用户被关注的数量
	 * @param fid
	 * @return
	 */
	int countByFid(Integer fid);
	
	/**
	 * 判断是否已关注 0：否 1：是
	 * @param uid
	 * @param fid
	 * @return
	 */
	int isFollow(Integer uid,Integer fid);
}
