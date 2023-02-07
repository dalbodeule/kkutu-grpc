#include <condition_variable>
#include <iostream>
#include <memory>
#include <mutex>
#include <string>

#include "myproto/test.pb.h"
#include "myproto/test.grpc.pb.h"

#include <grpcpp/channel.h>
#include <grpcpp/client_context.h>
#include <grpcpp/create_channel.h>
#include <grpcpp/security/credentials.h>

using space::mori::onlinejudge::protobuf::TestService;
using space::mori::onlinejudge::protobuf::HelloRequest;
using space::mori::onlinejudge::protobuf::HelloResponse;

using grpc::Channel;
using grpc::ClientContext;
using grpc::ClientReader;
using grpc::ClientReaderWriter;
using grpc::ClientWriter;
using grpc::Status;
using grpc::ChannelInterface;

int main(int argc, char** argv) {
    std::cout << "Starting!" << std::endl;

    auto channel = grpc::CreateChannel("172.28.144.1:50000", grpc::InsecureChannelCredentials());

    auto stub = TestService::NewStub(channel);

    HelloRequest req;
    req.set_greeting("DAVID");

    HelloResponse res;
    ClientContext context;
    Status status = stub->SayHello(&context, req, &res);

    if (status.ok()) {
        std::cout << res.greeting() << std::endl;
    } else {
        std::cerr << status.error_code() << ": " << status.error_message() << std::endl;
    }
}