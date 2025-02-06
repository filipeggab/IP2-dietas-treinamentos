package br.ufrpe.treinos_dietas.exceptions;

public class DataInvalidaException extends Exception{
    public DataInvalidaException(){
        super("Data de nascimento impossivel.");
    }
}
