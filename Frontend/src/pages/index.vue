<script setup lang="ts">
useHead({
  title: '글자로 놀자! 끄투 온라인',
})
</script>

<script lang="ts">
import "@/assets/css/in_portal.css"
import "@/assets/css/in_game_kkutu.css"
</script>

<template>
  <NuxtLayout name="default">
    <template #middle>
      <div style="float: left; width: 100%; margin: 5px 0px;"></div>
      <div style="float: left; width: 100%">
        <div style="float: left; padding-left: 70px;">
          <h3 id="holder">글자로 놀자! 끄투 온라인</h3>
          <img id="logo" src="/img/kkutu/short_logo.png"/>
        </div>
        <div id="menu-bar" style="float: right; margin-top:25px;">
          <a target="_blank" href="https://cafe.naver.com/sdbx" style="background-color: #63A95B;">Sandbox</a>
          <a target="_blank" href="http://blog.jjo.kr/" style="background-color: #4078DC;">JJoriping</a>
        </div>
        <div class="jjoriping" style="margin-top: 40px;">
          <img class="jjoObj jjoEyeL" src="/img/jjoeyeL.png" style="left: -270px;"/>
          <img class="jjoObj jjoNose" src="/img/jjonose.png" style="left: -170px;"/>
          <img class="jjoObj jjoEyeR" src="/img/jjoeyeR.png" style="left: -70px;"/>
          <div class="jjoDisplayBar" style="width: 325px; height: 85px;">
            <button id="game-start" class="jjo-display">게임 시작</button>
          </div>
        </div>
      </div>
    </template>
  </NuxtLayout>
</template>