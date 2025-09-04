// js/register.js
document.getElementById('registerForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const username = document.getElementById('username').value.trim();
  const email = document.getElementById('email').value.trim();
  const password = document.getElementById('password').value.trim();
  const role = document.getElementById('role').value;

  if (!username || username.length < 3) { showAlert('Username must be at least 3 characters', 'error'); return; }
  if (!/^\S+@\S+\.\S+$/.test(email)) { showAlert('Enter a valid email', 'error'); return; }
  if (!password || password.length < 6) { showAlert('Password must be at least 6 characters', 'error'); return; }
  if (!role) { showAlert('Please select a role', 'error'); return; }

  try {
    const newUser = await apiFetch('/users/register', {
      method: 'POST',
      body: JSON.stringify({ username, email, password, role })
    });

    saveUserSession(newUser);
    showAlert('Registered successfully!', 'success');
    setTimeout(() => { window.location.href = 'login.html'; }, 800);
  } catch (err) {
    // apiFetch shows error
  }
});
