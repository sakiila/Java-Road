package me.bob.repository.impl;

import me.bob.entity.StudentEntity;
import me.bob.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    public StudentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Boolean add(StudentEntity student) {
        String sql = "INSERT INTO t_student(UUID, NAME) VALUES('" + student.getUuid() + "', '" + student.getName() + "')";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public Boolean update(StudentEntity student) throws SQLException {
        String sql = "UPDATE t_student SET name = ?  WHERE id = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "Cindy");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public StudentEntity locate(Long id) {
        return null;
    }

    @Override
    public List<StudentEntity> matchName(String name) {
        return null;
    }
}
