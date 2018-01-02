package ru.shaldnikita.Tags.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
@SpringView
public class AccessDeniedView extends AccessDeniedViewDesign implements View {

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
