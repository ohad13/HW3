package Domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;


public class Team {
    String id;
    String teamName;
    String fieldID;

    public Team(String id, String teamName, String fieldID) {
        this.id = id;
        this.teamName = teamName;
        this.fieldID = fieldID;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getFieldID() {
        return fieldID;
    }
}