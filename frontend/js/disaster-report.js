const form = document.getElementById('disasterReportForm');

function validateReport(body) {
  if (!body.type || body.type.length < 3) return 'Type must be at least 3 characters.';
  if (!body.location || body.location.length < 2) return 'Location is required.';
  if (!['HIGH','MEDIUM','LOW'].includes(body.severity)) return 'Severity must be HIGH, MEDIUM or LOW.';
  if (!body.description || body.description.length < 5) return 'Description must be at least 5 characters.';
  return null;
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const user = getUserSession();
  if (!user) {
    showAlert('Please login first', 'error');
    return;
  }
  const body = {
    type: document.getElementById('type').value.trim(),
    location: document.getElementById('location').value.trim(),
    severity: document.getElementById('severity').value.trim().toUpperCase(),
    description: document.getElementById('description').value.trim()
  };

  const err = validateReport(body);
  if (err) { showAlert(err, 'error'); return; }

  try {
    await apiFetch(`/reports/create/${user.id}`, {
      method: 'POST',
      body: JSON.stringify(body)
    });
    showAlert('Report submitted!', 'success');
    setTimeout(() => window.location.href = 'dashboard.html', 600);
  } catch {
    // apiFetch already shows errors
  }
});
