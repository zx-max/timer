package max.utility.tomato.tasks;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThorwUncheckedExceptionTask implements Callable<Object> {

    public static final Logger logger = LoggerFactory
            .getLogger(ThorwUncheckedExceptionTask.class);
    public static final String BLA_BLA_BLA = "bla bla bla";

    @Override
    public Object call() throws Exception {
        logger.debug("call from ThorwUncheckedExceptionTask");
        throw new RuntimeException(BLA_BLA_BLA);
    }

}
