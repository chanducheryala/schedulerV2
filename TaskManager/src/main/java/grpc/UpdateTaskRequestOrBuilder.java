// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: taskmanager.proto

package grpc;

public interface UpdateTaskRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UpdateTaskRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string taskId = 1;</code>
   * @return The taskId.
   */
  java.lang.String getTaskId();
  /**
   * <code>string taskId = 1;</code>
   * @return The bytes for taskId.
   */
  com.google.protobuf.ByteString
      getTaskIdBytes();

  /**
   * <code>.TaskStatus taskStatus = 2;</code>
   * @return The enum numeric value on the wire for taskStatus.
   */
  int getTaskStatusValue();
  /**
   * <code>.TaskStatus taskStatus = 2;</code>
   * @return The taskStatus.
   */
  grpc.TaskStatus getTaskStatus();
}
