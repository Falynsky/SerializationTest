import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private int age;
    private boolean alive;

    Person(String name, int age, boolean alive) {
        setName(name);
        setAge(age);
        setAlive(alive);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public boolean isAlive() {
        return alive;
    }

    private void setAlive(boolean alive) {
        this.alive = alive;
    }
}
