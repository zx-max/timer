/**
 * This file is part of timer-review.
 *
 * timer-review is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * timer-review is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with timer-review.  If not, see <http://www.gnu.org/licenses/>.
 */

package zxmax.tools.timerreview.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogExceptionAspect {

    public static final Logger logger = LoggerFactory
            .getLogger(LogExceptionAspect.class);

    public static final String CATCH_BLOCK = "#catch_block#";

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            logger.debug(joinPoint.toLongString());
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error(LogExceptionAspect.CATCH_BLOCK, e);
            return null;
        }
    }

    @Pointcut("execution(public * zxmax.tools.timerreview.tasks.*.*(..))")
    public void methodsToBeProfiled() {
    }
}
