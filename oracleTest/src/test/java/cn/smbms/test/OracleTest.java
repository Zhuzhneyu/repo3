package cn.smbms.test;

import cn.smbms.utils.BaseDao;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleTest {

    /**
     * 查询
     */
    @Test
    public void test1() {
        BaseDao dao = new BaseDao();
//        String sql = "select * from emp";
        String sql = "select * from emp where ename like ?";
        Object[] params = {"%S%"};
        ResultSet rs = dao.executeQuery(sql, params);
        try {
            while (rs.next()) {
                System.out.println("姓名：" + rs.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close(rs, null, null);
        }
    }

    @Test
    public void test2() {
        BaseDao dao = new BaseDao();
        String sql = "insert into emp(empno,ename) values(?,?)";
        Object[] params = {"9000", "马云"};

        int count = 0;
        try {
            count = dao.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }

}
