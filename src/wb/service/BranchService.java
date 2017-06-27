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

}

