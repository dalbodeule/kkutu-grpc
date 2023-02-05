// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('@grpc/grpc-js');
var test_pb = require('./test_pb.js');

function serialize_space_mori_onlinejudge_protobuf_HelloRequest(arg) {
  if (!(arg instanceof test_pb.HelloRequest)) {
    throw new Error('Expected argument of type space.mori.onlinejudge.protobuf.HelloRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_space_mori_onlinejudge_protobuf_HelloRequest(buffer_arg) {
  return test_pb.HelloRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_space_mori_onlinejudge_protobuf_HelloResponse(arg) {
  if (!(arg instanceof test_pb.HelloResponse)) {
    throw new Error('Expected argument of type space.mori.onlinejudge.protobuf.HelloResponse');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_space_mori_onlinejudge_protobuf_HelloResponse(buffer_arg) {
  return test_pb.HelloResponse.deserializeBinary(new Uint8Array(buffer_arg));
}


var TestServiceService = exports.TestServiceService = {
  sayHello: {
    path: '/space.mori.onlinejudge.protobuf.TestService/SayHello',
    requestStream: false,
    responseStream: false,
    requestType: test_pb.HelloRequest,
    responseType: test_pb.HelloResponse,
    requestSerialize: serialize_space_mori_onlinejudge_protobuf_HelloRequest,
    requestDeserialize: deserialize_space_mori_onlinejudge_protobuf_HelloRequest,
    responseSerialize: serialize_space_mori_onlinejudge_protobuf_HelloResponse,
    responseDeserialize: deserialize_space_mori_onlinejudge_protobuf_HelloResponse,
  },
};

exports.TestServiceClient = grpc.makeGenericClientConstructor(TestServiceService);
