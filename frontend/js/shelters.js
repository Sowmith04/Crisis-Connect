document.addEventListener('DOMContentLoaded', async () => {
  try {
    const shelters = await apiFetch('/shelters');
    const tbody = document.querySelector('#sheltersTable tbody');
    tbody.innerHTML = shelters.map(s => `
      <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.location}</td>
        <td>${s.capacity}</td>
        <td>${s.reliefResources?.length || 0}</td>
      </tr>`).join('');
  } catch {}
});
