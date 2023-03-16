package model;

import CustomException.NoteNegatifException;

// value object
public class NoteConcours {

    private final int note;

    public NoteConcours(int note) {
        if(note < 0)
            throw new NoteNegatifException("La note ne peut être négatif");
        if(note > 20)
            throw new NoteNegatifException("La note ne peut être supérieur à 20");
        this.note = note;
    }

    public int getNote() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteConcours that = (NoteConcours) o;

        return note == that.note;
    }

    @Override
    public int hashCode() {
        return note;
    }
}
