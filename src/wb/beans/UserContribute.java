package wb.beans;

import java.io.Serializable;
import java.util.Date;

public class UserContribute implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id ;
	private int user_id;
	private String name;
	private int branch_id;
	private int section_id;
	private int text_id;
	private String title;
	private String text;
	private String category;
	private Date insert_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranchId() {
		return branch_id;
	}

	public void setBranchId(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getSectionId() {
		return section_id;
	}

	public void setSectionId(int section_id) {
		this.section_id = section_id;
	}


	public int getTextId() {
		return text_id;
	}

	public void setTextId(int text_id) {
		this.text_id = text_id;
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

	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}
}
