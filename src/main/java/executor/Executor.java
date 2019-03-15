package executor;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    public static void execUpdate(Connection connection, String update) throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
        connection.commit();
    }

    public static <T> T execQuery(Connection connection, String query,
                                  ResultHandler<T> handler) throws SQLException  {

        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();
        connection.commit();
        return value;

    }
//    public static <T> T execQuery(String query,
//                           ResultHandler<T> handler) {
//        try (Connection connection = (new dbConnection()).getConnection()) {
//            connection.setAutoCommit(false);
//            Statement stmt = connection.createStatement();
//            stmt.execute(query);
//            ResultSet result = stmt.getResultSet();
//            T value = handler.handle(result);
//            result.close();
//            stmt.close();
//            connection.commit();
//            return value;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
