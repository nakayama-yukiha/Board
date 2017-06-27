package wb.service;

import static wb.utils.CloseableUtil.*;
import static wb.utils.DBUtil.*;

import java.sql.Connection;

import wb.dao.CommentDao;
import wb.dao.ContributeDao;

public class DeleteService {

	//コメント消す
	public void commentDelete(int commentId) {

		Connection connection = null;
		try {
			connection = getConnection();


			CommentDao commentDao = new CommentDao();
			commentDao.commentDelete(connection, commentId);

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

	//投稿消す
	public void contributeDelete(int contributeId) {

		Connection connection = null;
		try {
			connection = getConnection();


			ContributeDao contributeDao = new ContributeDao();
			contributeDao.contributeDelete(connection, contributeId);

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

}
