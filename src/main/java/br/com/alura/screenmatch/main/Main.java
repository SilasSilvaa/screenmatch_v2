package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DataSeries;
import br.com.alura.screenmatch.model.DataSeason;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.service.APIConsumption;
import br.com.alura.screenmatch.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final APIConsumption consumption = new APIConsumption();
    private static final ConvertData convertData = new ConvertData();
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=9f3ad196";
    private static final List<DataSeries> seriesList = new ArrayList<>();

    public static void showMenu() {

        int option = -1;
        while (option != 0) {
            var menu = """
                    1 - Search Series
                    2 - Search Episodes
                    3 - List of series searched
                    0 - Exit
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchSeriesWeb();
                    break;
                case 2:
                    searchEpPerSeries();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }

    }

    private static void listSearchedSeries(){
        List<Series> series = new ArrayList<>();
        series = seriesList.stream()
                        .map(Series::new)
                                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Series::getGenero))
                .forEach(System.out::println);
    }

    private static void searchSeriesWeb() {
        DataSeries data = getSeriesData();
        seriesList.add(data);
        System.out.println(data);
    }

    private static DataSeries getSeriesData() {
        System.out.println("Type name for search series");
        var seriesName = scanner.nextLine();
        var json = consumption.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        return convertData.getData(json, DataSeries.class);
    }

    private static void searchEpPerSeries() {
        DataSeries dataSeries = getSeriesData();
        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSeries.totalTemporadas(); i++) {
            var json = consumption.getData(ADDRESS + dataSeries.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeason dataSeason = convertData.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }
        seasons.forEach(System.out::println);
    }
}