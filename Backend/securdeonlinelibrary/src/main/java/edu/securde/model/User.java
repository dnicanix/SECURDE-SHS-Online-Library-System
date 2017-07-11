package edu.securde.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by Danica C. Sevilla on 7/11/2017.
 */
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private int userid;

    @Column(name = "idnum")
    private int idnum;

    @Column(name = "usertype")
    private int usertype;

    @Column(name = "firstname")
    @NotEmpty(message = "*First name is required")
    private String firstname;

    @Column(name = "lastname")
    @NotEmpty(message = "*Last name is required")
    private String lastname;

    @Column(name = "username")
    @NotEmpty(message = "*Username is required")
    private String username;

    @Column(name = "password")
    @Length(min = 8, message = "*Password must have at least 8 characters")
    @NotEmpty(message = "*Password is required")
    @org.springframework.data.annotation.Transient
    private String password;

    @Column(name = "emailaddress")
    @Email(message = "*Enter a valid email")
    @NotEmpty(message = "*Email is required")
    private String emailaddress;

    @Column(name = "birthday")
    @NotEmpty(message = "*Birthday is required")
    private String birthday;

    @Column(name = "secretquestion")
    @NotEmpty(message = "*Secret Question is required")
    private String secretquestion;

    @Column(name = "secretanswer")
    @NotEmpty(message = "*Secret Answer is required")
    private String secretanswer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSecretquestion() {
        return secretquestion;
    }

    public void setSecretquestion(String secretquestion) {
        this.secretquestion = secretquestion;
    }

    public String getSecretanswer() {
        return secretanswer;
    }

    public void setSecretanswer(String secretanswer) {
        this.secretanswer = secretanswer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}