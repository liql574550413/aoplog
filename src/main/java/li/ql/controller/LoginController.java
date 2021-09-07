//package li.ql.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @author liql
// * @date 2021/4/13
// */
//@Controller
//
//public class LoginController {
//
//    //这个是首页，不需要登录（也可以设置需要登录）就可以访问，但是里面的操作必须要登录
//    @RequestMapping({"/","/index"})
//    public String index(Model model){
//        //参数model  并不是从url找那个穿金路来的参数，而是我们在代码找那个给model赋值，
//        // 然后给页面里的 <p th:text=" ${msg}"></p> 量赋值
//        //或者说 页面展现需要（非必须）给这个玩意赋值，所以我们写个model 让model携带者参数给页面
//        System.out.println("controller 进来");
//        model.addAttribute("msg", "你好");
//        return "index";
//    }
//
//    //用来跳转到登录界面使用
//    @RequestMapping("/login")
//    public String login(){
//        return "login";
//    }
//    @RequestMapping("/user/add")
//    public String toAdd(){
//        System.out.println("进入add");
//        return "add";
//    }
//    @RequestMapping("/user/update")
//    public String toUpdate(){
//        System.out.println("进入update");
//        return "update";
//    }
//}
