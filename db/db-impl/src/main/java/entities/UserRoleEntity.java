package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
@Entity
@Table(name = "user_role", schema = "public", catalog = "netcracker")
public class UserRoleEntity extends CommonEntity{
    private String name;
    private List<UserEntity> users;

    @OneToMany(mappedBy = "userRole")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
