package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import wb.beans.Contributes;
import wb.exception.SQLRuntimeException;

public class ContributeDao {

	public void insert(Connection connection, Contributes contribute) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO contributes ( ");
			sql.append("user_id");
			sql.append(",title");
			sql.append(",category");
			sql.append(",text");
			sql.append(",insert_date");
			sql.append(",update_date");
			sql.append(")VALUES(");
			sql.append("?");//user_id
			sql.append(",?");//title
			sql.append(",?");//category
			sql.append(",?");//text
			sql.append(", CURRENT_TIMESTAMP");//insert_date
			sql.append(", CURRENT_TIMESTAMP");//update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, contribute.getUserId());
			ps.setString(2, contribute.getTitle());
			ps.setString(3, contribute.getCategory());
			ps.setString(4, contribute.getText());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//投稿削除
	public void contributeDelete(Connection connection, int contributeId) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM contributes WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, contributeId);


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}

