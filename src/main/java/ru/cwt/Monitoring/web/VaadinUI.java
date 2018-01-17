package ru.cwt.Monitoring.web;

import com.vaadin.annotations.Push;
import org.springframework.beans.factory.annotation.Autowired;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import ru.cwt.Monitoring.web.view.AccessDeniedView;
import ru.cwt.Monitoring.app.HasLogger;
import ru.cwt.Monitoring.web.navigation.NavigationManager;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@SpringUI
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Title("Monitoring")
@Theme("apptheme")
@Push
public class VaadinUI extends UI implements HasLogger {

    private final SpringViewProvider viewProvider;

    private final NavigationManager navigationManager;

    private final MainView mainView;

    @Autowired
    public VaadinUI(SpringViewProvider viewProvider, NavigationManager navigationManager, MainView mainView) {
        this.viewProvider = viewProvider;
        this.navigationManager = navigationManager;
        this.mainView = mainView;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getLogger().info("init UI");

        setErrorHandler(event -> {
            Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
            getLogger().error("Error during request", t);
        });

        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        setContent(mainView);

        navigationManager.navigateToDefaultView();
    }
}


