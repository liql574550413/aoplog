package li.ql.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liql
 * @date 2021/4/13
 */
@Controller
public class ShiroController {
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        System.out.println("进入index");
        //参数model  并不是从url找那个穿金路来的参数，而是我们在代码找那个给model赋值，
        // 然后给页面里的 <p th:text=" ${msg}"></p> 量赋值
        //或者说 页面展现需要（非必须）给这个玩意赋值，所以我们写个model 让model携带者参数给页面
        model.addAttribute("msg", "hello Shiro");
        return "index";
    }
    @RequestMapping("/user/add")
    public String toAdd(){
        System.out.println("进入add");
        return "add";
    }
    @RequestMapping("/user/update")
    public String toUpdate(){
        System.out.println("进入update");
        return "update";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/toLogin")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "登录失败");
            return "login";
        }
    }
}