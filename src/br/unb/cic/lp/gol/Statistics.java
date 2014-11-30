package br.unb.cic.lp.gol;

/**
 * Essa tambem eh uma classe com baixa coesao, pois mustura caracteristicas de
 * Model (as propriedades) com caracteristicas de view (metodo display())
 *
 * Nao eh uma boa implementacao.
 *
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class Statistics {

    private int revivedCells;
    private int killedCells;
    private int generations;

    public Statistics() {
        revivedCells = 0;
        killedCells = 0;
        generations = 0;
    }

    public int getRevivedCells() {
        return revivedCells;
    }

    public void recordRevive() {
        this.revivedCells++;
    }

    public int getKilledCells() {
        return killedCells;
    }

    public void recordKill() {
        this.killedCells++;
    }

    public void incNumOfGenerations() {
        generations++;
    }
    public void resetNumOfGenerations() {
        this.generations = 0;
    }

    public int getNumOfGenerations() {
        return generations;
    }
}
