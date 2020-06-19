package cn.smbms.utils;

import java.sql.*;

/**
 * 数据库连接的问题，数据的增删改查
 */
public class BaseDao {
    private Connection connection;
    private PreparedStatement psmt;
    private String url = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl";
    private String user = "scott";
    private String password = "tiger";

    public Connection getConnection() {
        try {
            //1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2.获取连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询
     *
     * @return
     */
    public ResultSet executeQuery(String sql, Object[] params) {
        ResultSet rs = null;
        try {
            this.getConnection();
            psmt = connection.prepareStatement(sql);
            //判断用户是否传递参数
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i + 1, params[i]);
                }
            }
            //执行查询
            rs = psmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * \
     * 执行增删改
     *
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql, Object[] params) {
        int row = 0;
        try {
            this.getConnection();
            psmt = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i + 1, params[i]);
                }
            }
            //执行增删改
            row = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(null, psmt, connection);
        }
        return row;
    }

    public void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
