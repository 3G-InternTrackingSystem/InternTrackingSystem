package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ServletConfigListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(getClass().getName());
    DatabaseConnection databaseConnection;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("ServletConfigListener: Application scoped init!");
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP.
    }

}