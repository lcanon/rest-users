// tag::adocEntity[]
package org.frontdev2ops.users;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Random;

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

    @Roles
    public String role;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + username + '\'' +
            ", password='" + password + '\'' +
            ", role='" + role + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocEntity[]
