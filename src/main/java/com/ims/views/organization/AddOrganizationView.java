package com.ims.views.organization;

import java.io.InputStream;

import com.ims.views.MainLayout;
import com.ims.views.dashboard.DashboardView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.dom.Style.Overflow;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import jakarta.annotation.security.RolesAllowed;

/**
 * This is the default (and only) view in this example.
 * <p>
 * It demonstrates how to create a form using Vaadin and the Binder. The backend
 * service and data class are in the <code>.data</code> package.
 */
@Route(value = "add-organization", layout = MainLayout.class)
@RolesAllowed("ADMINISTRATOR")
@Uses(Icon.class)
@PageTitle("Add Organization")
public class AddOrganizationView extends VerticalLayout {

	public AddOrganizationView() {
		H3 title = new H3("Add New Organization");
		Hr hr1 = new Hr();

		TextField productNameField = new TextField("Product Name");
		productNameField.setRequired(true);
		productNameField.setMaxLength(32);
		productNameField.setErrorMessage("Product Name is required");
		productNameField.setWidthFull();
		
		
		
		TextArea descriptionField = new TextArea();
		descriptionField.setErrorMessage("Description is required");
		descriptionField.setRequired(true);
		descriptionField.setMaxLength(100);
		descriptionField.setWidthFull();
		descriptionField.setTooltipText("Description");
		descriptionField.setLabel("Description");
		
		MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setWidthFull();
        upload.setMaxFiles(3);
//        upload.setMaxFileSize(1024);
        upload.setHeightFull();
        upload.setDropAllowed(true);
        upload.getStyle().setOverflow(Overflow.AUTO);
        upload.setAcceptedFileTypes("image/jpg","image/jpeg", "image/png", "image/gif");
        
        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);
            Notification nt = Notification.show(fileName);
            nt.addThemeVariants(NotificationVariant.LUMO_WARNING);
            // Do something with the file data
            // processFile(inputStream, fileName);
        });
		
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setAlignItems(Alignment.CENTER);

		Button submitButton = new Button("Submit");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(e -> {
			UI.getCurrent().navigate(OrganizationView.class);
		});
		
		buttons.add(submitButton, cancelButton);

		add(title, hr1, productNameField, descriptionField, upload, buttons);
		setMaxWidth("500px");
		getStyle().set("margin", "0 auto");
		setSizeFull();
		setHeightFull();
		
		submitButton.addClickListener(e -> {
			
		});

	}
}