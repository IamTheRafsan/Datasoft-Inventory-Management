<template>
  <div class="container">
    <!-- Header Section -->
    <div class="header">
      <h1 class="title">Product Management</h1>
      <p class="subtitle">Manage your products efficiently</p>
    </div>

    <!-- Action Bar -->
    <div class="action-bar">
      <router-link to="/addProduct" class="add-button">
        <span class="button-icon">+</span>
        Add New Product
      </router-link>
      <div class="search-bar">
        <input
          type="text"
          placeholder="Search products..."
          class="search-input"
        />
        <span class="search-icon">🔍</span>
      </div>
    </div>

    <!-- Product Table -->
    <div class="table-container">
      <table class="product-table">
        <thead>
          <tr>
            <th class="table-header">ID</th>
            <th class="table-header">Name</th>
            <th class="table-header">Code</th>
            <th class="table-header">Brand</th>
            <th class="table-header">Category</th>
            <th class="table-header">Status</th>
            <th class="table-header actions-header">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(p, i) in products"
            :key="p.id"
            :class="['table-row', { 'even-row': i % 2 === 0 }]"
          >
            <td class="table-cell id-cell">{{ p.id }}</td>
            <td class="table-cell name-cell">{{ p.name }}</td>
            <td class="table-cell code-cell">
              <span class="code-badge">{{ p.code }}</span>
            </td>
            <td class="table-cell brand-cell">{{ p.brandId }}</td>
            <td class="table-cell category-cell">{{ p.categoryId }}</td>
            <td class="table-cell status-cell">
              <span
                :class="[
                  'status-badge',
                  p.status === 'Available' ? 'status-active' : 'status-inactive',
                ]"
              >
                {{ p.status }}
              </span>
            </td>
            <td class="table-cell actions-cell">
              <div class="action-buttons">
                <router-link :to="`editProduct/${p.id}`" class="edit-button">
                  <span class="button-icon">✏️</span>
                  Edit
                </router-link>
                <button @click="deleteProduct(p.id!)" class="delete-button">
                  <span class="button-icon">🗑️</span>
                  Delete
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="products.length === 0">
            <td colspan="7" class="empty-state">
              <div class="empty-content">
                <span class="empty-icon">📦</span>
                <p class="empty-text">No products found</p>
                <p class="empty-subtext">
                  Add your first product to get started
                </p>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Footer Stats -->
    <div class="footer-stats">
      <span class="stat-item">Total Products: {{ products.length }}</span>
      <span class="stat-item">
        Active: {{ products.filter((p) => p.status === "Available").length }}
      </span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import api from "../axios";

interface Product {
  id?: number;
  name: string;
  code: string;
  brandId: number;
  categoryId: number;
  description: string;
  status: string;
}

const products = ref<Product[]>([]);

// Fetch products
const fetchProducts = async () => {
  try {
    const response = await api.get("/product");
    products.value = response.data;
  } catch (error) {
    console.error("Error fetching products:", error);
  }
};

//Delete product
const deleteProduct = async (id: number) => {
  if (confirm("Are you sure you want to delete this product?")) {
    try {
      await api.delete(`/product/delete/${id}`);
      fetchProducts();
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  }
};

onMounted(() => {
  fetchProducts();
});
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
  font-family: "Inter", -apple-system, BlinkMacSystemFont, sans-serif;
}

/* Header Styles */
.header {
  text-align: center;
  margin-bottom: 2rem;
}

.title {
  font-size: 2.5rem;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 0.5rem;
}

.subtitle {
  color: #6b7280;
  font-size: 1.1rem;
}

/* Action Bar */
.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  gap: 1rem;
}

.add-button {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  background: linear-gradient(135deg, #10b981, #059669);
  color: white;
  padding: 0.75rem 1.5rem;
  border-radius: 12px;
  text-decoration: none;
  font-weight: 600;
  transition: all 0.3s ease;
  box-shadow: 0 4px 6px rgba(16, 185, 129, 0.2);
}

.add-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 15px rgba(16, 185, 129, 0.3);
}

.search-bar {
  position: relative;
  display: flex;
  align-items: center;
}

.search-input {
  padding: 0.75rem 1rem 0.75rem 2.5rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  width: 300px;
  transition: all 0.3s ease;
  font-size: 0.9rem;
}

.search-input:focus {
  outline: none;
  border-color: #10b981;
  box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);
}

.search-icon {
  position: absolute;
  left: 0.75rem;
  color: #9ca3af;
}

/* Table Styles */
.table-container {
  background: white;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05);
  margin-bottom: 1.5rem;
}

.product-table {
  width: 100%;
  border-collapse: collapse;
}

.table-header {
  background: linear-gradient(135deg, #f8fafc, #f1f5f9);
  padding: 1.25rem 1rem;
  text-align: left;
  font-weight: 600;
  color: #374151;
  border-bottom: 2px solid #e5e7eb;
  font-size: 0.9rem;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.actions-header {
  text-align: center;
}

.table-row {
  transition: all 0.3s ease;
  border-bottom: 1px solid #f3f4f6;
}

.table-row:hover {
  background-color: #f8fafc;
  transform: scale(1.01);
}

.even-row {
  background-color: #fafafa;
}

.table-cell {
  padding: 1.25rem 1rem;
  color: #4b5563;
  font-size: 0.95rem;
}

.id-cell {
  font-weight: 600;
  color: #6b7280;
  font-family: "Monaco", "Consolas", monospace;
}

.name-cell {
  font-weight: 600;
  color: #1f2937;
}

.code-badge {
  background: #eff6ff;
  color: #1d4ed8;
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: 500;
  font-family: "Monaco", "Consolas", monospace;
}

.status-badge {
  padding: 0.375rem 1rem;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.status-active {
  background: #d1fae5;
  color: #065f46;
}

.status-inactive {
  background: #fee2e2;
  color: #991b1b;
}

/* Action Buttons */
.actions-cell {
  text-align: center;
}

.action-buttons {
  display: flex;
  gap: 0.5rem;
  justify-content: center;
}

.edit-button,
.delete-button {
  display: flex;
  align-items: center;
  gap: 0.25rem;
  padding: 0.5rem 1rem;
  border-radius: 8px;
  font-size: 0.85rem;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.3s ease;
  border: none;
  cursor: pointer;
}

.edit-button {
  background: #fef3c7;
  color: #d97706;
}

.edit-button:hover {
  background: #fde68a;
  transform: translateY(-1px);
}

.delete-button {
  background: #fee2e2;
  color: #dc2626;
}

.delete-button:hover {
  background: #fecaca;
  transform: translateY(-1px);
}

.button-icon {
  font-size: 0.8rem;
}

/* Empty State */
.empty-state {
  padding: 4rem 2rem;
}

.empty-content {
  text-align: center;
  color: #9ca3af;
}

.empty-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
  display: block;
}

.empty-text {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
}

.empty-subtext {
  font-size: 0.9rem;
}

/* Footer Stats */
.footer-stats {
  display: flex;
  justify-content: center;
  gap: 2rem;
  padding: 1rem;
  background: #f8fafc;
  border-radius: 12px;
  margin-top: 1rem;
}

.stat-item {
  color: #6b7280;
  font-weight: 500;
  font-size: 0.9rem;
}

/* Responsive Design */
@media (max-width: 768px) {
  .container {
    padding: 1rem;
  }

  .action-bar {
    flex-direction: column;
  }

  .search-input {
    width: 100%;
  }

  .action-buttons {
    flex-direction: column;
  }

  .table-header,
  .table-cell {
    padding: 0.75rem 0.5rem;
    font-size: 0.85rem;
  }
}
</style>
