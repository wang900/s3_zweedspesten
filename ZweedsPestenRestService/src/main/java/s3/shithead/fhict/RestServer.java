package s3.shithead.fhict;


import client.AuthenticationRestClient;
import communication.rest.BaseRestClient;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import server.AuthenticationRestService;

import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class RestServer
{
    public static void main( String[] args )
    {
        Server server = new Server(8096);

        server.setHandler(getJerseyHandler());
        try {
            server.start();
            server.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            server.destroy();
        }
    }

    private static Handler getJerseyHandler() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        handler.setContextPath("/");

        addServlet(handler, "/authentication", AuthenticationRestService.class);

        return handler;
    }

    private static <T extends BaseRestClient> void addServlet(ServletContextHandler handler, String pathSpec, Class EndpointClass)
    {
        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, pathSpec + "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                EndpointClass.getCanonicalName());
    }
}
