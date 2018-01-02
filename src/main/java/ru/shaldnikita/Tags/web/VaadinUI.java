package ru.shaldnikita.Tags.web;

import com.vaadin.annotations.Push;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import org.springframework.beans.factory.annotation.Autowired;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.web.navigation.NavigationManager;
import ru.shaldnikita.Tags.web.view.AccessDeniedView;

import javax.servlet.ServletException;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@SpringUI
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Title("Tags")
@Theme("apptheme")
@Push(transport = Transport.WEBSOCKET_XHR)
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


