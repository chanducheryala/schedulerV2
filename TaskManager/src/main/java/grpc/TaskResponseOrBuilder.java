// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: taskmanager.proto

package grpc;

public interface TaskResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TaskResponse)
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
   * <code>.TaskStatus status = 2;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.TaskStatus status = 2;</code>
   * @return The status.
   */
  grpc.TaskStatus getStatus();
}
