package com.ims.views.message;

import com.ims.views.MainLayout;
import com.vaadin.flow.component.Composite;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
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
        VerticalLayout layoutColumn3 = new VerticalLayout();
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        Checkbox checkbox = new Checkbox();
        TextArea mobileNumbersField = new TextArea();
        
        TextArea messageFields = new TextArea();
        messageFields.setWidthFull();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        Hr hr = new Hr();
        Paragraph textSmall = new Paragraph();
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
        layoutColumn3.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth(null);
        checkboxGroup.setLabel("Send Via");
        checkboxGroup.setItems("SMS", "WhatsApp");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkbox.setLabel("Send message to all users");
        checkbox.setWidthFull();
        mobileNumbersField.setLabel("Enter Mobile numbers");
        mobileNumbersField.setWidthFull();
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidthFull();
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.START);
        buttonPrimary.setText("Send");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        textSmall.setText("NOTE: You can add multiple phone numbers by comma (,) separated.");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutRow);
        layoutRow.add(layoutColumn3);
        
        layoutColumn3.add(title,hr1);
        
        layoutColumn3.add(checkboxGroup);
        layoutColumn3.add(checkbox);
        layoutColumn3.add(mobileNumbersField);
        layoutColumn3.add(messageFields);
        layoutColumn3.add(layoutRow2);
        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonSecondary);
        layoutColumn3.add(hr);
        layoutColumn3.add(textSmall);
        
		getStyle().set("margin", "0 auto");
    }
}
