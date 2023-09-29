package com.ims.views.message;

import java.io.InputStream;

import com.ims.views.MainLayout;
import com.ims.views.dashboard.DashboardView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.dom.Style.Overflow;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import jakarta.annotation.security.PermitAll;

@PageTitle("Message")
@Route(value = "message-send", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class MessageView extends Composite<VerticalLayout> {

	public MessageView() {

		H3 title = new H3("Send Message");
		Hr hr1 = new Hr();

		VerticalLayout layoutColumn2 = new VerticalLayout();
		HorizontalLayout layoutRow = new HorizontalLayout();
		VerticalLayout fieldVerticalLayout = new VerticalLayout();

		CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
		checkboxGroup.setLabel("Send Via");
		checkboxGroup.setItems("SMS", "WhatsApp");
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

		Checkbox checkbox = new Checkbox();
		checkbox.setLabel("Send message to all users");
		checkbox.setWidthFull();

		TextArea mobileNumbersField = new TextArea();

		TextArea messageFields = new TextArea();
		messageFields.setWidthFull();
		messageFields.setLabel("Message");

		Paragraph hint = new Paragraph("Maximum file size: 1 MB");
		hint.getStyle().set("color", "var(--lumo-secondary-text-color)");

		int maxFileSizeInBytes = 1 * 1024 * 1024; // 1MB

		FileBuffer buffer = new FileBuffer();
		Upload upload = new Upload(buffer);
		upload.setWidthFull();
		upload.setMaxFileSize(maxFileSizeInBytes);
		upload.setHeightFull();
		upload.setDropAllowed(true);
		upload.getStyle().setOverflow(Overflow.INHERIT);
		upload.setAcceptedFileTypes("text/csv", "application/vnd.ms-excel",
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		upload.addSucceededListener(event -> {
			String fileName = event.getFileName();
			InputStream inputStream = buffer.getInputStream();
			Notification nt = Notification.show(fileName);
			nt.addThemeVariants(NotificationVariant.LUMO_WARNING);
			// Do something with the file data
			// processFile(inputStream, fileName);
		});

		upload.addFileRejectedListener(event -> {
			String errorMessage = event.getErrorMessage();
			Notification notification = Notification.show(errorMessage, 5000, Notification.Position.MIDDLE);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		});

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addClassName(Gap.MEDIUM);
		buttons.setWidthFull();
		buttons.setAlignItems(Alignment.START);
		buttons.setJustifyContentMode(JustifyContentMode.START);

		Button sendButton = new Button();
		sendButton.setText("Send");
		sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		Button cancelButton = new Button("Cancel", e -> {
			UI.getCurrent().navigate(DashboardView.class);
		});

		buttons.add(sendButton);
		buttons.add(cancelButton);

		Hr hr = new Hr();
		Paragraph notes = new Paragraph();
		getContent().setHeightFull();
		getContent().setWidthFull();
		getContent().setMaxWidth("500px");
		getStyle().set("margin", "0 auto");

		layoutColumn2.setWidthFull();
		getContent().setFlexGrow(1.0, layoutColumn2);
		layoutRow.addClassName(Gap.XLARGE);
		layoutColumn2.setFlexGrow(1.0, layoutRow);
		layoutRow.setWidthFull();
		layoutRow.setAlignItems(Alignment.START);
		layoutRow.setJustifyContentMode(JustifyContentMode.START);
		fieldVerticalLayout.setHeightFull();
		layoutRow.setFlexGrow(1.0, fieldVerticalLayout);
		fieldVerticalLayout.setWidth(null);

		mobileNumbersField.setLabel("Enter Mobile numbers");
		mobileNumbersField.setWidthFull();

		notes.setText("NOTE: You can add multiple phone numbers by comma (,) separated.");
		notes.getStyle().set("font-size", "var(--lumo-font-size-xs)");
		getContent().add(layoutColumn2);
		layoutColumn2.add(layoutRow);
		layoutRow.add(fieldVerticalLayout);

		fieldVerticalLayout.add(title, hr1);
		fieldVerticalLayout.add(notes);
		fieldVerticalLayout.add(checkboxGroup);
		fieldVerticalLayout.add(checkbox);
		fieldVerticalLayout.add(mobileNumbersField);
		fieldVerticalLayout.add(new Text("OR Select csv or xlsx file"), upload, hint);
		fieldVerticalLayout.add(messageFields);
		fieldVerticalLayout.add(buttons);
		fieldVerticalLayout.add(hr);

		getStyle().set("margin", "0 auto");
	}
}
