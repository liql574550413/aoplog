package li.ql.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author liql
 * @date 2021/4/12
 */

public interface ActionService {
    void  actionOne(String username, String password);

    void  actionTwo(String username2, String password2);
}
