package wb.dao;


import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import wb.beans.UserContribute;
import wb.exception.SQLRuntimeException;

public class UserContributeDao {

	//日時でソートして投稿を取得
	public List<UserContribute> getUserContributes(Connection connection, String first,
			String end, String category, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_contribute ");
			sql.append("WHERE insert_date BETWEEN ? AND ? ");
			if (category != null) {
				sql.append("AND category = ? ");
			}
			sql.append("ORDER BY insert_date DESC limit " + num);


			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, first);
			ps.setString(2, end);
			if (category != null) {
				ps.setString(3, category);
			}


			ResultSet rs = ps.executeQuery();
			List<UserContribute> ret = toUserContributeList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserContribute> toUserContributeList(ResultSet rs)
			throws SQLException {

		List<UserContribute> ret = new ArrayList<UserContribute>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int sectionId = rs.getInt("section_id");
				int textId = rs.getInt("text_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");


				UserContribute message = new UserContribute();
				message.setId(id);
				message.setUserId(userId);
				message.setName(name);
				message.setBranchId(branchId);
				message.setSectionId(sectionId);
				message.setTextId(textId);
				message.setTitle(title);
				message.setText(text);
				message.setCategory(category);
				message.setInsertDate(insertDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}



	//カテゴリをとる
	public List<UserContribute> getCategory(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_contribute");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserContribute> ret = toCategory(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserContribute> toCategory(ResultSet rs)
			throws SQLException {

		List<UserContribute> ret = new ArrayList<UserContribute>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int sectionId = rs.getInt("section_id");
				int textId = rs.getInt("text_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");


				UserContribute message = new UserContribute();
				message.setId(id);
				message.setUserId(userId);
				message.setName(name);
				message.setBranchId(branchId);
				message.setSectionId(sectionId);
				message.setTextId(textId);
				message.setTitle(title);
				message.setText(text);
				message.setCategory(category);
				message.setInsertDate(insertDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
