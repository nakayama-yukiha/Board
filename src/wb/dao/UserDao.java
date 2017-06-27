package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import wb.beans.User;
import wb.exception.NoRowsUpdatedRuntimeException;
import wb.exception.SQLRuntimeException;

public class UserDao {

	//ログイン時
	public User getUser(Connection connection, String loginId,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	//ログイン情報をｂｅａｎに入れる
	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch = rs.getInt("branch_id");
				int section = rs.getInt("section_id");
				int stoppedId = rs.getInt("is_stopped");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch);
				user.setSectionId(section);
				user.setIsStopped(stoppedId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	//insertメソッドbeanのユーザーとコネクション
	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", section_id");
			sql.append(", is_stopped");//最初はみんな”1”で登録
			sql.append(") VALUES (");
			sql.append(" ?"); // loginId
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branchId
			sql.append(", ?"); // sectionId
			sql.append(", ?"); // isStopped
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getSectionId());
			ps.setInt(6, user.getIsStopped());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザー一覧取得
	public List<User> getUserAcount(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserAcountList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserAcountList(ResultSet rs)
			throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int sectionId = rs.getInt("section_id");
				int isStopped = rs.getInt("is_stopped");

				User acountDate = new User();
				acountDate.setId(id);
				acountDate.setLoginId(loginId);
				acountDate.setPassword(password);
				acountDate.setName(name);
				acountDate.setBranchId(branchId);
				acountDate.setSectionId(sectionId);
				acountDate.setIsStopped(isStopped);


				ret.add(acountDate);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserSettingList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	//idを指定してDBから取得したデータをBeanに書き込み
	private List<User> toUserSettingList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch = rs.getInt("branch_id");
				int section = rs.getInt("section_id");
				int stoppedId = rs.getInt("is_stopped");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch);
				user.setSectionId(section);
				user.setIsStopped(stoppedId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	//データ編集しDBに書き込み(settingで利用)
	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  login_id = ?");
			if (StringUtils.isEmpty(user.getPassword()) == false) {
				sql.append(", password = ?");
			}

			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", section_id = ?");
			sql.append(", is_stopped = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");


			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());

			if (StringUtils.isEmpty(user.getPassword()) == false){
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setInt(4, user.getBranchId());
				ps.setInt(5, user.getSectionId());
				ps.setInt(6, user.getIsStopped());
				ps.setInt(7, user.getId());
			} else{

				ps.setString(2, user.getName());
				ps.setInt(3, user.getBranchId());
				ps.setInt(4, user.getSectionId());
				ps.setInt(5, user.getIsStopped());
				ps.setInt(6, user.getId());
			}


			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	//ユーザー情報変更（isStopped)
	public void update(Connection connection, int id, int is_stopped) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET is_stopped = ? WHERE id = ?");


			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, is_stopped);
			ps.setInt(2, id);


			System.out.println(ps.toString());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	//ユーザー情報の削除。指定されたidのデータを削除する
	public void userDelete(Connection connection, int UserId) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, UserId);


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


	//ユーザーIDの重複チェック用。（新規登録時）
	public User getUserCheck(Connection connection, String loginId) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);

			ResultSet rs = ps.executeQuery();
			List<User> userCheckList = toUserCheckList(rs);
			if (userCheckList.isEmpty() == true) {
				return null;
			} else if (2 <= userCheckList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userCheckList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	//ログイン情報をｂｅａｎに入れる
	private List<User> toUserCheckList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch = rs.getInt("branch_id");
				int section = rs.getInt("section_id");
				int stoppedId = rs.getInt("is_stopped");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch);
				user.setSectionId(section);
				user.setIsStopped(stoppedId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}
