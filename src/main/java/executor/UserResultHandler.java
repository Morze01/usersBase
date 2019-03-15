package executor;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResultHandler implements ResultHandler<List<User>> {
    @Override
    public List<User> handle(ResultSet result) throws SQLException {
        List<User> resultList = new ArrayList<>();
        while (result.next()) {
            resultList.add(new User(result.getLong(1),
                                    result.getString(2),
                                    result.getString(3),
                                    result.getString(4),
                                    result.getString(5)));
        }
        return resultList;
    }
}
