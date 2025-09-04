const volunteerForm = document.getElementById('volunteerForm');
const volunteerShelterSelect = document.getElementById('volunteerShelterId');
const volunteersTable = document.querySelector('#volunteersTable tbody');

function validateVolunteer(v) {
  if (!v.name || v.name.length < 2) return 'Name must be at least 2 characters.';
  if (v.email && !/^\S+@\S+\.\S+$/.test(v.email)) return 'Enter a valid email.';
  if (v.contactNumber && !/^[0-9+\-() ]{7,20}$/.test(v.contactNumber)) return 'Enter a valid phone.';
  if (!v.shelterId) return 'Select a shelter.';
  return null;
}

async function loadSheltersForVolunteers() {
  const shelters = await apiFetch('/shelters');
  volunteerShelterSelect.innerHTML = shelters
    .map(s => `<option value="${s.id}">${s.name} (${s.location})</option>`)
    .join('');
}

async function loadVolunteers() {
  const volunteers = await apiFetch('/volunteers');
  volunteersTable.innerHTML = '';
  volunteers.forEach(v => {
    volunteersTable.innerHTML += `<tr>
      <td>${v.id}</td>
      <td>${v.name}</td>
      <td>${v.email || ''}</td>
      <td>${v.contactNumber || ''}</td>
      <td>${v.shelter?.name || ''}</td>
    </tr>`;
  });
}

volunteerForm.addEventListener('submit', async e => {
  e.preventDefault();
  const payload = {
    name: document.getElementById('volunteerName').value.trim(),
    email: document.getElementById('volunteerEmail').value.trim(),
    contactNumber: document.getElementById('volunteerPhone').value.trim(),
    skills: document.getElementById('volunteerSkills') ? document.getElementById('volunteerSkills').value.trim() : '',
    availability: true,
    status: 'Active',
    shelterId: parseInt(volunteerShelterSelect.value, 10)
  };
  const err = validateVolunteer(payload);
  if (err) { showAlert(err, 'error'); return; }

  await apiFetch('/volunteers', {
    method: 'POST',
    body: JSON.stringify({
      name: payload.name,
      email: payload.email,
      contactNumber: payload.contactNumber,
      skills: payload.skills,
      availability: payload.availability,
      status: payload.status,
      shelter: { id: payload.shelterId }
    })
  });
  showAlert('Volunteer added', 'success');
  loadVolunteers();
});

document.addEventListener('DOMContentLoaded', () => {
  loadSheltersForVolunteers();
  loadVolunteers();
});
