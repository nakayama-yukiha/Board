package wb.service;

import static wb.utils.CloseableUtil.*;
import static wb.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wb.beans.Branches;
import wb.dao.BranchDao;

public class BranchService {

	public List<Branches> getBranches() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao BranchDao = new BranchDao();
			List<Branches> ret =  BranchDao.getBranches(connection);//DAOで引数となる

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

	public void register(Branches branch) {
		//投稿したメッセージをdao通してDBに格納する
		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			branchDao.insert(connection, branch);

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

	public void branchDelete(String branchId) {

		Connection connection = null;
		try {
			connection = getConnection();


			BranchDao branchDao = new BranchDao();
			branchDao.branchDelete(connection, branchId);

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

