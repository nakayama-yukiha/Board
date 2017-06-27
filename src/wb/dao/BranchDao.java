package wb.dao;

import static wb.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wb.beans.Branches;
import wb.exception.SQLRuntimeException;


public class BranchDao {

	public List<Branches> getBranches(Connection connection) {
//SQLに接続してDBを取得
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM branches ");//SQL文作ってる

			ps = connection.prepareStatement(sql.toString());//SQL文をDBに送るため

			ResultSet rs = ps.executeQuery();//SQLを発行して結果を返している
			List<Branches> ret = toBranchesList(rs);//使いやすい形に直す
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Branches> toBranchesList(ResultSet rs)//beanにデータ入れている
			throws SQLException {

		List<Branches> ret = new ArrayList<Branches>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branches branch = new Branches();

				branch.setId(id);
				branch.setName(name);


				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}
