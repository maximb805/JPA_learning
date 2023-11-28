package edu.jpa_study.repositories;

import edu.jpa_study.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> findByUsernameOrEmail(String username, String email);
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByLevelOrderByUsernameDesc(int level);
    List<User> findByLevelGreaterThanEqual(int level);
    List<User> findByUsernameContaining(String text);
    List<User> findByUsernameLike(String text);
    List<User> findByUsernameStartingWith(String start);
    List<User> findByUsernameEndingWith(String end);
    List<User> findByActive(boolean active);
    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);
    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

    @Query("SELECT u FROM User u WHERE u.level = :level AND u.active = :active")
    List<User> findByLevelAndActive(@Param("level") int level, @Param("active")boolean active);

    @Query("SELECT COUNT(u) FROM User u WHERE u.active = ?1")
    int findCountOfUsersByActive(boolean active);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %?1%")
    List<User> findAllUsersWithNameLike(String text);

    // nativeQuery = true обозначает, что используем SQL, а не JPQL
    @Query(value = "SELECT COUNT(*) FROM users WHERE active = ?1", nativeQuery = true)
    int findNumberOfUsersByActive(boolean active);

    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

    List<Projection.NameOnly> findByEmail(String username);

    <T> List<T> findByEmail(String email, Class<T> type);

    // Modifying Queries
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.level = ?2 WHERE u.level = ?1")
    int updateLevel(int oldLevel, int newLevel);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.level = ?2 WHERE u.username = ?1")
    int updateLevelByUsername(String username, int newLevel);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.active = ?2 WHERE u.username = ?1")
    int updateActiveByUsername(String username, boolean active);

    @Transactional
    int deleteByLevel(int level);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.level = ?1")
    int deleteBulkByLevel(int level);
}
