import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081/inventory", // <-- adjust if your Spring Boot backend runs on another port or path
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;
