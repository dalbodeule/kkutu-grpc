# OnlineJudge

## Language
- Java(Kotlin) to Backends
- TypeScript(Vue, Nuxt) to Frontends
- C++ to JudgeEngine
- protobuf to gRPC

## NOTE
### This project depends on that item.
- [x] `npm i -g grpc-tools` (to Frontend)
- [x] `yarn` (to Frontend)
- [x] `protobuf` on `PATH` (to Frontend, JudgeEngine)
- [x] `Linux` systems. `JudgeEngine` is Linux dependent.

## How to build?

### Backend
- [x] gradlew :backend:build
- [x] gradlew :backend:nativeBuild (in vcvars.bat on Windows)

### Frontend
- [x] gradlew :frontend:build
- It depends with Node.js and npm. So You should install them.

### JudgeEngine
- [x] gradlew :judgeengine:build
- It depends with Linux OS. So You should running this commands on Linux (Ex: WSL, Linux Computers)
- And I don't known this system compatable OS'X, So this system not target OS'X