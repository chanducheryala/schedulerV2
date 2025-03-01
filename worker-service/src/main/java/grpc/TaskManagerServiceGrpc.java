package grpc;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: taskmanager.proto")
public final class TaskManagerServiceGrpc {

  private TaskManagerServiceGrpc() {}

  public static final String SERVICE_NAME = "TaskManagerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.UpdateTaskRequest,
      grpc.UpdateTaskResponse> getUpdateTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateTask",
      requestType = grpc.UpdateTaskRequest.class,
      responseType = grpc.UpdateTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.UpdateTaskRequest,
      grpc.UpdateTaskResponse> getUpdateTaskMethod() {
    io.grpc.MethodDescriptor<grpc.UpdateTaskRequest, grpc.UpdateTaskResponse> getUpdateTaskMethod;
    if ((getUpdateTaskMethod = TaskManagerServiceGrpc.getUpdateTaskMethod) == null) {
      synchronized (TaskManagerServiceGrpc.class) {
        if ((getUpdateTaskMethod = TaskManagerServiceGrpc.getUpdateTaskMethod) == null) {
          TaskManagerServiceGrpc.getUpdateTaskMethod = getUpdateTaskMethod = 
              io.grpc.MethodDescriptor.<grpc.UpdateTaskRequest, grpc.UpdateTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TaskManagerService", "updateTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UpdateTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.UpdateTaskResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TaskManagerServiceMethodDescriptorSupplier("updateTask"))
                  .build();
          }
        }
     }
     return getUpdateTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.HeartBeatRequest,
      grpc.HeartBeatResponse> getSendHeartBeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendHeartBeat",
      requestType = grpc.HeartBeatRequest.class,
      responseType = grpc.HeartBeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.HeartBeatRequest,
      grpc.HeartBeatResponse> getSendHeartBeatMethod() {
    io.grpc.MethodDescriptor<grpc.HeartBeatRequest, grpc.HeartBeatResponse> getSendHeartBeatMethod;
    if ((getSendHeartBeatMethod = TaskManagerServiceGrpc.getSendHeartBeatMethod) == null) {
      synchronized (TaskManagerServiceGrpc.class) {
        if ((getSendHeartBeatMethod = TaskManagerServiceGrpc.getSendHeartBeatMethod) == null) {
          TaskManagerServiceGrpc.getSendHeartBeatMethod = getSendHeartBeatMethod = 
              io.grpc.MethodDescriptor.<grpc.HeartBeatRequest, grpc.HeartBeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TaskManagerService", "sendHeartBeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.HeartBeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.HeartBeatResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TaskManagerServiceMethodDescriptorSupplier("sendHeartBeat"))
                  .build();
          }
        }
     }
     return getSendHeartBeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TaskManagerServiceStub newStub(io.grpc.Channel channel) {
    return new TaskManagerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TaskManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TaskManagerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TaskManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TaskManagerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TaskManagerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void updateTask(grpc.UpdateTaskRequest request,
        io.grpc.stub.StreamObserver<grpc.UpdateTaskResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateTaskMethod(), responseObserver);
    }

    /**
     */
    public void sendHeartBeat(grpc.HeartBeatRequest request,
        io.grpc.stub.StreamObserver<grpc.HeartBeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendHeartBeatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUpdateTaskMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.UpdateTaskRequest,
                grpc.UpdateTaskResponse>(
                  this, METHODID_UPDATE_TASK)))
          .addMethod(
            getSendHeartBeatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.HeartBeatRequest,
                grpc.HeartBeatResponse>(
                  this, METHODID_SEND_HEART_BEAT)))
          .build();
    }
  }

  /**
   */
  public static final class TaskManagerServiceStub extends io.grpc.stub.AbstractStub<TaskManagerServiceStub> {
    private TaskManagerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskManagerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskManagerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskManagerServiceStub(channel, callOptions);
    }

    /**
     */
    public void updateTask(grpc.UpdateTaskRequest request,
        io.grpc.stub.StreamObserver<grpc.UpdateTaskResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendHeartBeat(grpc.HeartBeatRequest request,
        io.grpc.stub.StreamObserver<grpc.HeartBeatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendHeartBeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TaskManagerServiceBlockingStub extends io.grpc.stub.AbstractStub<TaskManagerServiceBlockingStub> {
    private TaskManagerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskManagerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskManagerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskManagerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.UpdateTaskResponse updateTask(grpc.UpdateTaskRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.HeartBeatResponse sendHeartBeat(grpc.HeartBeatRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendHeartBeatMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TaskManagerServiceFutureStub extends io.grpc.stub.AbstractStub<TaskManagerServiceFutureStub> {
    private TaskManagerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskManagerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskManagerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskManagerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.UpdateTaskResponse> updateTask(
        grpc.UpdateTaskRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.HeartBeatResponse> sendHeartBeat(
        grpc.HeartBeatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendHeartBeatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_TASK = 0;
  private static final int METHODID_SEND_HEART_BEAT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TaskManagerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TaskManagerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_TASK:
          serviceImpl.updateTask((grpc.UpdateTaskRequest) request,
              (io.grpc.stub.StreamObserver<grpc.UpdateTaskResponse>) responseObserver);
          break;
        case METHODID_SEND_HEART_BEAT:
          serviceImpl.sendHeartBeat((grpc.HeartBeatRequest) request,
              (io.grpc.stub.StreamObserver<grpc.HeartBeatResponse>) responseObserver);
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

  private static abstract class TaskManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TaskManagerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.Taskmanager.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TaskManagerService");
    }
  }

  private static final class TaskManagerServiceFileDescriptorSupplier
      extends TaskManagerServiceBaseDescriptorSupplier {
    TaskManagerServiceFileDescriptorSupplier() {}
  }

  private static final class TaskManagerServiceMethodDescriptorSupplier
      extends TaskManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TaskManagerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TaskManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TaskManagerServiceFileDescriptorSupplier())
              .addMethod(getUpdateTaskMethod())
              .addMethod(getSendHeartBeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
