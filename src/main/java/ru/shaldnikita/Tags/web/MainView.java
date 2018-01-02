package ru.shaldnikita.Tags.web;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.navigator.ViewLeaveAction;
import com.vaadin.spring.access.SecuredViewAccessControl;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.web.components.TimeSelectView;
import ru.shaldnikita.Tags.web.navigation.NavigationManager;
import ru.shaldnikita.Tags.web.view.ProfileView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@SpringViewDisplay
@UIScope
public class MainView extends MainViewDesign implements ViewDisplay,HasLogger{

    private final Map<Class<? extends View>, Button> navigationButtons = new HashMap<>();
    private final NavigationManager navigationManager;
    private final SecuredViewAccessControl viewAccessControl;

    @Autowired
    public MainView(NavigationManager navigationManager, SecuredViewAccessControl viewAccessControl) {
        this.navigationManager = navigationManager;
        this.viewAccessControl = viewAccessControl;
    }

    @PostConstruct
    public void init() {
        attachNavigation(profileButton, ProfileView.class);
        attachNavigation(table, TimeSelectView.class);

        logout.addClickListener(e -> logout());
    }

    @Override
    public void showView(View view) {
        content.removeAllComponents();
        content.addComponent(view.getViewComponent());

        navigationButtons.forEach((viewClass, button) -> button.setStyleName("selected", viewClass == view.getClass()));

        Button menuItem = navigationButtons.get(view.getClass());
        String viewName = "";
        if (menuItem != null) {
            viewName = menuItem.getCaption();
        }
        activeViewName.setValue(viewName);
    }

    private void attachNavigation(Button navigationButton, Class<? extends View> targetView) {
        boolean hasAccessToView = viewAccessControl.isAccessGranted(targetView);
        navigationButton.setVisible(hasAccessToView);

        if (hasAccessToView) {
            navigationButtons.put(targetView, navigationButton);
            navigationButton.addClickListener(e -> navigationManager.navigateTo(targetView));
        }
    }

    public void logout() {
            UI ui = getUI();
            ui.getSession().getSession().invalidate();
            ui.getPage().reload();
    }
}
