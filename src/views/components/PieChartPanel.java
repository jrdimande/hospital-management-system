package views.components;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {

    public PieChartPanel() {

        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:16; background:#edf2fb;");
        setBackground(Color.decode("#edf2fb"));


        PieChart chart = new PieChartBuilder()
                .width(400)
                .height(300)
                .title("Pacientes")
                .build();

        chart.addSeries("Normal", 40);
        chart.addSeries("Urgente", 25);
        chart.addSeries("Prioritário", 15);

        chart.getStyler().setChartBackgroundColor(Color.decode("#edf2fb"));
        chart.getStyler().setPlotBackgroundColor(Color.decode("#edf2fb"));
        chart.getStyler().setPlotBorderVisible(false);


        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setBackground(Color.decode("#edf2fb"));

        add(chartPanel, BorderLayout.CENTER);
    }
}