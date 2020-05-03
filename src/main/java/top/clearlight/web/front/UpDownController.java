package top.clearlight.web.front;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.clearlight.dto.DMLExecution;
import top.clearlight.dto.Result;
import top.clearlight.entity.User;
import top.clearlight.entity.UpDown;
import top.clearlight.service.UpDownService;

/**
 * 点赞或者点踩
 * @author Clearlight
 * TODO
 */
@RestController
@RequestMapping(value = "/topic")
public class UpDownController extends BaseController{

	@Autowired
	private UpDownService upDownService;
	
	@RequestMapping(value = "/vote",method = RequestMethod.GET)
	private Result<DMLExecution> up(Integer tid,boolean vote,HttpServletRequest request){
		User user = getUser(request);
		if(user == null) {
			return new Result<>(false,"未登录");
		}
		UpDown upDown = new UpDown();
		upDown.setUid(user.getUserId());
		upDown.setTid(tid);
		upDown.setUpDown(vote);
		upDown.setCreateDate(new Date());
		upDown.setDelete(false);
		DMLExecution save = upDownService.save(upDown);
		return new Result<DMLExecution>(true,save);
	}
	
	@RequestMapping(value = "/vote/count",method = RequestMethod.GET)
	private Result<Integer> count(Integer tid,boolean vote){
		int countUpOrDown = upDownService.countUpOrDown(tid, vote?1:0);
		Integer integer = new Integer(countUpOrDown);
		return new Result<Integer>(true, integer);
	}
}
