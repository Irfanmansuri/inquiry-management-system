package com.ims.views.inquiry;

import java.io.InputStream;

import com.ims.views.MainLayout;
import com.ims.views.subproducts.SubProductsView;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
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
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
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
@Route(value = "add-inquiry", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
@PageTitle("Add New Inquiry")
public class AddInquiryView extends VerticalLayout {

	public AddInquiryView() {
		H3 title = new H3("Add New Inquiry");
		Hr hr1 = new Hr();
		FormLayout formLayout = new FormLayout();

		ComboBox<String> productsComboBox = new ComboBox<>("Select Product");
		productsComboBox.setItems("product1", "product2", "product3", "product4", "product5");
		productsComboBox.setRequired(true);
		productsComboBox.setWidthFull();
		productsComboBox.setErrorMessage("Product is required field");
//		comboBox.setItemLabelGenerator(Country::getName);

		ComboBox<String> subProductsComboBox = new ComboBox<>("Select Sub Product");
		subProductsComboBox.setItems("product1", "product2", "product3", "product4", "product5");
		subProductsComboBox.setRequired(true);
		subProductsComboBox.setWidthFull();
		subProductsComboBox.setErrorMessage("Product is required field");
//		comboBox.setItemLabelGenerator(Country::getName);

		TextField firstnameField = new TextField("First name");
		firstnameField.setRequired(true);
		firstnameField.setMaxLength(32);

		TextField lastnameField = new TextField("Last name");
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

		NumberField pincodeField = new NumberField("Pincode");

		TextArea descriptionField = new TextArea();
		descriptionField.setErrorMessage("Description is required");
		descriptionField.setRequired(true);
		descriptionField.setMaxLength(5000);
//		descriptionField.setWidthFull();
		descriptionField.setTooltipText("Description");
		descriptionField.setLabel("Description");
		descriptionField.getStyle().set("resize", "vertical");
		descriptionField.getStyle().set("overflow", "auto");

		/*MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
		Upload upload = new Upload(buffer);
		upload.setWidthFull();
		upload.setMaxFiles(3);
//        upload.setMaxFileSize(1024);
		upload.setHeightFull();
		upload.setDropAllowed(true);
		upload.getStyle().setOverflow(Overflow.INHERIT);
		upload.setAcceptedFileTypes("image/jpg", "image/jpeg", "image/png", "image/gif");

		upload.addSucceededListener(event -> {
			String fileName = event.getFileName();
			InputStream inputStream = buffer.getInputStream(fileName);
			Notification nt = Notification.show(fileName);
			nt.addThemeVariants(NotificationVariant.LUMO_WARNING);
			// Do something with the file data
			// processFile(inputStream, fileName);
		});*/

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setAlignItems(Alignment.CENTER);

		Button submitButton = new Button("Submit");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(e -> {
			UI.getCurrent().navigate(SubProductsView.class);
		});

		buttons.add(submitButton, cancelButton);

		formLayout.add(title, hr1, new Hr(), productsComboBox, subProductsComboBox, firstnameField, lastnameField,
				mobileField, emailField, country, state, cityField, pincodeField, descriptionField, buttons);
		formLayout.setMaxWidth("500px");
		formLayout.getStyle().set("margin", "0 auto");

		formLayout.setResponsiveSteps(
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

		formLayout.setColspan(title, 2);
		formLayout.setColspan(cancelButton, 2);
		formLayout.setColspan(descriptionField, 2);

		add(formLayout);
//		setMaxWidth("500px");
//		getStyle().set("margin", "0 auto");
		setSizeFull();
		setHeightFull();

		submitButton.addClickListener(e -> {

		});

	}
}