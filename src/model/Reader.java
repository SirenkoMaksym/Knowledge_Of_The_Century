/*
 * created by max$
 */


package model;

public class Reader {
    private String name;
    private int readerId;

    public Reader(String name, int readerId) {
        this.name = name;
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", readerId=" + readerId +
                '}';
    }
}
