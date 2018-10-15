package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SettingsDaoImplementation implements GenericDAO<Settings> {

    List<Settings> settingsList;
    Settings settings;

    @Override
    public List<Settings> getAll() throws DAOException {
        return settingsList;
    }

    @Override
    public Settings getByIndex(int index) throws DAOException {

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            String query = "SELECT * FROM settings WHERE settingsId = " + index + ";";
            ResultSet resultSet = dbConnection.executeResultSetQuery(query);

            while (resultSet.next()) {
                int settingsId = resultSet.getInt("settingsId");
                int dailyRoundSize = resultSet.getInt("dailyRoundSize");
                int dailySessionGoal = resultSet.getInt("dailySessionGoal");
                int sound = resultSet.getInt("sound");
                int lengthShortBreak = resultSet.getInt("lengthShortBreak");
                int lengthLongBreak = resultSet.getInt("lengthLongBreak");

                settings = new Settings(settingsId, dailyRoundSize, dailySessionGoal, sound, lengthShortBreak, lengthLongBreak);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return settings;
    }

    @Override
    public boolean save(Settings settings) throws DAOException {

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            String insertQuery =
                    "INSERT INTO settings (settingsId, dailyRoundSize, dailySessionGoal,sound, lengthShortBreak, lengthLongBreak)" +
                            "VALUES (" + null + ", " + settings.getRoundSize() + ", "
                            + settings.getSessionGoal() + ", " + settings.getSound() + ", " + settings.getLengthShortBreak() + ", " + settings.getLengthLongBreak() + ");";

            dbConnection.executeUpdateQuery(insertQuery);

            return true;
        } catch (SQLException exception) {
            System.out.println(exception);
            return false;
        }
    }

    @Override
    public boolean update(int index, Settings settings) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int index, Settings settings) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteAll() throws DAOException {
        return false;
    }
}
