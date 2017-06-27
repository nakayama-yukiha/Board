package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import wb.beans.UserComment;
import wb.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComment(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int sectionId = rs.getInt("section_id");
				int commentId = rs.getInt("comment_id");
				int contributeId = rs.getInt("contribute_id");
				String comment = rs.getString("comment");
				Timestamp insertDate = rs.getTimestamp("insert_date");


				UserComment message = new UserComment();
				message.setId(id);
				message.setUserId(userId);
				message.setName(name);
				message.setBranchId(branchId);
				message.setSectionId(sectionId);
				message.setCommentId(commentId);
				message.setContributeId(contributeId);
				message.setComment(comment);
				message.setInsertDate(insertDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}

