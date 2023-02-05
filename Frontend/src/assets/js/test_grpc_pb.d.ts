// package: space.mori.onlinejudge.protobuf
// file: test.proto

/* tslint:disable */
/* eslint-disable */

import * as grpc from "@grpc/grpc-js";
import * as test_pb from "./test_pb";

interface ITestServiceService extends grpc.ServiceDefinition<grpc.UntypedServiceImplementation> {
    sayHello: ITestServiceService_ISayHello;
}

interface ITestServiceService_ISayHello extends grpc.MethodDefinition<test_pb.HelloRequest, test_pb.HelloResponse> {
    path: "/space.mori.onlinejudge.protobuf.TestService/SayHello";
    requestStream: false;
    responseStream: false;
    requestSerialize: grpc.serialize<test_pb.HelloRequest>;
    requestDeserialize: grpc.deserialize<test_pb.HelloRequest>;
    responseSerialize: grpc.serialize<test_pb.HelloResponse>;
    responseDeserialize: grpc.deserialize<test_pb.HelloResponse>;
}

export const TestServiceService: ITestServiceService;

export interface ITestServiceServer extends grpc.UntypedServiceImplementation {
    sayHello: grpc.handleUnaryCall<test_pb.HelloRequest, test_pb.HelloResponse>;
}

export interface ITestServiceClient {
    sayHello(request: test_pb.HelloRequest, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
    sayHello(request: test_pb.HelloRequest, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
    sayHello(request: test_pb.HelloRequest, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
}

export class TestServiceClient extends grpc.Client implements ITestServiceClient {
    constructor(address: string, credentials: grpc.ChannelCredentials, options?: Partial<grpc.ClientOptions>);
    public sayHello(request: test_pb.HelloRequest, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
    public sayHello(request: test_pb.HelloRequest, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
    public sayHello(request: test_pb.HelloRequest, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: test_pb.HelloResponse) => void): grpc.ClientUnaryCall;
}
