package lesson2;

public class MyArrayDataException extends Exception {
    public int line;
    public int column;

    public MyArrayDataException(int line, int column) {
        this.line = line;
        this.column = column;
    }
}
