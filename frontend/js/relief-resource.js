const resourceForm = document.getElementById('resourceForm');
const shelterSelect = document.getElementById('shelterId');
const resourcesTable = document.querySelector('#resourcesTable tbody');

function validateResource(r) {
  if (!r.resourceName || r.resourceName.length < 2) return 'Resource name must be at least 2 characters.';
  if (!r.resourceType || r.resourceType.length < 2) return 'Type is required.';
  if (isNaN(r.quantity) || r.quantity < 0) return 'Quantity must be 0 or more.';
  if (!r.location || r.location.length < 2) return 'Location is required.';
  if (!r.status || r.status.length < 2) return 'Status is required.';
  if (!r.shelterId) return 'Select a shelter.';
  return null;
}

async function loadSheltersForResources() {
  const shelters = await apiFetch('/shelters');
  shelterSelect.innerHTML = shelters
    .map(s => `<option value="${s.id}">${s.name} (${s.location})</option>`)
    .join('');
}

async function loadResources() {
  const resources = await apiFetch('/resources');
  resourcesTable.innerHTML = '';
  resources.forEach(r => {
    resourcesTable.innerHTML += `<tr>
      <td>${r.id}</td>
      <td>${r.resourceName}</td>
      <td>${r.resourceType}</td>
      <td>${r.quantity}</td>
      <td>${r.location}</td>
      <td>${r.status}</td>
      <td>${r.shelter?.name || ''}</td>
    </tr>`;
  });
}

resourceForm.addEventListener('submit', async e => {
  e.preventDefault();
  const payload = {
    resourceName: document.getElementById('resourceName').value.trim(),
    resourceType: document.getElementById('resourceType').value.trim(),
    quantity: parseInt(document.getElementById('resourceQuantity').value, 10),
    location: document.getElementById('resourceLocation') ? document.getElementById('resourceLocation').value.trim() : '',
    status: document.getElementById('resourceStatus') ? document.getElementById('resourceStatus').value.trim() : 'Available',
    shelterId: parseInt(shelterSelect.value, 10)
  };
  const err = validateResource(payload);
  if (err) { showAlert(err, 'error'); return; }

  // Backend expects nested shelter object to bind ManyToOne
  await apiFetch('/resources', {
    method: 'POST',
    body: JSON.stringify({
      resourceName: payload.resourceName,
      resourceType: payload.resourceType,
      quantity: payload.quantity,
      location: payload.location,
      status: payload.status,
      shelter: { id: payload.shelterId }
    })
  });
  showAlert('Resource added', 'success');
  loadResources();
});

document.addEventListener('DOMContentLoaded', () => {
  loadSheltersForResources();
  loadResources();
});
