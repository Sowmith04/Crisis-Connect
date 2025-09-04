const form = document.getElementById('alertForm');
const select = document.getElementById('disasterReportId');
const tableBody = document.querySelector('#manageAlertsTable tbody');

function validateAlert(a) {
  if (!a.message || a.message.length < 5) return 'Message must be at least 5 characters.';
  if (!['HIGH','MEDIUM','LOW'].includes(a.severity)) return 'Severity must be HIGH, MEDIUM or LOW.';
  if (!a.location || a.location.length < 2) return 'Location is required.';
  if (!a.disasterReportId) return 'Select a Disaster Report.';
  return null;
}

async function loadDisasterReports() {
  const reports = await apiFetch('/reports');
  select.innerHTML = reports.map(r => `<option value="${r.id}">${r.type} (${r.location})</option>`).join('');
}

async function loadAlerts() {
  const alerts = await apiFetch('/alerts');
  tableBody.innerHTML = '';
  alerts.forEach(a => {
    tableBody.innerHTML += `<tr>
      <td>${a.id}</td>
      <td>${a.message}</td>
      <td>${a.severity}</td>
      <td>${a.location}</td>
      <td>${a.disasterReport?.type || ''}</td>
      <td>${a.createdBy?.username || ''}</td>
    </tr>`;
  });
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    message: document.getElementById('message').value.trim(),
    severity: document.getElementById('severity').value.trim().toUpperCase(),
    location: document.getElementById('location').value.trim(),
    disasterReportId: parseInt(select.value, 10)
  };
  const err = validateAlert(payload);
  if (err) { showAlert(err, 'error'); return; }

  await apiFetch(`/alerts?disasterReportId=${payload.disasterReportId}`, {
    method: 'POST',
    body: JSON.stringify({
      message: payload.message,
      severity: payload.severity,
      location: payload.location
    })
  });
  showAlert('Alert created', 'success');
  loadAlerts();
});

document.addEventListener('DOMContentLoaded', () => {
  loadDisasterReports().then(loadAlerts);
});
