package top.clearlight.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import top.clearlight.entity.User;

public class CookieAndSessionUtil {

	private CookieAndSessionUtil() {}
	
	/**
	 * setCookie
	 * @param cookieName
	 * @param response
	 */
	public static void setCookie(String cookieName,String cookieValue,int maxAge, 
			String path,boolean isHttpOnly,HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		// cookie存在本地的有效时长(单位为秒); 默认为-1, 表示页面关闭cookie就失效
		cookie.setMaxAge(maxAge);
		// 设置访问该域名下某个路径时生效
		cookie.setPath(path);
		cookie.setHttpOnly(isHttpOnly);
		// 添加到response
		response.addCookie(cookie);
	}
	
	/**
	 * removeCookie
	 * @param response
	 */
	public static void removeCookie(HttpServletResponse response,String cookieName,String path,boolean isHttpOnly) {
		Cookie cookie = new Cookie(cookieName, null);
    	cookie.setMaxAge(0);
    	cookie.setPath(path);
    	cookie.setHttpOnly(isHttpOnly);
    	response.addCookie(cookie);
	}
	
	/**
	 * getCookie
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		// 获取cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * editCookie
	 * @param request
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void editCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
								  String cookieValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookie.setValue(cookieValue);
					response.addCookie(cookie);
				}
			}
		}
	}
	/**
	 * setSession
	 * @param request
	 * @param sessionName
	 * @param user
	 */
	public static void setSession (HttpServletRequest request, String sessionName, User user) {
		// getSession(): 获取session, 没有则创建
		// setAttribute(key, value): 存储数据
		request.getSession().setAttribute(sessionName, user);
	}
	
	/**
	 * removeSession
	 * @param request
	 * @param sessionName
	 */
	public static void removeSession(HttpServletRequest request,String sessionName) {
	    // 移除某个数据
		request.getSession().removeAttribute(sessionName);
	}
	
	/**
	 * getSession
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static User getSession(HttpServletRequest request,String sessionName) {
		User user = null;
		// getAttribute(key): 获取存储的某个值
		Object attribute = request.getSession().getAttribute(sessionName);
		if(attribute != null) {
			user = (User) attribute;
			return user;
		}
		return null;
	}
}
