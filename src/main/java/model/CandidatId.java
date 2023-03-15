package model;

import java.util.Objects;

public class CandidatId {
    private final int value;

    private CandidatId(int valueId){this.value = valueId;}


    public static CandidatId of(int value){return new CandidatId(value);}

    public int getValue(){return value;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatId that = (CandidatId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
