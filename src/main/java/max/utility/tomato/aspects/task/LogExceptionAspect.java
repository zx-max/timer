package max.utility.tomato.aspects.task;

import max.utility.tomato.Main;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogExceptionAspect {
	public static final Logger logger = LoggerFactory.getLogger(LogExceptionAspect.class);

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

	@Pointcut("execution(public * max.utility.tomato.tasks.*.*(..))")
	public void methodsToBeProfiled() {
	}
}
