package statcalc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class StatisticsPief {

    List<Entry> entries;
    Map<String, Double> statisticData;

    public StatisticsPief() {
        entries = new ArrayList<>();
        statisticData = new HashMap<>();
    }

    public boolean addEntry(Entry entry) {
        return entries.add(entry);
    }

    public void addData(String identifier, Double value) {
        statisticData.put(identifier, value);
    }

    /* Para facilitar, podemos já jogar os valores no HashMap dentro dos próprios
     métodos.
     Também, em todos os métodos, testar se já existe o valor que está sendo
     calculado no HashMap. Ex: no método da média, checar se há um valor designado
     para a chave 'media'.
     Não checamos todos os métodos, só os primeiros! Podem haver erros nos
     outros. E testem, pra ver se eles funcionam na prática. */
    public double mean() {
        double sum = 0;
        for (Entry entry : entries) {
            sum += entry.getTemperature();
        }
        sum = sum / entries.size();
        statisticData.put("mean", sum);
        return (sum);
    }

    //Mediana - REVISÃO
    public double median() {
        double mediana;
        if (entries.size() % 2 == 0) {
            int pos = entries.size();
            mediana = entries.get((pos + 1) / 2).getTemperature();
            /* Testar se a posição é quebrada. Se sim, fazer a média */

        } else {
            int pos1 = (entries.size() / 2);
            pos1--;
            int pos2 = pos1 + 1;
            mediana = (entries.get(pos1).getTemperature() + entries.get(pos2).getTemperature());
            mediana /= 2;
        }
        statisticData.put("median", mediana);
        return mediana;
    }

    public double mode() {
        double md = 0;
        int maxcount = 0;
        for (int i = 0; i < entries.size(); i++) {
            int count = 0;
            for (int j = 0; j < entries.size(); j++) {
                if (entries.get(j).getTemperature() == entries.get(i).getTemperature()) {
                    count++;
                }
                if (count > maxcount) {
                    maxcount = count;
                    md = entries.get(i).getTemperature();
                }
            }
        }
        statisticData.put("mode", md);
        return md;
    }

    public double variance() {
        double pow = 0;
        double sum = 0;
        for (int i = 0; i < entries.size(); i++) {
            pow = Math.pow(entries.get(i).getTemperature() - mean(), 2);
            sum += pow;
        }
        sum = sum / (entries.size() - 1);
        statisticData.put("variance", sum);
        return (sum);
    }

    public double standardDeviation() {
        double sD = Math.sqrt(variance());
        statisticData.put("standardDeviation", sD);
        return sD;
    }

    public double meanDeviation() {
        double abs = 0;
        double sum = 0;
        for (int i = 0; i < entries.size(); i++) {
            abs = Math.abs(entries.get(i).getTemperature() - mean());
            sum += abs;
        }
        sum = sum / entries.size();
        statisticData.put("meanDeviation", sum);
        return sum;
    }

    public double standardMeanError() {
        double sMr = standardDeviation() / Math.sqrt(entries.size());
        statisticData.put("standardMeanError", sMr);
        return sMr;
    }

    public double variationCoefficient() {
        double cv = standardDeviation() * 100 / mean();
        statisticData.put("variationCoefficient", cv);
        return cv;
    }

    public double asymmetryCoefficient() {
        double n = entries.size() / ((entries.size() - 1) * (entries.size() - 2));
        double sum = 0;
        double pow = 0;
        for (int i = 0; i < entries.size(); i++) {
            pow = (entries.get(i).getTemperature() - mean()) / standardDeviation();
            sum += Math.pow(pow, 3);
        }
        sum = n * sum;
        statisticData.put("asymmetryCoefficient", sum);
        return sum;
    }

    public double maximumValue() {
        double max = 0;
        for (int i = 0; i < entries.size(); i++) {
            if (i == 0) {
                max = entries.get(i).getTemperature();
            } else if (entries.get(i).getTemperature() > entries.get(i + 1).getTemperature()) {
                max = entries.get(i).getTemperature();
            } else {
                max = entries.get(i + 1).getTemperature();
            }
        }
        statisticData.put("maximumValue", max);
        return max;
    }

    public double minimumValue() {
        double min = 0;
        for (int i = 0; i < entries.size(); i++) {
            if (i == 0) {
                min = entries.get(i).getTemperature();
            } else if (min < entries.get(i + 1).getTemperature()) {
                min = entries.get(i).getTemperature();
            } else {
                min = entries.get(i + 1).getTemperature();
            }
        }
        statisticData.put("minimumValue", min);
        return min;
    }

    public double numberOfEntries() {
        int n = entries.size();
        return n;

    }

    public double firstQuartile() {
        double pos = entries.size() / 4;
        double Q1;
        int pos1 = (int) pos;
        int pos2 = (int) pos + 1;
        double posQ = (pos1 + pos2) / 2;
        if (posQ > pos) {
            pos2 = (int) posQ + 1;
            Q1 = (entries.get(pos1).getTemperature() + entries.get(pos2).getTemperature()) / 2;
        } else {
            pos2 = (int) posQ;
            Q1 = (entries.get(pos1).getTemperature() + entries.get(pos2).getTemperature()) / 2;
        }
        statisticData.put("firstQuartile", Q1);
        return Q1;
    }

    public double thirdQuartile() {
        double pos = 3 * (entries.size() / 4);
        double Q3;
        int pos1 = (int) pos;
        int pos2 = (int) pos + 1;
        double posQ = (pos1 + pos2) / 2;
        if (posQ > pos) {
            pos2 = (int) posQ + 1;
            Q3 = (entries.get(pos1).getTemperature() + entries.get(pos2).getTemperature()) / 2;
        } else {
            pos2 = (int) posQ;
            Q3 = (entries.get(pos1).getTemperature() + entries.get(pos2).getTemperature()) / 2;
        }
        statisticData.put("thirdQuartile", Q3);
        return Q3;
    }

    public double interquartileAmplitude() {
        double AIQ = thirdQuartile() - firstQuartile();
        statisticData.put("interquartileAmplitude", AIQ);
        return AIQ;
    }

    public JFreeChart createGraph(int type) {

        JFreeChart chart = null;
        switch (type) {
            case 0:
                break;
            case 1:
                chart = ChartFactory.createScatterPlot("Histograma", "Temperatura", "Frequência", createScatterPlotXYDataset(), PlotOrientation.VERTICAL, false, true, false);
                break;
            case 2:
                chart = ChartFactory.createHistogram("Histograma", "Temperatura", "Frequência", createHistogramXYDataset(), PlotOrientation.VERTICAL, false, true, false);
                break;

        }

        if (chart != null) {
            //Algumas customizações (cores)            
            chart.setBackgroundPaint(Color.white);
            XYPlot plot = chart.getXYPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);

            NumberAxis axis2 = (NumberAxis) plot.getRangeAxis();
            axis2.setTickUnit(new NumberTickUnit(1));
        }

        return chart;
    }

    private IntervalXYDataset createHistogramXYDataset() {

        XYSeries series = new XYSeries("Data");

        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry entry1, Entry entry2) {
                return entry1.compareTo(entry2);
            }
        });

        int count = 0;
        for (int i = 0; i < entries.size() - 1; i++) {
            Entry previous = null;
            if (i - 1 >= 0) {
                previous = entries.get(i - 1);
            }

            if (previous == null || previous.getTemperature() == entries.get(i).getTemperature()) {
                count++;
            } else {
                series.add(previous.getTemperature(), count);
                count = 1;
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        return dataset;
    }

    private XYDataset createScatterPlotXYDataset() {
        XYSeries series = new XYSeries("");

        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry entry1, Entry entry2) {
                return entry1.compareTo(entry2);
            }
        });

        int count = 0;
        for (int i = 0; i < entries.size() - 1; i++) {
            Entry previous = null;
            if (i - 1 >= 0) {
                previous = entries.get(i - 1);
            }

            if (previous == null || previous.getTemperature() == entries.get(i).getTemperature()) {
                count++;
            } else {
                series.add(previous.getTemperature(), count);
                count = 1;
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
}
