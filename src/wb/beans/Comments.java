package wb.beans;

import java.io.Serializable;
import java.util.Date;


public class Comments implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private int contributeId;
	private int userId;
	private String text;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContributeId() {
		return contributeId;
	}

	public void setContributeId(int contributeId) {
		this.contributeId = contributeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertdate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}