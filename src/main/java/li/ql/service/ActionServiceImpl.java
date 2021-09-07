package li.ql.service;

import li.ql.annotation.LogAnnotation;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liql
 * @date 2021/4/12
 */
@Service
public class ActionServiceImpl implements ActionService {

        @Override
        @LogAnnotation("这是行为一")
        public void  actionOne(String username, String password){
            System.out.println(" service层 actionOne: username:"+username+",password:"+password);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    @Override

    public void  actionTwo(String username2,String password2){
        System.out.println(" service层 actionOne: username:"+username2+",password:"+password2);
    }

}
