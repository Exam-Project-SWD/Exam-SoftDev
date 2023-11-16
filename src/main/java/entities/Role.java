package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "roles.deleteAllRows", query = "DELETE from Role r")
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_name", length = 45)
    private String rolename;

    @ManyToMany(mappedBy = "roleList", cascade = {CascadeType.PERSIST})
    private List<User> userList = new ArrayList<>();

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleName() {
        return rolename;
    }

    public void setRoleName(String rolename) {
        this.rolename = rolename;
    }
}
