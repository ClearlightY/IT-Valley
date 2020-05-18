package top.clearlight.web.front;

import java.util.Date;
import java.util.List;
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
import top.clearlight.entity.Follow;
import top.clearlight.entity.Topic;
import top.clearlight.entity.User;
import top.clearlight.service.CollectService;
import top.clearlight.service.FollowService;
import top.clearlight.service.NoticeService;
import top.clearlight.service.TopicService;

@Controller
public class FollowController extends BaseController {

    @Autowired
    private FollowService followService;
    @Autowired
    private CollectService collectDaoService;
    @Autowired
    private TopicService rootTopicService;
    @Autowired
    private NoticeService rootNoticeService;

    /**
     * 是否已关注
     *
     * @param fid
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow/isFollow", method = RequestMethod.GET)
    @ResponseBody
    private Result<Integer> isFollow(Integer fid, HttpServletRequest request) {
        User user = getUser(request);
        if (user == null) return new Result<>(false, "未关注");
        if (user.getUserId() == fid) {
            return new Result<>(false, "同一用户");
        }
        int follow = followService.isFollow(user.getUserId(), fid);
        if (follow == 0) {
            return new Result<>(false, "未关注");
        }
        return new Result<>(true, follow);
    }

    /**
     * 关注
     *
     * @param fid
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow/save", method = RequestMethod.POST)
    @ResponseBody
    private Result<Integer> save(Integer fid, HttpServletRequest request) {
        Follow follow = new Follow();
        // 登录用户的ID
        follow.setUid(getUser(request).getUserId());
        // 关注用户的ID
        follow.setFid(fid);
        // 关注的日期
        follow.setCreateDate(new Date());
        // 将关注的用户存到关注表中
        int insert = followService.insert(follow);
        // 添加成功的话,返回成功的标志
        if (insert == 1) {
            String info = "关注成功";
            return new Result<Integer>(true, info);
        }
        return new Result<>(false, "关注失败");
    }

    ;

    /**
     * 取消关注
     *
     * @param fid
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow/delete", method = RequestMethod.POST)
    @ResponseBody
    private Result<Integer> delete(Integer fid, HttpServletRequest request) {
        int delete = followService.delete(getUser(request).getUserId(), fid);
        if (delete == 1) {
            String info = "取消关注成功";
            return new Result<Integer>(true, info);
        }
        return new Result<>(false, "取消关注失败");
    }

    /**
     * 我关注的数量
     *
     * @param uid
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow/count/for", method = RequestMethod.GET)
    @ResponseBody
    private Result<Integer> countByUid(Integer uid, HttpServletRequest request) {
        int countByUid = followService.countByUid(uid);
        return new Result<Integer>(true, countByUid);
    }

    /**
     * 关注我的数量
     *
     * @param fid
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow/count/to", method = RequestMethod.GET)
    @ResponseBody
    private Result<Integer> countByFid(Integer fid, HttpServletRequest request) {
        int countByFid = followService.countByFid(fid);
        return new Result<Integer>(true, countByFid);
    }

    /**
     * 特别关注
     *
     * @param request
     * @param p
     * @return
     */
    @RequestMapping(value = "/follow/topics", method = RequestMethod.GET)
    private String followTopics(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") Integer p) {
        User user = getUser(request);
        if (user == null) {
            return "error-page/404.jsp";
        }

        List<User> f_user = followService.followUser(user.getUserId());
        System.out.println(f_user);
        if (f_user != null) {
            for (User user1 : f_user) {
                System.out.println("关注的用户:" + user1);
            }
        }

        int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
        int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());//用户发布的主题的数量
        int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());//未读通知的数量
        PageDataBody<Topic> pageTopic = followService.pageTopic(p, 20, user.getUserId());
        // System.out.println(pageTopic);
        BaseEntity baseEntity = new BaseEntity();
        request.setAttribute("baseEntity", baseEntity);
        request.setAttribute("countCollect", countCollect);
        request.setAttribute("countTopicByUserName", countTopicByUserName);
        request.setAttribute("notReadNotice", notReadNotice);
        request.setAttribute("user", user);
        request.setAttribute("page", pageTopic);

        request.setAttribute("follows",  f_user);
        return "user/follow_topics";
    }

}
