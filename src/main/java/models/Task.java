package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    MyJDBC myJDBC = new MyJDBC("timemanagementooad");

    private String taskName;
    private Activity activity;

    public Task(String taskName, Activity activity) {
        this.taskName = taskName;
        this.activity = activity;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ArrayList<String> getAllTasks() {
        ArrayList<String> taskList = new ArrayList<>();

        try {
            String sql = "SELECT name FROM task";
            ResultSet resultSet = myJDBC.executeResultSetQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                taskList.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public String getAllTasksPartTwo() {
        return myJDBC.executeStringListQuery("SELECT name FROM task");
    }
}