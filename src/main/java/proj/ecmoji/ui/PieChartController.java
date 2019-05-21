package address.view;

import java.awt.event.ActionEvent;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import address.model.Comment;


public class PieChartController {
    @FXML
    private PieChart piechart;

//    @FXML
//    private void handleButton1Action(ActionEvent event){
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("January", 100),
//                new PieChart.Data("February", 200),
//                new PieChart.Data("March", 50),
//                new PieChart.Data("April", 75),
//                new PieChart.Data("May", 110),
//                new PieChart.Data("June", 300),
//                new PieChart.Data("July", 111),
//                new PieChart.Data("August", 30),
//                new PieChart.Data("September", 75),
//                new PieChart.Data("October", 55),
//                new PieChart.Data("November", 225),
//                new PieChart.Data("December", 99));
//        piechart.setTitle("Monthly Record");
//        piechart.setData(pieChartData);
//    }
//
//    @FXML
//    private void handleButton2Action(ActionEvent event) {
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//                        new PieChart.Data("Sunday", 30),
//                        new PieChart.Data("Monday", 45),
//                        new PieChart.Data("Tuesday", 70),
//                        new PieChart.Data("Wednesday", 97),
//                        new PieChart.Data("Thursday", 100),
//                        new PieChart.Data("Friday", 80),
//                        new PieChart.Data("Saturday", 10));
//
//        piechart.setTitle("Weekly Record");
//        piechart.setData(pieChartData);
//    }
//    @FXML
//    private void handleButtonClearAction(ActionEvent event) {
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList();
//        piechart.setTitle("");
//        piechart.setData(pieChartData);
//    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleButton1Action(javafx.event.ActionEvent actionEvent) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("January", 1000),
                new PieChart.Data("February", 200),
                new PieChart.Data("March", 50),
                new PieChart.Data("April", 75),
                new PieChart.Data("May", 110),
                new PieChart.Data("June", 300),
                new PieChart.Data("July", 111),
                new PieChart.Data("August", 30),
                new PieChart.Data("September", 75),
                new PieChart.Data("October", 55),
                new PieChart.Data("November", 225),
                new PieChart.Data("December", 99));
//        piechart.setTitle("Monthly Record");
        piechart.setData(pieChartData);
    }

    public void handleButton2Action(javafx.event.ActionEvent actionEvent) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Sunday", 30),
                        new PieChart.Data("Monday", 45),
                        new PieChart.Data("Tuesday", 70),
                        new PieChart.Data("Wednesday", 97),
                        new PieChart.Data("Thursday", 100),
                        new PieChart.Data("Friday", 80),
                        new PieChart.Data("Saturday", 10));

//        piechart.setTitle("Weekly Record");
        piechart.setData(pieChartData);
    }

    public void handleButtonClearAction(javafx.event.ActionEvent actionEvent) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
//        piechart.setTitle("");
        piechart.setData(pieChartData);
    }
}
