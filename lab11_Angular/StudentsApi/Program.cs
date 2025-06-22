using Microsoft.EntityFrameworkCore;
using StudentsApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Rejestracja usług
builder.Services.AddDbContext<StudentContext>(
    opt => opt.UseInMemoryDatabase("StudentList"));


builder.Services.AddCors();
builder.Services.AddControllers(); // <-- TO DODAJ
builder.Services.AddOpenApi();

var app = builder.Build();

// Konfiguracja środowiska
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

// app.UseHttpsRedirection(); // możesz włączyć, jeśli chcesz HTTPS

// Mapa endpointu pogody (demo)
var summaries = new[]
{
    "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
};

app.MapGet("/weatherforecast", () =>
{
    var forecast = Enumerable.Range(1, 5).Select(index =>
        new WeatherForecast(
            DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
            Random.Shared.Next(-20, 55),
            summaries[Random.Shared.Next(summaries.Length)]
        ))
        .ToArray();
    return forecast;
})
.WithName("GetWeatherForecast");



app.UseCors(options =>
options.WithOrigins("http://localhost:4200")
.AllowAnyMethod()
.AllowAnyHeader());
app.UseAuthorization();
// TO DODAJ — bez tego /api/students NIE ZADZIAŁA
app.MapControllers();

app.Run();

record WeatherForecast(DateOnly Date, int TemperatureC, string? Summary)
{
    public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);
}
