// tag::adocTransactional[]
package org.frontdev2ops.users;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {

    public User persistUser(@Valid User user) {
        String passwordEncrypted = DigestUtils
            .md5Hex(user.password).toUpperCase();
        user.password = passwordEncrypted;
        user.role = "user";
        user.persist();
        return user;
    }

    @Transactional(SUPPORTS)
    public User findUser(@Valid User user) {
        String passwordEncrypted = DigestUtils
            .md5Hex(user.password).toUpperCase();
        String username = user.username;
        return User.findByUsernameAndPassword(username,passwordEncrypted);
    }


}
// end::adocTransactional[]
