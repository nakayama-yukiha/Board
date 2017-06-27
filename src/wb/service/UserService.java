package wb.service;

import static wb.utils.CloseableUtil.*;
import static wb.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import wb.beans.User;
import wb.dao.UserDao;
import wb.utils.CipherUtil;

public class UserService {

	public void register(User user) {
		//ログインするため。パスワードをエンコしてビーンに入力
		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);


			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//ユーザーと紐付けたメッセージを投稿日時順にしてdaoを通して取得
	public List<User> getUserAcount() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUserAcount(connection);

			commit(connection);

			return ret;

		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			throw e;
		}finally {
			close(connection);
		}
	}
	//ユーザー情報変更、パラメータで渡されたIDを元にDBからデータ取得
	public User getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, userId);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	//変更内容を新しくBEANに入力。idが同じなためupdateされる
	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			if (StringUtils.isEmpty(user.getPassword()) == false) {

				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);

			}

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

		commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//isStopped専用のアップデートに接続
		public void isStoppedUpdate(int id, int is_stopped) {

			Connection connection = null;
			try {
				connection = getConnection();

				UserDao userDao = new UserDao();
				userDao.update(connection, id, is_stopped);

			commit(connection);
			} catch (RuntimeException e) {
				rollback(connection);
				throw e;
			} catch (Error e) {
				rollback(connection);
				throw e;
			} finally {
				close(connection);
			}
		}

	//ユーザー情報削除のためのDAO接続
	public void userDelete(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();


			UserDao userDao = new UserDao();
			userDao.userDelete(connection, userId);

		commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


	//ユーザー情報確認
	public User getUserCheck(String loginId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUserCheck(connection, loginId);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}