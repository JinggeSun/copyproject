package com.sun.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 服务端
 * 服务器端使用jetty当服务器
 * @author zcm
 */
@Slf4j
public class HttpTransportServer implements TransportServer{

    /**
     * server
     */
    private Server server;
    private RequestHandler requestHandler;

    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);

        //注册servlet
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        server.setHandler(servletContextHandler);

        //holder
        ServletHolder holder = new ServletHolder(new RequestServlet());
        servletContextHandler.addServlet(holder,"/*");

    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void stop() {

    }

    /**
     * 内部类。
     * 处理发过来的post请求
     */
    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();

            if (requestHandler != null){
                requestHandler.onRequest(inputStream,outputStream);
            }

            outputStream.flush();
        }
    }
}
