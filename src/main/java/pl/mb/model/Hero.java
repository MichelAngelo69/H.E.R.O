package pl.mb.model;

import pl.mb.dao.HeroDao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String heroHumanName;
    private String heroHumanSurname;
    private String heroFullName;
    private Long phoneNumber;
    private String email;
    private String specialPower;


    public Hero() {
    }

    public Hero(String heroHumanName, String heroHumanSurname, String heroFullName, Long phoneNumber, String email, String specialPower) {
        this.heroHumanName = heroHumanName;
        this.heroHumanSurname = heroHumanSurname;
        this.heroFullName = heroFullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialPower = specialPower;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(String specialPower) {
        this.specialPower = specialPower;
    }

    public String getHeroHumanName() {
        return heroHumanName;
    }

    public void setHeroHumanName(String heroHumanName) {
        this.heroHumanName = heroHumanName;
    }

    public String getHeroHumanSurname() {
        return heroHumanSurname;
    }

    public void setHeroHumanSurname(String heroHumanSurname) {
        this.heroHumanSurname = heroHumanSurname;
    }

    public String getHeroFullName() {
        return heroFullName;
    }

    public void setHeroFullName(String heroFullName) {
        this.heroFullName = heroFullName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
