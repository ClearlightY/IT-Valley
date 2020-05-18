package top.clearlight.web.front;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import top.clearlight.config.SiteConfig;
import top.clearlight.config.service.RedisService;
import top.clearlight.entity.User;
import top.clearlight.service.CollectService;
import top.clearlight.service.NoticeService;
import top.clearlight.service.ReplyService;
import top.clearlight.service.NodeTabService;
import top.clearlight.service.TopicService;
import top.clearlight.service.UserService;
import top.clearlight.util.Base64Util;
import top.clearlight.util.CookieAndSessionUtil;
import top.clearlight.util.JsonUtil;

@Controller
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService rootUserService;
    @Autowired
    private TopicService rootTopicService;
    @Autowired
    private NodeTabService rootSectionService;
    @Autowired
    private NoticeService rootNoticeService;
    @Autowired
    private ReplyService rootReplyService;
    @Autowired
    private CollectService collectDaoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private RedisService redisService;

    /**
     * 获取登录用户的信息，先从 Redis 里面取，如果取不到，再从 seesion 里面取
     *
     * @param request
     * @return
     */
    public User getUser(HttpServletRequest request) {

        String token = CookieAndSessionUtil.getCookie(request, siteConfig.getCookieConfig().getName());
        try {
            if (token != null) {
                token = Base64Util.decode(token);
                String redisUser = redisService.getString(token);
                if (redisUser != null) {
                    return JsonUtil.jsonToObject(redisUser, User.class);
                } else {
                    return CookieAndSessionUtil.getSession(request, "user");
                }
            } else {
                return CookieAndSessionUtil.getSession(request, "user");
            }
        } catch (Exception e) {
            System.out.println("Redis 未开启");
            e.printStackTrace();
            return null;
        }
    }

    public String isLogin(HttpServletRequest request, String errorPage, String suesscePage) {
        User user = getUser(request);
        if (user == null) {
            return errorPage;
        }
        return suesscePage;
    }

    /**
     * 未读通知的数量
     *
     * @param request
     * @return
     */
    public int getNotReadNotice(HttpServletRequest request) {
        int notReadNotice = 0;
        notReadNotice = rootNoticeService.countNotReadNotice(getUser(request).getUserName());
        return notReadNotice;
    }

    /**
     * 发布的主题的数量
     *
     * @param request
     * @return
     */
    public int getCountTopicByUserName(HttpServletRequest request) {
        int countTopicByUserName = 0;
        countTopicByUserName = rootTopicService.countByUserName(getUser(request).getUserName());
        return countTopicByUserName;
    }

    /**
     * 收藏话题的数量
     *
     * @param request
     * @return
     */
    public int getCountCollect(HttpServletRequest request) {
        int countCollect = 0;
        collectDaoService.count(getUser(request).getUserId());
        return countCollect;
    }
}
