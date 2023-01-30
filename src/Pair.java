import java.util.*;
public class Pair implements Comparable<Pair> {
    private char value; // value
    private double prob; // prob



    public Pair(char value,double prob){
        this.value = value;
        this.prob = prob;
    }

    public Pair(){

    }

    public char getValue() {
        return value;
    }

    public double getProb() {
        return prob;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    @Override
    public String toString() {
        return "pair{" +
                "value=" + value +
                ", prob=" + prob +
                '}';
    }

    @Override
    public int compareTo(Pair p) {
        return Double.compare(this.getProb(),p.getProb());
    }
}
