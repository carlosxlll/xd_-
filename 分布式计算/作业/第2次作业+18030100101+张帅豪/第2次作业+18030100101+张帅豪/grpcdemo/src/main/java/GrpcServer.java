import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private static final int port=9999;
    public static void main(String argc[]) throws IOException, InterruptedException {
        Server server= ServerBuilder.forPort(port).addService(new CalculatorServiceImpl())
                .build().start();
        System.out.println("grpc server started, port="+port);
        server.awaitTermination();
    }
}
