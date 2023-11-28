package edu.jpa_study.repositories;

import org.springframework.beans.factory.annotation.Value;

public class Projection {

    public interface UserSummary {

        //данный метод будет возвращать username
        String getUsername();

        // данный метод вернёт строку "username email"
        @Value("#{target.username} #{target.email}")
        String getInfo();
    }

    public static class NameOnly {
        private String firstname;

        public NameOnly(String firstname) {
            this.firstname = firstname;
        }

        public String getFirstname() {
            return firstname;
        }
    }
}
