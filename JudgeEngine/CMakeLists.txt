cmake_minimum_required(VERSION 3.22.0)

project(JudgeEngine C CXX)

find_package(OpenSSL REQUIRED)
if (OPENSSL_FOUND)
  message(STATUS "Found OpenSSL ${OPENSSL_VERSION}")
else()
  message(STATUS "OpenSSL Not Found")
endif()

add_subdirectory(proto)

add_executable(${PROJECT_NAME}
    main.cpp
)

target_link_libraries(${PROJECT_NAME}
    PRIVATE
        myproto
)

