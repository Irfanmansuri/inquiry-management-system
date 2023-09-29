package com.ims.views.organization;

import java.io.InputStream;

import com.ims.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.dom.Style.Overflow;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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
		FormLayout formLayout = new FormLayout();

		TextField orgName = new TextField("Organization name");
		orgName.setRequired(true);
		orgName.setMaxLength(32);

		TextField orgRegisterNumberField = new TextField("Register Number");
		orgRegisterNumberField.setMaxLength(32);

		TextField orgLicenceNumberField = new TextField("Licence Number");
		orgLicenceNumberField.setMaxLength(32);

		TextField orgGstNumberField = new TextField("GST Number");
		orgGstNumberField.setMaxLength(32);

		NumberField orgMobileField = new NumberField("Organization Mobile");
		orgMobileField.setRequired(true);

		NumberField orgPhoneField = new NumberField("Organization Phone");
		orgPhoneField.setRequired(true);

		EmailField orgEmailField = new EmailField("Email");
		orgEmailField.setRequired(true);
		orgEmailField.setMaxLength(32);

		ComboBox<String> country = new ComboBox<>("Select Contry");
		country.setItems("India");
		country.setWidthFull();
		country.setErrorMessage("Contry is required field");

		ComboBox<String> state = new ComboBox<>("Select State");
		state.setItems("Gujarat", "Rajasthan", "Maharashtra", "UP", "MP", "Delhi");
		state.setRequired(true);
		state.setWidthFull();
		state.setErrorMessage("State is required field");

		TextField cityField = new TextField("City");
		cityField.setRequired(true);
		cityField.setMaxLength(32);
		cityField.setErrorMessage("City is required field");

		NumberField orgPincodeField = new NumberField("Pincode");

		DatePicker orgEstablishFrom = new DatePicker("Organization Establish From");

		TextArea descriptionField = new TextArea();
		descriptionField.setErrorMessage("Description is required");
		descriptionField.setRequired(true);
		descriptionField.setMaxLength(5000);
		descriptionField.setTooltipText("Description");
		descriptionField.setLabel("Description");
		descriptionField.getStyle().set("resize", "vertical");
		descriptionField.getStyle().set("overflow", "auto");

		TextField firstnameField = new TextField("Owner First name");
		firstnameField.setRequired(true);
		firstnameField.setMaxLength(32);

		TextField lastnameField = new TextField("Owner Last name");
		lastnameField.setRequired(true);
		lastnameField.setMaxLength(32);

		NumberField mobileField = new NumberField("Mobile");
		mobileField.setRequired(true);

		mobileField.addValueChangeListener(e -> {
			if (mobileField.getValue().SIZE != 10) {
				Notification notification = Notification.show("Invalid Mobile Number");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				mobileField.setAutofocus(true);
				mobileField.setAutoselect(true);
			}

		});

		EmailField emailField = new EmailField("Email");
		emailField.setRequired(true);
		emailField.setMaxLength(32);

		FileBuffer buffer = new FileBuffer();
		Upload upload = new Upload(buffer);
		upload.setWidthFull();
//      upload.setMaxFileSize(1024);
		upload.setHeightFull();
		upload.setDropAllowed(true);
		upload.getStyle().setOverflow(Overflow.INHERIT);
		upload.setAcceptedFileTypes("image/jpg", "image/jpeg", "image/png");

		upload.addSucceededListener(event -> {
			String fileName = event.getFileName();
			InputStream inputStream = buffer.getInputStream();
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

		CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
		checkboxGroup.setLabel("Enable Marketing");
		checkboxGroup.setItems("SMS", "WhatsApp");

		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

		formLayout.add(title, hr1, new Hr(), orgName, orgRegisterNumberField, orgLicenceNumberField, orgGstNumberField,
				orgMobileField, orgPhoneField, orgEmailField, country, state, cityField, orgPincodeField,
				orgEstablishFrom, firstnameField, lastnameField, mobileField, emailField, descriptionField, upload,
				checkboxGroup, buttons);
		formLayout.setMaxWidth("500px");
		formLayout.getStyle().set("margin", "0 auto");

		formLayout.setResponsiveSteps(
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

		formLayout.setColspan(title, 2);
		formLayout.setColspan(cancelButton, 2);
		formLayout.setColspan(descriptionField, 2);
		formLayout.setColspan(upload, 2);
		formLayout.setColspan(checkboxGroup, 2);

		add(formLayout);
//		setMaxWidth("500px");
//		getStyle().set("margin", "0 auto");
		setSizeFull();
		setHeightFull();

		submitButton.addClickListener(e -> {

		});

	}
}