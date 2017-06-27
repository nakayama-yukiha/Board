package wb.beans;

import java.io.Serializable;
import java.util.Date;


public class Contributes implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private String title;
	private String text;
	private String category;
	private Date insert_date;
	private Date update_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getInsertDate() {
		return insert_date;
	}

	public void setInsertdate(Date insert_date) {
		this.insert_date = insert_date;
	}

	public Date getUpdateDate() {
		return update_date;
	}

	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}

}



