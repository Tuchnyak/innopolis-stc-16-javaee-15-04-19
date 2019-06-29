package ru.innopolis.stc16.tasks.hw19.entity;

import java.util.Date;
import java.util.Objects;

public class Person {
    private int id;
    private String name;
    private Date birthDate;
    private String email;
    private String phone;

    private Person() {
//        this.name = builder.name;
//        this.birthDate = builder.birthDate;
//        this.email = builder.email;
//        this.phone = builder.phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, email, phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static class Builder extends PersonBuilderTemplate {
        private String name;
        private Date birthDate;
        private String email;
        private String phone;

        @Override
        public void setName(String arg) {
            this.name = arg;
        }

        @Override
        public void setBirthDate(String arg) {
            this.birthDate = new Date(Long.parseLong(arg));
        }

        @Override
        public void setEmail(String arg) {
            this.email = arg;
        }

        @Override
        public void setPhone(String arg) {
            this.phone = arg;
        }

        public Person build() {
            Person p = new Person();
            p.name = this.name;
            p.birthDate = this.birthDate;
            p.email = this.email;
            p.phone = this.phone;
            return p;
        }
    }
}
