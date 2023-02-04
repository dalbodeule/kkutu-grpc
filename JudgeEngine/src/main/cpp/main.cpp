#include <iostream>

#pragma warning( disable: 4251 )
#pragma warning( disable: 4819 )

#include <grpc/grpc.h>

#include "test.grpc.pb.cc"

using grpc::Channel;
using grpc::ClientContext;
using grpc::ClientReaderWriter;
using grpc::ClientWriter;
using grpc::Status;

int main(int argc, char** argv) {
    std::cout << "Hello World \n" << std::endl;
}