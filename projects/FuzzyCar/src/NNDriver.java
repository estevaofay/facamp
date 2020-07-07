import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;

public class NNDriver implements LearningEventListener {

	private Double maxPhi = 0.0;
	private Double maxPositionError = 0.0;
	private Double maxTheta = 0.0;
	private Double minTheta = Double.MAX_VALUE;
	private Double minPhi = Double.MAX_VALUE;
	private Double minPositionError = Double.MAX_VALUE;
	private Double learningRate = 0.02;
	private Double maxError = 0.0001;
	private Integer maxIterations = 30;
	private static XYSeries trainingErrorDataSet;
	private static XYSeries testingErrorDataSet;
	private String trainingData = "trainingData.csv";
	private String testingData = "testingData.csv";
	private String fuzzyDataFilePath = "fuzzyData.csv";
	private String neuralNetworkModelFilePath = "nnDriver.nnet";
	private Integer lineCounter = 0;
	private Integer lineCounterAux = 0;
	private Double theta = 0.0;
	private Double normalizedPhi = 0.0;
	private Double normalizedTheta = 0.0;
	private Double normalizedError = 0.0;
	private Integer graphCounter = 0;
	private LinkedList<Double> phiQueue = new LinkedList<>();
	private LinkedList<Double> positionErrorQueue = new LinkedList<>();
	private LinkedList<Double> thetaQueue = new LinkedList<>();

	public NNDriver(double learningRate, double maxError, int maxIterarions) {
		this.learningRate = learningRate;
		this.maxError = maxError;
		this.maxIterations = maxIterations;
		this.trainingErrorDataSet = new XYSeries("Error", false);
		this.testingErrorDataSet = new XYSeries("Error", false);
	}

