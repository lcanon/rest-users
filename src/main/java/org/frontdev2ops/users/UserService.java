// tag::adocTransactional[]
package org.frontdev2ops.users;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import io.quarkus.elytron.security.common.BcryptUtil;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {

    public User persistUser(@Valid User user) {
        user.password= BcryptUtil.bcryptHash(user.password);
        user.role="user";
        user.persist();
        return user;
    }


}
// end::adocTransactional[]
