package wb.service;

import static wb.utils.CloseableUtil.*;
import static wb.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wb.beans.Sections;
import wb.dao.SectionDao;

public class SectionService {

	public List<Sections> getSections() {

		Connection connection = null;
		try {
			connection = getConnection();

			SectionDao SectionDao = new SectionDao();
			List<Sections> ret =  SectionDao.getSections(connection);//DAOで引数となる

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

