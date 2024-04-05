<script setup lang="ts">
import {useAuthStore} from "~/stores/auth";
import delay from "delay";

const authStore = useAuthStore()
try {
  await delay(100)
  if(authStore.authenticated == false) await authStore.getUserMeta()
} catch(e) {
  console.log(e)
}
</script>

<style scoped src="@/assets/css/style.css"/>
<style scoped src="@/assets/css/in_portal.css"/>
<style scoped src="@/assets/css/in_game_kkutu.css"/>

<template>
  <div id="Top">
    <div class="Menu">
      <button class="menu-btn" onclick="location.href='http://jjo.kr';">HOME</button>
      <div id="account" v-if="!authStore.authenticated">
        <span id="profile">GUEST</span>
        <nuxt-link id="account-info" href="/login">로그인</nuxt-link>
      </div>
      <div id="account" v-else>
        <span id="profile">{{ authStore.userInfo.nickname }}</span>
        <NuxtLink id="account-info" href="/logout">{{authStore.userInfo.nickname || ''}}</NuxtLink>
      </div>
    </div>
    <div id="global-notice" v-if="false">
      <div id="gn-content">
      </div>
    </div>
    <slot name="top"/>
  </div>
  <div id="jungle">
    <slot name="jungle"/>
  </div>
  <div id="Middle" style="margin-left: 780px;">
    <slot name="middle"/>
  </div>
  <div id="Bottom" style="width: 100%">
    <slot name="bottom"/>
    <div id="bottom-legal">
      <div style='color: #666;'>글자로 놀자! 끄투 온라인 Copyright (C) {년도} {배포자}({이메일 등})<br>
        이 프로그램은 제품에 대한 어떠한 형태의 보증도 제공되지 않습니다.<br>
        이 프로그램은 자유 소프트웨어이며 배포 규정을 만족시키는 조건 아래 자유롭게 재배포할 수 있습니다.<br>
        이에 대한 자세한 사항은 본 프로그램의 구현을 담은 다음 레포지토리에서 확인하십시오: <a target='_blank' href='{이 서버의 소스 코드 위치}'>{이 서버의 소스 코드 위치}</a>
      </div>
    </div>
    <Seprator len="10"/>
  </div>
</template>