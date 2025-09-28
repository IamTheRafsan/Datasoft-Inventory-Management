<script setup lang="ts">
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import api from "../axios";

const router = useRouter();

// Form fields
const name = ref("");
const code = ref("");
const brandId = ref<number | null>(null);
const categoryId = ref<number | null>(null);
const description = ref("");
const status = ref("Available");

// Data for category and brand dropdowns
const brands = ref<{ id: number; name: string; code: string }[]>([]);
const categories = ref<{ id: number; name: string; code: string }[]>([]);

// Fetch brands for dropdown
const fetchBrands = async () => {
  try {
    const response = await api.get("/brand");
    brands.value = response.data;
  } catch (error) {
    console.error("Error fetching brands:", error);
  }
};

// Fetch categories for dropdown
const fetchCategories = async () => {
  try {
    const response = await api.get("/category");
    categories.value = response.data;
  } catch (error) {
    console.error("Error fetching categories:", error);
  }
};

// Form submit
const submitForm = async () => {
  if (!name.value || !code.value || !brandId.value || !categoryId.value) {
    alert("Please fill all required fields!");
    return;
  }

  try {
    await api.post("/product/add", {
      name: name.value,
      code: code.value,
      brandId: brandId.value,
      categoryId: categoryId.value,
      description: description.value,
      status: status.value,
    });
    alert("Product added successfully!");
    router.push("/product");
  } catch (error) {
    console.error("Error adding product:", error);
    alert("Failed to add product.");
  }
};

// Fetch data when component mounts
onMounted(() => {
  fetchBrands();
  fetchCategories();
});
</script>

<template>
  <div class="form-container">
    <!-- Header Section -->
    <div class="form-header">
      <div class="header-icon">📦</div>
      <h1 class="form-title">Add New Product</h1>
      <p class="form-subtitle">Fill in the details to create a new product</p>
    </div>

    <form @submit.prevent="submitForm" class="product-form">
      <!-- Basic Information Card -->
      <div class="form-card">
        <div class="card-header">
          <span class="card-icon">📝</span>
          <h2 class="card-title">Basic Information</h2>
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
            />
            <span class="input-icon"></span>
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
            />
            <span class="input-icon"></span>
          </div>
        </div>
      </div>

      <!-- Category & Brand Card -->
      <div class="form-card">
        <div class="card-header">
          <span class="card-icon">🏷️</span>
          <h2 class="card-title">Category & Brand</h2>
        </div>

        <div class="form-grid">
          <div class="input-group">
            <label class="input-label">
              Brand <span class="required">*</span>
            </label>
            <div class="select-container">
              <select v-model.number="brandId" class="form-select" required>
                <option value="" disabled>Select a brand</option>
                <option
                  v-for="brand in brands"
                  :key="brand.id"
                  :value="brand.id"
                >
                  {{ brand.name }} ({{ brand.code }})
                </option>
              </select>
              <span class="select-icon">⭐</span>
            </div>
          </div>

          <div class="input-group">
            <label class="input-label">
              Category <span class="required">*</span>
            </label>
            <div class="select-container">
              <select v-model.number="categoryId" class="form-select" required>
                <option value="" disabled>Select a category</option>
                <option
                  v-for="category in categories"
                  :key="category.id"
                  :value="category.id"
                >
                  {{ category.name }} ({{ category.code }})
                </option>
              </select>
              <span class="select-icon">📂</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Additional Details Card -->
      <div class="form-card">
        <div class="card-header">
          <span class="card-icon">📋</span>
          <h2 class="card-title">Additional Details</h2>
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
              v-for="option in ['Available', 'Soldout', 'Lowstock']"
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
        <router-link to="/product" class="cancel-button">
          <span class="button-icon">←</span>
          Back to Products
        </router-link>
        <div class="action-buttons">
          <button type="submit" class="submit-button">
            <span class="button-icon">+</span>
            Add Product
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped>
.form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
  font-family: "Inter", -apple-system, BlinkMacSystemFont, sans-serif;
}

/* Header Styles */
.form-header {
  text-align: center;
  margin-bottom: 2rem;
}

.header-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.form-title {
  font-size: 2.25rem;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 0.5rem;
}

.form-subtitle {
  color: #6b7280;
  font-size: 1.1rem;
}

/* Form Cards */
.form-card {
  background: white;
  border-radius: 16px;
  padding: 2rem;
  margin-bottom: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  border: 1px solid #f3f4f6;
  transition: transform 0.3s ease;
  box-sizing: border-box;
  width: 100%;
}

.form-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
}

.card-header {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1.5rem;
  padding-bottom: 1rem;
  border-bottom: 2px solid #f3f4f6;
}

.card-icon {
  font-size: 1.5rem;
}

.card-title {
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
  border-color: #10b981;
  background: white;
  box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);
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
  border-color: #10b981;
  background: white;
  box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);
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
  border-color: #10b981;
  background: #d1fae5;
  color: #065f46;
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
  border-color: #10b981;
  background: #10b981;
}

/* Form Actions */
.form-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2rem;
  padding-top: 2rem;
  border-top: 2px solid #f3f4f6;
}

.cancel-button {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.5rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  text-decoration: none;
  color: #6b7280;
  font-weight: 500;
  transition: all 0.3s ease;
}

.cancel-button:hover {
  background: #f3f4f6;
  border-color: #d1d5db;
}

.action-buttons {
  display: flex;
  gap: 1rem;
}

.submit-button {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 2rem;
  background: linear-gradient(135deg, #10b981, #059669);
  color: white;
  border: none;
  border-radius: 10px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 6px rgba(16, 185, 129, 0.2);
}

.submit-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 15px rgba(16, 185, 129, 0.3);
}

/* Responsive Design */
@media (max-width: 768px) {
  .form-container {
    padding: 1rem;
  }

  .form-actions {
    flex-direction: column;
    gap: 1rem;
  }

  .action-buttons {
    width: 100%;
    justify-content: stretch;
  }

  .draft-button,
  .submit-button {
    flex: 1;
    justify-content: center;
  }

  .progress-steps {
    gap: 1rem;
  }

  .step-label {
    font-size: 0.8rem;
  }
}

/* dropdown styles */

.select-container {
  position: relative;
  display: flex;
  align-items: center;
}

.form-select {
  width: 100%;
  padding: 0.75rem 1rem 0.75rem 2.5rem;
  border: 2px solid #e5e7eb;
  border-radius: 10px;
  font-size: 0.95rem;
  transition: all 0.3s ease;
  background: #fafafa;
  box-sizing: border-box;
  appearance: none;
  cursor: pointer;
}

.form-select:focus {
  outline: none;
  border-color: #10b981;
  background: white;
  box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);
}

.form-select:disabled {
  background: #f3f4f6;
  color: #9ca3af;
  cursor: not-allowed;
}

.select-icon {
  position: absolute;
  left: 0.75rem;
  top: 50%;
  transform: translateY(-50%);
  color: #9ca3af;
  pointer-events: none;
}

/* Custom dropdown arrow */
.select-container::after {
  content: "▼";
  position: absolute;
  right: 1rem;
  top: 50%;
  transform: translateY(-50%);
  color: #6b7280;
  font-size: 0.8rem;
  pointer-events: none;
}

/* Loading state for dropdowns */
.loading-text {
  color: #6b7280;
  font-style: italic;
}

/* Option styling */
.form-select option {
  padding: 0.5rem;
  background: white;
}
</style>
