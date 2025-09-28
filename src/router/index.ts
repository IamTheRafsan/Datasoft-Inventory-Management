import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Product from "../Pages/Product.vue";
import Category from "../Pages/Category.vue";
import Brand from "../Pages/Brand.vue";
import AddProduct from "../pages/addProduct.vue";
import EditProduct from "../pages/editProduct.vue";


// Define route objects with type
const routes: Array<RouteRecordRaw> = [
  {
    path: "/product",
    name: "Product",
    component: Product,
  },
  {
    path: "/category",
    name: "Category",
    component: Category,
  },
  {
    path: "/brand",
    name: "Brand",
    component: Brand,
  },
  {
    path: "/addProduct",
    name: "AddProduct",
    component: AddProduct,
  },
  {
  path: "/editProduct/:id",
  name: "EditProduct",
  component: EditProduct
  },
  {
    path: "/",
    redirect: "/product",
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL), // Vite env variable
  routes, // <-- pass the array directly
});

export default router;
