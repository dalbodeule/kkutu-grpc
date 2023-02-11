find_package(Threads)

find_package(PkgConfig REQUIRED)

find_package(Protobuf REQUIRED)
find_package(gRPC CONFIG)

message(STATUS "===========")
if(gRPC_FOUND)
    message(STATUS "grpc founded!")
    set(GRPCPP_INCLUDE_DIRS $<TARGET_PROPERTY:gRPC::grpc++,INTERFACE_INCLUDE_DIRECTORIES>)
    set(GRPCPP_LIBRARIES $<TARGET_PROPERTY:gRPC::grpc++,INTERFACE_LINK_LIBRARIES>)
else()
    message(STATUS "grpc not founded!")
    pkg_search_module(GRPC REQUIRED grpc)
    pkg_search_module(GRPCPP REQUIRED grpc++)


    list(APPEND GRPCPP_INCLUDE_DIRS ${GRPC_INCLUDE_DIRS})
    list(APPEND GRPCPP_LIBRARIES ${GRPC_LIBRARIES})
endif()

if(Protobuf_FOUND)
    message(STATUS "protobuf founded!")
else()
    message(STATUS "protobuf not founded!")
endif()

message(STATUS "===========")

set(PROTO_FILES
    ${CMAKE_CURRENT_SOURCE_DIR}/myproto/test.proto
)

add_library(myproto ${PROTO_FILES})

message(STATUS "==========")
message(STATUS ${GRPCPP_LIBRARIES})
message(STATUS ${PROTOBUF_LIBRARIES})
message(STATUS "==========")
message(STATUS ${PROTO_FILES})
message(STATUS "==========")

protobuf_generate(TARGET myproto LANGUAGE cpp)
protobuf_generate(TARGET myproto LANGUAGE grpc GENERATE_EXTENSIONS .grpc.pb.h .grpc.pb.cc PLUGIN "protoc-gen-grpc=/bin/grpc_cpp_plugin")

target_link_libraries(myproto
    PUBLIC
        ${GRPCPP_LIBRARIES}
        ${PROTOBUF_LIBRARIES}
)

target_include_directories(myproto
    PUBLIC
        ${CMAKE_CURRENT_BINARY_DIR}
)

message(STATUS "proto build end!")
