/* ===========================
   utils.js
   Common helper functions
   =========================== */

// Adjust this to your backend root
const API_BASE_URL = "http://localhost:8080/api";

/**
 * Wrapper for fetch() with automatic JSON & error handling
 * Pass only the path after API_BASE_URL, e.g. "/users/register"
 * @param {string} url - endpoint path (starting with "/")
 * @param {object} options - fetch options (method, headers, body)
 */
async function apiFetch(url, options = {}) {
  const fullUrl = url.startsWith("http")
    ? url
    : `${API_BASE_URL}${url}`;

  const defaultHeaders = {
    "Content-Type": "application/json",
  };

  options.headers = {
    ...defaultHeaders,
    ...(options.headers || {}),
  };

  try {
    const response = await fetch(fullUrl, options);
    if (!response.ok) {
      const contentType = response.headers.get("content-type");
      let errorPayload;
      if (contentType && contentType.includes("application/json")) {
        errorPayload = await response.json();
      } else {
        errorPayload = await response.text();
      }
      let msg = typeof errorPayload === 'string' ? errorPayload : JSON.stringify(errorPayload);
      throw new Error(msg || `HTTP ${response.status}`);
    }
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
      return await response.json();
    } else {
      return await response.text();
    }
  } catch (err) {
    console.error("API Fetch error:", err);
    showAlert(`Error: ${err.message}`, "error");
    throw err;
  }
}

/** Store logged-in user info (token/role/id) in localStorage */
function saveUserSession(userObj) {
  localStorage.setItem("loggedInUser", JSON.stringify(userObj));
}

/** Get logged-in user info from localStorage */
function getUserSession() {
  const data = localStorage.getItem("loggedInUser");
  return data ? JSON.parse(data) : null;
}

/** Clear logged-in user info from localStorage */
function clearUserSession() {
  localStorage.removeItem("loggedInUser");
}

/**
 * Simple alert box in page
 * @param {string} message
 * @param {"success"|"error"|"info"} type
 */
function showAlert(message, type = "info") {
  let alertBox = document.getElementById("alertBox");
  if (!alertBox) {
    alertBox = document.createElement("div");
    alertBox.id = "alertBox";
    alertBox.style.position = "fixed";
    alertBox.style.top = "10px";
    alertBox.style.right = "10px";
    alertBox.style.padding = "10px 15px";
    alertBox.style.zIndex = 9999;
    alertBox.style.borderRadius = "5px";
    alertBox.style.fontSize = "14px";
    document.body.appendChild(alertBox);
  }

  let bgColor;
  switch (type) {
    case "success": bgColor = "#4CAF50"; break;
    case "error": bgColor = "#f44336"; break;
    default: bgColor = "#2196F3";
  }

  alertBox.style.backgroundColor = bgColor;
  alertBox.style.color = "#fff";
  alertBox.innerText = message;

  setTimeout(() => {
    alertBox.remove();
  }, 3000);
}

/**
 * Utility to populate a select box with data
 * @param {HTMLSelectElement} selectElement
 * @param {Array} items
 * @param {string} valueField
 * @param {string} textField
 */
function populateSelect(selectElement, items, valueField, textField) {
  selectElement.innerHTML = "";
  items.forEach((item) => {
    const opt = document.createElement("option");
    opt.value = item[valueField];
    opt.textContent = item[textField];
    selectElement.appendChild(opt);
  });
}

/** Logout and redirect to login */
function logout() {
  clearUserSession();
  window.location.href = "login.html";
}

/** Require login helper for pages that need it */
function requireLogin() {
  const user = getUserSession();
  if (!user) {
    showAlert('Please login first', 'error');
    window.location.href = 'login.html';
    throw new Error('Not logged in');
  }
  return user;
}

// Make functions globally available
window.apiFetch = apiFetch;
window.saveUserSession = saveUserSession;
window.getUserSession = getUserSession;
window.clearUserSession = clearUserSession;
window.showAlert = showAlert;
window.populateSelect = populateSelect;
window.logout = logout;
window.requireLogin = requireLogin;
