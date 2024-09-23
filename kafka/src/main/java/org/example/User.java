package org.example;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("users")  // Assuming you have a "users" table in your PostgreSQL database
public class User {

    @Id
    private Long id;

    private String full_name;
    private String email;
    private Date birth_date;
    private int score;
    private String country_code;
    private String preferred_language;
    private int books_read;

    public User(String full_name, String email, Date birth_date, int score,
                String country_code, String preferred_language, int books_read) {
        this.full_name = full_name;
        this.email = email;
        this.birth_date = birth_date;
        this.score = score;
        this.country_code = country_code;
        this.preferred_language = preferred_language;
        this.books_read = books_read;
    }

    public User() {

    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPreferred_language() {
        return preferred_language;
    }

    public void setPreferred_language(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    public int getBooks_read() {
        return books_read;
    }

    public void setBooks_read(int books_read) {
        this.books_read = books_read;
    }
}
