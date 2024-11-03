package regular.ls3;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        InMemoryDatabase<User> userDatabase = new InMemoryDatabase<>();

        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob@example.com");

        userDatabase.save(user1);
        userDatabase.save(user2);

        System.out.println("All users: " + userDatabase.findAll());

        userDatabase.delete(1);

        System.out.println("User with ID 2: " + userDatabase.findById(2));
        System.out.println("All users after deletion: " + userDatabase.findAll());
    }
}