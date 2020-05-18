package top.clearlight.web.front;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.clearlight.base.BaseEntity;
import top.clearlight.dto.PageDataBody;
import top.clearlight.dto.Result;
import top.clearlight.entity.Collect;
import top.clearlight.entity.Topic;
import top.clearlight.entity.User;
import top.clearlight.service.CollectService;
import top.clearlight.service.NoticeService;
import top.clearlight.service.TopicService;

@Controller
public class CollectController extends BaseController{

	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private NoticeService rootNoticeService;
	/**
	 * 收藏话题列表
	 * @param request
	 * @param p
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/collect/topics",method = RequestMethod.GET)
	private String list(HttpServletRequest request,@RequestParam(value = "p",defaultValue = "1")Integer p) {
		User user = getUser(request);
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());//用户发布的主题的数量
		int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());//未读通知的数量
		PageDataBody<Topic> page = collectDaoService.page(p, 50, user.getUserId());
		BaseEntity baseEntity = new BaseEntity();
		request.setAttribute("baseEntity", baseEntity);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		return "user/collect";
	}
	
	/**
	 * 是否已收藏此话题
	 * @param uid
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/collect/isCollect",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> isCollect(Integer tid,HttpServletRequest request){
		User user = getUser(request);
		int collect = 0;
		if(user != null) {
			collect = collectDaoService.isCollect(user.getUserId(), tid);
		}
		if(collect == 0) {
			return new Result<>(false, "未收藏此此话题");
		}
		return new Result<Integer>(true, collect);
	}
	
	/**
	 * 收藏话题
	 * @param uid
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/collect/save",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> save(Integer tid,HttpServletRequest request){
		Collect collect = new Collect();
		collect.setUid(getUser(request).getUserId());
		collect.setTid(tid);
		collect.setCreateDate(new Date());
		int insert = collectDaoService.insert(collect);
		if(insert == 1) {
			String info = "收藏成功";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"收藏失败");
	}
	
	/**
	 * 取消收藏
	 * @param uid
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/collect/delete",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> delete(Integer tid,HttpServletRequest request){
		int delete = collectDaoService.delete(getUser(request).getUserId(), tid);
		if(delete == 1) {
			String info = "取消收藏成功";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"取消收藏失败");
	}
	
	/**
	 * 统计话题被收藏的数量
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/collect/count",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> count(Integer tid){
		int countByTid = collectDaoService.countByTid(tid);
		return new Result<Integer>(true, countByTid);
	}
}
