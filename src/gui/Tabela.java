/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manoela
 */
public class Tabela extends AbstractTableModel{
    
    private ArrayList linha = null;   
    private String[] coluna = null; // armazenar o nome das colunas
    
    public Tabela(ArrayList i, String[] j){
        setLinha(i);
        setColuna(j);
    }
    
     public ArrayList getLinha() {
        return linha;
    }

    public void setLinha(ArrayList dados) {
        linha = dados;
    }

    public String[] getColuna() {
        return coluna;
    }

    public void setColuna(String[] nomeColuna) {
        coluna = nomeColuna;
    }
    
    @Override
    public int getColumnCount(){
        return coluna.length; // qtd decaracteres q terá na coluna
    }
    
    @Override
    public int getRowCount(){
        return linha.size(); //qnts elementos no array
    }
    
    @Override
    public String getColumnName(int qtdColuna){
        return coluna[qtdColuna]; // retorna os nomes
        
    }
    
    @Override
    public Object getValueAt(int qtdLinha, int qtdColuna){ //inform. para montar a tabela
        //Object[] linha = (Object[])getLinha().get(qtdLinha); // objeto recebe o nuero de linhas q foi passado por parâmetro
        Object[] linha2 = (Object[])getLinha().get(qtdLinha);
        return linha2[qtdColuna]; //retorna para a tabela linhas q foram passadas(dados) de acordo com o número de colunas
    }
}
