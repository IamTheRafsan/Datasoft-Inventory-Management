import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Product from "../pages/product.vue";
import Category from "../pages/category.vue";
import Brand from "../pages/crand.vue";
import AddProduct from "../pages/addProduct.vue";
import EditProduct from "../pages/editProduct.vue";
import AddBrand from "../pages/addBrand.vue";
import EditBrand from "../pages/editBrand.vue";
import AddCategory from "../pages/addCategory.vue";
import EditCategory from "../pages/editCategory.vue";



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
    path: "/addBrand",
    name: "AddBrand",
    component: AddBrand,
  },
  {
  path: "/editBrand/:id",
  name: "EditBrand",
  component: EditBrand,
  },
  {
    path: "/addCategory",
    name: "AddCategory",
    component: AddCategory,
  },
  {
  path: "/editCategory/:id",
  name: "EditCategory",
  component: EditCategory,
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
