package li.ql.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author liql
 * @date 2021/4/12
 */
@Aspect //@Aspect注释告诉Spring这是个切面类
@Component
public class ActionAspect {

    //@Pointcut 声明切入点
    @Pointcut("bean(actionServiceImpl)")
    public void logPointCut(){}

    @Around(value = "logPointCut()") //在logPointCut()这个切入点方法执行前 需要做的事
    public void beforeMethod(ProceedingJoinPoint pj) throws Throwable {//ProceedingJoinPoint pj 参数只支持around 环绕通知
        //环绕通知有个bug ，如果不调用 pj.proceed(); 让被代理的切入点继续执行就会阻塞
        System.out.println("Aronud环绕通知第一次做的事");

        //方法上的实参
        String s = Arrays.toString(pj.getArgs());
        System.out.println("执行的参数 实参："+ s);
        long start = System.currentTimeMillis();

        pj.proceed(); //让被拦截的方法继续执行下去
        long end = System.currentTimeMillis();
//        Class<?> aClass = pj.getTarget().getClass();
//        System.out.println(aClass.getMethods().toString());
        String methodName = pj.getSignature().getName();//获取被执行的方法名

        //获取参数列表(形参)  比较多
        Signature signature = pj.getSignature();
        System.out.println("getSignature:"+signature);
        MethodSignature  methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        System.out.println("参数列表："+Arrays.toString(parameterNames));



        System.out.println("日志：被调用的方法名："+methodName+",参数列表(形参):"+Arrays.toString(parameterNames)
        +",实际参数:"+s+",执行时长:"+(end-start)+" 毫秒");

        System.out.println("Aronud环绕通知第二次做的事");
    }

      @After(value = "logPointCut()") //在logPointCut()这个切入点方法执行后 需要做的事
    public void afterMethod(){
        System.out.println("切入点执行后 做的事");
    }

}
