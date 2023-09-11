package Body;

import java.util.Random;

public class RandomNameGenerator {
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Emily", "David", "Sarah", "William", "Olivia", "James", "Sophia"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Brown", "Davis", "Jones", "Wilson", "Miller", "Moore", "Taylor", "Anderson"
    };

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
//        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        return firstName;
    }

    public static void main(String[] args) {
        String randomName = generateRandomName();
        System.out.println(randomName);
    }
}

