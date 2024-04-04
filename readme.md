# KKuTu-gRPC

## Language
- Java(Kotlin) and Armeria gRPC GameServer
- Java(Kotlin) and Springboot AUTH Server
- TypeScript(Vue, Nuxt) to Frontends
- protobuf to gRPC

## NOTE
### This project depends on that item.
- [x] `npm i -g grpc-tools` (to Frontend)
- [x] `npm` (to Frontend)
- [x] `protobuf` on `PATH` (to Frontend)
- [x] `Linux` systems. `JudgeEngine` is Linux dependent.
- [x] `protobuf` and `grpc_cpp_plugin` with `sudo apt install protobuf-compiler protobuf-compiler-grpc libprotobuf-dev libgrpc++-dev`

## How to build?

### Backend (Game/AUTH)
- [x] gradlew :backend:build
- [x] gradlew :backend:nativeBuild (if using Windows, use vcvars.bat)

### Frontend
- [x] gradlew :frontend:build
- It depends with Node.js and npm. So You should install them.
