package top.clearlight.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.clearlight.dto.Result;
import top.clearlight.exception.ApiException;

/**
 * 全局异常处理
 * @author miansen.wang
 * @date 2018年10月31日 下午4:03:45
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {
	
	private HttpStatus getStatus(HttpServletRequest request) {
	    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    if (statusCode == null) {
	      return HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return HttpStatus.valueOf(statusCode);
	  }
	
	/**
	 * 错误页面统一处理
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
	    e.printStackTrace();
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e.getMessage());
	    mav.addObject("errorCode", getStatus(request));
	    mav.setViewName("error/error");
	    return mav;
	  }
	
	/**
	 * 接口错误统一处理
	 * @param e
	 * @return
	 * @throws ApiException
	 */
	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public Result<String> jsonErrorHandler(ApiException e) throws ApiException{
		return new Result<>(false, e.getMessage());
	}
}
