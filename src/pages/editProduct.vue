<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import api from "../axios";

const route = useRoute();
const router = useRouter();

const productId = route.params.id as string;

// Form fields
const name = ref("");
const code = ref("");
const brandId = ref<number | null>(null);
const categoryId = ref<number | null>(null);
const description = ref("");
const status = ref("Available");

// Loading state
const isLoading = ref(true);
const isUpdating = ref(false);

// Fetch product data by ID
const fetchProduct = async () => {
  try {
    const response = await api.get(`/product/${productId}`);
    const product = response.data;
    name.value = product.name;
    code.value = product.code;
    brandId.value = product.brandId;
    categoryId.value = product.categoryId;
    description.value = product.description;
    status.value = product.status;
  } catch (error) {
    console.error("Error fetching product:", error);
    alert("Failed to load product.");
  } finally {
    isLoading.value = false;
  }
};

// Update product
const updateProduct = async () => {
  if (!name.value || !code.value || !brandId.value || !categoryId.value) {
    alert("Please fill all required fields!");
    return;
  }

  isUpdating.value = true;
  try {
    await api.put(`/product/update/${productId}`, {
      name: name.value,
      code: code.value,
      brandId: brandId.value,
      categoryId: categoryId.value,
      description: description.value,
      status: status.value,
    });
    alert("Product updated successfully!");
    router.push("/product");
  } catch (error) {
    console.error("Error updating product:", error);
    alert("Failed to update product.");
  } finally {
    isUpdating.value = false;
  }
};

onMounted(() => {
  fetchProduct();
});
</script>

<template>
  <div class="edit-container">
    <!-- Header Section -->
    <div class="page-header">
      <div class="header-content">
        <router-link to="/product" class="back-button">
          <span class="back-icon">←</span>
          Back to Products
        </router-link>
        <div class="header-info">
          <div class="header-icon">✏️</div>
          <div>
            <h1 class="page-title">Edit Product</h1>
            <p class="page-subtitle">Update product information</p>
            <div class="product-id">Product ID: #{{ productId }}</div>
          </div>
        </div>
      </div>
    </div>

    <!-- Loading State -->
    <div v-if="isLoading" class="loading-state">
      <div class="loading-spinner"></div>
      <p>Loading product details...</p>
    </div>

    <!-- Edit Form -->
    <form v-else @submit.prevent="updateProduct" class="edit-form">
      <!-- Basic Information Card -->
      <div class="form-section">
        <div class="section-header">
          <span class="section-icon">📋</span>
          <h2 class="section-title">Product Information</h2>
        </div>
        
        <div class="form-grid">
          <div class="input-group">
            <label class="input-label">
              Product Name <span class="required">*</span>
            </label>
            <input
              v-model="name"
              type="text"
              class="form-input"
              placeholder="Enter product name"
              required
            />
            <span class="input-icon">🏷️</span>
          </div>

          <div class="input-group">
            <label class="input-label">
              Product Code <span class="required">*</span>
            </label>
            <input
              v-model="code"
              type="text"
              class="form-input"
              placeholder="Enter unique code"
              required
            />
            <span class="input-icon">🔢</span>
          </div>
        </div>
      </div>

      <!-- Category & Brand Card -->
      <div class="form-section">
        <div class="section-header">
          <span class="section-icon">🏷️</span>
          <h2 class="section-title">Category & Brand</h2>
        </div>
        
        <div class="form-grid">
          <div class="input-group">
            <label class="input-label">
              Brand ID <span class="required">*</span>
            </label>
            <div class="input-with-button">
              <input
                v-model.number="brandId"
                type="number"
                class="form-input"
                placeholder="Enter brand ID"
                required
              />
              <button type="button" class="browse-button" title="Browse brands">
                📋
              </button>
            </div>
          </div>

          <div class="input-group">
            <label class="input-label">
              Category ID <span class="required">*</span>
            </label>
            <div class="input-with-button">
              <input
                v-model.number="categoryId"
                type="number"
                class="form-input"
                placeholder="Enter category ID"
                required
              />
              <button type="button" class="browse-button" title="Browse categories">
                📋
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Additional Details Card -->
      <div class="form-section">
        <div class="section-header">
          <span class="section-icon">📝</span>
          <h2 class="section-title">Additional Details</h2>
        </div>
        
        <div class="input-group">
          <label class="input-label">Description</label>
          <div class="textarea-container">
            <textarea
              v-model="description"
              class="form-textarea"
              placeholder="Enter product description..."
              rows="4"
            ></textarea>
            <span class="char-count">{{ description.length }}/500</span>
          </div>
        </div>

        <div class="input-group">
          <label class="input-label">Status</label>
          <div class="status-selector">
            <label 
              v-for="option in ['Available', 'Lowstock', 'Soldout']" 
              :key="option"
              :class="['status-option', { active: status === option }]"
            >
              <input
                type="radio"
                :value="option"
                v-model="status"
                class="radio-input"
              />
              <span class="status-label">{{ option }}</span>
              <span class="status-indicator"></span>
            </label>
          </div>
        </div>
      </div>


      <!-- Form Actions -->
      <div class="form-actions">
        <div class="action-buttons">
          <button 
            type="button" 
            class="cancel-button"
            @click="router.push('/product')"
          >
            <span class="button-icon">×</span>
            Cancel
          </button>
          <button 
            type="button" 
            class="reset-button"
            @click="fetchProduct"
          >
            <span class="button-icon">↺</span>
            Reset Changes
          </button>
          <button 
            type="submit" 
            class="update-button"
            :disabled="isUpdating"
          >
            <span class="button-icon" v-if="!isUpdating">✓</span>
            <span class="loading-spinner-small" v-else></span>
            {{ isUpdating ? 'Updating...' : 'Update Product' }}
          </button>
        </div>
      </div>
    </form>

  </div>
