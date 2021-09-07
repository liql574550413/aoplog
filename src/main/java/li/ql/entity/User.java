package li.ql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liql
 * @date 2021/4/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String uname;
    private String pwd;
    private String perms;  //权限 存在数据库
}
