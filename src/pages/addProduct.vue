<script setup lang="ts">
import { ref } from "vue";
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

// Handle form submit
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
      <div class="form-card">
        <div class="card-header">
          <span class="card-icon">🏷️</span>
          <h2 class="card-title">Category & Brand</h2>
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
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
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
  
  .draft-button, .submit-button {
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
</style>