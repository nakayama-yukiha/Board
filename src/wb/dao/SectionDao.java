package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wb.beans.Sections;
import wb.exception.SQLRuntimeException;


public class SectionDao {

	public List<Sections> getSections(Connection connection) {
//SQLに接続してDBを取得
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM Sections ");//SQL文作ってる

			ps = connection.prepareStatement(sql.toString());//SQL文をDBに送るため

			ResultSet rs = ps.executeQuery();//SQLを発行して結果を返している
			List<Sections> ret = toSectionsList(rs);//使いやすい形に直す
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Sections> toSectionsList(ResultSet rs)//beanにデータ入れている
			throws SQLException {

		List<Sections> ret = new ArrayList<Sections>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Sections Section = new Sections();

				Section.setId(id);
				Section.setName(name);


				ret.add(Section);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}

