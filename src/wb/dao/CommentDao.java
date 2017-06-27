package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import wb.beans.Comments;
import wb.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comments comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("id");
			sql.append(",contribute_id");
			sql.append(",user_id");
			sql.append(",text");
			sql.append(",insert_date");
			sql.append(",update_date");
			sql.append(")VALUES(");
			sql.append("?");//id
			sql.append(",?");//contribute_id
			sql.append(",?");//user_id
			sql.append(",?");//text
			sql.append(", CURRENT_TIMESTAMP");//insert_date
			sql.append(", CURRENT_TIMESTAMP");//update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getId());
			ps.setInt(2, comment.getContributeId());
			ps.setInt(3, comment.getUserId());
			ps.setString(4, comment.getText());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void commentDelete(Connection connection, int commentId) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM comments WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, commentId);


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}

