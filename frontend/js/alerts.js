document.addEventListener('DOMContentLoaded', async () => {
  try {
    const alerts = await apiFetch('/alerts');
    const tbody = document.querySelector('#alertsTable tbody');
    tbody.innerHTML = '';
    alerts.forEach(a => {
      tbody.innerHTML += `<tr>
        <td>${a.id}</td>
        <td>${a.message}</td>
        <td>${a.severity}</td>
        <td>${a.location}</td>
        <td>${a.disasterReport?.type || ''}</td>
        <td>${a.createdBy?.username || ''}</td>
      </tr>`;
    });
  } catch (e) {
    // showAlert called in apiFetch on error already
  }
});
