// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test.proto

#include "test.pb.h"

#include <algorithm>

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/wire_format_lite.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)
#include <google/protobuf/port_def.inc>

PROTOBUF_PRAGMA_INIT_SEG

namespace _pb = ::PROTOBUF_NAMESPACE_ID;
namespace _pbi = _pb::internal;

namespace space {
namespace mori {
namespace onlinejudge {
namespace protobuf {
PROTOBUF_CONSTEXPR HelloRequest::HelloRequest(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.greeting_)*/{&::_pbi::fixed_address_empty_string, ::_pbi::ConstantInitialized{}}
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct HelloRequestDefaultTypeInternal {
  PROTOBUF_CONSTEXPR HelloRequestDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~HelloRequestDefaultTypeInternal() {}
  union {
    HelloRequest _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 HelloRequestDefaultTypeInternal _HelloRequest_default_instance_;
PROTOBUF_CONSTEXPR HelloResponse::HelloResponse(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.greeting_)*/{&::_pbi::fixed_address_empty_string, ::_pbi::ConstantInitialized{}}
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct HelloResponseDefaultTypeInternal {
  PROTOBUF_CONSTEXPR HelloResponseDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~HelloResponseDefaultTypeInternal() {}
  union {
    HelloResponse _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 HelloResponseDefaultTypeInternal _HelloResponse_default_instance_;
}  // namespace protobuf
}  // namespace onlinejudge
}  // namespace mori
}  // namespace space
static ::_pb::Metadata file_level_metadata_test_2eproto[2];
static constexpr ::_pb::EnumDescriptor const** file_level_enum_descriptors_test_2eproto = nullptr;
static constexpr ::_pb::ServiceDescriptor const** file_level_service_descriptors_test_2eproto = nullptr;

const uint32_t TableStruct_test_2eproto::offsets[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::space::mori::onlinejudge::protobuf::HelloRequest, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::space::mori::onlinejudge::protobuf::HelloRequest, _impl_.greeting_),
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::space::mori::onlinejudge::protobuf::HelloResponse, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::space::mori::onlinejudge::protobuf::HelloResponse, _impl_.greeting_),
};
static const ::_pbi::MigrationSchema schemas[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  { 0, -1, -1, sizeof(::space::mori::onlinejudge::protobuf::HelloRequest)},
  { 7, -1, -1, sizeof(::space::mori::onlinejudge::protobuf::HelloResponse)},
};

static const ::_pb::Message* const file_default_instances[] = {
  &::space::mori::onlinejudge::protobuf::_HelloRequest_default_instance_._instance,
  &::space::mori::onlinejudge::protobuf::_HelloResponse_default_instance_._instance,
};

const char descriptor_table_protodef_test_2eproto[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) =
  "\n\ntest.proto\022\037space.mori.onlinejudge.pro"
  "tobuf\" \n\014HelloRequest\022\020\n\010greeting\030\001 \001(\t\""
  "!\n\rHelloResponse\022\020\n\010greeting\030\001 \001(\t2x\n\013Te"
  "stService\022i\n\010SayHello\022-.space.mori.onlin"
  "ejudge.protobuf.HelloRequest\032..space.mor"
  "i.onlinejudge.protobuf.HelloResponseB\006B\004"
  "Testb\006proto3"
  ;
static ::_pbi::once_flag descriptor_table_test_2eproto_once;
const ::_pbi::DescriptorTable descriptor_table_test_2eproto = {
    false, false, 252, descriptor_table_protodef_test_2eproto,
    "test.proto",
    &descriptor_table_test_2eproto_once, nullptr, 0, 2,
    schemas, file_default_instances, TableStruct_test_2eproto::offsets,
    file_level_metadata_test_2eproto, file_level_enum_descriptors_test_2eproto,
    file_level_service_descriptors_test_2eproto,
};
PROTOBUF_ATTRIBUTE_WEAK const ::_pbi::DescriptorTable* descriptor_table_test_2eproto_getter() {
  return &descriptor_table_test_2eproto;
}

// Force running AddDescriptors() at dynamic initialization time.
PROTOBUF_ATTRIBUTE_INIT_PRIORITY2 static ::_pbi::AddDescriptorsRunner dynamic_init_dummy_test_2eproto(&descriptor_table_test_2eproto);
namespace space {
namespace mori {
namespace onlinejudge {
namespace protobuf {

// ===================================================================

class HelloRequest::_Internal {
 public:
};

HelloRequest::HelloRequest(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:space.mori.onlinejudge.protobuf.HelloRequest)
}
HelloRequest::HelloRequest(const HelloRequest& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  HelloRequest* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.greeting_){}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  _impl_.greeting_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.greeting_.Set("", GetArenaForAllocation());
  #endif // PROTOBUF_FORCE_COPY_DEFAULT_STRING
  if (!from._internal_greeting().empty()) {
    _this->_impl_.greeting_.Set(from._internal_greeting(), 
      _this->GetArenaForAllocation());
  }
  // @@protoc_insertion_point(copy_constructor:space.mori.onlinejudge.protobuf.HelloRequest)
}

inline void HelloRequest::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.greeting_){}
    , /*decltype(_impl_._cached_size_)*/{}
  };
  _impl_.greeting_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.greeting_.Set("", GetArenaForAllocation());
  #endif // PROTOBUF_FORCE_COPY_DEFAULT_STRING
}

HelloRequest::~HelloRequest() {
  // @@protoc_insertion_point(destructor:space.mori.onlinejudge.protobuf.HelloRequest)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void HelloRequest::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.greeting_.Destroy();
}

void HelloRequest::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void HelloRequest::Clear() {
// @@protoc_insertion_point(message_clear_start:space.mori.onlinejudge.protobuf.HelloRequest)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.greeting_.ClearToEmpty();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* HelloRequest::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // string greeting = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 10)) {
          auto str = _internal_mutable_greeting();
          ptr = ::_pbi::InlineGreedyStringParser(str, ptr, ctx);
          CHK_(ptr);
          CHK_(::_pbi::VerifyUTF8(str, "space.mori.onlinejudge.protobuf.HelloRequest.greeting"));
        } else
          goto handle_unusual;
        continue;
      default:
        goto handle_unusual;
    }  // switch
  handle_unusual:
    if ((tag == 0) || ((tag & 7) == 4)) {
      CHK_(ptr);
      ctx->SetLastTag(tag);
      goto message_done;
    }
    ptr = UnknownFieldParse(
        tag,
        _internal_metadata_.mutable_unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(),
        ptr, ctx);
    CHK_(ptr != nullptr);
  }  // while
