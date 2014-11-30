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

    private int createdCells;
    private int generatedCells;
    private int killedCells;
    private int alivedCells;
    private int numOfGenerations;

    public Statistics() {
        createdCells = 0;
        generatedCells = 0;
        killedCells = 0;
        alivedCells = 0;
        numOfGenerations = 0;
    }

    public Statistics(int createdCells, int generatedCells, int killedCells, int alivedCells, int generations) {
        this.createdCells = createdCells;
        this.generatedCells = generatedCells;
        this.killedCells = killedCells;
        this.alivedCells = alivedCells;
        this.numOfGenerations = generations;
    }

    public int getCreatedCells() {
        return createdCells;
    }

    public void incCreatedCells() {
        createdCells++;
        alivedCells++;
    }
    
    public int getGeneratedCells() {
        return generatedCells;
    }

    public void incGeneratedCells() {
        generatedCells++;
        alivedCells++;
    }

    public int getKilledCells() {
        return killedCells;
    }

    public void incKilledCells() {
        killedCells++;
        alivedCells--;
    }

    public int getAlivedCells() {
        return alivedCells;
    }

    public int getNumOfGenerations() {
        return numOfGenerations;
    }
    
    public void incNumOfGenerations() {
        numOfGenerations++;
    }
}
