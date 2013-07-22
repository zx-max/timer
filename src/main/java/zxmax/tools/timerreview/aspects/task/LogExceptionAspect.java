package zxmax.tools.timerreview.aspects.task;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.Main;

@Aspect
public class LogExceptionAspect {
    public static final Logger logger = LoggerFactory
            .getLogger(LogExceptionAspect.class);

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            logger.debug(joinPoint.toLongString());
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error(Main.CATCH_BLOCK, e);
            return null;
        }
    }

    @Pointcut("execution(public * zxmax.tools.timerreview.tasks.*.*(..))")
    public void methodsToBeProfiled() {
    }
}
