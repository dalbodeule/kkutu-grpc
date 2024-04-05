<script setup lang="ts">
const auth: Array<{color: string, fontColor: string, vendor: string, displayName: string }> = [
  { color: '#ffffff', fontColor: '#000000', vendor: 'google', displayName: 'withGoogle'},
  { color: '#55acee', fontColor: '#ffffff', vendor: 'twitter', displayName: 'withTwitter'},
  { color: '#235ee3', fontColor: '#ffffff', vendor: 'facebook', displayName: 'withFacebook'},
  { color: '#1ec800', fontColor: '#ffffff', vendor: 'naver', displayName: 'withNaver'},
  { color: '#ffde00', fontColor: '#3c1e1e', vendor: 'kakao', displayName: 'withKakao'}
]

const config = useRuntimeConfig()

useHead({
  title: '글자로 놀자! 끄투 온라인',
})
</script>

<style scoped src="@/assets/css/style.css"/>
<style scoped src="@/assets/css/in_login.css"/>

<template>
  <NuxtLayout name="login">
    <Seprator len="30"/>
    <div class="login-with" style="width: 100%">로그인 방법을 선택하세요.</div>
    <Seprator len="30"/>
    <div v-for="i in auth" style="margin: auto; display:block; max-width: fit-content">
      <a v-bind:href="`${config.public.AUTH_SERVER}/oauth2/authorization/${i.vendor}`" style="display:block; width: 210px;">
        <button v-bind:style="`color: ${i.fontColor}; background-color: ${i.color};`">{{i.displayName}}</button>
      </a>
    </div>
  </NuxtLayout>
</template>

<style scoped>

</style>