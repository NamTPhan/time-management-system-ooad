package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Task {
//       static TempDBConnection dBConnection = new TempDBConnection();
//    static Connection conn = dBConnection.connectDB();
//    static PreparedStatement stmt;
    MyJDBC myJDBC = new MyJDBC("timemanagement");

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

//    public List<Task> getAllTasks() {
//        List<Task> taskList = new ArrayList<>()
//
//        try {
//            String sql = "SELECT name FROM task";
//            ResultSet resultSet = myJDBC.executeResultSetQuery(sql);
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                taskList.add(name);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return taskList;
//    }
}