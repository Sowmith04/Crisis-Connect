const teamForm = document.getElementById('teamForm');
const teamDisasterSelect = document.getElementById('teamDisasterId');
const teamsTable = document.querySelector('#teamsTable tbody');

function validateTeam(t) {
  if (!t.teamName || t.teamName.length < 2) return 'Team name must be at least 2 characters.';
  if (!t.contactNumber || !/^[0-9+\-() ]{7,20}$/.test(t.contactNumber)) return 'Enter a valid contact number.';
  if (!t.location || t.location.length < 2) return 'Location is required.';
  if (!t.disasterReportId) return 'Select a disaster report.';
  return null;
}

async function loadDisasterReportsForTeams() {
  const reports = await apiFetch('/reports');
  teamDisasterSelect.innerHTML = reports
    .map(r => `<option value="${r.id}">${r.type} (${r.location})</option>`)
    .join('');
}

async function loadTeams() {
  const teams = await apiFetch('/rescue-teams');
  teamsTable.innerHTML = '';
  teams.forEach(t => {
    teamsTable.innerHTML += `<tr>
      <td>${t.id}</td>
      <td>${t.teamName}</td>
      <td>${t.contactNumber}</td>
      <td>${t.location}</td>
      <td>${t.assignedDisaster?.type || ''}</td>
    </tr>`;
  });
}

teamForm.addEventListener('submit', async e => {
  e.preventDefault();
  const payload = {
    teamName: document.getElementById('teamName').value.trim(),
    contactNumber: document.getElementById('teamContact').value.trim(),
    specialization: document.getElementById('teamSpecialization') ? document.getElementById('teamSpecialization').value.trim() : '',
    location: document.getElementById('teamLocation') ? document.getElementById('teamLocation').value.trim() : '',
    disasterReportId: parseInt(teamDisasterSelect.value, 10)
  };
  const err = validateTeam(payload);
  if (err) { showAlert(err, 'error'); return; }

  await apiFetch('/rescue-teams/register', {
    method: 'POST',
    body: JSON.stringify({
      teamName: payload.teamName,
      contactNumber: payload.contactNumber,
      specialization: payload.specialization,
      location: payload.location,
      assignedDisaster: { id: payload.disasterReportId }
    })
  });
  showAlert('Rescue team added', 'success');
  loadTeams();
});

document.addEventListener('DOMContentLoaded', () => {
  loadDisasterReportsForTeams();
  loadTeams();
});
