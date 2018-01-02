package ru.shaldnikita.Tags.backend.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@NamedEntityGraphs({@NamedEntityGraph(name = "Reg.time",
        attributeNodes = { @NamedAttributeNode("time")})})
public class Reg extends AbstractEntity {

    private LocalDate date;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<LocalTime> time;

    private boolean selected;

    public List<LocalTime> getTime() {
        return time;
    }

    public void setTime(List<LocalTime> time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
