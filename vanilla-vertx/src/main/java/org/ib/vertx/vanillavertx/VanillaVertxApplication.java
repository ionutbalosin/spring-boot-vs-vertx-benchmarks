package org.ib.vertx.vanillavertx;

import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;

public class VanillaVertxApplication extends AbstractVerticle {

    public final static Logger logger = Logger.getLogger(VanillaVertxApplication.class);

    static Vertx vertx = Vertx.vertx();
    private HttpServer server;

    public static void main(String[] args) {
        vertx.deployVerticle(new VanillaVertxApplication(), new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                logger.info("Verticle Deployment Complete");
            }
        });
    }

    @Override
    public void start(Future<Void> startFuture) {
        int port = 8080;;
        try {
            port = Integer.parseInt( System.getProperty( "server.port" ) );
        } catch (Exception e) {
            // swallow all exceptions
        }

        server = vertx.createHttpServer().requestHandler(req -> {
            req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
        });

        // Now bind the server:
        server.listen(port, res -> {
            if (res.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(res.cause());
            }
        });
        logger.info("Verticle Started");
        logger.info("JVM running for " + (ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0) + " sec");
        System.out.println("Forcing a normal application shutdown ...");
        System.exit(1);
    }

    @Override
    public void stop() throws Exception {
        logger.info("Verticle Stopped");
    }
}