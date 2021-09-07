package li.ql.controller;

import li.ql.annotation.LogAnnotation;
import li.ql.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liql
 * @date 2021/4/12
 */
//@Controller
@RestController
@RequestMapping("/action")
public class ActionController {
    @Autowired
    private ActionService actionService;

    @RequestMapping("/1")
    public void  actionOne(String username,String password){
        System.out.println(" controller层 actionOne: username:"+username+",password:"+password);
        actionService.actionOne(username,password);
    }

    @RequestMapping("/2")
    @LogAnnotation
    public void  actionTwo(String username,String password){
        System.out.println(" controller层 actionOne: username:"+username+",password:"+password);
        actionService.actionTwo(username,password);
    }
}
