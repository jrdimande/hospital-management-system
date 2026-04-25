package views.components;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BarChartPanel extends JPanel {

    public BarChartPanel() {

        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:16; background:#edf2fb;");
        setBackground(Color.decode("#edf2fb"));


        CategoryChart chart = new CategoryChartBuilder()
                .width(400)
                .height(300)
                .title("Atendimentos por Dia da Semana")
                .xAxisTitle("Dia")
                .yAxisTitle("Quantidade")
                .build();

        List<String> dias = Arrays.asList(
                "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"
        );

        List<Integer> atendimentos = Arrays.asList(
                20, 35, 28, 40, 50, 18, 10
        );

        chart.addSeries("Atendimentos", dias, atendimentos);


        chart.getStyler().setChartBackgroundColor(Color.decode("#edf2fb"));
        chart.getStyler().setPlotBackgroundColor(Color.decode("#edf2fb"));
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotGridLinesVisible(false);
//        chart.getStyler().setYAxisGridLinesVisible(false);
//        chart.getStyler().setXAxisGridLinesVisible(false);
        //chart.getStyler().setAnnotationsEnabled(true);

        XChartPanel<CategoryChart> chartPanel =
                new XChartPanel<>(chart);

        chartPanel.setBackground(Color.decode("#edf2fb"));

        add(chartPanel, BorderLayout.CENTER);
    }
}