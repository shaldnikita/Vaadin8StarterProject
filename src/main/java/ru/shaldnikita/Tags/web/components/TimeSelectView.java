package ru.shaldnikita.Tags.web.components;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shaldnikita.Tags.backend.repositories.RegRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringView
public class TimeSelectView extends TimeSelectViewDesign implements View {

    @Autowired
    RegRepository regRepository;

    @PostConstruct
    public void init() {
        dateSelect.setPlaceholder("Выберите дату..");
        dateSelect.setTextFieldEnabled(false);
        dateSelect.addValueChangeListener(e -> {
            updateTime(e.getValue());
        });

    }

    private void updateTime(LocalDate e) {
        if (e == null)
            return;


        clearContent();

        List<Label> labelList = new ArrayList<>();
        regRepository.findOneByDateEquals(e).getTime().stream()
                .sorted()
                .forEachOrdered(time -> {
                    Label label = new Label();
                    label.setValue(time.toString() + " - " +LocalTime.of(time.getHour()+1,time.getMinute()));
                    labelList.add(label);
                });

        for (int i = 0; i < labelList.size() / 2; i++) {
            leftTimeLayout.addComponent(labelList.get(i));
        }

        for (int i = labelList.size() / 2; i < labelList.size(); i++) {
            rightTimeLayout.addComponent(labelList.get(i));
        }
    }

    private void clearContent() {
        leftTimeLayout.removeAllComponents();
        rightTimeLayout.removeAllComponents();
    }

}
