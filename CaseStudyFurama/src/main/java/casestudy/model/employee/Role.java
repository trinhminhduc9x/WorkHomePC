package casestudy.model.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    private String role_name;


    @ManyToMany(mappedBy = "roles")
    private Set<User> users;




    public Role() {
    }

    public Role(int role_id, String role_name, Set<User> users) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.users = users;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<User> getUses() {
        return users;
    }

    public void setUses(Set<User> users) {
        this.users = users;
    }
}
