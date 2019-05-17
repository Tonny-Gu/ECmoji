package address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import address.model.Comment;

/**
 * The controller for the birthday statistics view.
 *
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
//        System.out.println(months);
//        String[] months = {"Good","good","third","fourth"};
        // Convert it to a list and add it to our ObservableList of months.
//        monthNames.addAll(Arrays.asList(months));
        String[] test = {"good","better","least","fourth"};
        monthNames.addAll(Arrays.asList(test));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     *
     * @param comments
     */
    public void setCommentData(List<Comment> comments) {
        // Count the number of people having their birthday in a specific month.
//        int[] monthCounter = new int[12];
        // Count the number of people having their birthday in a specific month.
//        int[] monthCounter = new int[12];
        int[] monthCounter = new int[4];


        for (Comment p : comments) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}