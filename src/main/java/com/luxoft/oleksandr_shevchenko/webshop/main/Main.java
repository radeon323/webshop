package com.luxoft.oleksandr_shevchenko.webshop.main;

import com.luxoft.oleksandr_shevchenko.webshop.servlets.AddProductServlet;
import com.luxoft.oleksandr_shevchenko.webshop.servlets.AllRequestsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(new AddProductServlet()), "/product/add");


        Server server = new Server(3000);
        server.setHandler(context);

        server.start();
    }
}