<script setup lang="ts">
  const props = defineProps(['len'])
</script>

<template>
  <div style="float: left; width: 100%;" v-bind:style="`margin: ${props.len || 10}px; 0px`"></div>
</template>

<style scoped>

</style>