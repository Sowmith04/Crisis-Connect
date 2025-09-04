document.getElementById('loginForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const email = document.getElementById('email').value.trim();
  const password = document.getElementById('password').value;

  if (!email || !/^\S+@\S+\.\S+$/.test(email)) {
    showAlert('Please enter a valid email', 'error'); return;
  }
  if (!password || password.length < 6) {
    showAlert('Password must be at least 6 characters', 'error'); return;
  }

  try {
    const user = await apiFetch('/users/login', {
      method: 'POST',
      body: JSON.stringify({ email, password })
    });

    if (!user || !user.id) {
      showAlert('Invalid credentials', 'error'); return;
    }

    saveUserSession(user);
    showAlert('Login successful', 'success');
    setTimeout(() => window.location.href = 'dashboard.html', 500);
  } catch (err) {
    // apiFetch shows alert
  }
});
