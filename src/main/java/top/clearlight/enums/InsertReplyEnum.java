package top.clearlight.enums;

/**
 * 评论的数据字典
 */
public enum InsertReplyEnum {

	SUCCESS(1, "评论成功"), NO_USER(0,"禁用状态"),REPEAT_USER(-1, "发布失败"),INNER_ERROR(-2, "系统异常");
	
	private int state;// 信息代码
	private String stateInfo;// 信息说明
	
	private InsertReplyEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	public static InsertReplyEnum stateOf(int index) {
		for(InsertReplyEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