message_done:
  return ptr;
failure:
  ptr = nullptr;
  goto message_done;
#undef CHK_
}

uint8_t* HelloRequest::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:space.mori.onlinejudge.protobuf.HelloRequest)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // string greeting = 1;
  if (!this->_internal_greeting().empty()) {
    ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::VerifyUtf8String(
      this->_internal_greeting().data(), static_cast<int>(this->_internal_greeting().length()),
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::SERIALIZE,
      "space.mori.onlinejudge.protobuf.HelloRequest.greeting");
    target = stream->WriteStringMaybeAliased(
        1, this->_internal_greeting(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:space.mori.onlinejudge.protobuf.HelloRequest)
  return target;
}

size_t HelloRequest::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:space.mori.onlinejudge.protobuf.HelloRequest)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // string greeting = 1;
  if (!this->_internal_greeting().empty()) {
    total_size += 1 +
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::StringSize(
        this->_internal_greeting());
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData HelloRequest::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    HelloRequest::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*HelloRequest::GetClassData() const { return &_class_data_; }


void HelloRequest::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<HelloRequest*>(&to_msg);
  auto& from = static_cast<const HelloRequest&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:space.mori.onlinejudge.protobuf.HelloRequest)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  if (!from._internal_greeting().empty()) {
    _this->_internal_set_greeting(from._internal_greeting());
  }
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void HelloRequest::CopyFrom(const HelloRequest& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:space.mori.onlinejudge.protobuf.HelloRequest)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool HelloRequest::IsInitialized() const {
  return true;
}

void HelloRequest::InternalSwap(HelloRequest* other) {
  using std::swap;
  auto* lhs_arena = GetArenaForAllocation();
  auto* rhs_arena = other->GetArenaForAllocation();
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::internal::ArenaStringPtr::InternalSwap(
      &_impl_.greeting_, lhs_arena,
      &other->_impl_.greeting_, rhs_arena
  );
}

::PROTOBUF_NAMESPACE_ID::Metadata HelloRequest::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_test_2eproto_getter, &descriptor_table_test_2eproto_once,
      file_level_metadata_test_2eproto[0]);
}

// ===================================================================

class HelloResponse::_Internal {
 public:
};

HelloResponse::HelloResponse(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:space.mori.onlinejudge.protobuf.HelloResponse)
}
HelloResponse::HelloResponse(const HelloResponse& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  HelloResponse* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.greeting_){}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  _impl_.greeting_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.greeting_.Set("", GetArenaForAllocation());
  #endif // PROTOBUF_FORCE_COPY_DEFAULT_STRING
  if (!from._internal_greeting().empty()) {
    _this->_impl_.greeting_.Set(from._internal_greeting(), 
      _this->GetArenaForAllocation());
  }
  // @@protoc_insertion_point(copy_constructor:space.mori.onlinejudge.protobuf.HelloResponse)
}