</template>

<style scoped>
.edit-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
}

/* Header Styles */
.page-header {
  background: linear-gradient(135deg, #fff7ed, #ffedd5);
  border-radius: 16px;
  padding: 2rem;
  margin-bottom: 2rem;
  border-left: 4px solid #f59e0b;
}

.header-content {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.back-button {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  color: #d97706;
  text-decoration: none;
  font-weight: 500;
  transition: all 0.3s ease;
  padding: 0.5rem 1rem;
  border-radius: 8px;
  background: rgba(245, 158, 11, 0.1);
  width: fit-content;
}

.back-button:hover {
  background: rgba(245, 158, 11, 0.2);
  transform: translateX(-5px);
}

.header-info {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.header-icon {
  font-size: 3rem;
}

.page-title {
  font-size: 2rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 0.25rem;
}

.page-subtitle {
  color: #6b7280;
  margin-bottom: 0.5rem;
}

.product-id {
  background: #f59e0b;
  color: white;
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: 600;
  width: fit-content;
}

/* Loading State */
.loading-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 4rem 2rem;
  color: #6b7280;
}

.loading-spinner {
  width: 3rem;
  height: 3rem;
  border: 3px solid #e5e7eb;
  border-left: 3px solid #f59e0b;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin-bottom: 1rem;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Form Sections */
.form-section {
  background: white;
  border-radius: 16px;
  padding: 2rem;
  margin-bottom: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  border: 1px solid #f3f4f6;
  transition: transform 0.3s ease;
}

.form-section:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
}

.section-header {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1.5rem;
  padding-bottom: 1rem;
  border-bottom: 2px solid #f3f4f6;
}

.section-icon {
  font-size: 1.5rem;
}

.section-title {
  font-size: 1.25rem;
  font-weight: 600;
  color: #1f2937;
}

/* Form Grid */
.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
}

@media (max-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr;
  }
}

/* Input Groups */
.input-group {
  position: relative;
}

.input-label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 600;
  color: #374151;
  font-size: 0.9rem;
}

.required {
  color: #ef4444;
}

.form-input {
  width: 100%;
  padding: 0.75rem 1rem 0.75rem 2.5rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  font-size: 0.95rem;
  transition: all 0.3s ease;
  background: #fafafa;
  box-sizing: border-box;
}

