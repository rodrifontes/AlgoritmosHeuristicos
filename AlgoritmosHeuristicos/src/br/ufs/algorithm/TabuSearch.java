package br.ufs.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import br.ufs.benchmark.IBenchmark;

public class TabuSearch extends BaseAlgorithm {
	
	//Comprimento m�ximo da lista tabu
	private int lengthTabu;
	//Numero de Tweak Desejado
	private int nTweak;
	//N�mero de Itera��es do Algoritmo
	protected int iterations;
	
	public TabuSearch(int lengthArray, double p, int rangeSolution, int min, int max, double rangeTweak, int lengthTabu, int iterations, int nTweak) {
		super(lengthArray, p, rangeSolution, min, max, rangeTweak);
		this.lengthTabu = lengthTabu;
		this.iterations = iterations;
		this.nTweak = nTweak;
	}
	
	public double[] execute(IBenchmark b) {
		
		double[] s = initSolution(lengthArray);
		double[] best = s;
		Queue<Integer> tabu = new LinkedList<>(); 
		tabu.add(b.quality(s).intValue());
		double[] evolutionQuality = new double[iterations];
		
		int cont = 0;
		while(cont < iterations) {
			
			evolutionQuality[cont] = b.quality(best);
			
			if(tabu.size() > lengthTabu) {
				tabu.remove();
			}
			
			double[] r = tweak(copy(s));
			
			for (int i = 0; i < nTweak; i++) {

				double[] w = tweak(copy(s));
	
				if((!tabu.contains(b.quality(w).intValue())) && (b.quality(w) < b.quality(r) || tabu.contains(b.quality(r).intValue())))
					r = w;

			}
			
			if(!tabu.contains(b.quality(r).intValue())) {
				s = r;
				tabu.add(b.quality(r).intValue());
			}
			
			if(b.quality(s) < b.quality(best))
				best = s;
			
			cont++;
			
		}
		
		return evolutionQuality;
		
	}

}
