package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Task {
    MyJDBC myJDBC = new MyJDBC("timemanagementooad");
    PreparedStatement stmt;

    private String taskName;
    private Activity activity;

    public Task(String taskName, Activity activity) {
        this.taskName = taskName;
        this.activity = activity;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task() {

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            String sql = "SELECT name FROM task";
            ResultSet resultSet = myJDBC.executeResultSetQuery(sql);
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskName(resultSet.getString("name"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void saveReadingTask(String name, int to, int from) {
        //Broken, I have no idea why :(
        try {
            String sql = "INSERT INTO task (name) VALUES (?);";
            stmt = myJDBC.connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            stmt.close();

            String sql2 = "INSERT INTO readactivity (taskId, to, from, pageProgression) VALUES (?,?,?,?);";
            stmt = myJDBC.connection.prepareStatement(sql2);
            stmt.setInt(1, selectId(name));
            stmt.setInt(2, to);
            stmt.setInt(3, from);
            stmt.setInt(4, 0);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void saveWritingTask(String name, int words) {
        try {
            String sql = "INSERT INTO task (name) VALUES (?);";
            stmt = myJDBC.connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            stmt.close();

            String sql2 = "INSERT INTO writeactivity (taskId, words, wordprogression) VALUES (?,?,?);";
            stmt = myJDBC.connection.prepareStatement(sql2);
            stmt.setInt(1, selectId(name));
            stmt.setInt(2, words);
            stmt.setInt(3, 0);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int selectId(String name) {
        int id = 0;
        try {
            String sql = "SELECT taskId FROM task WHERE name = '" + name + "';";
            ResultSet resultSet = myJDBC.executeResultSetQuery(sql);
            while (resultSet.next()) {
                id = resultSet.getInt("taskId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void deleteTask() {
//        try {
//            String sql = "DELETE FROM task"
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}