inline void HelloResponse::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.greeting_){}
    , /*decltype(_impl_._cached_size_)*/{}
  };
  _impl_.greeting_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.greeting_.Set("", GetArenaForAllocation());
  #endif // PROTOBUF_FORCE_COPY_DEFAULT_STRING
}

HelloResponse::~HelloResponse() {
  // @@protoc_insertion_point(destructor:space.mori.onlinejudge.protobuf.HelloResponse)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void HelloResponse::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.greeting_.Destroy();
}

void HelloResponse::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void HelloResponse::Clear() {
// @@protoc_insertion_point(message_clear_start:space.mori.onlinejudge.protobuf.HelloResponse)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.greeting_.ClearToEmpty();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* HelloResponse::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // string greeting = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 10)) {
          auto str = _internal_mutable_greeting();
          ptr = ::_pbi::InlineGreedyStringParser(str, ptr, ctx);
          CHK_(ptr);
          CHK_(::_pbi::VerifyUTF8(str, "space.mori.onlinejudge.protobuf.HelloResponse.greeting"));
        } else
          goto handle_unusual;
        continue;
      default:
        goto handle_unusual;
    }  // switch
  handle_unusual:
    if ((tag == 0) || ((tag & 7) == 4)) {
      CHK_(ptr);
      ctx->SetLastTag(tag);
      goto message_done;
    }
    ptr = UnknownFieldParse(
        tag,
        _internal_metadata_.mutable_unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(),
        ptr, ctx);
    CHK_(ptr != nullptr);
  }  // while
message_done:
  return ptr;
failure:
  ptr = nullptr;
  goto message_done;
#undef CHK_
}

uint8_t* HelloResponse::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:space.mori.onlinejudge.protobuf.HelloResponse)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // string greeting = 1;
  if (!this->_internal_greeting().empty()) {
    ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::VerifyUtf8String(
      this->_internal_greeting().data(), static_cast<int>(this->_internal_greeting().length()),
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::SERIALIZE,
      "space.mori.onlinejudge.protobuf.HelloResponse.greeting");
    target = stream->WriteStringMaybeAliased(
        1, this->_internal_greeting(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:space.mori.onlinejudge.protobuf.HelloResponse)
  return target;
}

size_t HelloResponse::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:space.mori.onlinejudge.protobuf.HelloResponse)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // string greeting = 1;
  if (!this->_internal_greeting().empty()) {
    total_size += 1 +
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::StringSize(
        this->_internal_greeting());
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData HelloResponse::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    HelloResponse::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*HelloResponse::GetClassData() const { return &_class_data_; }


void HelloResponse::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<HelloResponse*>(&to_msg);
  auto& from = static_cast<const HelloResponse&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:space.mori.onlinejudge.protobuf.HelloResponse)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  if (!from._internal_greeting().empty()) {
    _this->_internal_set_greeting(from._internal_greeting());
  }
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void HelloResponse::CopyFrom(const HelloResponse& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:space.mori.onlinejudge.protobuf.HelloResponse)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool HelloResponse::IsInitialized() const {
  return true;
}

void HelloResponse::InternalSwap(HelloResponse* other) {
  using std::swap;
  auto* lhs_arena = GetArenaForAllocation();
  auto* rhs_arena = other->GetArenaForAllocation();
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::internal::ArenaStringPtr::InternalSwap(
      &_impl_.greeting_, lhs_arena,
      &other->_impl_.greeting_, rhs_arena
  );
}

::PROTOBUF_NAMESPACE_ID::Metadata HelloResponse::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_test_2eproto_getter, &descriptor_table_test_2eproto_once,
      file_level_metadata_test_2eproto[1]);
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace protobuf
}  // namespace onlinejudge
}  // namespace mori
}  // namespace space
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::space::mori::onlinejudge::protobuf::HelloRequest*
Arena::CreateMaybeMessage< ::space::mori::onlinejudge::protobuf::HelloRequest >(Arena* arena) {
  return Arena::CreateMessageInternal< ::space::mori::onlinejudge::protobuf::HelloRequest >(arena);
}
template<> PROTOBUF_NOINLINE ::space::mori::onlinejudge::protobuf::HelloResponse*
Arena::CreateMaybeMessage< ::space::mori::onlinejudge::protobuf::HelloResponse >(Arena* arena) {
  return Arena::CreateMessageInternal< ::space::mori::onlinejudge::protobuf::HelloResponse >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>