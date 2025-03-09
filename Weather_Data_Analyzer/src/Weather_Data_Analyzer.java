import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Weather_Data_Analyzer {

    /** Represents weather data */
    public record WeatherRecord(String date, double temperature, double humidity, boolean isRainy) {

    }

    /** Functional interface */
    public interface WeatherFilter extends Predicate<WeatherRecord> {

    }

    /** Parses weather data */
    public static List<WeatherRecord> parseWeatherData(String filePath) throws IOException {
        return List.of();
    }

    /** Computes average temperature for a month */
    public static double averageTemperature(List<WeatherRecord> records, String month) {
        return records.stream()
                .filter(r -> r.date.contains(month))
                .mapToDouble(WeatherRecord::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /** Finds days with temperatures */
    public static List<WeatherRecord> hotDays(List<WeatherRecord> records, double threshold) {
        return records.stream().filter(r -> r.temperature > threshold).toList();
    }

    /** Counts the number of rainy days */
    public static long countRainyDays(List<WeatherRecord> records) {
        return records.stream().filter(WeatherRecord::isRainy).count();
    }

    /** Categorizes weather using an enhanced switch statement */
    public static String categorizeWeather(double temperature) {
        return switch ((int) temperature / 10) {
            case 3, 4 -> "Hot";
            case 2 -> "Warm";
            case 1, 0 -> "Cold";
            default -> "Extreme";
        };
    }

    /** Main Method */
    public static void main(String[] args) throws IOException {
        List<WeatherRecord> data = parseWeatherData("weatherdata.csv");
        System.out.println("Average Temperature in March: " + averageTemperature(data, "March"));
        System.out.println("Hot Days above 30°C: " + hotDays(data, 30).size());
        System.out.println("Number of Rainy Days: " + countRainyDays(data));
    }
}