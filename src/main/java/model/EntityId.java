package model;

import java.util.Objects;

public class EntityId {
    private final int value;

    private EntityId(int valueId) {
        this.value = valueId;
    }


    public static EntityId of(int value) {
        return new EntityId(value);
    }

    public int getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId that = (EntityId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
