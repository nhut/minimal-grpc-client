package fi.donhut.grpcclient;

import fi.donhut.grpc.HelloRequest;
import fi.donhut.grpc.HelloResponse;
import fi.donhut.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext()
                .build();

        final HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        final HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Jaska")
                .setLastName("Jokunen")
                .build());

        System.out.println("Response received from server:\n" + helloResponse.getGreeting()
                + "\nName: " + helloResponse.getFirstName());

        channel.shutdown();
    }

}
