<template>
  <div id="app">
    <Hybrid></Hybrid>
  </div>
</template>

<script>
import Hybrid from "@/components/Hybrid";

export default {
  name: 'App',
  components: {
    Hybrid
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
