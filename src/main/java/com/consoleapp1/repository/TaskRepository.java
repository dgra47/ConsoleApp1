package com.consoleapp1.repository;

import com.consoleapp1.model.Task;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class TaskRepository implements com.consoleapp1.repository.TaskRepositoryInterface {
    private static final String SELECT_ALL_QUERY = "SELECT * from tasks";
    private static final String SELECT_BY_ID = "SELECT * FROM tasks where id=?";
    private final Connection connection;

    public TaskRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public Task getTask(int id) {
        Task task = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                task = taskMap(resultSet);
                log.info("Get task {}", task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }


    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taskList.add(taskMap(resultSet));
            }
            return taskList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public Task addTask(Task task){
        try {
            String sql = "INSERT INTO tasks (id,name,assigned,priority,description,completed) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, task.getId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getAssigned());
            preparedStatement.setInt(4, task.getPriority());
            preparedStatement.setString(5, task.getDescription());
            preparedStatement.setBoolean(6, task.isCompleted());
            int resultSet = preparedStatement.executeUpdate();
            log.info("Inserted {} rows", resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public boolean deleteTask(int id) {
        boolean isDeleted= true;
        try {
            String sql = "DELETE FROM tasks WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int resultSet = preparedStatement.executeUpdate();
            log.info("Deleted {} rows", resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            isDeleted = false;
        }
        return isDeleted;
    }

    private Task taskMap(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("assigned"),
                resultSet.getInt("priority"),
                resultSet.getString("description"),
                resultSet.getBoolean("completed"));
    }
}
