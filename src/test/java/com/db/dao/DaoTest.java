package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DaoTest {

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        Dao dao = new Dao();
        String id = "11";
        dao.add(new User(id, "Mandy", "password"));
        User user = dao.findById(id);

    }

}