.form-input:focus {
  outline: none;
  border-color: #f59e0b;
  background: white;
  box-shadow: 0 0 0 3px rgba(245, 158, 11, 0.1);
}

.input-icon {
  position: absolute;
  left: 0.75rem;
  top: 50%;
  transform: translateY(-50%);
  color: #9ca3af;
}

/* Input with Button */
.input-with-button {
  position: relative;
  display: flex;
}

.browse-button {
  position: absolute;
  right: 0.5rem;
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  padding: 0.25rem;
  border-radius: 6px;
  transition: background 0.3s ease;
}

.browse-button:hover {
  background: #f3f4f6;
}

/* Textarea */
.textarea-container {
  position: relative;
}

.form-textarea {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  font-size: 0.95rem;
  transition: all 0.3s ease;
  background: #fafafa;
  resize: vertical;
  font-family: inherit;
  box-sizing: border-box;
}

.form-textarea:focus {
  outline: none;
  border-color: #f59e0b;
  background: white;
  box-shadow: 0 0 0 3px rgba(245, 158, 11, 0.1);
}

.char-count {
  position: absolute;
  bottom: 0.5rem;
  right: 0.75rem;
  font-size: 0.8rem;
  color: #9ca3af;
}

/* Status Selector */
.status-selector {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
}

.status-option {
  position: relative;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.5rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  background: #fafafa;
}

.status-option:hover {
  border-color: #d1d5db;
}

.status-option.active {
  border-color: #f59e0b;
  background: #fef3c7;
  color: #d97706;
}

.radio-input {
  position: absolute;
  opacity: 0;
}

.status-label {
  font-weight: 500;
  z-index: 1;
}

.status-indicator {
  width: 1rem;
  height: 1rem;
  border: 2px solid #d1d5db;
  border-radius: 50%;
  transition: all 0.3s ease;
}

.status-option.active .status-indicator {
  border-color: #f59e0b;
  background: #f59e0b;
}

/* Change History */
.change-history {
  background: #f0f9ff;
  border-radius: 12px;
  padding: 1.5rem;
  margin: 2rem 0;
  border-left: 4px solid #0ea5e9;
}

.history-title {
  font-size: 1rem;
  font-weight: 600;
  color: #0369a1;
  margin-bottom: 1rem;
}

.history-items {
  display: grid;
  gap: 0.5rem;
}

.history-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.history-label {
  font-weight: 500;
  color: #0c4a6e;
}

.history-value {
  color: #0369a1;
  font-size: 0.9rem;
}

/* Form Actions */
.form-actions {
  margin-top: 2rem;
}

.action-buttons {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
}

.cancel-button, .reset-button, .update-button {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 10px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  text-decoration: none;
}

.cancel-button {
  background: #f3f4f6;
  color: #6b7280;
}

.cancel-button:hover {
  background: #e5e7eb;
  transform: translateY(-1px);
}

.reset-button {
  background: #fef3c7;
  color: #d97706;
  border: 2px solid #f59e0b;
}

.reset-button:hover {
  background: #fde68a;
  transform: translateY(-1px);
}

.update-button {
  background: linear-gradient(135deg, #f59e0b, #d97706);
  color: white;
  box-shadow: 0 4px 6px rgba(245, 158, 11, 0.2);
}

.update-button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 8px 15px rgba(245, 158, 11, 0.3);
}

.update-button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
  transform: none;
}

.loading-spinner-small {
  width: 1rem;
  height: 1rem;
  border: 2px solid transparent;
  border-left: 2px solid white;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

/* Quick Actions */
.quick-actions {
  background: #f8fafc;
  border-radius: 12px;
  padding: 1.5rem;
  margin-top: 2rem;
}

.actions-title {
  font-size: 1rem;
  font-weight: 600;
  color: #374151;
  margin-bottom: 1rem;
}

.action-buttons-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 1rem;
}


/* Responsive Design */
@media (max-width: 768px) {
  .edit-container {
    padding: 1rem;
  }
  
  .action-buttons {
    flex-direction: column;
  }
  
  .header-info {
    flex-direction: column;
    text-align: center;
  }
  
  .product-id {
    align-self: center;
  }
}
</style>