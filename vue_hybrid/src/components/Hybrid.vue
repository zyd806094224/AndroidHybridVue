<template>

  <div>
    <div>Android调用js方法！{{ value }}</div>
    <br/>
    <div @click="showAndroidToast">点我调用Android吐司</div>
    <br/>
    <div @click="onFinishActivity">页面销毁</div>
  </div>

</template>

<script>

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Hybrid",
  data() {
    return {
      value: '我是js'
    }
  },
  methods: {
    // Android调用js方法
    callJsFunction(str) {
      this.value = str;
      return "js调用成功";
    },
    // js调用Android方法
    showAndroidToast() {
      // eslint-disable-next-line no-undef
      $Android.showToast("我是js调用的");
    },
    onFinishActivity() {
      // eslint-disable-next-line no-undef
      $Android.onFinishActivity();
    }
  },
  mounted() {
    //将要给原生调用的方法挂载到 window 上面，方便原生直接调用
    window.callJsFunction = this.callJsFunction;
  }
}
</script>

<style scoped>

</style>