	void prepareData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fuzzyDataFilePath));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				lineCounter++;
				String[] tokens = line.split(",");
				double aux = Double.valueOf(tokens[0]);
				// Max & Min Phi
				if (aux > maxPhi) {
					maxPhi = aux;
				}
				if (aux < minPhi) {
					minPhi = aux;
				}
				// Max & Min Position Error
				double aux2 = Double.valueOf(tokens[1]);
				if (aux2 > maxPositionError) {
					maxPositionError = aux2;
				}
				if (aux2 < minPositionError) {
					minPositionError = aux2;
				}
				// Max & Min Theta
				double aux3 = Double.valueOf(tokens[2]);
				if (aux3 > maxTheta) {
					maxTheta = aux3;
				}
				if (aux3 < minTheta) {
					minTheta = aux3;
				}
			}
		} finally {
			reader.close();
		}
		reader = new BufferedReader(new FileReader(fuzzyDataFilePath));

		PrintWriter pw = new PrintWriter(new File(trainingData));
		StringBuilder sb = new StringBuilder();

		try {
			String line;
			while ((line = reader.readLine()) != null && lineCounterAux < ((int) lineCounter * 0.6)) {
				double crtPhi = Double.valueOf(line.split(",")[0]);
				normalizedPhi = normalizePhi(crtPhi);
				sb.append(normalizedPhi);
				sb.append(',');
				
				double crtError = Double.valueOf(line.split(",")[1]);
				normalizedError = normalizeError(crtError);
				sb.append(normalizedError);
				sb.append(',');
				
				double crtTheta = Double.valueOf(line.split(",")[2]);
				normalizedTheta = normalizeTheta(crtTheta);
				sb.append(normalizedTheta);
				sb.append(',');
				sb.append('\n');
				lineCounterAux++;
			}

		} finally {
			reader.close();
			pw.write(sb.toString());
			pw.close();
		}
		try {
			reader = new BufferedReader(new FileReader(fuzzyDataFilePath));
			pw = new PrintWriter(new File(testingData));
			sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null && lineCounterAux < lineCounter) {
				double crtPhi = Double.valueOf(line.split(",")[0]);
				double normalizedPhi = normalizePhi(crtPhi);
				sb.append(normalizedPhi);
				sb.append(',');

				double crtError = Double.valueOf(line.split(",")[1]);
				double normalizedError = normalizeError(crtError);
				sb.append(normalizedError);
				sb.append(',');

				double crtTheta = Double.valueOf(line.split(",")[2]);
				double normalizedTheta = normalizeError(crtTheta);
				sb.append(normalizedTheta);
				sb.append(',');
				sb.append('\n');
				lineCounterAux++;
			}
		} finally {
			reader.close();
			pw.write(sb.toString());
			pw.close();
		}

	}

	private DataSet loadTrainingData(String learningDataFilePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(learningDataFilePath));
		DataSet trainingSet = new DataSet(2, 1);
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				double trainValues[] = new double[2];
				trainValues[0] = Double.valueOf(tokens[0]);
				trainValues[1] = Double.valueOf(tokens[1]);
				
				double expectedValue[] = new double[1];
				expectedValue[0] = Double.valueOf(tokens[2]);
				trainingSet.addRow(trainValues, expectedValue);

			}
		} finally {
			reader.close();
		}
		return trainingSet;
	}

	void trainNetwork() throws IOException {
		NeuralNetwork<BackPropagation> neuralNetwork = new MultiLayerPerceptron(2, 5, 1);
		SupervisedLearning learningRule = neuralNetwork.getLearningRule();
		DataSet trainingSet = loadTrainingData(trainingData);

		// STOP CRITERIA
		learningRule.setMaxError(maxError);
		learningRule.setLearningRate(learningRate);
		learningRule.setMaxIterations(maxIterations);

		// LISTENERS
		learningRule.addListener(this);

		// TRAIN
		prepareTestingData();
		neuralNetwork.learn(trainingSet);
		neuralNetwork.save(neuralNetworkModelFilePath);

		// Show GRAPH
		
		nnGraph();
	}

	@SuppressWarnings("rawtypes")
	public void calculate() {
		double[] networkOutput = null;
		NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(neuralNetworkModelFilePath);

		double[] inputVector = new double[2];
		inputVector[0] = normalizePhi(ParkingLot.getInstance().getSimulatedCar().getPhi());
		inputVector[1] = normalizeError((double) ParkingLot.getInstance().getPositionError());
		neuralNetwork.setInput(inputVector);
		neuralNetwork.calculate();
		networkOutput = neuralNetwork.getOutput();
		theta = denormalizeTheta(networkOutput[0]);
		//theta = networkOutput[0];
	}

	public void prepareTestingData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(testingData));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				double crtPhi = Double.valueOf(tokens[0]);
				double crtPositionError = Double.valueOf(tokens[1]);
				double crtTheta = Double.valueOf(tokens[2]);
				
				phiQueue.addLast(crtPhi);
				positionErrorQueue.addLast(crtPositionError);
				thetaQueue.addLast(crtTheta);
			}
		} finally {
			reader.close();
		}
	}

	@Override
	public void handleLearningEvent(LearningEvent learningEvent) {
		if (graphCounter % 1 == 0) {
			SupervisedLearning rule = (SupervisedLearning) learningEvent.getSource();
			double iteration = rule.getCurrentIteration();
			double networkError = rule.getTotalNetworkError();
			double[] networkOutput = null;

			NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(neuralNetworkModelFilePath);

			double[] inputVector = new double[2];

			inputVector[0] = phiQueue.pop();
			inputVector[1] = positionErrorQueue.pop();
			double theta = thetaQueue.pop();

			neuralNetwork.setInput(inputVector);

			neuralNetwork.calculate();
			networkOutput = neuralNetwork.getOutput();
			
			double outputTheta = networkOutput[0];
			double testingError = Math.pow(theta - outputTheta, 2);

			trainingErrorDataSet.add(iteration, networkError);
			testingErrorDataSet.add(iteration, testingError);
			/*
			System.out.println("theta from queue: " + theta+ " theta from network: " + outputTheta);
			*/
			//System.out.println("testingError: " + testingError);

			
			  System.out.println("Network error for iteration " + rule.getCurrentIteration() + ": " + rule.getTotalNetworkError());
			
		}
		
		 
		 
		
		 
		// finalError = rule.getTotalNetworkError();
		graphCounter++;
	}

	public static void nnGraph() {

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(trainingErrorDataSet);
		dataset.addSeries(testingErrorDataSet);

		LineChart chart = new LineChart("Training Error", ("TrainingError"), dataset, "iterations", "Error");
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}

	public Double getTheta() {
		return theta;
	}


	// NORMALIZATION METHODS
	private double normalizePhi(double input) {
		return (input - minPhi) / (maxPhi - minPhi);
	}

	private double normalizeError(double input) {
		return (input - minPositionError) / (maxPositionError - minPositionError);
	}

	private double normalizeTheta(double input) {
		return (input - minTheta) / (maxTheta - minTheta);
	}

	// DENORMALIZATION METHODS
	private double denormalizePhi(double input) {
		return minPhi + (input * (maxPhi - minPhi));
	}

	private double denormalizeError(double input) {
		return minPositionError + (input * (maxPositionError - minPositionError));
	}

	private double denormalizeTheta(double input) {
		return minTheta + (input * (maxTheta - minTheta));
	}

}
