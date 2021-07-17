package autogenerated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *服务接口定义，服务端和客户端都要遵循该接口进行通信
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: proto/calservice.proto")
public final class CalculatorServiceGrpc {

  private CalculatorServiceGrpc() {}

  public static final String SERVICE_NAME = "CalculatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<autogenerated.bookInformation,
      autogenerated.bookID> getAddBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addBook",
      requestType = autogenerated.bookInformation.class,
      responseType = autogenerated.bookID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<autogenerated.bookInformation,
      autogenerated.bookID> getAddBookMethod() {
    io.grpc.MethodDescriptor<autogenerated.bookInformation, autogenerated.bookID> getAddBookMethod;
    if ((getAddBookMethod = CalculatorServiceGrpc.getAddBookMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getAddBookMethod = CalculatorServiceGrpc.getAddBookMethod) == null) {
          CalculatorServiceGrpc.getAddBookMethod = getAddBookMethod = 
              io.grpc.MethodDescriptor.<autogenerated.bookInformation, autogenerated.bookID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CalculatorService", "addBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookInformation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookID.getDefaultInstance()))
                  .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("addBook"))
                  .build();
          }
        }
     }
     return getAddBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getSearchByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchByID",
      requestType = autogenerated.bookID.class,
      responseType = autogenerated.bookInformation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getSearchByIDMethod() {
    io.grpc.MethodDescriptor<autogenerated.bookID, autogenerated.bookInformation> getSearchByIDMethod;
    if ((getSearchByIDMethod = CalculatorServiceGrpc.getSearchByIDMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getSearchByIDMethod = CalculatorServiceGrpc.getSearchByIDMethod) == null) {
          CalculatorServiceGrpc.getSearchByIDMethod = getSearchByIDMethod = 
              io.grpc.MethodDescriptor.<autogenerated.bookID, autogenerated.bookInformation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CalculatorService", "searchByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookInformation.getDefaultInstance()))
                  .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("searchByID"))
                  .build();
          }
        }
     }
     return getSearchByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<autogenerated.bookName,
      autogenerated.bookID> getSearchByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchByName",
      requestType = autogenerated.bookName.class,
      responseType = autogenerated.bookID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<autogenerated.bookName,
      autogenerated.bookID> getSearchByNameMethod() {
    io.grpc.MethodDescriptor<autogenerated.bookName, autogenerated.bookID> getSearchByNameMethod;
    if ((getSearchByNameMethod = CalculatorServiceGrpc.getSearchByNameMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getSearchByNameMethod = CalculatorServiceGrpc.getSearchByNameMethod) == null) {
          CalculatorServiceGrpc.getSearchByNameMethod = getSearchByNameMethod = 
              io.grpc.MethodDescriptor.<autogenerated.bookName, autogenerated.bookID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CalculatorService", "searchByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookID.getDefaultInstance()))
                  .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("searchByName"))
                  .build();
          }
        }
     }
     return getSearchByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getDeleteByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteByID",
      requestType = autogenerated.bookID.class,
      responseType = autogenerated.bookInformation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getDeleteByIDMethod() {
    io.grpc.MethodDescriptor<autogenerated.bookID, autogenerated.bookInformation> getDeleteByIDMethod;
    if ((getDeleteByIDMethod = CalculatorServiceGrpc.getDeleteByIDMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getDeleteByIDMethod = CalculatorServiceGrpc.getDeleteByIDMethod) == null) {
          CalculatorServiceGrpc.getDeleteByIDMethod = getDeleteByIDMethod = 
              io.grpc.MethodDescriptor.<autogenerated.bookID, autogenerated.bookInformation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CalculatorService", "deleteByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookInformation.getDefaultInstance()))
                  .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("deleteByID"))
                  .build();
          }
        }
     }
     return getDeleteByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getShowBooksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "showBooks",
      requestType = autogenerated.bookID.class,
      responseType = autogenerated.bookInformation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<autogenerated.bookID,
      autogenerated.bookInformation> getShowBooksMethod() {
    io.grpc.MethodDescriptor<autogenerated.bookID, autogenerated.bookInformation> getShowBooksMethod;
    if ((getShowBooksMethod = CalculatorServiceGrpc.getShowBooksMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getShowBooksMethod = CalculatorServiceGrpc.getShowBooksMethod) == null) {
          CalculatorServiceGrpc.getShowBooksMethod = getShowBooksMethod = 
              io.grpc.MethodDescriptor.<autogenerated.bookID, autogenerated.bookInformation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CalculatorService", "showBooks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  autogenerated.bookInformation.getDefaultInstance()))
                  .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("showBooks"))
                  .build();
          }
        }
     }
     return getShowBooksMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorServiceStub newStub(io.grpc.Channel channel) {
    return new CalculatorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CalculatorServiceFutureStub(channel);
  }

  /**
   * <pre>
   *服务接口定义，服务端和客户端都要遵循该接口进行通信
   * </pre>
   */
  public static abstract class CalculatorServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *接收请求，返回响应
     *rpc Initbookshelf("") returns(""){}; //初始化
     * </pre>
     */
    public void addBook(autogenerated.bookInformation request,
        io.grpc.stub.StreamObserver<autogenerated.bookID> responseObserver) {
      asyncUnimplementedUnaryCall(getAddBookMethod(), responseObserver);
    }

    /**
     */
    public void searchByID(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchByIDMethod(), responseObserver);
    }

    /**
     */
    public void searchByName(autogenerated.bookName request,
        io.grpc.stub.StreamObserver<autogenerated.bookID> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchByNameMethod(), responseObserver);
    }

    /**
     */
    public void deleteByID(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteByIDMethod(), responseObserver);
    }

    /**
     */
    public void showBooks(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnimplementedUnaryCall(getShowBooksMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddBookMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                autogenerated.bookInformation,
                autogenerated.bookID>(
                  this, METHODID_ADD_BOOK)))
          .addMethod(
            getSearchByIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                autogenerated.bookID,
                autogenerated.bookInformation>(
                  this, METHODID_SEARCH_BY_ID)))
          .addMethod(
            getSearchByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                autogenerated.bookName,
                autogenerated.bookID>(
                  this, METHODID_SEARCH_BY_NAME)))
          .addMethod(
            getDeleteByIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                autogenerated.bookID,
                autogenerated.bookInformation>(
                  this, METHODID_DELETE_BY_ID)))
          .addMethod(
            getShowBooksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                autogenerated.bookID,
                autogenerated.bookInformation>(
                  this, METHODID_SHOW_BOOKS)))
          .build();
    }
  }

  /**
   * <pre>
   *服务接口定义，服务端和客户端都要遵循该接口进行通信
   * </pre>
   */
  public static final class CalculatorServiceStub extends io.grpc.stub.AbstractStub<CalculatorServiceStub> {
    private CalculatorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *接收请求，返回响应
     *rpc Initbookshelf("") returns(""){}; //初始化
     * </pre>
     */
    public void addBook(autogenerated.bookInformation request,
        io.grpc.stub.StreamObserver<autogenerated.bookID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchByID(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchByName(autogenerated.bookName request,
        io.grpc.stub.StreamObserver<autogenerated.bookID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteByID(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void showBooks(autogenerated.bookID request,
        io.grpc.stub.StreamObserver<autogenerated.bookInformation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShowBooksMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *服务接口定义，服务端和客户端都要遵循该接口进行通信
   * </pre>
   */
  public static final class CalculatorServiceBlockingStub extends io.grpc.stub.AbstractStub<CalculatorServiceBlockingStub> {
    private CalculatorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *接收请求，返回响应
     *rpc Initbookshelf("") returns(""){}; //初始化
     * </pre>
     */
    public autogenerated.bookID addBook(autogenerated.bookInformation request) {
      return blockingUnaryCall(
          getChannel(), getAddBookMethod(), getCallOptions(), request);
    }

    /**
     */
    public autogenerated.bookInformation searchByID(autogenerated.bookID request) {
      return blockingUnaryCall(
          getChannel(), getSearchByIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public autogenerated.bookID searchByName(autogenerated.bookName request) {
      return blockingUnaryCall(
          getChannel(), getSearchByNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public autogenerated.bookInformation deleteByID(autogenerated.bookID request) {
      return blockingUnaryCall(
          getChannel(), getDeleteByIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public autogenerated.bookInformation showBooks(autogenerated.bookID request) {
      return blockingUnaryCall(
          getChannel(), getShowBooksMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *服务接口定义，服务端和客户端都要遵循该接口进行通信
   * </pre>
   */
  public static final class CalculatorServiceFutureStub extends io.grpc.stub.AbstractStub<CalculatorServiceFutureStub> {
    private CalculatorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *接收请求，返回响应
     *rpc Initbookshelf("") returns(""){}; //初始化
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<autogenerated.bookID> addBook(
        autogenerated.bookInformation request) {
      return futureUnaryCall(
          getChannel().newCall(getAddBookMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<autogenerated.bookInformation> searchByID(
        autogenerated.bookID request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchByIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<autogenerated.bookID> searchByName(
        autogenerated.bookName request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchByNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<autogenerated.bookInformation> deleteByID(
        autogenerated.bookID request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteByIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<autogenerated.bookInformation> showBooks(
        autogenerated.bookID request) {
      return futureUnaryCall(
          getChannel().newCall(getShowBooksMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_BOOK = 0;
  private static final int METHODID_SEARCH_BY_ID = 1;
  private static final int METHODID_SEARCH_BY_NAME = 2;
  private static final int METHODID_DELETE_BY_ID = 3;
  private static final int METHODID_SHOW_BOOKS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CalculatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CalculatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_BOOK:
          serviceImpl.addBook((autogenerated.bookInformation) request,
              (io.grpc.stub.StreamObserver<autogenerated.bookID>) responseObserver);
          break;
        case METHODID_SEARCH_BY_ID:
          serviceImpl.searchByID((autogenerated.bookID) request,
              (io.grpc.stub.StreamObserver<autogenerated.bookInformation>) responseObserver);
          break;
        case METHODID_SEARCH_BY_NAME:
          serviceImpl.searchByName((autogenerated.bookName) request,
              (io.grpc.stub.StreamObserver<autogenerated.bookID>) responseObserver);
          break;
        case METHODID_DELETE_BY_ID:
          serviceImpl.deleteByID((autogenerated.bookID) request,
              (io.grpc.stub.StreamObserver<autogenerated.bookInformation>) responseObserver);
          break;
        case METHODID_SHOW_BOOKS:
          serviceImpl.showBooks((autogenerated.bookID) request,
              (io.grpc.stub.StreamObserver<autogenerated.bookInformation>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return autogenerated.Calservice.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CalculatorService");
    }
  }

  private static final class CalculatorServiceFileDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier {
    CalculatorServiceFileDescriptorSupplier() {}
  }

  private static final class CalculatorServiceMethodDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalculatorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CalculatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorServiceFileDescriptorSupplier())
              .addMethod(getAddBookMethod())
              .addMethod(getSearchByIDMethod())
              .addMethod(getSearchByNameMethod())
              .addMethod(getDeleteByIDMethod())
              .addMethod(getShowBooksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
