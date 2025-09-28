import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";

// Create Vue app, use router, mount to #app
createApp(App).use(router).mount("#app");
