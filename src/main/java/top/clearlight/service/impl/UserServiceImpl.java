package top.clearlight.service.impl;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import top.clearlight.config.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.clearlight.dao.UserDao;
import top.clearlight.dto.PageDataBody;
import top.clearlight.dto.UserExecution;
import top.clearlight.entity.User;
import top.clearlight.entity.Top100;
import top.clearlight.enums.InsertUserEnum;
import top.clearlight.enums.UpdateUserEnum;
import top.clearlight.exception.OperationFailedException;
import top.clearlight.exception.OperationRepeaException;
import top.clearlight.exception.OperationSystemException;
import top.clearlight.service.CollectService;
import top.clearlight.service.NoticeService;
import top.clearlight.service.ReplyService;
import top.clearlight.service.TopicService;
import top.clearlight.service.UserService;
import top.clearlight.store.StorageService;
import top.clearlight.util.CookieAndSessionUtil;
import top.clearlight.util.JsonUtil;
import top.clearlight.util.StringUtil;
import top.clearlight.util.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao rootUserDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private RedisService redisService;

    /**
     * 根据ID查找用户
     */
    @Override
    public User findById(Integer userId) {
        return rootUserDao.selectByUserId(userId);
    }

    /**
     * 根据昵称查找用户
     */
    @Override
    public User findByName(String userName) {
        return rootUserDao.selectByUserName(userName);
    }

    /**
     * 根据email查找用户
     */
    @Override
    public User findByEmail(String email) {
        return rootUserDao.selectByEmail(email);
    }

    /**
     * 根据昵称和密码查找用户
     */
    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return rootUserDao.selectByUserNameAndPassword(userName, password);
    }

    /**
     * 根据邮箱和密码查找用户
     */
    @Override
    public User findByEmailAndPassword(String email, String password) {
        return rootUserDao.selectByEmailAndPassword(email, password);
    }

    /**
     * 积分榜用户
     */
    @Override
    public List<Top100> scores(Integer limit) {
        // 调用DAO, 查询前100名用户的信息
        return rootUserDao.selectByScore(limit);
    }

    /**
     * 分页查询所有用户，倒叙
     */
    @Override
    public PageDataBody<User> page(Integer pageNumber, Integer pageSize) {
        List<User> list = rootUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
        int totalRow = rootUserDao.countUserAll();
        return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
    }

    /**
     * 更新用户
     */
    @Transactional
    @Override
    public UserExecution updateUser(User user) {
        try {
            if (user == null) {
                throw new OperationRepeaException("用户不存在");
            } else {
                int updateUser = rootUserDao.updateUser(user);
                User rootUser = rootUserDao.selectByUserName(user.getUserName());
                if (updateUser <= 0) {
                    throw new OperationFailedException("修改失败");
                } else {
                    //更新redis
                    // ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
                    // opsForValue.set(rootUser.getThirdAccessToken(), JsonUtil.objectToJson(rootUser));
                    redisService.setString(rootUser.getThirdAccessToken(), JsonUtil.objectToJson(rootUser));
                    return new UserExecution(user.getUserName(), UpdateUserEnum.SUCCESS, rootUser);
                }
            }
        } catch (OperationRepeaException e1) {
            throw e1;
        } catch (OperationFailedException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OperationSystemException("update RootUser erroe " + e.getMessage());
        }
    }

    /**
     * 根据ID删除用户
     */
    @Override
    public void deleteUserById(Integer userId) {
        rootUserDao.deleteUserByUserId(userId);
    }

    /**
     * 根据昵称删除用户
     */
    @Override
    public void deleteUserByName(String userName) {
        rootUserDao.deleteUserByUserName(userName);
    }

    /**
     * 注册用户
     */
    @Transactional
    @Override
    public UserExecution save(User user) {
        try {
            // 调用DAO. 执行添加用户的SQL语句
            int insertUser = rootUserDao.insertUser(user);
            if (insertUser <= 0) {
                throw new OperationFailedException("注册失败");
            } else {
                // 通过注册用户的用户名,查询刚添加的用户的所有信息,保存用户信息到rootUser中
                User rootUser = rootUserDao.selectByUserName(user.getUserName());
                // redis是非关系型数据库, 以键值对形式存储. 向redis中存储用户信息
                redisService.setString(rootUser.getThirdAccessToken(), JsonUtil.objectToJson(rootUser));
                // 返回添加用户注册成功的信息
                return new UserExecution(user.getUserName(), InsertUserEnum.SUCCESS, rootUser);
            }
        } catch (OperationRepeaException e1) {
            throw e1;
        } catch (OperationFailedException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OperationSystemException("insert into RootUser error " + e.getMessage());
        }
    }

    @Override
    public UserExecution createUser(String username, String password, String email) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setScore(10);
        user.setEmail(email);
        user.setUrl(null);
        user.setThirdId(null);
        user.setReceiveMsg(false);
        user.setCreateDate(new Date());
        user.setUpdateDate(null);
        user.setIsBlock(false);
        // 作为Redis的key值,其中getUUID()获取的是唯一不重复的值.
        user.setThirdAccessToken(StringUtil.getUUID());
        user.setStatusCd("1000");
        user.setUserType("2");
        user.setAvatar("/resources/images/default-avatar.jpg");
        user.setSignature("这家伙很懒，什么都没留下");
        return save(user);
    }

    /**
     * 统计所有注册会员的数量
     */
    @Override
    public int countUserAll() {
        return rootUserDao.countUserAll();
    }

    @Transactional
    @Override
    public void updateScore(Integer score, Integer userId) {
        rootUserDao.updateScore(score, userId);
    }

    /**
     * 积分值
     */
    @Override
    public int countScore(Integer userId) {
        return rootUserDao.countScore(userId);
    }

    @Override
    public int countToday() {
        return rootUserDao.countToday();
    }

    /**
     * 更新头像
     */
    @Override
    @Transactional
    public void updateAvatar(String avatarBase64, String path, User user, HttpServletRequest request) {
        // 存储头像
        String avatarURL = storageService.store(avatarBase64, Paths.get(path));
        user.setAvatar(avatarURL);
        user.setUpdateDate(new Date());
        // 更新用户
        updateUser(user);
        // 更新话题
        topicService.updateTopicAvatar(user);
        // 重新设置 session
        CookieAndSessionUtil.removeSession(request, "user");
        CookieAndSessionUtil.setSession(request, "user", user);
    }

    @Override
    public PageDataBody<User> pageForAdmin(String username, String email, Integer pageNumber, Integer pageSize) {
        List<User> list = rootUserDao.selectAllForAdmin(username, email, (pageNumber - 1) * pageSize, pageSize);
        int totalRow = countAllForAdmin(username, email);
        return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
    }

    @Override
    public int countAllForAdmin(String username, String email) {
        return rootUserDao.countAllForAdmin(username, email);
    }

    /**
     * 更新用户，主要用于后台操作
     */
    @Override
    public void updateAdmin(User user) {
        // 删除Redis里面的缓存
        // stringRedisTemplate.delete(findById(user.getUserId()).getThirdAccessToken());
        // 如果密码不为空，则加密在保存
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        user.setUpdateDate(new Date());
        updateUser(user);
    }

    /**
     * 删除用户，主要用于后台操作
     */
    @Override
    @Transactional
    public void deleteAdmin(Integer id) {
        User user = findById(id);
        // 删除话题
        topicService.deleteByAuthor(user.getUserName());
        // 删除评论
        replyService.deleteByReplyAuthorName(user.getUserName());
        // 删除收藏
        collectService.deleteByUid(user.getUserId());
        // 删除通知
        noticeService.deleteByTargetAuthorName(user.getUserName());
        // 删除Redis里面的缓存
        stringRedisTemplate.delete(findById(user.getUserId()).getThirdAccessToken());
        // 删除用户
        deleteUserById(user.getUserId());
    }

    /**
     * 用户网站排名
     *
     * @param id
     * @return
     */
    @Override
    public int rankTotal(Integer id) {
        int rank = rootUserDao.selectRankByScore(id);
        return rank;
    }

}
