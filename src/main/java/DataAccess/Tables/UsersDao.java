package DataAccess.Tables;

import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class UsersDao{

//    public HashMap<String, User> getAll(){
//
//        HashMap<String, User> teamsMap = new HashMap<>();
//
//        try {
//            Connection conn = DBConnector.getConnection();
//
//            String sql = "" +
//                    "SELECT username,name,password,referees.username AS refUsername,FaRepresentatives.username AS faUsername  FROM users JOIN referees ON users.username = referees.username" +
//                    " JOIN FaRepresentatives ON FaRepresentatives.username = users.username";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String username = rs.getString("username");
//                String name = rs.getString("name");
//                String password = rs.getString("password");
//                String refUsername = rs.getString("refUsername");
//                String faUsername = rs.getString("faUsername");
//
//                String maxim = "test";
//                // create user
////                User u = new User(username,name, password);
////
////                teamsMap.put(teamID, team);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return teamsMap;
//    }

    public HashMap<String, User> getAll(HashMap<String, Referee> refereeMap, HashMap<String, FArepresentative> faMap) {
        HashMap<String, User> usersMap = new HashMap<String, User>();

        for(String refID : refereeMap.keySet()){
            usersMap.put(refID, refereeMap.get(refID));
        }

        for(String faID : faMap.keySet()){
            usersMap.put(faID, faMap.get(faID));
        }
        return usersMap;
    }
//
//    @Override
//    public void save(Object o) throws SQLException {
//
//    }
//
//    @Override
//    public void update(Object o, String[] params) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
}
