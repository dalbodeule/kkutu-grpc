<script lang="ts">
import "@/assets/css/style.css"
</script>

<template>
  <div id="Top">
    <div class="Menu">
      <button class="menu-btn" onclick="location.href='http://jjo.kr';">HOME</button>
      <div id="account">
        <span id="profile">TEST</span>
        <div id="account-info"></div>
      </div>
    </div>
    <div id="gn-content">
    </div>
    <div id="global-notice">
    </div>
    <slot name="top"/>
  </div>
  <div id="jungle">
    <img id="Background" src="/img/bg/def.png"/>
  </div>
  <div id="Middle" style="margin-left: 780px;">
    <slot name="middle"/>
  </div>
  <div id="bottom">
    <slot name="bottom"/>
    <div id="bottom-legal">

    </div>
  </div>
</template>