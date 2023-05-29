package br.univesp.analisedados.helpers;

public class QueriesConstantes {

	//para calcular o per capita, tratando os null e zeros
	public final static String POP_ESTIMATE_PER_CAPITA = """
			( 
				CASE when 
					t.populationEst is null
					or t.populationEst = 0 
				then 1
				else t.populationEst
				end
				)
			""";
	
	public final static String CALC_PIB_PER_CAPITA = """ 
			( 
				CASE when 
					t.populationEst is null
					or t.populationEst = 0 
				then 0
				else p.totalGdp
				end
				)/
			"""
			+ POP_ESTIMATE_PER_CAPITA;
	
	public final static String CALC_CO2_PER_CAPITA = """ 
			( 
				CASE when 
					t.populationEst is null
					or t.populationEst = 0 
				then 0
				else c.AnnualCo
				end
				)/
			"""
			+ POP_ESTIMATE_PER_CAPITA;
}
