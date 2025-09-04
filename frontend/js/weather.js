const WEATHER_API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";

async function getWeather() {
  const city = document.getElementById("cityInput").value;
  if (!city) {
    showAlert("Please enter a city name", "error");
    return;
  }
  try {
    const res = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${encodeURIComponent(
        city
      )}&appid=${WEATHER_API_KEY}&units=metric`
    );
    if (!res.ok) throw new Error("Weather API error");
    const data = await res.json();
    const html = `
      <p><strong>${data.name}</strong>: ${data.weather[0].description}</p>
      <p>Temp: ${data.main.temp}°C</p>
      <p>Humidity: ${data.main.humidity}%</p>
    `;
    document.getElementById("weatherResult").innerHTML = html;
  } catch (err) {
    console.error(err);
    showAlert("Could not fetch weather", "error");
  }
}
