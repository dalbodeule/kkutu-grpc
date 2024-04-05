<script setup lang="ts">
import {useAuthStore} from "~/stores/auth";

useHead({
  title: '글자로 놀자! 끄투 온라인',
})

const user = useAuthStore()
user.getUserMeta()
</script>

<style src="@/assets/css/style.css"/>
<style scoped src="@/assets/css/in_portal.css"/>
<style scoped src="@/assets/css/in_game_kkutu.css"/>

<template>
  <NuxtLayout name="default">
    <template #middle>
      <Seprator len="5"/>
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
        <div style="float: right; margin-top: -120px; width: 50%;">
          <div class="server-list-box">
            <h3 class="server-list-title">
              <a id="server-refresh">
                <i class="fa fa-refresh"></i>
                <div class="expl" style="width: initial">
                  <h5>Refresh</h5>
                </div>
                <label>서버 목록</label>
                <label id="server-total" style="color: #AAAAAA; font-size: 13px;">&nbsp; 총 0명</label>
              </a>
            </h3>
            <div id="server-list">
              <div class="server">
                <div class="server-status ss-o"></div>
                <div class="server-name">감자</div>
                <div class="server-people graph">
                  <div class="graph-bar" style="width: 1%;"></div>
                  <label>4 / 400</label>
                </div>
                <div class="server-enter">접속</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div style="float: left; width: calc(100% - 4px); border: 2px solid #CCC; margin-top: 10px; background-color: #EEE;">
        <iframe class="kkutu-bulletin" height="400px" width="100%" src="/kkutu_builtin.html" />
      </div>
      <Seprator len="10"/>
    </template>
    <template #jungle>
      <img id="Background" class="jt-image" style="background-image: url(&quot;/img/kkutu/gamebg.png&quot;); background-size: 200px 200px;"/>
    </template>
    <template #bottom>
      <div class="bottom-legal">
        사전 정보 제공:<a href='http://wordnet.princeton.edu' target='_blank'>WordNet</a><br>
        <label style='color: #777777;'>WordNet 3.0 Copyright 2006 by Princeton University. All rights reserved.</label></div>
      <div class="bottom-legal">
        도움:<a href='http://cafe.naver.com/sdbx' target='_blank'>샌드박스 :: SDBX</a>
      </div>
    </template>
  </NuxtLayout>
</template>