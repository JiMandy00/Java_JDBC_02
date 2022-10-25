package com.db.dao;

import com.db.domain.User;

import java.sql.*;
import java.util.Map;

public class Dao {

    private Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv(); // 환경 변수를 가져옵니다.
        Connection c; // DB와 연결합니다.

        // DB접속
        c = DriverManager.getConnection(env.get("DB_HOST"),
                env.get("DB_NAME"),
                env.get("DB_PASSWORD"));

        return c;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection c = makeConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");


        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public User findById(String id) throws SQLException {

        Connection c = makeConnection(); // 환경변수 값이 들어간 내용이 c에 들어감
        // Query문 작성
        PreparedStatement ps = c.prepareStatement("SELEC * FROM users WHERE id=?");
        ps.setString(1, id);

        // Query문 실행
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("DB_HOST"), rs.getString("DB_NAME"), rs.getString("DB_PASSWORD")); // id자리는 칼럼명

        rs.close();
        ps.close();
        c.close();


        return user;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Dao dao = new Dao();
//        dao.add();
    }
}
