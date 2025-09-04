document.addEventListener('DOMContentLoaded', async () => {
  try {
    const reports = await apiFetch('/reports');
    const tbody = document.querySelector('#reportsTable tbody');
    tbody.innerHTML = '';
    reports.forEach(r => {
      tbody.innerHTML += `<tr>
        <td>${r.id}</td>
        <td>${r.type}</td>
        <td>${r.location}</td>
        <td>${r.severity}</td>
        <td>${r.description}</td>
        <td>${r.reportedBy?.username || ''}</td>
        <td>${r.reportedAt || ''}</td>
      </tr>`;
    });
  } catch {}
});
