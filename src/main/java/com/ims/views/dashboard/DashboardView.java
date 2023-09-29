package com.ims.views.dashboard;

import com.ims.views.MainLayout;
import com.ims.views.dashboard.ServiceHealth.Status;
import com.ims.views.inquiry.AddInquiryView;
import com.ims.views.products.AddProductView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.Marker;
import com.vaadin.flow.component.charts.model.PlotOptionsAreaspline;
import com.vaadin.flow.component.charts.model.PointPlacement;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.Style.Display;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

import jakarta.annotation.security.PermitAll;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {

	public DashboardView() {
		addClassName("dashboard-view");

		
		HorizontalLayout addInquiryButtonLayout = new HorizontalLayout();
		addInquiryButtonLayout.setWidth("94%");
		addInquiryButtonLayout.setPadding(true);
		addInquiryButtonLayout.setMargin(true);
		addInquiryButtonLayout.setAlignItems(Alignment.END);
		
		
		Button addNewInquiryButton = new Button("Add New Inquiry");
		addNewInquiryButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		addNewInquiryButton.setIcon(new Icon(VaadinIcon.PLUS));

		addNewInquiryButton.addClickListener(e -> {
			UI.getCurrent().navigate(AddInquiryView.class);
		});

		Div actions = new Div(addNewInquiryButton);
		actions.addClassName(LumoUtility.Gap.SMALL);
		actions.addClassName("actions");
		
		addInquiryButtonLayout.add(actions);
		
//        Board board = new Board();
//        board.addRow(createHighlight("Current users", "745", 33.7), createHighlight("View events", "54.6k", -112.45),
//                createHighlight("Conversion rate", "18%", 3.9), createHighlight("Custom metric", "-123.45", 0.0));
//        board.addRow(createViewEvents());
//        board.addRow(createServiceHealth(), createResponseTimes());
//        add(board);
		
		
		HorizontalLayout statusInfoLayout = new HorizontalLayout();
		statusInfoLayout.setPadding(true);
		statusInfoLayout.setMargin(true);
		statusInfoLayout.setAlignItems(Alignment.BASELINE);
		// layout.setBoxSizing(BoxSizing.CONTENT);
		statusInfoLayout.setSizeFull();
		statusInfoLayout.setWidthFull();
		statusInfoLayout.setHeightFull();
		statusInfoLayout.getStyle().set("float", "left").set("display", "block");

		Icon icon = new Icon(VaadinIcon.CLOCK);
		Span pending1 = new Span(icon, new Span("Pending"));
		pending1.getElement().getThemeList().add("badge");
		pending1.setHeight(25, Unit.PERCENTAGE);
		pending1.setWidth(30, Unit.PERCENTAGE);
		pending1.getStyle().set("justify-content", "normal").set("margin", "10px");
		statusInfoLayout.add(pending1);

		Span pending2 = new Span("Pending");
		pending2.getElement().getThemeList().add("badge");
		pending2.setHeight(25, Unit.PERCENTAGE);
		pending2.setWidth(30, Unit.PERCENTAGE);
		pending2.getStyle().set("justify-content", "normal").set("margin", "10px");
		statusInfoLayout.add(pending2);

		Span pending3 = new Span("Pending");
		pending3.getElement().getThemeList().add("badge");
		pending3.setHeight(25, Unit.PERCENTAGE);
		pending3.setWidth(30, Unit.PERCENTAGE);
		pending3.getStyle().set("justify-content", "normal").set("margin", "10px");
		statusInfoLayout.add(pending3);

		Span pending4 = new Span("Pending");
		pending4.getElement().getThemeList().add("badge");
		pending4.setHeight(25, Unit.PERCENTAGE);
		pending4.setWidth(30, Unit.PERCENTAGE);
		pending4.getStyle().set("justify-content", "normal").set("margin", "10px");
		statusInfoLayout.add(pending4);

		add(addInquiryButtonLayout, statusInfoLayout);
		setSizeFull();
		setHeightFull();
		setWidthFull();
	}

	private Component createHighlight(String title, String value, Double percentage) {
		VaadinIcon icon = VaadinIcon.ARROW_UP;
		String prefix = "";
		String theme = "badge";

		if (percentage == 0) {
			prefix = "±";
		} else if (percentage > 0) {
			prefix = "+";
			theme += " success";
		} else if (percentage < 0) {
			icon = VaadinIcon.ARROW_DOWN;
			theme += " error";
		}

		H2 h2 = new H2(title);
		h2.addClassNames(FontWeight.NORMAL, Margin.NONE, TextColor.SECONDARY, FontSize.XSMALL);

		Span span = new Span(value);
		span.addClassNames(FontWeight.SEMIBOLD, FontSize.XXXLARGE);

		Icon i = icon.create();
		i.addClassNames(BoxSizing.BORDER, Padding.XSMALL);

		Span badge = new Span(i, new Span(prefix + percentage.toString()));
		badge.getElement().getThemeList().add(theme);

		VerticalLayout layout = new VerticalLayout(h2, span, badge);
		layout.addClassName(Padding.LARGE);
		layout.setPadding(false);
		layout.setSpacing(false);
		return layout;
	}

	private Component createViewEvents() {
		// Header
		Select year = new Select();
		year.setItems("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021");
		year.setValue("2021");
		year.setWidth("100px");

		HorizontalLayout header = createHeader("View events", "City/month");
		header.add(year);

		// Chart
		Chart chart = new Chart(ChartType.AREASPLINE);
		Configuration conf = chart.getConfiguration();
		conf.getChart().setStyledMode(true);

		XAxis xAxis = new XAxis();
		xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(xAxis);

		conf.getyAxis().setTitle("Values");

		PlotOptionsAreaspline plotOptions = new PlotOptionsAreaspline();
		plotOptions.setPointPlacement(PointPlacement.ON);
		plotOptions.setMarker(new Marker(false));
		conf.addPlotOptions(plotOptions);

		conf.addSeries(new ListSeries("Berlin", 189, 191, 291, 396, 501, 403, 609, 712, 729, 942, 1044, 1247));
		conf.addSeries(new ListSeries("London", 138, 246, 248, 348, 352, 353, 463, 573, 778, 779, 885, 887));
		conf.addSeries(new ListSeries("New York", 65, 65, 166, 171, 293, 302, 308, 317, 427, 429, 535, 636));
		conf.addSeries(new ListSeries("Tokyo", 0, 11, 17, 123, 130, 142, 248, 349, 452, 454, 458, 462));

		// Add it all together
		VerticalLayout viewEvents = new VerticalLayout(header, chart);
		viewEvents.addClassName(Padding.LARGE);
		viewEvents.setPadding(false);
		viewEvents.setSpacing(false);
		viewEvents.getElement().getThemeList().add("spacing-l");
		return viewEvents;
	}

	private Component createServiceHealth() {
		// Header
		HorizontalLayout header = createHeader("Service health", "Input / output");

		// Grid
		Grid<ServiceHealth> grid = new Grid();
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.setAllRowsVisible(true);

		grid.addColumn(new ComponentRenderer<>(serviceHealth -> {
			Span status = new Span();
			String statusText = getStatusDisplayName(serviceHealth);
			status.getElement().setAttribute("aria-label", "Status: " + statusText);
			status.getElement().setAttribute("title", "Status: " + statusText);
			status.getElement().getThemeList().add(getStatusTheme(serviceHealth));
			return status;
		})).setHeader("").setFlexGrow(0).setAutoWidth(true);
		grid.addColumn(ServiceHealth::getCity).setHeader("City").setFlexGrow(1);
		grid.addColumn(ServiceHealth::getInput).setHeader("Input").setAutoWidth(true).setTextAlign(ColumnTextAlign.END);
		grid.addColumn(ServiceHealth::getOutput).setHeader("Output").setAutoWidth(true)
				.setTextAlign(ColumnTextAlign.END);

		grid.setItems(new ServiceHealth(Status.EXCELLENT, "Münster", 324, 1540),
				new ServiceHealth(Status.OK, "Cluj-Napoca", 311, 1320),
				new ServiceHealth(Status.FAILING, "Ciudad Victoria", 300, 1219));

		// Add it all together
		VerticalLayout serviceHealth = new VerticalLayout(header, grid);
		serviceHealth.addClassName(Padding.LARGE);
		serviceHealth.setPadding(false);
		serviceHealth.setSpacing(false);
		serviceHealth.getElement().getThemeList().add("spacing-l");
		return serviceHealth;
	}

	private Component createResponseTimes() {
		HorizontalLayout header = createHeader("Response times", "Average across all systems");

		// Chart
		Chart chart = new Chart(ChartType.PIE);
		Configuration conf = chart.getConfiguration();
		conf.getChart().setStyledMode(true);
		chart.setThemeName("gradient");

		DataSeries series = new DataSeries();
		series.add(new DataSeriesItem("System 1", 12.5));
		series.add(new DataSeriesItem("System 2", 12.5));
		series.add(new DataSeriesItem("System 3", 12.5));
		series.add(new DataSeriesItem("System 4", 12.5));
		series.add(new DataSeriesItem("System 5", 12.5));
		series.add(new DataSeriesItem("System 6", 12.5));
		conf.addSeries(series);

		// Add it all together
		VerticalLayout serviceHealth = new VerticalLayout(header, chart);
		serviceHealth.addClassName(Padding.LARGE);
		serviceHealth.setPadding(false);
		serviceHealth.setSpacing(false);
		serviceHealth.getElement().getThemeList().add("spacing-l");
		return serviceHealth;
	}

	private HorizontalLayout createHeader(String title, String subtitle) {
		H2 h2 = new H2(title);
		h2.addClassNames(FontSize.XLARGE, Margin.NONE);

		Span span = new Span(subtitle);
		span.addClassNames(TextColor.SECONDARY, FontSize.XSMALL);

		VerticalLayout column = new VerticalLayout(h2, span);
		column.setPadding(false);
		column.setSpacing(false);

		HorizontalLayout header = new HorizontalLayout(column);
		header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
		header.setSpacing(false);
		header.setWidthFull();
		return header;
	}

	private String getStatusDisplayName(ServiceHealth serviceHealth) {
		Status status = serviceHealth.getStatus();
		if (status == Status.OK) {
			return "Ok";
		} else if (status == Status.FAILING) {
			return "Failing";
		} else if (status == Status.EXCELLENT) {
			return "Excellent";
		} else {
			return status.toString();
		}
	}

	private String getStatusTheme(ServiceHealth serviceHealth) {
		Status status = serviceHealth.getStatus();
		String theme = "badge primary small";
		if (status == Status.EXCELLENT) {
			theme += " success";
		} else if (status == Status.FAILING) {
			theme += " error";
		}
		return theme;
	}

}
