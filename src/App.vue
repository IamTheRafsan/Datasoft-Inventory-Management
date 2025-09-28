<template>
  <div id="app" class="app-container">
    <!-- Sidebar -->
    <aside class="sidebar">
      <Navbar />
    </aside>

    <!-- Main Content -->
    <main class="main-content">
      <router-view />
    </main>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Navbar from "./components/Navbar.vue";

export default defineComponent({
  components: { Navbar },
});
</script>

<style scoped>
.app-container {
  display: flex;
  height: 100vh; /* full viewport height */
}

/* Sidebar */
.sidebar {
  width: 220px;
  background-color: #093581;
  color: white;
  padding: 20px;
}

/* Main content area */
.main-content {
  flex: 1;
  padding: 20px;
  background-color: #f5f5f5;
  min-height: 100vh;
}
</style>
