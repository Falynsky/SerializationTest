import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Group implements Serializable {

    private Map<String, Person> people = new HashMap<>();
    private String name = "";
    private String room = "";

    Group(String name, String room) {
        setName(name);
        setRoom(room);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    private void setRoom(String room) {
        this.room = room;
    }

    void addPerson(String name, int age, boolean alive){
        Person person = new Person(name, age, alive);
        people.put(name, person);
    }

}
