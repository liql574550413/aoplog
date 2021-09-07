package li.ql.aspect;

import li.ql.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liql
 * @date 2021/4/12
 */
@Aspect //@Aspect注释告诉Spring这是个切面类
@Component
public class ActionAspect2 {

    //@Pointcut 声明切入点  这个切入点是个注解
    //如果注解上没有其它内容，只要进入这个切面，及代理成功，就可以写业务了
    //如果注解上还有其它的内容 则需要通过反射 获取主街上的内容
    @Pointcut("@annotation(li.ql.annotation.LogAnnotation)")
    public void logPointCut(){}

    @Around(value = "logPointCut()") //在logPointCut()这个切入点方法执行前 需要做的事
    public void beforeMethod(ProceedingJoinPoint pj) throws Throwable {//ProceedingJoinPoint pj 参数只支持around 环绕通知
        //环绕通知有个bug ，如果不调用 pj.proceed(); 让被代理的切入点继续执行就会阻塞
        System.out.println("第二个切面拦截注解 Aronud环绕通知第一次做的事");


        //先获取方法  才能检查方法上有没有携带注解
        Signature signature = pj.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //检查方法上是否有注解 先要获取目标类，然后根据目标方法名和类型 获取类的方法的反射 ，然后才能获取注解，
        // 省略获取类方法的反射，则拿不到注解
    //    LogAnnotation annotation = methodSignature.getClass().getDeclaredAnnotation(LogAnnotation.class);// 这个不行
    //   LogAnnotation annotation = methodSignature.getClass().getDeclaredAnnotation(LogAnnotation.class);// 拿不到

        Object targetClass = pj.getTarget();
        System.out.println(methodSignature.getName()+","+methodSignature.getParameterTypes());
        Method declaredMethod = targetClass.getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //这下才能拿到注解
        LogAnnotation annotation = declaredMethod.getAnnotation(LogAnnotation.class);
        System.out.println("获取的注解："+annotation);

        //上面那么多都是为了拿到这个value 踩写的
        String value = annotation.value();
//        System.out.println("注解上的值："+value);

        System.out.println("aop获得的注解上的值："+value);

        pj.proceed(); //让被拦截的方法继续执行下去



        System.out.println(" 第二个切面 Aronud环绕通知第二次做的事");
    }

      @After(value = "logPointCut()") //在logPointCut()这个切入点方法执行后 需要做的事
     public void afterMethod(){
        System.out.println("切入点执行后 做的事");
    }

}
