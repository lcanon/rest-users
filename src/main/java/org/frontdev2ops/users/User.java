// tag::adocEntity[]
package org.frontdev2ops.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @NotNull
    public String username;

    @NotNull
    public String password;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

    public static User findByUsernameAndPassword(String username, String password) {
        return find("username = ?1 and password = ?2", username, password).firstResult();
    }
    // end::adocSkip[]
}
// end::adocEntity[]
