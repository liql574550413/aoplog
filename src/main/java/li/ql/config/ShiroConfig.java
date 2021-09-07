package li.ql.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import li.ql.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liql
 * @date 2021/4/13
 * Subject：主体，代表了当前 “用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是 Subject，如网络爬虫，机器人等；
 * 即一个抽象概念；所有 Subject 都绑定到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；
 * 可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；
 *
 * SecurityManager：安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互；且它管理着所有 Subject；
 * 可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器；
 *
 * Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，
 * 那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；
 * 可以把 Realm 看成 DataSource，即安全数据源。
 */
@Configuration
public class ShiroConfig {
    //围绕着三个写 分别是 Subject ,  SecurityManager ,Realm


    //1.Subject ,



    // 2.SecurityManager



    // 3.,Realm
    public  UserRealm getUserRealm(){

        return null;
    }



    //shiro整合thymeleaf
    /*  需要这个依赖   shiro的方言  关于thymeleaf  的方言
    <!-- thymeleaf-extras-shiro -->
        <dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>
        </dependency>*/
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}


//这个部分是重点  自定义 realm
class UserRealm extends AuthorizingRealm{

    //这个是授权 ，就是访问权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("shiro 框架正在执行授权操作 AuthorizationInfo, 方法名是 doGetAuthorizationInfo");
        //SimpleAuthorizationInfo 这个是认证的  和授权的SimpleAuthorizationInfo 长得非常想 注意区分
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前登录的对象
        Subject subject= SecurityUtils.getSubject();
        //把拿到的这个 "用户 subject” 转成我们可以使用的用户对象
        User currentUser = (User) subject.getPrincipal();//当前登录的对象

        //授权  perms是用户的权限 一半存在数据库
        info.addStringPermission(currentUser.getPerms());
        System.out.println("当前用户授权完毕");

        return info;
    }


    //这个是认证 也就是登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("shiro 框架正在执行认证登录操作 AuthenticationInfo, 方法名是 doGetAuthenticationInfo");

        //这个token是shiro框架自己的   我们也可以定义紫的的token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        return null;
    }
}