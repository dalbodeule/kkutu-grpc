<script setup lang="ts">
import {useAuthStore} from "@/stores/auth";

const auth = useAuthStore()
const router = useRouter()
try {
  if(auth.authenticated) await auth.logout()
  console.log("logout!")
} catch(e) {
  console.log(e)
} finally {
  router.push("/")
}
</script>

<template>

</template>

<style scoped>

</style>