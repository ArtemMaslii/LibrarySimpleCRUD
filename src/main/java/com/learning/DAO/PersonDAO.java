package com.learning.DAO;

import com.learning.models.Book;
import com.learning.models.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return this.jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper(Person.class));
    }

    public Person show(int personId) {
        return (Person)this.jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new Object[]{personId}, new BeanPropertyRowMapper(Person.class)).stream().findAny().orElse((Object)null);
    }

    public void save(Person newPerson) {
        this.jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES (?, ?)", new Object[]{newPerson.getFullName(), newPerson.getYearOfBirth()});
    }

    public void update(int id, Person updatedPerson) {
        this.jdbcTemplate.update("UPDATE Person SET fullName=?, yearOfBirth=? WHERE personId=?", new Object[]{updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id});
    }

    public void delete(int id) {
        this.jdbcTemplate.update("DELETE FROM Person WHERE personId=?", new Object[]{id});
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return this.jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new Object[]{fullName}, new BeanPropertyRowMapper(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int personId) {
        return this.jdbcTemplate.query("SELECT * FROM Book WHERE ownerId=?", new Object[]{personId}, new BeanPropertyRowMapper(Book.class));
    }
}
