package co.zpdev.core.discord.exception;

import co.zpdev.core.discord.util.PostUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Uncaught and caught exception handler.
 *
 * @author zpdev
 * @version 1.0
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static String token = "", name = "";

    /**
     * Initialises the handler.
     *
     * @param token the pushbullet token to use
     * @param name the instance name to use
     */
    public static void init(String token, String name) {
        ExceptionHandler.token = token;
        ExceptionHandler.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        handleException("unexpected", e);
    }

    /**
     * Handles an exception. If token not present, print to console.
     *
     * @param issue when the exception occured
     * @param e the error it threw
     */
    public static void handleException(String issue, Throwable e) {
        if (token.isEmpty()) {
            e.printStackTrace();
            return;
        }
        String paste = PostUtil.paste(getStackTrace(e));
        PostUtil.push(token, name, "Encountered an exception when " + issue + ".\n" + paste);
    }

    /**
     * Converts a stacktrace to a string.
     *
     * @param t the throwable
     * @return the parsed string
     */
    private static String getStackTrace(Throwable t) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        t.printStackTrace(printWriter);
        return result.toString();
    }

}
