// tag::adocEntity[]
package org.frontdev2ops.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@UserDefinition
public class User extends PanacheEntity {

    @Username
    @NotNull
    public String username;

    @Password
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
