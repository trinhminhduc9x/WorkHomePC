package com.book.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookAspect {
    private int count = 1;
    @AfterReturning(pointcut = "execution(* com.book.model.controller.BookController.*(..))")
    public void infoProcessing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        int c = count++;
        System.err.println(" Method " + methodName + " đã chạy xong " + c + " lần");
    }
}
