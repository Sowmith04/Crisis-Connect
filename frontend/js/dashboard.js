document.addEventListener('DOMContentLoaded', () => {
  const user = requireLogin();

  document.getElementById('dashboardTitle').innerText = `${user.role} Dashboard`;

  document.getElementById('citizenActions').style.display =
    user.role === 'CITIZEN' ? 'block' : 'none';
  document.getElementById('volunteerActions').style.display =
    user.role === 'VOLUNTEER' ? 'block' : 'none';
  document.getElementById('adminActions').style.display =
    user.role === 'ADMIN' ? 'block' : 'none';
});

function requireLogin() {
  const user = getUserSession();
  if (!user) {
    showAlert('Please login first', 'error');
    window.location.href = 'login.html';
    throw new Error('Not logged in');
  }
  return user;
}
