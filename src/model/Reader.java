/*
 * created by max$
 */


package model;

public class Reader {
    private String name;
    private int idReader;

    public Reader(String name, int idReader) {
        this.name = name;
        this.idReader = idReader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", idReader=" + idReader +
                '}';
    }
}

