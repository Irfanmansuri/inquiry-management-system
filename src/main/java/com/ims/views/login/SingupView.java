package com.ims.views.login;

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
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.EmailValidator;
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
@Route(value = "singup", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
@PageTitle("Singup")
public class SingupView extends VerticalLayout {

	private PasswordField passwordField1;
	private PasswordField passwordField2;

	private boolean enablePasswordValidation;

	/**
	 * We use Spring to inject the backend into our view
	 */
	public SingupView() {
		H3 title = new H3("Create User");
		Hr hr1 = new Hr();
		Hr hr2 = new Hr();

		TextField firstnameField = new TextField("First name");
		firstnameField.setRequired(true);
		firstnameField.setMaxLength(32);

		TextField lastnameField = new TextField("Last name");
		lastnameField.setRequired(true);
		lastnameField.setMaxLength(32);
		
		NumberField mobileField = new NumberField("Mobile");
		mobileField.setRequired(true);
		
		mobileField.addValueChangeListener(e ->{
			if(mobileField.getValue().SIZE != 10 ) {
				Notification notification = Notification.show("Invalid Mobile Number");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				mobileField.setAutofocus(true);
				mobileField.setAutoselect(true);
			}
			
		});
		
		
		EmailField emailField = new EmailField("Email");
		emailField.setRequired(true);
		emailField.setMaxLength(32);

		passwordField1 = new PasswordField("Password");
		passwordField1.setRequired(true);
		passwordField1.setMinLength(8);
		passwordField1.setMaxLength(32);

		passwordField2 = new PasswordField("Confirm Password");
		passwordField2.setRequired(true);
		passwordField2.setMinLength(8);
		passwordField2.setMaxLength(32);

		Span errorMessage = new Span();

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setAlignItems(Alignment.CENTER);

		Button submitButton = new Button("Submit");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		Button cancelButton = new Button("Cancel");
		buttons.add(submitButton, cancelButton);

		FormLayout formLayout = new FormLayout(title, hr1, hr2, firstnameField, lastnameField, mobileField,
				emailField,passwordField1, passwordField2, /* allowMarketingBox, */  errorMessage, buttons);

		formLayout.setMaxWidth("500px");
		formLayout.getStyle().set("margin", "0 auto");

		formLayout.setResponsiveSteps(
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

		formLayout.setColspan(title, 2);
		formLayout.setColspan(errorMessage, 2);
		formLayout.setColspan(cancelButton, 2);

		errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
		errorMessage.getStyle().set("padding", "15px 0");
		add(formLayout);

		passwordField2.addValueChangeListener(e -> {
			enablePasswordValidation = true;
		});

		cancelButton.addClickListener(e -> {
			try {
				UI.getCurrent().navigate(DashboardView.class);
			} catch (Exception e2) {
				e2.printStackTrace();
				errorMessage.setText("Saving the data failed, please try again");
			}
		});

		submitButton.addClickListener(e -> {
			ValidationResult validate = passwordValidator(passwordField1.getValue(), null);
			if (validate.isError()) {
				Notification notification = Notification.show(validate.getErrorMessage());
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}
		});

	}

	/**
	 * We call this method when form submission has succeeded
	 */
	private void showSuccess() {
		Notification notification = Notification.show("Data saved, welcome ");
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

		// Here you'd typically redirect the user to another view
	}

	/**
	 * Method to validate that:
	 * <p>
	 * 1) Password is at least 8 characters long
	 * <p>
	 * 2) Values in both fields match each other
	 */
	private ValidationResult passwordValidator(String pass1, ValueContext ctx) {

		/*
		 * Just a simple length check. A real version should check for password
		 * complexity as well!
		 */
		if (pass1 == null || pass1.length() < 8) {
			return ValidationResult.error("Password should be at least 8 characters long");
		}

		if (!enablePasswordValidation) {
			// user hasn't visited the field yet, so don't validate just yet, but next time.
			enablePasswordValidation = true;
			return ValidationResult.ok();
		}

		String pass2 = passwordField2.getValue();

		if (pass1 != null && pass1.equals(pass2)) {
			return ValidationResult.ok();
		}

		return ValidationResult.error("Passwords do not match");
	}

	/**
	 * Method that demonstrates using an external validator. Here we ask the backend
	 * if this handle is already in use.
	 */
	private ValidationResult validateHandle(String handle, ValueContext ctx) {

		String errorMsg = "";

		if (errorMsg == null) {
			return ValidationResult.ok();
		}

		return ValidationResult.error(errorMsg);
	}

	/**
	 * Custom validator class that extends the built-in email validator.
	 * <p>
	 * Ths validator checks if the field is visible before performing the
	 * validation. This way, the validation is only performed when the user has told
	 * us they want marketing emails.
	 */
	public class VisibilityEmailValidator extends EmailValidator {

		public VisibilityEmailValidator(String errorMessage) {
			super(errorMessage);
		}

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			// normal email validation
			return super.apply(value, context);
		}
	}
}