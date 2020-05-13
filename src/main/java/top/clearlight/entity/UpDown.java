package top.clearlight.entity;

import java.util.Date;

public class UpDown {

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer uid;
	
	/**
	 * 文章ID
	 */
	private Integer tid;

	/**
	 * 文章的作者id
	 */
	private Integer t_uid;

	public Integer getT_uid() {
		return t_uid;
	}

	public void setT_uid(Integer t_uid) {
		this.t_uid = t_uid;
	}

	/**
	 * true:up false:down
	 */
	private boolean upDown;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 是否删除 true:否  false:是
	 */
	private boolean isDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public boolean isUpDown() {
		return upDown;
	}

	public void setUpDown(boolean upDown) {
		this.upDown = upDown;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "UpDown{" +
				"id=" + id +
				", uid=" + uid +
				", tid=" + tid +
				", t_uid=" + t_uid +
				", upDown=" + upDown +
				", createDate=" + createDate +
				", isDelete=" + isDelete +
				'}';
	}
}
