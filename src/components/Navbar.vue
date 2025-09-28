<template>
  <nav class="sidebar-nav" :class="{ 'nav-collapsed': isCollapsed }">
    <!-- Logo Section -->
    <div class="logo-section">
      <div class="logo">
        <router-link to="/" class="logo-link">
          <div class="logo-icon">📊</div>
          <div class="logo-text">
            <span class="logo-main">Datasoft</span>
            <span class="logo-sub">Inventory</span>
          </div>
        </router-link>
      </div>
    </div>

    <!-- Navigation Menu -->
    <ul class="nav-menu">
      <li class="nav-item">
        <router-link to="/product" class="nav-link">
          <span class="nav-icon">📦</span>
          <span class="nav-text">Products</span>
        </router-link>
      </li>
      <li class="nav-item">
        <router-link to="/category" class="nav-link">
          <span class="nav-icon">🏷️</span>
          <span class="nav-text">Categories</span>
        </router-link>
      </li>
      <li class="nav-item">
        <router-link to="/brand" class="nav-link">
          <span class="nav-icon">⭐</span>
          <span class="nav-text">Brands</span>
        </router-link>
      </li>
    </ul>


  
  </nav>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";

const isCollapsed = ref(false);
const productCount = ref(42); // This could come from your API

const navItems = [
  { path: '/product', text: 'Products', icon: '📦' },
  { path: '/category', text: 'Categories', icon: '🏷️' },
  { path: '/brand', text: 'Brands', icon: '⭐' }
];
</script>

<style scoped>
.sidebar-nav {
  width: 220px;
  height: 100%;
  background: linear-gradient(135deg, #1e293b 0%, #334155 100%);
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  box-shadow: 4px 0 20px rgba(0, 0, 0, 0.1);
  border-right: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-nav.nav-collapsed {
  width: 80px;
}

/* Logo Section */
.logo-section {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 2rem;
  padding-bottom: 1.5rem;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo-link {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  text-decoration: none;
  color: white;
}

.logo-icon {
  font-size: 2rem;
  background: linear-gradient(135deg, #10b981, #059669);
  padding: 0.5rem;
  border-radius: 12px;
}

.logo-text {
  display: flex;
  flex-direction: column;
  transition: opacity 0.3s ease;
}

.nav-collapsed .logo-text {
  opacity: 0;
  pointer-events: none;
}

.logo-main {
  font-size: 1.25rem;
  font-weight: 700;
  line-height: 1.2;
}

.logo-sub {
  font-size: 0.85rem;
  opacity: 0.8;
  font-weight: 400;
}

/* Navigation Menu */
.nav-menu {
  list-style: none;
  padding: 0;
  margin: 0;
  flex: 1;
}

.nav-item {
  margin-bottom: 0.5rem;
}

.nav-link {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 0.875rem 1rem;
  color: #cbd5e1;
  text-decoration: none;
  border-radius: 12px;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.nav-link::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 3px;
  background: linear-gradient(135deg, #10b981, #059669);
  transform: scaleY(0);
  transition: transform 0.3s ease;
}

.nav-link:hover {
  background: rgba(255, 255, 255, 0.05);
  color: white;
  transform: translateX(5px);
}

.nav-link:hover::before {
  transform: scaleY(1);
}

.nav-link.router-link-active {
  background: linear-gradient(135deg, rgba(16, 185, 129, 0.2), rgba(5, 150, 105, 0.2));
  color: #10b981;
  border-left: 3px solid #10b981;
}

.nav-link.router-link-active::before {
  transform: scaleY(1);
}

.nav-icon {
  font-size: 1.25rem;
  width: 24px;
  text-align: center;
  transition: transform 0.3s ease;
}

.nav-link:hover .nav-icon {
  transform: scale(1.1);
}

.nav-text {
  font-weight: 500;
  transition: opacity 0.3s ease;
  white-space: nowrap;
}

.nav-collapsed .nav-text {
  opacity: 0;
  pointer-events: none;
}


/* Additional Features */
.nav-features {
  margin: 2rem 0;
  padding: 1rem 0;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.feature-item {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 0.75rem 1rem;
  color: #cbd5e1;
  cursor: pointer;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.feature-item:hover {
  background: rgba(255, 255, 255, 0.05);
  color: white;
}

.feature-icon {
  font-size: 1.1rem;
  width: 24px;
  text-align: center;
}

.feature-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex: 1;
  transition: opacity 0.3s ease;
}

.nav-collapsed .feature-content {
  opacity: 0;
  pointer-events: none;
}

.feature-text {
  font-size: 0.9rem;
  font-weight: 500;
}

.feature-badge {
  background: #f59e0b;
  color: white;
  padding: 0.2rem 0.4rem;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
}

/* User Profile */
.user-profile {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 12px;
  margin-top: auto;
}

.user-avatar {
  width: 40px;
  height: 40px;
  background: linear-gradient(135deg, #8b5cf6, #a855f7);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.avatar-icon {
  font-size: 1.25rem;
}

.user-info {
  display: flex;
  flex-direction: column;
  flex: 1;
  transition: opacity 0.3s ease;
}

.nav-collapsed .user-info {
  opacity: 0;
  pointer-events: none;
}

.user-name {
  font-weight: 600;
  color: white;
  font-size: 0.9rem;
}

.user-role {
  font-size: 0.8rem;
  color: #94a3b8;
}

.logout-btn {
  background: rgba(239, 68, 68, 0.1);
  border: none;
  color: #ef4444;
  width: 32px;
  height: 32px;
  border-radius: 8px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.logout-btn:hover {
  background: rgba(239, 68, 68, 0.2);
  transform: scale(1.1);
}

/* Tooltip for Collapsed State */
.nav-tooltip {
  position: fixed;
  left: 80px;
  top: 0;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 1rem;
  pointer-events: none;
}

.tooltip-content {
  background: #1e293b;
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 6px;
  font-size: 0.8rem;
  font-weight: 500;
  margin-bottom: 0.5rem;
  opacity: 0;
  transform: translateX(-10px);
  transition: all 0.3s ease;
  white-space: nowrap;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.nav-collapsed .nav-item:hover .tooltip-content {
  opacity: 1;
  transform: translateX(0);
}

/* Responsive Design */
@media (max-width: 768px) {
  .sidebar-nav {
    width: 100%;
    height: auto;
    position: relative;
    flex-direction: row;
    padding: 1rem;
  }

  .sidebar-nav.nav-collapsed {
    width: 100%;
  }

  .logo-section {
    margin-bottom: 0;
    padding-bottom: 0;
    border-bottom: none;
  }

  .nav-menu {
    display: flex;
    gap: 1rem;
  }

  .nav-features, .user-profile {
    display: none;
  }

  .nav-collapsed .nav-text {
    opacity: 1;
  }
}

/* Smooth transitions for all interactive elements */
.sidebar-nav * {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Scrollbar styling */
.sidebar-nav::-webkit-scrollbar {
  width: 4px;
}

.sidebar-nav::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.1);
}

.sidebar-nav::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.3);
  border-radius: 2px;
}

.sidebar-nav::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 255, 255, 0.5);
}
</style>