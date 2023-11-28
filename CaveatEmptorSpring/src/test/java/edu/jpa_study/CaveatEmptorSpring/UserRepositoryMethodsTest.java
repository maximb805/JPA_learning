package edu.jpa_study.CaveatEmptorSpring;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import edu.jpa_study.model.User;
import edu.jpa_study.repositories.Projection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryMethodsTest extends CaveatEmptorSpringApplicationTests {
    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(11, users.size());
        displayData(users);
    }

    @Test
    void testFindUser() {
        User user = userRepository.findByUsername("maximb805");
        assertEquals("Maxim", user.getFirstname());
        displayData(user);
    }

    @Test
    void testFindAllByOrderByUsernameAsc() {
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        assertEquals(11, users.size());
        displayData(users);
    }

    @Test
    void testFindByRegistrationDateBetween() {
        List<User> users = userRepository.findByRegistrationDateBetween(LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1));
        assertEquals(2, users.size());
        displayData(users);
    }

    @Test
    void queriesTest() {
        List<User> users1 = userRepository.findAllUsersWithNameLike("a");
        List<User> users2 = userRepository.findByLevelAndActive(0, true);
        int count1 = userRepository.findCountOfUsersByActive(false);
        int count2 = userRepository.findNumberOfUsersByActive(true);

        displayData(users1);
        System.out.println();
        displayData(users2);

        assertAll(
                () -> assertEquals(7, users1.size()),
                () -> assertEquals(1, users2.size()),
                () -> assertEquals(4, count1),
                () -> assertEquals(7, count2)
        );

    }

    @Test
    void projectionTest() {
        List<Projection.NameOnly> users1 = userRepository.findByEmail("maximb805@gmail.com");
        List<Projection.UserSummary> users2 = userRepository.findByRegistrationDateAfter(LocalDate.of(2021, 5, 1));

        System.out.println(users1.get(0).getFirstname());
        users2.forEach(u -> System.out.println(u.getInfo()));

        assertAll(
                () -> assertEquals("Maxim", users1.get(0).getFirstname()),
                () -> assertEquals(4, users2.size())
        );
    }

    @Test
    void testModifyLevel() {
        int i = userRepository.updateLevel(0, 3);
        int j = userRepository.updateLevelByUsername("sanya2005", 5);
        int k = userRepository.updateLevelByUsername("lubagal228", 6);
        assertAll(
                () -> assertEquals(1, i),
                () -> assertEquals(1, j),
                () -> assertEquals(1, k)
        );
        if (i == j && j == k && k == 1) {
            List<User> users = userRepository.findAll();
            User petr = users.stream().filter(u -> u.getUsername().equals("petr1987")).toList().get(0);
            User luba = users.stream().filter(u -> u.getUsername().equals("lubagal228")).toList().get(0);
            User sanya = users.stream().filter(u -> u.getUsername().equals("sanya2005")).toList().get(0);
            assertAll(
                    () -> assertEquals(luba.getLevel(), 6),
                    () -> assertEquals(sanya.getLevel(), 5),
                    () -> assertEquals(petr.getLevel(), 3)
            );
            displayData(users);
        }
    }

    @Test
    void deleteTest() {
        int i = userRepository.deleteByLevel(1);
        int i1 = userRepository.deleteBulkByLevel(5);
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        displayData(users);
        assertAll(
                () -> assertEquals(i, 2),
                () -> assertEquals(i1, 2),
                () -> assertEquals(users.size(), 7)
        );
    }

    void displayData(List<User> userList) {
        System.out.println("   username    | firstname  |  lastname  |          email           | regis. date | level | active ");
        System.out.println("---------------+------------+------------+--------------------------+-------------+-------+--------");
        for (User user : userList) {
            System.out.printf(" %-14s| %-11s| %-11s| %-25s|%12s |%6s | %s \n",
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getRegistrationDate(),
                    user.getLevel(),
                    user.isActive()
            );
        }
    }

    void displayData(User user) {
        System.out.println("   username    | firstname  |  lastname  |          email           | regis. date | level | active");
        System.out.println("_______________+____________+____________+__________________________+_____________+_______+_______");
        System.out.printf(" %-14s| %-11s| %-11s| %-25s|%12s |%6s | %s \n",
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRegistrationDate(),
                user.getLevel(),
                user.isActive()
        );
    }
}
