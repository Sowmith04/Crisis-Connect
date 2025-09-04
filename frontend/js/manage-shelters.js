const shelterForm = document.getElementById('shelterForm');
const sheltersTable = document.querySelector('#manageSheltersTable tbody');

function validateShelter(s) {
  if (!s.name || s.name.length < 2) return 'Shelter name must be at least 2 characters.';
  if (!s.location || s.location.length < 2) return 'Location is required.';
  if (isNaN(s.capacity) || s.capacity < 0) return 'Capacity must be 0 or more.';
  return null;
}

async function loadShelters() {
  const shelters = await apiFetch('/shelters');
  sheltersTable.innerHTML = shelters.map(s =>
    `<tr><td>${s.id}</td><td>${s.name}</td><td>${s.location}</td><td>${s.capacity}</td></tr>`
  ).join('');
}

shelterForm.addEventListener('submit', async e => {
  e.preventDefault();
  const body = {
    name: document.getElementById('shelterName').value.trim(),
    location: document.getElementById('shelterLocation').value.trim(),
    capacity: parseInt(document.getElementById('shelterCapacity').value, 10),
    occupied: 0,
    contact: document.getElementById('shelterContact') ? document.getElementById('shelterContact').value.trim() : ''
  };
  const err = validateShelter(body);
  if (err) { showAlert(err, 'error'); return; }

  await apiFetch('/shelters', {
    method: 'POST',
    body: JSON.stringify(body)
  });
  showAlert('Shelter added', 'success');
  loadShelters();
});

document.addEventListener('DOMContentLoaded', loadShelters);
