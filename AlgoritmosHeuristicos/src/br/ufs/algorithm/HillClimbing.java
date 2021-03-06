package br.ufs.algorithm;

import br.ufs.benchmark.IBenchmark;

public class HillClimbing extends BaseAlgorithm {

	//N�mero de Itera��es do Algoritmo
	protected int iterations;
	
	public HillClimbing(int lengthArray, double p, int rangeSolution, int min, int max, double rangeTweak, int iterations) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.iterations = iterations;
	}
	
	/**
	 * Executa o HillClimbing
	 * 
	 * @return Evolu��o da Qualidade da Solu��o 
	 */
	public double[] execute(IBenchmark b) {

		double[] s = initSolution();
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while (cont < iterations) {
			evolutionQuality[cont] = b.quality(s);
			double[] r = tweak(copy(s));
			if (b.quality(r) < b.quality(s)) {
				s = r;
			}
			cont++;
		}

		return evolutionQuality;

	}

}
