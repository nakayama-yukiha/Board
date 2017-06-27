package wb.service;

import static wb.utils.CloseableUtil.*;
import static wb.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wb.beans.Comments;
import wb.beans.Contributes;
import wb.beans.UserComment;
import wb.beans.UserContribute;
import wb.dao.CommentDao;
import wb.dao.ContributeDao;
import wb.dao.UserCommentDao;
import wb.dao.UserContributeDao;

public class NewTextService {

	public void register(Contributes contribute) {
		//投稿したメッセージをdao通してDBに格納する
		Connection connection = null;
		try {
			connection = getConnection();

			ContributeDao contributeDao = new ContributeDao();
			contributeDao.insert(connection, contribute);

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

	public void register(Comments comment) {
		//投稿したコメントをdao通してDBに格納する
		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

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

	//***********＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	private static final int LIMIT_NUM = 1000;


	public List<UserContribute> getContribute(String first,
			String end, String category) {
		//ユーザーと紐付けたメッセージを投稿日時順にしてdaoを通して取得
		Connection connection = null;
		try {
			connection = getConnection();

			UserContributeDao contributeDao = new UserContributeDao();
			List<UserContribute> ret = contributeDao.getUserContributes(connection,
					first, end, category, LIMIT_NUM);

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

	public List<UserComment> getComment() {
	//ユーザーと紐付けたコメントを投稿日時順にしてdaoを通して取得
	Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao commentDao = new UserCommentDao();
			List<UserComment> ret = commentDao.getUserComment(connection,
LIMIT_NUM);

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

	public List<UserContribute> getCategory() {
		//ユーザーと紐付けたメッセージを投稿日時順にしてdaoを通して取得
		Connection connection = null;
		try {
			connection = getConnection();

			UserContributeDao contributeDao = new UserContributeDao();
			List<UserContribute> ret = contributeDao.getCategory(connection);

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

}

