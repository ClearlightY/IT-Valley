package top.clearlight.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.clearlight.dto.PageDataBody;
import top.clearlight.dto.Result;
import top.clearlight.entity.Node;
import top.clearlight.entity.NodeTab;
import top.clearlight.entity.Topic;
import top.clearlight.exception.ApiAssert;
import top.clearlight.service.NodeService;
import top.clearlight.service.NodeTabService;
import top.clearlight.service.TopicService;


@Controller
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NodeTabService nodeTabService;
	
	/**
	 * 根据板块查询节点
	 * @param tabName
	 * @return
	 */
	@RequestMapping(value = "/node/tab/{tabName}",method = RequestMethod.GET)
	@ResponseBody
	private Result<List> nodeByTab(@PathVariable String tabName){
		ApiAssert.notNull(tabName, "板块不能为空");
		List<Node> list = nodeService.findAllByTab(tabName, null, null);
		return new Result<List>(true, list);
	}
	
	@RequestMapping(value = "/n/{name}",method = RequestMethod.GET)
	private String toNode(@PathVariable String name,
						  @RequestParam(value = "s", defaultValue = "all") String nodeTab,
						  @RequestParam(value = "p", defaultValue = "1") Integer p,
						  HttpServletRequest request,HttpServletResponse response) {
		Node node = nodeService.findByTitle(name);
		if(node == null) {
			throw new RuntimeException("节点不存在， 返回 > <a href='/'>主页<a/>");
		}
		List<NodeTab> nodeTabList = nodeTabService.findAll();
		PageDataBody<Topic> page = topicService.pageByNodeAndNodeTab(p, 20, nodeTab, name);
		Node parentNode = nodeService.findByTitle(node.getParentNodeCode());//父节点
		List<Node> adjacencyNode = nodeService.adjacencyNode(node);//相邻节点
		List<Node> childrenNode = nodeService.findChildrenNode(node.getNodeTitle(), null, null);//子节点
		int countTopicByNode = topicService.countTopicByNode(name);
		request.setAttribute("nodeTabList", nodeTabList);
		request.setAttribute("page", page);
		request.setAttribute("node", node);
		request.setAttribute("nodeTab", nodeTab);
		request.setAttribute("parentNode", parentNode);
		request.setAttribute("adjacencyNode", adjacencyNode);
		request.setAttribute("childrenNode", childrenNode);
		request.setAttribute("countTopicByNode", countTopicByNode);
		return "node/detail";
	}
	
	@RequestMapping(value = "/nodes")
	private String nodes(HttpServletRequest request) {
		List<Node> nodeList = nodeService.findAll(null, null);
		request.setAttribute("nodeList", nodeList);
		return "node/node";
	}
}
