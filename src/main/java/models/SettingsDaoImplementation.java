package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nam Phan - 500769669
 */
public class SettingsDaoImplementation implements GenericDAO<Settings> {

    private List<Settings> settingsList = new ArrayList<>();
    private Settings settings;

    /**
     * @return settingsList A list that contains all the settings records in objects
     * @throws DAOException Exception thrown in case of sql problems
     */
    @Override
    public List<Settings> getAll() throws DAOException {

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            String query = "SELECT * FROM settings;";
            ResultSet resultSet = dbConnection.executeResultSetQuery(query);

            while (resultSet.next()) {
                int settingsId = resultSet.getInt("settingsId");
                int dailyRoundSize = resultSet.getInt("dailyRoundSize");
                int dailySessionGoal = resultSet.getInt("dailySessionGoal");
                int sound = resultSet.getInt("sound");
                int lengthShortBreak = resultSet.getInt("lengthShortBreak");
                int lengthLongBreak = resultSet.getInt("lengthLongBreak");
                int timerType = resultSet.getInt("timerType");

                settingsList.add(new Settings(settingsId, dailyRoundSize, dailySessionGoal, sound, lengthShortBreak, lengthLongBreak, timerType));
            }
        } catch (SQLException exception) {
            throw new DAOException(exception);
        }

        return settingsList;
    }

    /**
     * @param index The index of the settings configuration in the database
     * @return settings A settings object with all the values from the database
     * @throws DAOException Exception thrown in case of sql problems
     */
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
                int timerType = resultSet.getInt("timerType");

                settings = new Settings(settingsId, dailyRoundSize, dailySessionGoal, sound, lengthShortBreak, lengthLongBreak, timerType);
            }
        } catch (SQLException exception) {
            throw new DAOException(exception);
        }

        return settings;
    }

    /**
     * @param settings The settings object that has been saved for the first time
     * @return a boolean value. True is save successful, else an error will be printed
     * @throws DAOException Exception thrown in case of sql problems
     */
    @Override
    public boolean save(Settings settings) throws DAOException {

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            String insertQuery =
                    "INSERT INTO settings (settingsId, dailyRoundSize, dailySessionGoal,sound, lengthShortBreak, lengthLongBreak, timerType)" +
                            "VALUES (" + null + ", " + settings.getRoundSize() + ", "
                            + settings.getSessionGoal() + ", " + settings.getSound() + ", "
                            + settings.getLengthShortBreak() + ", " + settings.getLengthLongBreak() + ", "
                            + settings.getTimerType() + ");";

            dbConnection.executeUpdateQuery(insertQuery);

            return true;
        } catch (SQLException exception) {
            System.out.println(exception);
            return false;
        }
    }

    /**
     * @param index    The index of the settings configuration in the database
     * @param settings The settings object that has been updated with new values
     * @return a boolean value. True is save successful, else an error will be printed
     * @throws DAOException Exception thrown in case of sql problems
     */
    @Override
    public boolean update(int index, Settings settings) throws DAOException {

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            String insertQuery =
                    "UPDATE settings SET dailyRoundSize = "
                            + settings.getRoundSize() +
                            ", dailySessionGoal = " + settings.getSessionGoal() +
                            ",sound = " + settings.getSound() +
                            ", lengthShortBreak = " + settings.getLengthShortBreak() +
                            ", lengthLongBreak = " + settings.getLengthLongBreak() +
                            ", timerType = " + settings.getTimerType() +
                            " WHERE settingsId = " + index + ";";

            dbConnection.executeUpdateQuery(insertQuery);

            return true;
        } catch (SQLException exception) {
            System.out.println(exception);
            return false;
        }
    }

    @Override
    public boolean delete(int index, Settings settings) throws DAOException {
        return false; // Currently not used
    }

    @Override
    public boolean deleteAll() throws DAOException {
        return false; // Currently not used
    }
}
