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

	public void register(Sections section) {
		//投稿したメッセージをdao通してDBに格納する
		Connection connection = null;
		try {
			connection = getConnection();

			SectionDao sectionDao = new SectionDao();
			sectionDao.insert(connection, section);

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

	public void sectionDelete(String sectionId) {

		Connection connection = null;
		try {
			connection = getConnection();


			SectionDao sectionDao = new SectionDao();
			sectionDao.sectionDelete(connection, sectionId);

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

