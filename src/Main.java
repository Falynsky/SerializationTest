import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;


public class Main {

    private static Map<String, Group> groups = new HashMap<>();
    private final static Path groupsFolderPath = Paths.get("data/groups");
    private static List<String> results = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        getFileListFromFolder();

        results.forEach((n) -> {
            try {
                getGroup(n);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        int newGroup = 0;
        do {
            System.out.println("New group? 1. Yes 2. No");
            newGroup = in.nextInt();
        }
        while (newGroup != 1 && newGroup != 0);
        if (newGroup == 1) {
            in.nextLine();
            System.out.println("What's the name of the new group? : ");
            String name = in.nextLine();
            System.out.println("What's the room of the new group? : ");
            String room = in.nextLine();
            addGroup(name, room);
        }

        for (Map.Entry<String, Group> p : groups.entrySet()
        ) {
            System.out.println("Name: " + p.getValue().getName() + " Room : " + p.getValue().getRoom());
        }

    }

    private static void addGroup(String name, String room) throws IOException {
        Group group = new Group(name, room);
        groups.put(name, group);
        saveGroup(group);
    }

/*    static void addPerson(String groupName, String name, int age, boolean alive) {
        groups.get(groupName).addPerson(name, age, alive);
    }*/

    private static void saveGroup(Group group) throws IOException {
        File folder = new File(String.valueOf(groupsFolderPath));
        if (!Files.exists(groupsFolderPath)) folder.mkdirs();

        final String fileName = group.getName() + ".json";
        final Path filePath = Paths.get(groupsFolderPath + "/" + fileName);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(String.valueOf(filePath)));
        out.writeObject(group);
    }

    private static void getFileListFromFolder() {
        File[] files = new File(String.valueOf(groupsFolderPath)).listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
    }

    private static void getGroup(String n) throws IOException, ClassNotFoundException {
        Path filePath = Paths.get(groupsFolderPath + "/" + n);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(String.valueOf(filePath)));
        Group groupp = (Group) in.readObject();
        addGroup(groupp.getName(), groupp.getRoom());
    }

/*    static void getGroup(Group group) throws IOException, ClassNotFoundException {
        final String fileName = group.getName() + ".json";
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Group groupp = (Group) in.readObject();

            System.out.println("TEST: " + groupp.getName());
        }
    }*/
}
