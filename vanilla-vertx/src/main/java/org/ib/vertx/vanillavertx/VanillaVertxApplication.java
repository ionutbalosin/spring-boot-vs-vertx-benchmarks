package org.ib.vertx.vanillavertx;

import io.vertx.core.*;
import io.vertx.core.http.HttpServer;

import org.apache.log4j.Logger;

public class VanillaVertxApplication extends AbstractVerticle {

    public final static Logger logger = Logger.getLogger(VanillaVertxApplication.class);

    static Vertx vertx = Vertx.vertx();
    private HttpServer server;

    public static void main(String[] args) {
        vertx.deployVerticle(new VanillaVertxApplication(), new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                logger.info("Verticle deployment complete");
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
    }

    @Override
    public void stop() throws Exception {
        logger.info("Verticle Stopped");
    }
}